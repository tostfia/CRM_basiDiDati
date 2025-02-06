package it.crm.bd.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.Credentials;
import it.crm.bd.other.Role;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class RegisterProcedureDAO implements GenericProcedureDAO<Credentials> {

    @Override
    public Credentials execute(Object... params) throws DAOException {
        if (params.length != 3 || !(params[0] instanceof String username) || !(params[1] instanceof String password) || !(params[2] instanceof Role role)) {
            throw new IllegalArgumentException("Numero di parametri errato");
        }

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username e password non possono essere vuoti");
        }


        try (Connection conn = ConnectionFactory.getLoginConnection();
             CallableStatement cs = conn.prepareCall("{call register(?, ?, ?, ?)}")) {

            // Imposta i parametri di input
            cs.setString(1, username);
            cs.setString(2, password);
            cs.setInt(3, role.getId());

            // Registra il parametro di output
            cs.registerOutParameter(4, Types.INTEGER);

            // Esegui la procedura
            cs.execute();


            // Recupera il risultato dal parametro di output
            int result = cs.getInt(4);

            if (result <= 0) {
                throw new DAOException("Registrazione fallita");
            }

            // Ritorna un oggetto Credentials se la registrazione è riuscita
            return new Credentials(username, password, role);

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'esecuzione della procedura di registrazione", e);
        } catch (IOException e) {
            throw new DAOException("Errore durante la chiusura della connessione", e);
        }
    }
}

package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Credentials;
import it.crm.bd.model.domain.Role;

import java.io.IOException;
import java.sql.*;

public class LoginProcedureDAO implements GenericProcedureDAO<Credentials> {

    @Override
    public Credentials execute(Object... params) throws DAOException {
        // Validazione dei parametri di input
        if (params == null || params.length < 2 || !(params[0] instanceof String username) || !(params[1] instanceof String password)) {
            throw new DAOException("Invalid input: Username and password are required.");
        }

        int role;

        if (username.isEmpty() || password.isEmpty()) {
            throw new DAOException("Invalid input: Username and password cannot be empty.");
        }

        try {
            // Ottieni l'istanza Singleton di ConnectionFactory
            ConnectionFactory factory = ConnectionFactory.getInstance();

            // Ottieni la connessione
            try (Connection conn = factory.getConnection();
                 CallableStatement cs = conn.prepareCall("{call login(?,?,?)}")) {

                // Imposta i parametri della stored procedure
                cs.setString(1, username);
                cs.setString(2, password);
                cs.registerOutParameter(3, Types.INTEGER);

                // Esegui la stored procedure
                cs.execute();

                // Recupera il valore del parametro OUT
                role = cs.getInt(3);

                // Se il ruolo è 0 o negativo, segnala un errore di autenticazione
                if (role <= 0) {
                    throw new DAOException("Authentication failed: Invalid username or password.");
                }
                Role userRole=Role.fromInt(role);
                factory.changeRole(userRole);
            }
        } catch (SQLException | IOException e) {
            // Gestione eccezioni
            throw new DAOException("Login error: " + e.getMessage(), e);
        }

        // Converti il ruolo numerico in un oggetto Role


        // Restituisci l'oggetto Credentials con i dati ottenuti
        return new Credentials(username, password, Role.fromInt(role));
    }
}

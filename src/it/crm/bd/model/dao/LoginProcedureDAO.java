package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Credentials;
import it.crm.bd.model.domain.Role;

import java.sql.*;

public class LoginProcedureDAO implements GenericProcedureDAO<Credentials> {
    @Override
    public Credentials execute(Object... params) throws DAOException {
        String username = (String) params[0];
        String password = (String) params[1];
        int role;

        // Validazione input
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new DAOException("Invalid input");
        }

        // Chiamata alla procedura e gestione della connessione
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement cs = conn.prepareCall("{call login(?,?,?)}")) {

            // Settaggio dei parametri
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.INTEGER);

            // Esecuzione della procedura
            cs.execute();

            // Recupero del valore del parametro OUT
            role = cs.getInt(3);

        } catch (SQLException e) {
            // Gestione errori
            throw new DAOException("Login error: " + e.getMessage(), e);
        }
        Role userRole = Role.fromInt(role);

        // Restituisco l'oggetto Credentials con il ruolo determinato
        return new Credentials(username, password, userRole);
    }
}

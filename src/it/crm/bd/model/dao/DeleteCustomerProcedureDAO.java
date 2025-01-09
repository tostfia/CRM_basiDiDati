package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCustomerProcedureDAO implements GenericProcedureDAO<Customer> {

    @Override
    public Customer execute(Object... params) throws DAOException {
        // Validazione parametri: il primo parametro è il codice fiscale, il secondo è la connessione
        if (params == null || params.length != 2 || !(params[0] instanceof String fiscalcode) || !(params[1] instanceof Connection conn)) {
            throw new DAOException("Invalid parameters: A valid fiscal code and Connection are required.");
        }

        try {
            if (conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }
            try (CallableStatement cs = conn.prepareCall("{call deleteCustomer(?)}")) {
                // Imposta i parametri per la stored procedure
                cs.setString(1, fiscalcode);

                // Esegue la stored procedure
                cs.executeUpdate();

                return null;// Restituisce null in quanto non c'è nessun oggetto da restituire

            } catch (SQLException e) {
                // Gestione eccezioni
                throw new DAOException("Error executing stored procedure 'deleteCustomer': " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while executing stored procedure 'deleteCustomer': " + e.getMessage(), e);
        }
    }
}


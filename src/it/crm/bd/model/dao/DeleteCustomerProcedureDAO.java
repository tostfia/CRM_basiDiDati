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
            // Verifica che la connessione non sia nulla e non sia chiusa
            if (conn == null || conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }

            // Esegui la stored procedure di cancellazione
            try (CallableStatement cs = conn.prepareCall("{call deleteCustomer(?)}")) {
                // Imposta il parametro per la stored procedure (il codice fiscale del cliente)
                cs.setString(1, fiscalcode);

                // Esegui l'aggiornamento (eliminazione)
                int affectedRows = cs.executeUpdate();

                // Se l'eliminazione ha avuto successo, restituisci l'oggetto Customer con il codice fiscale eliminato
                // Oppure, in base alla logica aziendale, puoi restituire null se il cliente è stato eliminato con successo
                if (affectedRows > 0) {
                    return null;
                } else {
                    // Se non è stato trovato nessun cliente da eliminare
                    throw new DAOException("No customer found with fiscal code: " + fiscalcode);
                }

            } catch (SQLException e) {
                // Gestione errori durante l'esecuzione della stored procedure
                throw new DAOException("Error executing stored procedure 'deleteCustomer': " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            // Gestione errori generali
            throw new DAOException("Error while executing stored procedure 'deleteCustomer': " + e.getMessage(), e);
        }
    }
}


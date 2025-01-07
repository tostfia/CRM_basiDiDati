package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateContactsProcedureDAO implements GenericProcedureDAO<Customer> {

    @Override
    public Customer execute(Object... params) throws DAOException {
        // Verifica che i parametri siano corretti
        if (params == null || params.length == 0 || !(params[0] instanceof Customer customer)) {
            throw new DAOException("Invalid input parameters: A Customer object is required.");
        }

        // Recupera la connessione
        Connection conn;
        try {
            conn = (Connection) params[1];  // Il secondo parametro deve essere una Connection
            if (conn == null || conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }

            // Recupera i dettagli del cliente
            String fiscalCode = customer.getFiscalCode();  // Metodo getter per il codice fiscale
            String type = customer.getType();  // 'phone' o 'email'
            String value = String.valueOf(customer.getValues());  // Il valore del contatto (email o telefono)

            // Prepara la chiamata alla stored procedure
            try (CallableStatement cs = conn.prepareCall("{call updateContatti(?, ?, ?)}")) {
                // Imposta i parametri della stored procedure
                cs.setString(1, value);  // var_valore
                cs.setString(2, type);   // var_tipo (email o phone)
                cs.setString(3, fiscalCode);  // var_cliente (codice fiscale)

                // Esegui la stored procedure
                cs.executeUpdate();
                return customer;  // Restituisce l'oggetto Customer con i dati aggiornati
            }

        } catch (SQLException e) {
            throw new DAOException("Error while executing the stored procedure.", e);
        }
    }
}

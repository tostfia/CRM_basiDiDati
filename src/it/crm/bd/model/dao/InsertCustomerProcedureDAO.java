package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Customer;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class InsertCustomerProcedureDAO implements GenericProcedureDAO<Customer> {

    @Override
    public Customer execute(Object... params) throws DAOException {
        // Validazione parametri
        if (params == null || params.length == 0 || !(params[0] instanceof Customer customer)) {
            throw new DAOException("Invalid parameters: A valid Customer object is required.");
        }
            try (Connection conn = ConnectionFactory.getConnection();
                 CallableStatement cs = conn.prepareCall("{call insertCustomer(?,?,?,?,?,?,?,?,?,?)}")) {

                // Imposta i parametri per la stored procedure
                cs.setString(1, customer.getFiscalCode());
                cs.setString(2, customer.getName());
                cs.setString(3, customer.getSurname());
                cs.setDate(4, Date.valueOf(customer.getBirthdate()));
                cs.setDate(5, Date.valueOf(customer.getRegistrationDate())); // Data di registrazione
                cs.setString(6, convertListToCSV(customer.getEmails())); // Emails come stringa CSV
                cs.setString(7, convertListToCSV(customer.getPhones())); // Phones come stringa CSV
                cs.setString(8, customer.getCap());
                cs.setString(9, customer.getAddress());
                cs.setString(10, customer.getCity());

                // Esegue la stored procedure
                cs.executeUpdate();

                // Restituisce l'oggetto Customer
                return customer;
            } catch ( SQLException e) {
                // Gestione eccezioni
                throw new DAOException("Error executing stored procedure 'insertCustomer': " + e.getMessage(), e);
            }
    }

    /**
     * Converte una lista di stringhe in una stringa CSV.
     * @param list La lista di stringhe da convertire.
     * @return Una stringa con elementi separati da virgola, o una stringa vuota se la lista è null o vuota.
     */
    private String convertListToCSV(List<String> list) {
        return (list == null || list.isEmpty()) ? "" : String.join(",", list);
    }
}

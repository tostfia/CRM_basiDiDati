package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Customer;

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


        // Preparazione della connessione e del CallableStatement
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement cs = conn.prepareCall("{call insertCustomer(?,?,?,?,?,?,?,?,?,?)}")) {

            // Imposta i parametri per la stored procedure
            cs.setString(1, customer.getName());
            cs.setString(2, customer.getSurname());
            cs.setString(3, customer.getFiscalCode());
            cs.setDate(4, Date.valueOf(customer.getBirthdate())); // Conversione di LocalDate in SQL Date
            cs.setString(5, convertListToCSV(customer.getEmails())); // Emails come stringa CSV
            cs.setString(6, convertListToCSV(customer.getPhones())); // Phones come stringa CSV
            cs.setString(7, customer.getAddress());
            cs.setString(8, customer.getCity());
            cs.setString(9, customer.getCap());
            cs.setDate(10, Date.valueOf(customer.getRegistrationDate())); // Data di registrazione

            // Esegue la stored procedure
            cs.executeUpdate();

            // Restituisce l'oggetto Customer (eventualmente aggiornato con ID generato dal database)
            return customer;

        } catch (SQLException e) {
            // Gestione eccezioni SQL
            throw new DAOException("Error executing stored procedure 'insertCustomer': " + e.getMessage(), e);
        }
    }

    //Converte una lista di stringhe in una stringa CSV.
    //list La lista di stringhe da convertire.
    //Ritorna una stringa con elementi separati da virgola, o una stringa vuota se la lista è null o vuota.

    private String convertListToCSV(List<String> list) {
        return (list == null || list.isEmpty()) ? "" : String.join(",", list);
    }
}

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
        // Controllo parametri
        if (params == null || params.length < 1) {
            throw new DAOException("Invalid parameters provided to the procedure.");
        }

        Customer customer = (Customer) params[0]; // Retrieve customer from params

        // Database connection and callable statement setup
        try (Connection conn = ConnectionFactory.getConnection()) {
            CallableStatement cs = conn.prepareCall("{call insertCustomer(?,?,?,?,?,?,?,?,?,?)}");

            // Setting the parameters for the stored procedure
            cs.setString(1, customer.getName());        // Set customer name
            cs.setString(2, customer.getSurname());     // Set customer surname
            cs.setString(3, customer.getCf());          // Set customer CF (Codice Fiscale)
            cs.setDate(4, String.valueOf(customer.getBirthdate()));  // Set customer birthdate (converted to SQL Date)

            // Assuming emails and phones are lists, so you want to save them as comma-separated strings
            cs.setString(5, String.join(",", customer.getEmails()));  // Emails as comma-separated string
            cs.setString(6, String.join(",", customer.getPhones()));  // Phones as comma-separated string

            cs.setString(7, customer.getAddress());     // Set customer address
            cs.setString(8, customer.getCity());        // Set customer city
            cs.setString(9, customer.getCap());         // Set customer CAP (Postal Code)

            // Assuming the stored procedure doesn't return any output (if it does, use cs.getResultSet() or cs.getInt(1))
            cs.executeUpdate(); // Execute the stored procedure

            return customer; // Return the customer object (could be enhanced to return the customer with auto-generated ID)
        } catch (SQLException e) {
            // Handle SQL exceptions
            throw new DAOException("Error executing stored procedure 'insertCustomer': " + e.getMessage(), e);
        }
    }
}

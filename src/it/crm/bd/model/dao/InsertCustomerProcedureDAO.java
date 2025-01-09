package it.crm.bd.model.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.domain.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


public class InsertCustomerProcedureDAO implements GenericProcedureDAO<Customer> {

    @Override
    public Customer execute(Object... params) throws DAOException, SQLException {
        // Validazione parametri
        if (params == null || params.length != 2 || !(params[0] instanceof Customer customer) || !(params[1] instanceof Connection conn)) {
            throw new DAOException("Invalid parameters: A valid Customer object and Connection are required.");
        }

        if (conn.isClosed()) {
            throw new DAOException("Connection is closed or null.");
        }

        // Inizio della transazione - gestita dalle stored procedure
        try (CallableStatement cs = conn.prepareCall("{call insertCustomer(?,?,?,?,?,?,?,?)}")) {
            // Esegui il primo inserimento: Cliente e indirizzo
            cs.setString(1, customer.getFiscalCode());
            cs.setString(2, customer.getName());
            cs.setString(3, customer.getSurname());
            cs.setDate(4, Date.valueOf(customer.getBirthdate()));
            cs.setDate(5, Date.valueOf(customer.getRegistrationDate()));
            cs.setString(6, customer.getCap());
            cs.setString(7, customer.getAddress());
            cs.setString(8, customer.getCity());
            cs.executeUpdate();
        }

        // Inserimento delle email
        if (customer.getEmails() != null && !customer.getEmails().isEmpty()) {
            for (String email : customer.getEmails()) {
                try (CallableStatement cs = conn.prepareCall("{call insertEmail(?,?)}")) {
                    cs.setString(1, customer.getFiscalCode());
                    cs.setString(2, email);
                    cs.executeUpdate();
                }
            }
        }

        // Inserimento dei numeri di telefono
        if (customer.getPhones() != null && !customer.getPhones().isEmpty()) {
            for (String phone : customer.getPhones()) {
                try (CallableStatement cs = conn.prepareCall("{call insertPhone(?,?)}")) {
                    cs.setString(1, customer.getFiscalCode());
                    cs.setString(2, phone);
                    cs.executeUpdate();
                }
            }
        }

        // Restituisce l'oggetto Customer
        return customer;
    }
}

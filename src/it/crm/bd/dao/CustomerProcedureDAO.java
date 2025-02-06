package it.crm.bd.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.Customer;
import it.crm.bd.other.Printer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerProcedureDAO implements GenericProcedureDAO<List<Customer>> {

    @Override
    public List<Customer> execute(Object... params) throws DAOException {
        // Validazione dei parametri
        Connection conn = validateConnection(params);

        List<Customer> customers = new ArrayList<>();
        try {
            if (conn.isClosed()) {
                throw new DAOException("Connection is closed or null.");
            }

            // Chiamata alla procedura
            try (CallableStatement cs = conn.prepareCall("{call customer()}")) {

                // Esecuzione e mappatura del risultato
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                         mapCustomer(rs, customers);

                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error executing stored procedure 'customer': " + e.getMessage(), e);
        }

        return customers;
    }

    /**
     * Validazione della connessione dai parametri.
     */
    private Connection validateConnection(Object... params) throws DAOException {
        if (params == null || params.length < 1 || !(params[0] instanceof Connection)) {
            throw new DAOException("Invalid input: A Connection object is required.");
        }
        return (Connection) params[0];
    }

    /**
     * Mappa un oggetto Customer dai dati del ResultSet.
     */
    private void mapCustomer(ResultSet rs, List<Customer> customers) throws SQLException {
        // Estrazione dati cliente
        String fiscalCode = rs.getString("cliente_codicefiscale");

        // Verifica se il cliente già esiste nella lista

            Customer customer = customers.stream()
                    .filter(c -> c.getFiscalCode().equals(fiscalCode))
                    .findFirst()
                    .orElseGet(() -> {
                        Customer newCustomer = new Customer();
                    try{
                        newCustomer.setFiscalCode(fiscalCode);
                        newCustomer.setName(rs.getString("cliente_nome"));
                        newCustomer.setSurname(rs.getString("cliente_cognome"));
                        newCustomer.setBirthdate(rs.getDate("cliente_data_nascita").toLocalDate());
                        newCustomer.setRegistrationDate(rs.getDate("cliente_data_di_registrazione").toLocalDate());
                        newCustomer.setAddress(rs.getString("indirizzo_via"));
                        newCustomer.setCity(rs.getString("indirizzo_citta"));
                        newCustomer.setCap(rs.getString("indirizzo_cap"));
                        newCustomer.setSegreteria(rs.getString("cliente_segreteria"));
                        customers.add(newCustomer);
                    }catch(SQLException e){
                        Printer.errorPrint("Error while mapping customer: " + e.getMessage());
                    }
                    return newCustomer;
                    });

        // Estrazione dati contatto
        String contactValue = rs.getString("contatto_valore");
        String contactType = rs.getString("contatto_tipo");

        if (contactValue != null && contactType != null) {
            switch (contactType.toLowerCase()) {
                case "email":
                    customer.getEmails().add(contactValue);
                    break;
                case "phone":
                    customer.getPhones().add(contactValue);
                    break;
                default:
                    // Gestione eventuali tipi non mappati
                    Printer.errorPrint("Tipo di contatto sconosciuto: " + contactType);
                    break;
            }
        }

    }
}


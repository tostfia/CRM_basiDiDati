package it.crm.bd.dao;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.Contact;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchContactProcedureDAO implements GenericProcedureDAO<List<Contact>> {

    @Override
    public List<Contact> execute(Object... params) throws DAOException, SQLException {
        // Verifica che i parametri siano corretti
        if (params == null || params.length < 2 || !(params[0] instanceof String fiscalCode) || !(params[1] instanceof Connection conn)) {
            throw new DAOException("Invalid input parameters: A fiscalCode string and a valid Connection are required.");
        }

        // Ottieni la connessione dal secondo parametro
        if (conn.isClosed()) {
            throw new DAOException("Connection is closed or null.");
        }

        // Lista per memorizzare i contatti trovati
        List<Contact> contacts = new ArrayList<>();

        // Prepara la chiamata alla stored procedure
        try (CallableStatement cs = conn.prepareCall("{call searchContact(?)}")) {
            // Imposta il parametro di input per la stored procedure (Codice fiscale del cliente)
            cs.setString(1, fiscalCode);

            // Esegui la stored procedure
            try (ResultSet rs = cs.executeQuery()) {
                // Itera sui risultati (ogni riga rappresenta un contatto)
                while (rs.next()) {
                    // Crea un nuovo oggetto Contact per ogni riga
                    Contact c = new Contact();
                    // Popola il contatto con i dati ottenuti dalla query
                    c.setValue(rs.getString("ContactValue"));  // Valore (email/phone)
                    c.setType(rs.getString("Contact"));    // Tipo (email o phone)
                    c.setCustomer(fiscalCode);          // Il codice fiscale del cliente (lo stesso per tutti i contatti)
                    contacts.add(c);                    // Aggiungi il contatto alla lista
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while executing stored procedure 'searchContact': " + e.getMessage(), e);
        }

        // Restituisce la lista dei contatti trovati
        return contacts;
    }
}

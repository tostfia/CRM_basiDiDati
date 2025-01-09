package it.crm.bd.controller;

import it.crm.bd.exception.*;
import it.crm.bd.model.dao.*;
import it.crm.bd.model.domain.*;
import it.crm.bd.other.Printer;
import it.crm.bd.view.CustomerView;
import it.crm.bd.view.OfferView;
import it.crm.bd.view.SegreteriaView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SegreteriaController implements Controller {

    @Override
    public void start() throws DataBaseOperationException, ServiceException, LoadException, InputException {
        try {
            ConnectionFactory.getConnection(Role.SEGRETERIA);
        } catch (SQLException |IOException  e) {
            throw new ServiceException("Error while connecting to the database: " + e.getMessage(), e);

        }
        while(true){
            int choice;
            try {
                choice = SegreteriaView.showMenu();
            } catch(IOException e) {
                throw new LoadException("Error while showing menu: " + e.getMessage(), e);
            }
            switch(choice) {
                case 1-> insertCustomer();
                case 2-> deleteCustomer();
                case 3-> insertOffer();
                case 4-> reportCustomer();
                case 5-> showCustomer();
                case 6-> updateAddress();
                case 7-> updateContacts();
                case 8-> System.exit(0);
                default -> throw new InputException("Invalid choice.");
            }
        }
    }
    //Mostra cliente
    private void showCustomer() throws DataBaseOperationException {
        try(Connection conn= ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            CustomerProcedureDAO customerDAO = new CustomerProcedureDAO();
            List<Customer> customers = customerDAO.execute(conn);
            if (customers.isEmpty()) {
                Printer.errorPrint("No customers found in the database.");
            } else {
                for (Customer customer : customers) {
                    Printer.printGreen(customer.toString());
                }
            }
        }catch(DAOException | SQLException | IOException e){
            throw new DataBaseOperationException("Error while showing customers: "+e.getMessage(),e);
        }
    }
    //Elimina cliente
    private void deleteCustomer() throws DataBaseOperationException{
        try(Connection conn=ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            String fiscalCode = CustomerView.deleteCustomer();
            DeleteCustomerProcedureDAO deleteCustomerDAO = new DeleteCustomerProcedureDAO();
            deleteCustomerDAO.execute(fiscalCode, conn);
            Printer.printBlue("Customer successfully deleted from the database.");
        } catch (DAOException | SQLException | IOException e) {
            throw new DataBaseOperationException("Error while deleting customer from the database: " + e.getMessage(), e);
        }
    }


    //Aggiorna contatti
    private void updateContacts() throws DataBaseOperationException {
        try {
            String fiscalCode = CustomerView.researchCustomerContact();
            List<Contact> contacts = fetchContacts(fiscalCode);

            if (contacts.isEmpty()) {
                Printer.errorPrint("No contacts found for the customer.");
                return;
            }


            Contact contact = CustomerView.updateContacts(contacts);

            if (contact != null) {
                updateContactInDatabase(contact);
                Printer.printBlue("Contacts successfully updated into the database.");
            }
        } catch (IOException e) {
            Printer.errorPrint("Input error: " + e.getMessage());
        } catch (SQLException | DAOException | LoadException e) {
            Printer.errorPrint("Database error while updating contacts: " + e.getMessage());
            throw new DataBaseOperationException("Error while updating contacts: " + e.getMessage(), e);
        }
    }
    //Fetch dei contatti
    private List<Contact> fetchContacts(String fiscalCode) throws SQLException, DAOException, LoadException {
        try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            SearchContactProcedureDAO contactsDAO = new SearchContactProcedureDAO();
            return contactsDAO.execute(fiscalCode, conn);
        } catch (IOException e) {
            throw new LoadException("Error while fetching contacts from input: " + e.getMessage(), e);
        }
    }

    //Aggiorna contatto nel database
    private void updateContactInDatabase(Contact contact) throws SQLException, DAOException, IOException {
        try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            UpdateContactsProcedureDAO contactDAO = new UpdateContactsProcedureDAO();
            contactDAO.execute(contact, conn);
        }
    }


    //Aggiorna indirizzo
    private void updateAddress() throws DataBaseOperationException, LoadException {
        Customer customer;
        try{
            customer= CustomerView.updateAddress();
        }catch (IOException e){
            throw new LoadException("Error while creating customer from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.SEGRETERIA)){
            UpdateAddressProcedureDAO addressDAO = new UpdateAddressProcedureDAO();
            addressDAO.execute(customer, conn);
            Printer.printBlue("Address successfully updated into the database.");
        } catch (DAOException | SQLException | IOException e) {
            throw new DataBaseOperationException("Error while updating address into the database: " + e.getMessage(), e);
        }
    }
    //Inserimento cliente
    public void insertCustomer() throws LoadException, DataBaseOperationException {
        Customer customer;
        try {
            // Step 1: Creazione del cliente tramite vista
            customer = CustomerView.insertCustomer();
        } catch (IOException e) {
            // Gestione errore input/output
            throw new LoadException("Error while creating customer from input: " + e.getMessage(), e);
        }

        try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            // Step 2: Inserimento del cliente nel database tramite DAO
            InsertCustomerProcedureDAO customerDAO = new InsertCustomerProcedureDAO();
            customerDAO.execute(customer, conn);
            Printer.printBlue("Customer successfully inserted into the database.");
        } catch (DAOException | SQLException | IOException e) {
            // Gestione errore DAO
            throw new DataBaseOperationException("Error while inserting customer into the database: " + e.getMessage(), e);
        }
    }
    //Inserimento offerta
    public void insertOffer() throws DataBaseOperationException, LoadException {
        Offer offer;
        try{
            offer= OfferView.insertOffer();
        }catch(IOException e){
            throw new LoadException("Error while creating offer from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.SEGRETERIA)){
            InsertOfferProcedureDAO offerDAO = new InsertOfferProcedureDAO();
            offerDAO.execute(offer, conn);
            Printer.printBlue("Offer successfully inserted into the database.");
        } catch (DAOException | SQLException | IOException e) {
            throw new DataBaseOperationException("Error while inserting offer into the database: " + e.getMessage(), e);
        }
    }
    //Report cliente
    public void reportCustomer() throws LoadException {
        try {
            // Ottieni il range di date dall'utente
            LocalDate[] range = CustomerView.reportCustomer();
            LocalDate start = range[0];
            LocalDate end = range[1];

            // Connessione al database e generazione del report
            try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
                ReportCustomerProcedureDAO reportDAO = new ReportCustomerProcedureDAO();
                List<ReportCustomer> report =reportDAO.execute(start, end, conn);
                if(report.isEmpty()){
                    Printer.errorPrint("No customers found in the specified range.");
                }else{
                    for(ReportCustomer rc: report){
                        Printer.print(rc.toString());
                    }
                }
            } catch (DAOException | SQLException e) {
                Printer.errorPrint("Database error while generating report: " + e.getMessage());
                throw new DataBaseOperationException("Error while generating report: " + e.getMessage(), e);
            }
        } catch (IOException | DataBaseOperationException e) {
            Printer.errorPrint("Input error: " + e.getMessage());
            throw new LoadException("Error while generating report: " + e.getMessage(), e);
        }
    }

}

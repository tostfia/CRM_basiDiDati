package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
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
    public void start() {
        try {
            ConnectionFactory.getConnection(Role.SEGRETERIA);
        } catch (SQLException |IOException  e) {
            throw new RuntimeException("Error while connecting to the database: " + e.getMessage(), e);

        }
        while(true){
            int choice;
            try {
                choice = SegreteriaView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            switch(choice) {
                case 1-> insertCustomer();
                case 2-> insertOffer();
                case 3-> reportCustomer();
                case 4-> showCustomer();
                case 5-> updateAddress();
                case 6-> updateContacts();
                case 7-> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void showCustomer() {
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)) {
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
            throw new RuntimeException("Error while reporting customers: "+e.getMessage(),e);
        }
    }



    private void updateContacts() {
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
            handleException("Error while researching or updating customer contacts: " + e.getMessage(), e);
        } catch (SQLException | DAOException e) {
            handleException("Database error while updating contacts: " + e.getMessage(), e);
        }
    }

    private List<Contact> fetchContacts(String fiscalCode) throws SQLException, DAOException {
        try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            SearchContactProcedureDAO contactsDAO = new SearchContactProcedureDAO();
            return contactsDAO.execute(fiscalCode, conn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void updateContactInDatabase(Contact contact) throws SQLException, DAOException, IOException {
        try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            UpdateContactsProcedureDAO contactDAO = new UpdateContactsProcedureDAO();
            contactDAO.execute(contact, conn);
        }
    }

    private void handleException(String message, Exception e) {
        throw new RuntimeException(message, e);
    }

    private void updateAddress() {
        Customer customer;
        try{
            customer= CustomerView.updateAddress();
        }catch (IOException e){
            throw new RuntimeException("Error while updating address from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.SEGRETERIA)){
            UpdateAddressProcedureDAO addressDAO = new UpdateAddressProcedureDAO();
            addressDAO.execute(customer, conn);
            Printer.printBlue("Address successfully updated into the database.");
        } catch (DAOException | SQLException | IOException e) {
            throw new RuntimeException("Error while updating address into the database: " + e.getMessage(), e);
        }
    }

    public void insertCustomer() {
        Customer customer;
        try {
            // Step 1: Creazione del cliente tramite vista
            customer = CustomerView.insertCustomer();
        } catch (IOException e) {
            // Gestione errore input/output
            throw new RuntimeException("Error while creating customer from input: " + e.getMessage(), e);
        }

        try (Connection conn = ConnectionFactory.getConnection(Role.SEGRETERIA)) {
            // Step 2: Inserimento del cliente nel database tramite DAO
            InsertCustomerProcedureDAO customerDAO = new InsertCustomerProcedureDAO();
            customerDAO.execute(customer, conn);
            Printer.printBlue("Customer successfully inserted into the database.");
        } catch (DAOException | SQLException | IOException e) {
            // Gestione errore DAO
            throw new RuntimeException("Error while inserting customer into the database: " + e.getMessage(), e);
        }
    }

    public void insertOffer() {
        Offer offer;
        try{
            offer= OfferView.insertOffer();
        }catch(IOException e){
            throw new RuntimeException("Error while creating offer from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.SEGRETERIA)){
            InsertOfferProcedureDAO offerDAO = new InsertOfferProcedureDAO();
            offerDAO.execute(offer, conn);
            Printer.printBlue("Offer successfully inserted into the database.");
        } catch (DAOException | SQLException | IOException e) {
            throw new RuntimeException("Error while inserting offer into the database: " + e.getMessage(), e);
        }
    }
    public void reportCustomer() {
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
                throw new RuntimeException("Error while generating report: " + e.getMessage(), e);
            }
        } catch (IOException e) {
            Printer.errorPrint("Input error: " + e.getMessage());
            throw new RuntimeException("Error while creating report from input: " + e.getMessage(), e);
        }
    }

}

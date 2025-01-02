package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.dao.ConnectionFactory;
import it.crm.bd.model.dao.InsertCustomerProcedureDAO;
import it.crm.bd.model.dao.InsertOfferProcedureDAO;
import it.crm.bd.model.domain.Customer;
import it.crm.bd.model.domain.Offer;
import it.crm.bd.model.domain.Role;
import it.crm.bd.other.Printer;
import it.crm.bd.view.CustomerView;
import it.crm.bd.view.OfferView;
import it.crm.bd.view.SegreteriaView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
                case 4-> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
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

    }
}

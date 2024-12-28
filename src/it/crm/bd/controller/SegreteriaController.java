package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.dao.ConnectionFactory;
import it.crm.bd.model.dao.InsertCustomerProcedureDAO;
import it.crm.bd.model.domain.Customer;
import it.crm.bd.model.domain.Role;
import it.crm.bd.view.SegreteriaView;

import java.io.IOException;
import java.sql.SQLException;

public class SegreteriaController implements Controller {

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        try{
            customer= new InsertCustomerProcedureDAO().execute();
        }catch(DAOException e){
            throw new RuntimeException(e);
        }

    }
    public void insertOffer() {
        throw new RuntimeException("Not implemented yet");
    }
    public void reportCustomer() {
        throw new RuntimeException("Not implemented yet");
    }
}

package it.crm.bd.controller;

import it.crm.bd.model.dao.ConnectionFactory;
import it.crm.bd.model.domain.Role;
import it.crm.bd.view.OperatorView;

import java.io.IOException;
import java.sql.SQLException;

public class OperatoreController implements Controller {

    @Override
    public void start() {
        try{
            ConnectionFactory.changeRole(Role.OPERATORE);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        while(true){
            int choice;
            try{
                choice= OperatorView.showMenu();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
            switch(choice){
                case 1-> writeNotes();
                case 2-> callNotes();
                case 3-> addAppointment();
                case 4-> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }
    public void writeNotes() {
        throw new RuntimeException("Not implemented yet");
    }
    public void callNotes() {
        throw new RuntimeException("Not implemented yet");
    }
    public void addAppointment() {
        throw new RuntimeException("Not implemented yet");
    }
}

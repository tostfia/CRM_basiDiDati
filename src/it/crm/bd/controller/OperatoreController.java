package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.dao.*;
import it.crm.bd.model.domain.*;
import it.crm.bd.other.Printer;
import it.crm.bd.view.AppointmentView;
import it.crm.bd.view.InteractionView;
import it.crm.bd.view.NoteView;
import it.crm.bd.view.OperatorView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OperatoreController implements Controller {

    @Override
    public void start() {
        try{
            ConnectionFactory.getConnection(Role.OPERATORE);
        }catch(SQLException | IOException e){
            throw new RuntimeException("Error while changing role: "+e.getMessage(),e);
        }
        while(true){
            int choice;
            try{
                choice= OperatorView.showMenu();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
            switch(choice){
                case 1-> interaction();
                case 2-> writeNotes();
                case 3-> callNotes();
                case 4-> addAppointment();
                case 5-> showCustomers();
                case 6-> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }
    public void interaction() {
        Interaction interaction;
        try{
            // Step 1: Creazione dell'interazione tramite vista
            interaction= InteractionView.insertInteraction();
        }catch (IOException e){
            // Gestione errore input/output
            throw new RuntimeException("Error while creating interaction from input: " + e.getMessage(), e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)){
            // Step 2: Inserimento dell'interazione nel database tramite DAO
            InsertInteractionProcedureDAO interactionDAO= new InsertInteractionProcedureDAO();
            interactionDAO.execute(interaction,conn);
            Printer.printBlue("Interaction successfully inserted into the database.");
        }catch (IOException | SQLException |DAOException e){
            // Gestione errore DAO
            throw new RuntimeException("Error while inserting interaction into the database: " + e.getMessage(), e);
        }
    }
    public void writeNotes() {
        Note note;
        try {
            // Step 1: Creazione della nota tramite vista
            note = NoteView.writeNotes();
        } catch (IOException e) {
            // Gestione errore input/output
            throw new RuntimeException("Error while creating note from input: " + e.getMessage(), e);
        }
        try (Connection conn = ConnectionFactory.getConnection(Role.OPERATORE)) {
            // Step 2: Inserimento della nota nel database tramite DAO
            WriteNoteProcedureDAO noteDAO = new WriteNoteProcedureDAO();
            noteDAO.execute(note, conn);
            Printer.printBlue("Note successfully inserted into the database.");
        } catch (DAOException | IOException | SQLException e) {
            // Gestione errore DAO
            throw new RuntimeException("Error while inserting note into the database: " + e.getMessage(), e);
        }
    }
    public void callNotes() {
        String customerCF;
        try{
             customerCF=NoteView.callNotes();
        }catch(IOException e){
            throw new RuntimeException("Error while creating customer from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)){
            ReportNoteProcedureDAO noteDAO= new ReportNoteProcedureDAO();
            List<Note> notes=noteDAO.execute(customerCF,conn);
            if(notes.isEmpty()) {
                Printer.errorPrint("No notes found for the customer.");
            } else {
                for (Note note : notes) {
                    Printer.printGreen(note.toString());
                }
            }
        }catch(DAOException | SQLException | IOException e){
            throw new RuntimeException("Error while reporting customer's notes: "+e.getMessage(),e);
        }
    }
    public void addAppointment() {
        Appointment appointment;
        try{
            appointment= AppointmentView.scheduleAppointment();
        }catch(IOException e){
            throw new RuntimeException("Error while creating appointment from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)){
            InsertAppointmentProcedureDAO appointmentDAO= new InsertAppointmentProcedureDAO();
            appointmentDAO.execute(appointment,conn);
            Printer.printBlue("Appointment successfully inserted into the database.");
        }catch(DAOException | SQLException | IOException e){
            throw new RuntimeException("Error while inserting appointment into the database: "+e.getMessage(),e);
        }
    }
    public void showCustomers(){
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
}

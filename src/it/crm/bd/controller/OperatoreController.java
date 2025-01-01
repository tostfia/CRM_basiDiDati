package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.dao.ConnectionFactory;
import it.crm.bd.model.dao.InsertAppointmentProcedureDAO;
import it.crm.bd.model.dao.InsertInteractionProcedureDAO;
import it.crm.bd.model.dao.WriteNoteProcedureDAO;
import it.crm.bd.model.domain.Appointment;
import it.crm.bd.model.domain.Interaction;
import it.crm.bd.model.domain.Note;
import it.crm.bd.model.domain.Role;
import it.crm.bd.other.Printer;
import it.crm.bd.view.AppointmentView;
import it.crm.bd.view.InteractionView;
import it.crm.bd.view.NoteView;
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
                case 1-> interaction();
                case 2-> writeNotes();
                case 3-> callNotes();
                case 4-> addAppointment();
                case 5-> System.exit(0);
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
        try{
            // Step 2: Inserimento dell'interazione nel database tramite DAO
            InsertInteractionProcedureDAO interactionDAO= new InsertInteractionProcedureDAO();
            interactionDAO.execute(interaction);
            Printer.printBlue("Interaction successfully inserted into the database.");
        }catch (DAOException e){
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
        try {
            // Step 2: Inserimento della nota nel database tramite DAO
            WriteNoteProcedureDAO noteDAO = new WriteNoteProcedureDAO();
            noteDAO.execute(note);
            Printer.printBlue("Note successfully inserted into the database.");
        } catch (DAOException e) {
            // Gestione errore DAO
            throw new RuntimeException("Error while inserting note into the database: " + e.getMessage(), e);
        }
    }
    public void callNotes() {
        throw new RuntimeException("Not implemented yet");
    }
    public void addAppointment() {
        Appointment appointment;
        try{
            appointment= AppointmentView.scheduleAppointment();
        }catch(IOException e){
            throw new RuntimeException("Error while creating appointment from input: "+e.getMessage(),e);
        }
        try{
            InsertAppointmentProcedureDAO appointmentDAO= new InsertAppointmentProcedureDAO();
            appointmentDAO.execute(appointment);
            Printer.printBlue("Appointment successfully inserted into the database.");
        }catch(DAOException e){
            throw new RuntimeException("Error while inserting appointment into the database: "+e.getMessage(),e);
        }
    }
}

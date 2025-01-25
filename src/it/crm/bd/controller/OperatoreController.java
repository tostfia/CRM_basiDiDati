package it.crm.bd.controller;

import it.crm.bd.exception.*;
import it.crm.bd.model.dao.*;
import it.crm.bd.model.domain.*;
import it.crm.bd.other.Printer;
import it.crm.bd.view.AppointmentView;
import it.crm.bd.view.NoteView;
import it.crm.bd.view.OperatorView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OperatoreController implements Controller {

    @Override
    public void start() throws LoadException, InputException, DataBaseOperationException, ServiceException {
        try{
            ConnectionFactory.getConnection(Role.OPERATORE);
        }catch(SQLException | IOException e){
            throw new ServiceException("Error while connecting to the database: "+e.getMessage(),e);
        }
        while(true){
            int choice;
            try{
                choice= OperatorView.showMenu();
            }catch(IOException e){
                throw new LoadException("Error while showing menu: "+e.getMessage(),e);
            }
            switch(choice){
                case 1-> writeNotes();
                case 2-> callNotes();
                case 3-> addAppointment();
                case 4-> showCustomers();
                case 5-> showOffers();
                case 6-> showAppointments();
                case 7-> System.exit(0);
                default -> throw new InputException("Invalid choice.");
            }
        }
    }

    //Scrivo i dettagli della nota
    public void writeNotes() throws DataBaseOperationException, InputException {
        Note note;
        try {
            // Step 1: Creazione della nota tramite vista
            note = NoteView.writeNotes();
        } catch (IOException e) {
            // Gestione errore input/output
            throw new InputException("Error while creating note from input: " + e.getMessage(), e);
        }
        try (Connection conn = ConnectionFactory.getConnection(Role.OPERATORE)) {
            // Step 2: Inserimento della nota nel database tramite DAO
            WriteNoteProcedureDAO noteDAO = new WriteNoteProcedureDAO();
            noteDAO.execute(note, conn);
            Printer.printBlue("Note successfully inserted into the database.");
        } catch (DAOException | IOException | SQLException e) {
            // Gestione errore DAO
            throw new DataBaseOperationException("Error while inserting note into the database: " + e.getMessage(), e);
        }
    }
    //Chiamo le note
    public void callNotes() throws DataBaseOperationException, InputException {
        String customerCF;
        try {
            customerCF = NoteView.callNotes();
        } catch (IOException e) {
            throw new InputException("Error while getting customer fiscal code: " + e.getMessage(), e);
        }
        try (Connection conn = ConnectionFactory.getConnection(Role.OPERATORE)) {
            ReportNoteProcedureDAO noteDAO = new ReportNoteProcedureDAO();
            List<Note> notes = noteDAO.execute(customerCF, conn);
            if (notes.isEmpty()) {
                Printer.errorPrint("No notes found for the customer.");
            } else {
                int count = 1;
                for (Note note : notes) {
                    Printer.printGreen("\n"+count+"."+note.toString());
                    count++;
                }
            }
        } catch (DAOException | SQLException | IOException e) {
            throw new DataBaseOperationException("Error while fetching customer's notes: " + e.getMessage(), e);
        }
    }
    //Aggiungo un appuntamento
    public void addAppointment() throws DataBaseOperationException, InputException {
        Appointment appointment;
        try{
            appointment= AppointmentView.scheduleAppointment();
        }catch(IOException e){
            throw new InputException("Error while creating appointment from input: "+e.getMessage(),e);
        }
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)){
            InsertAppointmentProcedureDAO appointmentDAO= new InsertAppointmentProcedureDAO();
            appointmentDAO.execute(appointment,conn);
            Printer.printBlue("Appointment successfully inserted into the database.");
        }catch(DAOException | SQLException | IOException e){
            throw new DataBaseOperationException("Error while inserting appointment into the database: "+e.getMessage(),e);
        }
    }
    //Mostra i clienti
    public void showCustomers() throws DataBaseOperationException {
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)) {
            CustomerProcedureDAO customerDAO = new CustomerProcedureDAO();
            List<Customer> customers = customerDAO.execute(conn);
            if (customers.isEmpty()) {
                Printer.errorPrint("No customers found in the database.");
            } else {
                int count = 1;
                for (Customer customer : customers) {
                    Printer.printGreen(count+"."+customer.toString());
                    count++;
                }
            }
        }catch(DAOException | SQLException | IOException e){
            throw new DataBaseOperationException("Error while fetching customers: "+e.getMessage(),e);
        }
    }
    //Mostra le offerte
    public void showOffers() throws DataBaseOperationException {
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)){
            OfferProcedureDAO offerDAO= new OfferProcedureDAO();
            List<Offer> offers= offerDAO.execute(conn);
            if(offers.isEmpty()){
                Printer.errorPrint("No offers found in the database.");
            }else{
                int count=1;
                for(Offer offer: offers){
                    Printer.printGreen("\n"+count+"."+offer.toString());
                    count++;
                }
            }
        }catch(DAOException | SQLException | IOException e){
            throw new DataBaseOperationException("Error while fetching offers: "+e.getMessage(),e);
        }
    }
    //Mostra gli appuntamenti
    public void showAppointments() throws DataBaseOperationException {
        try(Connection conn= ConnectionFactory.getConnection(Role.OPERATORE)){
            AppointmentProcedureDAO appointmentDAO= new AppointmentProcedureDAO();
            List<Appointment> appointments= appointmentDAO.execute(conn);
            if(appointments.isEmpty()){
                Printer.errorPrint("No appointments found in the database.");
            }else{
                int count=1;
                for(Appointment appointment: appointments){
                    Printer.printGreen("\n"+count+"."+appointment.toString());
                    count++;
                }
            }
        }catch(DAOException | SQLException | IOException e){
            throw new DataBaseOperationException("Error while fetching appointments: "+e.getMessage(),e);
        }
    }
}

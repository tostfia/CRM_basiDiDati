package it.crm.bd.view;

import it.crm.bd.controller.OperatoreController;
import it.crm.bd.exception.DataBaseOperationException;
import it.crm.bd.model.domain.Appointment;
import it.crm.bd.model.domain.Note;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.time.LocalDate;

public class NoteView extends CommonView{
    public NoteView() {super();}
    static OperatoreController controller = new OperatoreController();
    static  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Note writeNotes() throws IOException, DataBaseOperationException {
        Printer.printlnBlue("\n---------------Write Notes---------------\n");
        String customer = inputString(reader, "Customer (Please enter the customer's fiscal code, you will find it in -showCustomers-)");
        LocalDate date = inputDate(reader, "Date (YYYY-MM-DD)");
        Time time = inputTime(reader);
        Printer.print("\nWhich offer do you have proposed?");
        controller.showOffers();
        String offer = inputString(reader, "\nInsert the offer description");
        Boolean outcome = inputBoolean(reader);
        String description = inputString(reader, "Description");
        String operator = inputString(reader, "Operator");
        Appointment appointment = askForAppoinment();
        return new Note(outcome, description, customer, operator, date, time, offer, appointment);

    }
    //Metodo per l'appuntamento
    private static Appointment askForAppoinment() throws IOException {
        String input = inputString(reader, "Do you want to add an appointment to this note? (yes/no)");
        if(input.equalsIgnoreCase("yes")) {
            String branch = inputString(reader, "Branch");
            LocalDate appointmentDate = inputDate(reader, "Appointment date (YYYY-MM-DD)");
            Time appointmentTime = inputTime(reader);
            return new Appointment(branch, appointmentDate, appointmentTime);
        } else {
            return null;
        }
    }
    //Metodo per il risultato
    private static Boolean inputBoolean(BufferedReader reader) throws IOException {
        Boolean outcome = null;
        while (outcome == null) {
            Printer.print("Outcome (accepted/refused)" + ": ");
            String input = reader.readLine().trim();
            if (input.equalsIgnoreCase("accepted")) {
                outcome = true;
            } else if (input.equalsIgnoreCase("refused")) {
                outcome = false;
            } else {
                Printer.errorPrint("Invalid outcome. Please use 'accepted' or 'refused'.");
            }
        }
        return outcome;
    }
    //Metodo per chiamare le note
    public static String callNotes() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printlnBlue("\n---------------Customer's notes report---------------\n");
        return inputString(reader, "Customer");
    }
}

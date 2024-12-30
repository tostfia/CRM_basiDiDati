package it.crm.bd.view;

import it.crm.bd.model.domain.Customer;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CustomerView {

    public static Customer insertCustomer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Printer.printBlue("-----------Insert customer data-----------");

        String name = inputString(reader, "\nName");
        String surname = inputString(reader, "Surname");
        String fiscalCode = inputString(reader, "Fiscal code");
        LocalDate birthDate = inputDate(reader, "Birth date (YYYY-MM-DD)");
        List<String> phones = inputMultipleStrings(reader, "phone numbers");
        List<String> emails = inputEmails(reader, "email addresses");
        String address = inputString(reader, "Address");
        String city = inputString(reader, "City");
        String cap = inputValidatedString(reader, "CAP", "\\d{5}", "CAP must be a 5-digit number.");
        LocalDate registrationDate = inputDate(reader, "Registration date (YYYY-MM-DD)");

        // Return un nuovo oggetto Customer
        return new Customer(
                name,
                surname,
                birthDate,
                fiscalCode,
                phones,
                address,
                city,
                cap,
                emails,
                registrationDate
        );
    }

    // Metodo generico per inserire una stringa
    private static String inputString(BufferedReader reader, String prompt) throws IOException {
        Printer.print(prompt + ": ");
        return reader.readLine().trim();
    }

    //Metodo per validare e inserire una stringa con un pattern specifico.
    private static String inputValidatedString(BufferedReader reader, String prompt, String pattern, String errorMessage) throws IOException {
        String input;
        while (true) {
            Printer.print(prompt + ": ");
            input = reader.readLine().trim();
            if (input.matches(pattern)) {
                return input;
            }
            Printer.print(errorMessage);
        }
    }

    //Metodo per inserire una data valida.

    private static LocalDate inputDate(BufferedReader reader, String prompt) throws IOException {
        LocalDate date = null;
        while (date == null) {
            Printer.print(prompt + ": ");
            String input = reader.readLine().trim();
            try {
                date = LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                Printer.print("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
        return date;
    }

    //Metodo per inserire più stringhe fino a quando non viene digitato 'done'.
    private static List<String> inputMultipleStrings(BufferedReader reader, String itemName) throws IOException {
        List<String> items = new ArrayList<>();
        Printer.print("Enter " + itemName + " (type 'done' when finished):");
        while (true) {
            String input = reader.readLine().trim();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            if (!input.isEmpty()) {
                items.add(input);
            }
        }
        return items;
    }

    //Metodo per validare e inserire email.
    private static List<String> inputEmails(BufferedReader reader, String prompt) throws IOException {
        List<String> emails = new ArrayList<>();
        Printer.print("Enter " + prompt + " (type 'done' when finished):");
        while (true) {
            String email = reader.readLine().trim();
            if (email.equalsIgnoreCase("done")) {
                break;
            }
            if (!email.isEmpty()) {
                if (email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                    emails.add(email);
                } else {
                    Printer.print("Invalid email format: " + email);
                }
            }
        }
        return emails;
    }
}



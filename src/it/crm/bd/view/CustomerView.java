package it.crm.bd.view;

import it.crm.bd.model.domain.Contact;
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
    private static final String FISCAL_CODE="Fiscal code";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static Customer insertCustomer() throws IOException {
        Printer.printBlue("-----------Insert customer data-----------");

        String name = inputString("Name");
        String surname = inputString("Surname");
        String fiscalCode = inputString(FISCAL_CODE);
        LocalDate birthDate = inputDate("Birth date (YYYY-MM-DD)");
        List<String> phones = inputMultipleStrings();
        List<String> emails = inputEmails();
        String address = inputString("Address");
        String city = inputString("City");
        String cap = inputString("CAP");
        LocalDate registrationDate = inputDate("Registration date (YYYY-MM-DD)");

        return new Customer(name, surname, birthDate, fiscalCode, phones, address, city, cap, emails, registrationDate);
    }

    private static String inputString(String prompt) throws IOException {
        Printer.print(prompt + ":");
        return reader.readLine().trim();
    }


    private static LocalDate inputDate(String prompt) throws IOException {
        while (true) {
            String input = inputString(prompt);
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                Printer.errorPrint("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }

    private static List<String> inputMultipleStrings() throws IOException {
        List<String> items = new ArrayList<>();
        Printer.print("Enter " + "phone numbers" + " (type 'done' when finished):");
        while (true) {
            String input = reader.readLine().trim();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
            if (!input.isEmpty()) {
                items.add(input);
            }
        }
        return items;
    }

    private static List<String> inputEmails() throws IOException {
        List<String> emails = new ArrayList<>();
        Printer.print("Enter " + "email addresses" + " (type 'done' when finished):");
        while (true) {
            String email = reader.readLine().trim();
            if ("done".equalsIgnoreCase(email)) {
                break;
            }
            if (email.isEmpty()) {
                Printer.print("Invalid email format: " + email);
            } else {
                emails.add(email);
            }
        }
        return emails;
    }

    public static String researchCustomerContact() throws IOException {
        Printer.printBlue("-----------Update customer contacts-----------\n");
        String fiscalCode = inputString(FISCAL_CODE);
        if (fiscalCode.isEmpty()) {
            throw new IOException("Fiscal code cannot be empty.");
        }
        return fiscalCode;
    }

    public static Contact updateContacts(List<Contact> contacts) throws IOException {
        if (contacts.isEmpty()) {
            Printer.errorPrint("No contacts found to update.");
            return null;
        }

        Printer.print("Existing contacts:");
        int index = 1;
        for (Contact contact : contacts) {
            Printer.print(index++ + ". " + contact.toString());
        }

        String choice = inputString("\nEnter the number of the contact you want to update");
        int contactIndex = parseContactChoice(choice, contacts.size());
        Contact selectedContact = contacts.get(contactIndex);

        String newType = inputString("\nEnter the new type of the contact (email/phone)");
        if (!newType.equalsIgnoreCase("email") && !newType.equalsIgnoreCase("phone")) {
            throw new IOException("Invalid contact type. Must be 'email' or 'phone'.");
        }

        String newValue = inputString("\nEnter the new value of the contact");
        if (newValue.isEmpty()) {
            throw new IOException("Contact value cannot be empty.");
        }

        selectedContact.setType(newType);
        selectedContact.setValue(newValue);
        return selectedContact;
    }

    private static int parseContactChoice(String choice, int totalContacts) throws IOException {
        try {
            int contactIndex = Integer.parseInt(choice) - 1;
            if (contactIndex < 0 || contactIndex >= totalContacts) {
                throw new IOException("Invalid contact number.");
            }
            return contactIndex;
        } catch (NumberFormatException e) {
            throw new IOException("Invalid input. Please enter a valid number.");
        }
    }



    // Method for updating customer address
    public static Customer updateAddress() throws IOException {
        Printer.printBlue("-----------Update customer address-----------");
        String fiscalCode = inputString(FISCAL_CODE);
        String address = inputString("Address");
        String city = inputString("City");
        String cap = inputString("CAP");
        return new Customer(fiscalCode, address, city, cap);
    }

    public static LocalDate[] reportCustomer() throws IOException {
        Printer.printBlue("\n-----------Report customer-----------");
        LocalDate start = inputDateWithValidation("Start date (YYYY-MM-DD)");
        LocalDate end = inputDateWithValidation("End date (YYYY-MM-DD)");

        Printer.printGreen("\nReport will be generated for the range: " + start + " - " + end);
        return new LocalDate[]{start, end};
    }

    private static LocalDate inputDateWithValidation(String prompt) throws IOException {
        LocalDate date = null;
        while (date == null) {
            date = inputDate(prompt);
        }
        return date;
    }
}



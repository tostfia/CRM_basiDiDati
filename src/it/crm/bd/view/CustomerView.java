package it.crm.bd.view;

import it.crm.bd.controller.SegreteriaController;
import it.crm.bd.exception.DataBaseOperationException;
import it.crm.bd.model.domain.Contact;
import it.crm.bd.model.domain.Customer;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerView  extends CommonView{
    public CustomerView() {super();}
    private static final String FISCAL_CODE="Fiscal code";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static Customer insertCustomer() throws IOException {
        Printer.printBlue("-----------Insert customer data-----------\n");

        String name = inputString(reader,"Name");
        String surname = inputString(reader,"Surname");
        String fiscalCode = inputString(reader,FISCAL_CODE);
        LocalDate birthDate = inputDate(reader,"Birth date (YYYY-MM-DD)");
        List<String> phones = inputMultipleStrings();
        List<String> emails = inputEmails();
        String address = inputString(reader,"Address");
        String city = inputString(reader,"City");
        String cap = inputString(reader,"CAP");
        return new Customer(name, surname, birthDate, fiscalCode, phones, address, city, cap, emails);
    }




    //Metodo per inserire piu numeri di telefono
    private static List<String> inputMultipleStrings() throws IOException {
        List<String> items = new ArrayList<>();
        Printer.print("Enter " + "phone numbers" + " (type 'done' when finished):");
        while (true) {
            String input = reader.readLine().trim();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
            String[] numbers = input.split("[,;\\s]+");
            for(String number : numbers) {
                if (number.isEmpty()) {
                    Printer.print("Invalid phone number format: " + number);
                } else {
                    items.add(number);
                }
            }
        }
        return items;
    }
    //Metodo per inserire piu email
    private static List<String> inputEmails() throws IOException {
        List<String> emails = new ArrayList<>();
        Printer.print("Enter " + "email addresses" + " (type 'done' when finished):");
        while (true) {
            String email = reader.readLine().trim();
            if ("done".equalsIgnoreCase(email)) {
                break;
            }
            String[] emailArray = email.split("[,;\\s]+");
            for(String e : emailArray) {
                if (e.isEmpty()) {
                    Printer.print("Invalid email format: " + email);
                } else {
                    emails.add(e);
                }
            }
        }
        return emails;
    }
    //Metodo per la ricerca del cliente
    public static String researchCustomerContact() throws IOException {
        Printer.printBlue("-----------Update customer contacts-----------\n");
        String fiscalCode = inputString(reader,FISCAL_CODE);
        if (fiscalCode.isEmpty()) {
            throw new IOException("Fiscal code cannot be empty.");
        }
        return fiscalCode;
    }

    //Metodo per aggiornare i contatti
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

        String choice = inputString(reader,"\nEnter the number of the contact you want to update");
        int contactIndex = parseContactChoice(choice, contacts.size());
        Contact selectedContact = contacts.get(contactIndex);

        String newType = inputString(reader,"\nEnter the new type of the contact (email/phone)");
        if (!newType.equalsIgnoreCase("email") && !newType.equalsIgnoreCase("phone")) {
            throw new IOException("Invalid contact type. Must be 'email' or 'phone'.");
        }

        String newValue = inputString(reader,"\nEnter the new value of the contact");
        if (newValue.isEmpty()) {
            throw new IOException("Contact value cannot be empty.");
        }

        selectedContact.setType(newType);
        selectedContact.setValue(newValue);
        return selectedContact;
    }
    //Metodo per la scelta del contatto
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



    // Metodo per aggiornare gli indirizzi
    public static Customer updateAddress() throws IOException {
        Printer.printBlue("-----------Update customer address-----------");
        String fiscalCode = inputString(reader,FISCAL_CODE);
        String address = inputString(reader,"Address");
        String city = inputString(reader,"City");
        String cap = inputString(reader,"CAP");
        return new Customer(fiscalCode, address, city, cap);
    }
    //Metodo per fare il report dei clienti
    public static LocalDate[] reportCustomer() throws IOException {
        Printer.printBlue("\n-----------Report customer-----------");
        LocalDate start = inputDateWithValidation("\nStart date (YYYY-MM-DD)");
        LocalDate end = inputDateWithValidation("End date (YYYY-MM-DD)");

        Printer.printGreen("\nReport will be generated for the range: " + start + " - " + end);
        return new LocalDate[]{start, end};
    }
    //Metodo per validare la data
    private static LocalDate inputDateWithValidation(String prompt) throws IOException {
        LocalDate date = null;
        while (date == null) {
            date = inputDate(reader,prompt);
        }
        return date;
    }

    public static String deleteCustomer() throws IOException, DataBaseOperationException {
        Printer.printBlue("\n-----------Delete customer-----------");
        SegreteriaController controller = new SegreteriaController();
        controller.showCustomer();
        Printer.print("\nEnter the fiscal code of the customer you want to delete:");
        String fiscalCode = reader.readLine().trim();
        if (fiscalCode.isEmpty()) {
            throw new IOException("Fiscal code cannot be empty.");
        }
        return fiscalCode;

    }
}



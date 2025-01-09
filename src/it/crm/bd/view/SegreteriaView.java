package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SegreteriaView extends CommonView {
    public SegreteriaView() {super();}
    public static int showMenu() throws IOException {
        Printer.printBlue("\n*********************************");
        Printer.printBlue("\n*    SECRETARY DASHBOARD    *");
        Printer.printBlue("\n*********************************\n");
        Printer.printBlue("\n*** What should I do for you? ***\n");
        Printer.printBlue("\n1) Add customer");
        Printer.printBlue("\n2) Delete customer");
        Printer.printBlue("\n3) Add offer");
        Printer.printBlue("\n4) Report customer");
        Printer.printBlue("\n5) Show customer");
        Printer.printBlue("\n6) Update  customer's address");
        Printer.printBlue("\n7) Update  customer's phone number or email");
        Printer.printBlue("\n8) Quit");

        int choice;
        do {
            choice = inputInt(new BufferedReader(new InputStreamReader(System.in)));
            if (choice < 1 || choice > 8) {
                Printer.errorPrint("Invalid choice. Please select a number between 1 and 6.");
            }
        } while (choice < 1 || choice > 8);

        return choice;

    }
}

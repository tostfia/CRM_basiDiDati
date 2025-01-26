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
        Printer.printBlue("\n2) Add offer");
        Printer.printBlue("\n3) Report customer");
        Printer.printBlue("\n4) Show customer");
        Printer.printBlue("\n5) Update  customer's address");
        Printer.printBlue("\n6) Update  customer's phone number or email");
        Printer.printBlue("\n7) Show offers");
        Printer.printBlue("\n8) Show appointments");
        Printer.printBlue("\n9) Quit");

        int choice;
        do {
            choice = inputInt(new BufferedReader(new InputStreamReader(System.in)));
            if (choice < 1 || choice > 9) {
                Printer.errorPrint("Invalid choice. Please select a number between 1 and 6.");
            }
        } while (choice < 1 || choice > 9);


        return choice;

    }
}

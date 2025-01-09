package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OperatorView extends CommonView {
    public OperatorView() {super();}
    public static int showMenu() throws IOException {

        Printer.printlnBlue("\n*********************************");
        Printer.printlnBlue("*    OPERATOR DASHBOARD    *");
        Printer.printlnBlue("*********************************\n");
        Printer.printlnBlue("*** What should I do for you? ***\n");
        Printer.printlnBlue("1) Interaction");
        Printer.printlnBlue("2) Write notes");
        Printer.printlnBlue("3) Call notes");
        Printer.printlnBlue("4) Add appointment");
        Printer.printlnBlue("5) Show customers");
        Printer.printlnBlue("6) Quit");
        Printer.printlnBlue("*********************************\n");

        int choice;
        do {
            choice = inputInt(new BufferedReader(new InputStreamReader(System.in)));
            if (choice < 1 || choice > 6) {
                Printer.errorPrint("Invalid choice. Please select a number between 1 and 6.");
            }
        } while (choice < 1 || choice > 6);

        return choice;

    }
}

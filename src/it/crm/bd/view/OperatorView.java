package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.IOException;
import java.util.Scanner;

public class OperatorView {
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

        Scanner input = new Scanner(System.in);
        int choice ;
        while (true) {
            Printer.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 6) {
                break;
            }
            Printer.errorPrint("Invalid option");
        }
        return choice;
    }
}

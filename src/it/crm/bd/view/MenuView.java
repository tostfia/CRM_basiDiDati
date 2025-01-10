package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MenuView extends CommonView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public MenuView() {
        super();
    }

    public static int menu() throws IOException {
        Printer.printlnBlue("\n*********************************");
        Printer.printlnBlue("\n********   CRM SYSTEM    ********");
        Printer.printlnBlue("\n*********************************");
        Printer.printlnBlue("\n*** What should I do for you? ***");
        Printer.printlnBlue("             1.Login               ");
        Printer.printlnBlue("             2.Register            ");
        Printer.printlnBlue("             3.Exit                ");
        Printer.printlnBlue("*********************************\n");
        return inputInt(reader);
    }
}

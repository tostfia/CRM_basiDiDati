package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.IOException;
import java.util.Scanner;

public class SegreteriaView {
    public static int showMenu() throws IOException {
        Printer.printBlue("*********************************");
        Printer.printBlue("*    SECRETARY DASHBOARD    *");
        Printer.printBlue("*********************************\n");
        Printer.printBlue("*** What should I do for you? ***\n");
        Printer.printBlue("1) Add customer");
        Printer.printBlue("2) Add offer");
        Printer.printBlue("3) Report customer");;
        Printer.printBlue("4) Quit");

        Scanner input= new Scanner(System.in);
        int choice=0;
        while(true){
            Printer.print("Please enter your choice: ");
            choice= input.nextInt();
            if(choice>=1 && choice<=4){
                break;
            }
            Printer.errorPrint("Invalid option");
        }
        return choice;

    }
}

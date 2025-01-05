package it.crm.bd.view;

import it.crm.bd.other.Printer;

import java.io.IOException;
import java.util.Scanner;

public class SegreteriaView {
    public static int showMenu() throws IOException {
        Printer.printBlue("\n*********************************");
        Printer.printBlue("\n*    SECRETARY DASHBOARD    *");
        Printer.printBlue("\n*********************************\n");
        Printer.printBlue("\n*** What should I do for you? ***\n");
        Printer.printBlue("\n1) Add customer");
        Printer.printBlue("\n2) Add offer");
        Printer.printBlue("\n3) Report customer");
        Printer.printBlue("\n4) Quit");

        Scanner input= new Scanner(System.in);
        int choice=0;
        while(true){
            Printer.print("\nPlease enter your choice: ");
            choice= input.nextInt();
            if(choice>=1 && choice<=4){
                break;
            }
            Printer.errorPrint("Invalid option");
        }
        return choice;

    }
}

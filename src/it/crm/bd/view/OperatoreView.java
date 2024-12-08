package it.crm.bd.view;

import java.io.IOException;
import java.util.Scanner;

public class OperatoreView {
    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*    OPERATOR DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Show customers");
        System.out.println("2) Create note");
        System.out.println("3) Show notes");
        System.out.println("4) Modify note");
        System.out.println("5) Delete note");
        System.out.println("6) Quit");

        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 6) {
                break;
            }
            System.out.println("Invalid option");
        }
        return choice;
    }
}

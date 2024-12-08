package it.crm.bd.view;

import java.io.IOException;

public class SegreteriaView {
    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*    SECRETARY DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Show customers's report");
        System.out.println("2) Add customer");
        System.out.println("3) Delete customer");
        System.out.println("4) Modify customer");
        System.out.println("5) Add offer");
        System.out.println("6) Modify offer");
        System.out.println("7) Delete offer");
        System.out.println("8) Quit");

        java.util.Scanner input = new java.util.Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 8) {
                break;
            }
            System.out.println("Invalid option");
        }
        return choice;

    }
}

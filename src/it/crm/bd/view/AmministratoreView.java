package it.crm.bd.view;

import java.io.IOException;
import java.util.Scanner;

public class AmministratoreView {
    public static int showMenu() throws IOException {
        System.out.println("*********************************");
        System.out.println("*    ADMINISTRATOR DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Add new user");
        System.out.println("2) Remove user");
        System.out.println("3) List users");
        System.out.println("4) Quit");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 4) {
                break;
            }
            System.out.println("Invalid option");
        }


       return choice;
    }
}

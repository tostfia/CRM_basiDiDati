package it.crm.bd.view;

import it.crm.bd.model.domain.Credentials;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView {
    public static Credentials authenticate() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printBlue("-----------Login-----------\n");
        Printer.print("-username: ");
        String username = reader.readLine();
        Printer.print("-password: ");
        String password = reader.readLine();
        return new Credentials(username, password, null);
    }
}

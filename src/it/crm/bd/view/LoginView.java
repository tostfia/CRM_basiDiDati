package it.crm.bd.view;

import it.crm.bd.model.domain.Credentials;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView extends CommonView{
    public LoginView() {super();}
    public static Credentials authenticate() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printBlue("-----------Login-----------\n");
        String username = inputString(reader, "Username");
        String password = inputString(reader, "Password");
        return new Credentials(username, password, null);
    }
}

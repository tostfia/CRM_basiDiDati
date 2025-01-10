package it.crm.bd.view;

import it.crm.bd.model.domain.Credentials;
import it.crm.bd.model.domain.Role;
import it.crm.bd.other.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterView extends CommonView {
    public RegisterView() {
        super();
    }

    public static Credentials register() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Printer.printBlue("-----------Register-----------\n");
        String username = inputString(reader, "Username");
        String password = inputString(reader, "Password");
        Role role = Role.valueOf(inputString(reader, "Role [SEGRETERIA,OPERATORE]"));
        return new Credentials(username, password, role);
    }
}

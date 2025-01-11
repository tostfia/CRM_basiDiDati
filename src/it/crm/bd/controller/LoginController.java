package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.model.dao.LoginProcedureDAO;
import it.crm.bd.model.domain.Credentials;
import it.crm.bd.other.Printer;
import it.crm.bd.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller {

    Credentials cred = null;

    @Override
    public void start() {
        try {
            cred = LoginView.authenticate();
        } catch(IOException e) {
            Printer.errorPrint(e.getMessage());
        }

        try {
            cred = new LoginProcedureDAO().execute(cred.getUsername(), cred.getPassword());
        } catch(DAOException e) {
            Printer.errorPrint(e.getMessage());
        }
    }

    public Credentials getCred() {
        return cred;
    }


}

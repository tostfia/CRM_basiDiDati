package it.crm.bd.controller;

import it.crm.bd.exception.DAOException;
import it.crm.bd.dao.RegisterProcedureDAO;
import it.crm.bd.model.Credentials;
import it.crm.bd.other.Printer;
import it.crm.bd.view.RegisterView;

import java.io.IOException;

public class AdminController implements Controller {
    private Credentials cred;
    @Override
    public void start()  {
        try {
            cred = RegisterView.register();
        }catch(IOException e){
            Printer.errorPrint(e.getMessage());
        }
        try {
            cred= new RegisterProcedureDAO().execute(cred.getUsername(), cred.getPassword(), cred.getRole());
        }catch(DAOException e){
            Printer.errorPrint(e.getMessage());
        }
    }
    public Credentials getCred() {return cred;}

}

package it.crm.bd.controller;
import it.crm.bd.model.domain.Credentials;
import it.crm.bd.model.domain.Role;
import it.crm.bd.other.Printer;


public class ApplicationController implements Controller {
    Credentials cred;
    @Override
    public void start(){
        LoginController loginController=new LoginController();
        loginController.start();
        cred = loginController.getCred();

        Role role = cred.getRole();
        if (role == null) {
            role = Role.NON_RICONOSCIUTO; // Imposta un ruolo di fallback se il ruolo è null
        }
        switch (role) {
            case SEGRETERIA:
                new SegreteriaController().start();
                break;
            case OPERATORE:
                new OperatoreController().start();
                break;
            default:
                Printer.print("Ruolo non riconosciuto");
        }

    }
}

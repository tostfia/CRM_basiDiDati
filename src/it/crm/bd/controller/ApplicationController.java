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

        Role role = cred != null ? cred.getRole() : Role.NON_RICONOSCIUTO;

        // Gestione dei ruoli
        switch (role) {
            case SEGRETERIA -> new SegreteriaController().start();
            case OPERATORE -> new OperatoreController().start();
            default -> Printer.print("Ruolo non riconosciuto o non autorizzato.");
        }

    }
}

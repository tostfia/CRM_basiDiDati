package it.crm.bd.controller;
import it.crm.bd.exception.*;
import it.crm.bd.model.Credentials;
import it.crm.bd.other.Role;
import it.crm.bd.other.Printer;
import it.crm.bd.view.MenuView;

import java.io.IOException;


public class ApplicationController implements Controller {

    private Credentials cred;

    @Override
    public void start() throws ServiceException, InputException, DataBaseOperationException, LoadException, IOException{
        boolean running = true;

        while (running) {
            int choice = MenuView.menu(); // Mostra il menu e acquisisce la scelta dell'utente
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    Printer.printGreen("Arrivederci!");
                    running = false; // Esce dal ciclo principale
                }
                default -> Printer.print("Scelta non valida.");
            }
        }
    }

    public void login() throws ServiceException, InputException, DataBaseOperationException, LoadException {
        LoginController loginController = new LoginController();
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

    public void register() {
        AdminController adminController = new AdminController();
        adminController.start();
        cred = adminController.getCred();

        // Messaggio di conferma e ritorno al menu
        if (cred != null) {
            Printer.printGreen("Utente registrato con successo!");
        } else {
            Printer.print("Registrazione annullata o non completata.");
        }
    }
}

package it.crm.bd;

import it.crm.bd.controller.ApplicationController;
import it.crm.bd.other.Printer;


public class Main {
    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController();
        try {
            applicationController.start();
        } catch (Exception e) {
            Printer.errorPrint(e.getMessage());
        }
    }
}

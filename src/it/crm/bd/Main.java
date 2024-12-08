package it.crm.bd;

import it.crm.bd.controller.ApplicationController;

public class Main {
    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController();
        applicationController.start();
    }
}

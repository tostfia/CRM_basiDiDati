package it.crm.bd;

import it.crm.bd.controller.ApplicationController;
import it.crm.bd.exception.DataBaseOperationException;
import it.crm.bd.exception.InputException;
import it.crm.bd.exception.LoadException;
import it.crm.bd.exception.ServiceException;

public class Main {
    public static void main(String[] args) throws ServiceException, InputException, DataBaseOperationException, LoadException {
        ApplicationController applicationController = new ApplicationController();
        applicationController.start();
    }
}

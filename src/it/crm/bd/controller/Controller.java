package it.crm.bd.controller;

import it.crm.bd.exception.DataBaseOperationException;
import it.crm.bd.exception.InputException;
import it.crm.bd.exception.LoadException;
import it.crm.bd.exception.ServiceException;

public interface Controller {
    void start() throws LoadException, ServiceException, InputException, DataBaseOperationException;
}

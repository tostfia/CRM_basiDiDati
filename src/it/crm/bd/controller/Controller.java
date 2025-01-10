package it.crm.bd.controller;

import it.crm.bd.exception.*;

import java.io.IOException;

public interface Controller {
    void start() throws LoadException, ServiceException, InputException, DataBaseOperationException, DAOException, IOException;
}

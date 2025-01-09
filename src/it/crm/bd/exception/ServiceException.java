package it.crm.bd.exception;

public class ServiceException extends Exception {
    public ServiceException() {super();}
    public ServiceException(String message) {super(message);}
    public ServiceException(String message, Throwable cause) {super(message, cause);}


}

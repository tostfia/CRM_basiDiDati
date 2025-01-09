package it.crm.bd.exception;

public class DataBaseOperationException extends Exception {
    public DataBaseOperationException() {super();}
    public DataBaseOperationException(String message) {super(message);}
    public DataBaseOperationException(String message, Throwable cause) {super(message, cause);}
}

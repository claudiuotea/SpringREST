package application.services.rest;

public class ServiceException extends RuntimeException {
    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

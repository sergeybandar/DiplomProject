package by.tms.strore.exception;

public class OrderAlreadyExistsException extends RuntimeException{
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}

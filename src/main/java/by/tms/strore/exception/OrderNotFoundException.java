package by.tms.strore.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(long id){
        super("User with id: "+id+" not found");
    }
    public OrderNotFoundException(String name){
        super("User with name: "+name+" not found");
    }

}

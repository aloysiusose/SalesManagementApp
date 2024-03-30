package dev.aloysius.SalesManagementSystem.CustomExceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

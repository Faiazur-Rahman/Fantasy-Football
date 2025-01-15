package com.brainstation.fantasyfootball.exception;


public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(){
        super();
    }
    public UserAlreadyExistException(String message){
        super(message);
    }
    public UserAlreadyExistException(Throwable cause){
        super(cause);
    }
    public UserAlreadyExistException(String message, Throwable cause){
        super(message, cause);
    }
}

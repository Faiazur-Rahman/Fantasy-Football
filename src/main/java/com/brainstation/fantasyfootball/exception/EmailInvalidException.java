package com.brainstation.fantasyfootball.exception;

/**
 * @author BS987
 * created date: 10/8/2022
 */
public class EmailInvalidException extends RuntimeException{
    public EmailInvalidException(){}
    public EmailInvalidException(String message){
        super(message);
    }
    public EmailInvalidException(Throwable cause){
        super(cause);
    }
    public EmailInvalidException(String message, Throwable cause){
        super(message,cause);
    }
}

package com.brainstation.fantasyfootball.exception;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(){
        super();
    }
    public UsernameNotFoundException(String message){
        super(message);
    }
    public UsernameNotFoundException(Throwable cause){
        super(cause);
    }
    public UsernameNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}


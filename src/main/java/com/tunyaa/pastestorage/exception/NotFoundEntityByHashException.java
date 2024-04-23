
package com.tunyaa.pastestorage.exception;


public class NotFoundEntityByHashException extends RuntimeException{
    public NotFoundEntityByHashException(String s){
        super(s);
        System.out.println("NotFoundEntityByHashException - " + s);
        
    }
}

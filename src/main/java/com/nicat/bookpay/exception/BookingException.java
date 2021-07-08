package com.nicat.bookpay.exception;

public class BookingException extends RuntimeException{
    public BookingException(Integer code,String message){
        super(message);
    }
}

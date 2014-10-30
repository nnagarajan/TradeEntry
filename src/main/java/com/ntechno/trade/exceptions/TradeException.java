package com.ntechno.trade.exceptions;

/**
 * Created by nanagarajan on 8/16/2014.
 */
public class TradeException extends RuntimeException {


    private static final long serialVersionUID = 1L;


    public TradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeException(String message) {
        super(message);
    }
}

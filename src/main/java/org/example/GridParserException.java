package org.example;

public class GridParserException extends Exception {
    
    String message;

    public GridParserException() { }
    
    public GridParserException(String message){
        super(message);
        this.message = message;
    }
}

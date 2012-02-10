package org.example;

public class PathFinderException extends Exception {

    String message;

    public PathFinderException() { }

    public PathFinderException(String message){
        super(message);
        this.message = message;
    }
}

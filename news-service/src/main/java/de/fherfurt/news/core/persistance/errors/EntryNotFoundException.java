package de.fherfurt.news.core.persistance.errors;

public class EntryNotFoundException extends Exception{
    public EntryNotFoundException(String message){
        super(message);
    }
}

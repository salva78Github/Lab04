package it.sella.tdp.lab04.exception;

public class StudenteNotFoundException extends GestioneSegreteriaStudentiException{

	private static final long serialVersionUID = 7585105794842625354L;

	public StudenteNotFoundException(String message){
		super(message);
	}
	
}

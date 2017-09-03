package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.exception.GestioneSegreteriaStudentiException;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model = model;
		
		List<Corso> listaCorsi = model.retrieveListaCorsi();
		Collections.sort(listaCorsi);
		this.comboCorso.getItems().addAll(listaCorsi);
		
	}

	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("");
		txtNome.setText("");
		txtCognome.setText("");
		txtMatricola.setText("");
	}

	
	private int getMatricola(){
		int matricola = -1;
		String matricolaToString = txtMatricola.getText();
		System.out.println("<doCercaNome>" + matricolaToString);
		if(matricolaToString==null || matricolaToString.trim().length()==0){
			txtResult.setText("Inserire una matricola.");
		}
		else{
			try{
				matricola = Integer.valueOf(matricolaToString);
			} catch(NumberFormatException nfe){
				txtResult.setText("La matricola è una stringa numerica.");
			}
		}
		return matricola;
		
	}
	
	@FXML
	void doCercaNome(ActionEvent event) {
		int matricola = getMatricola();
		if(matricola ==-1) {
			return;
		}
		
		try {
			Studente s =model.retrieveStudente(matricola);
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
			txtResult.setText("");
			
			
		} catch (GestioneSegreteriaStudentiException gsse) {
			// TODO Auto-generated catch block
			gsse.printStackTrace();
			txtResult.setText(gsse.getMessage());
		}
		

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		
		Corso corso = comboCorso.getValue();
		System.out.println("<doCercaIscrittiCorso> corso selezionato: " + corso);
		if(corso == null){
			txtResult.setText("Selezionare un corso.");
			return;
		}
		
		try {
			List<Studente> studenti = model.retrieveStudentiIscrittiAUnCorso(corso);
			txtResult.setText("");
			for(Studente s : studenti){
				txtResult.appendText(s.toString4TextArea() + "\n");
			}
			
		} catch (GestioneSegreteriaStudentiException gsse) {
			// TODO Auto-generated catch block
			txtResult.setText(gsse.getMessage());
		}
		

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		int matricola = getMatricola();
		if(matricola ==-1) {
			return;
		}

		try {
			List<Corso> corsi = model.retrieveCorsiACuiEIscrittoUnoStudente(matricola);
			txtResult.setText("");
			for(Corso c : corsi){
				txtResult.appendText(c.toString4TextArea() + "\n");
			}
			
		} catch (GestioneSegreteriaStudentiException gsse) {
			// TODO Auto-generated catch block
			txtResult.setText(gsse.getMessage());
		}
		
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		Corso corso = comboCorso.getValue();
		System.out.println("<doCercaIscrittiCorso> corso selezionato: " + corso);
		if(corso == null){
			txtResult.setText("Selezionare un corso.");
			return;
		}
		
		int matricola = getMatricola();
		if(matricola ==-1) {
			return;
		}

		Studente studente;
		try {
			studente = model.retrieveStudente(matricola);
			model.inscriviStudenteACorso(studente, corso);
			txtResult.setText("Lo studente con matricola " + studente.getMatricola() + " è stato iscritto al corso " + corso.getCodice());
		} catch (GestioneSegreteriaStudentiException gsse) {
			// TODO Auto-generated catch block
			txtResult.setText(gsse.getMessage());
		}
		
		
	}


	
	
	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		
		
		
	}

}

package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.exception.GestioneSegreteriaStudentiException;

public class Model {

	private static CorsoDAO cdao = new CorsoDAO();
	private static StudenteDAO sdao = new StudenteDAO();

	public List<Corso> retrieveListaCorsi() {
		// TODO Auto-generated method stub
		return cdao.getTuttiICorsi();
	}

	public Studente retrieveStudente(int matricola) throws GestioneSegreteriaStudentiException{
		return sdao.retrieveDetailsFromMatricola(matricola);
	}
	
	public List<Studente> retrieveStudentiIscrittiAUnCorso(Corso corso) throws GestioneSegreteriaStudentiException{
		return cdao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> retireveCorsiACuiEIscrittoUnoStudente(int matricola) throws GestioneSegreteriaStudentiException{
		return sdao.retrieveCorsiACuiEIscritto(matricola);
	}

}

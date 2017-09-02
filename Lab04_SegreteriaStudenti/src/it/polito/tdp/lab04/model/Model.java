package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {

	private static CorsoDAO cdao = new CorsoDAO();

	public List<Corso> retrieveListaCorsi() {
		// TODO Auto-generated method stub
		return cdao.getTuttiICorsi();
	}

}

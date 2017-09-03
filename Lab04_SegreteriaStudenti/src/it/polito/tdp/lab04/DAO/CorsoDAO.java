package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.exception.CorsoWithoutStudentsException;
import it.polito.tdp.lab04.exception.GestioneSegreteriaStudentiException;
import it.polito.tdp.lab04.exception.StudenteGiaIscrittoAlCorsoException;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT codins, crediti, nome, pd FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = ConnectDB.getConnection();
			st = conn.prepareStatement(sql);

			rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo Corso alla lista
				Corso c = new Corso(rs.getString("codins"), rs.getString("nome"), rs.getInt("crediti"), rs.getInt("pd"));
				corsi.add(c);
				
			}

			System.out.println("<getTuttiICorsi> Caricati " + corsi.size() + " corsi.");
			return corsi;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		} finally {
			ConnectDB.closeResources(conn , st, rs);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) throws GestioneSegreteriaStudentiException {
		// TODO
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Studente> studenti = new ArrayList<Studente>();
		
		try{
			c = ConnectDB.getConnection();
			
			String query = "SELECT s.matricola, s.nome, s.cognome, s.cds " +
						   "FROM studente s, iscrizione i " +
						   "WHERE s.matricola = i.matricola " +
						   "AND i.codins = ? " +
						   "ORDER BY cognome, nome, matricola ";
			
			ps = c.prepareStatement(query);
			ps.setString(1, corso.getCodice());
			rs = ps.executeQuery();
			
			while(rs.next()){
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				System.out.println(s);
				studenti.add(s);
			}

			if(studenti.isEmpty()){
				throw new CorsoWithoutStudentsException("Per il corso " + corso.getCodice() + " - " + corso.getNome() + " non ci sono studenti iscritti"); 
			}
			
			return studenti;
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			throw new GestioneSegreteriaStudentiException("Errore DB: " + sqle.getMessage());
		
		}finally{
			ConnectDB.closeResources(c, ps, rs);
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public void inscriviStudenteACorso(Studente studente, Corso corso) throws GestioneSegreteriaStudentiException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			c= ConnectDB.getConnection();
			String query = "INSERT " +
						   "INTO iscrizione (matricola, codins) " +
						   "VALUES(?, ?)";
			ps=c.prepareStatement(query);
			ps.setInt(1, studente.getMatricola());	
			ps.setString(2, corso.getCodice());
			ps.execute();
		
		} catch(SQLException sqle){
			if(sqle.getErrorCode()==1062){
				throw new StudenteGiaIscrittoAlCorsoException("Lo studente con matricola " + studente.getMatricola() + " è già iscritto al corso '" + corso.getCodice() + " - " + corso.getNome());
			}
			
			sqle.printStackTrace();
			throw new GestioneSegreteriaStudentiException("Errore DB: " + sqle.getMessage());
			
		} finally{
			ConnectDB.closeResources(c, ps, rs);
		}
		
	}
}

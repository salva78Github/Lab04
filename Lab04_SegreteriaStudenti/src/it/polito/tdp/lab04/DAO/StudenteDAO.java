package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.exception.GestioneSegreteriaStudentiException;
import it.polito.tdp.lab04.exception.StudentDoesNotAttendAnyCourseException;
import it.polito.tdp.lab04.exception.StudenteNotFoundException;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente retrieveDetailsFromMatricola(int matricola) throws GestioneSegreteriaStudentiException{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			c = ConnectDB.getConnection();
			String query = "SELECT s.matricola, s.nome, s.cognome, s.cds " +
						   "FROM studente s " +
						   "WHERE s.matricola = ?";
			
			ps = c.prepareStatement(query);
			ps.setInt(1, matricola);
			
			rs=ps.executeQuery();

			if(rs.next()){
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				System.out.println("<retrieveDetailsFromMatricola> " + s);
				return s;
			}else{
				throw new StudenteNotFoundException("Lo studente con matricola " + matricola + " non esite");
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new GestioneSegreteriaStudentiException("Errore Db: " + sqle.getMessage());

		}finally{
			ConnectDB.closeResources(c, ps, rs);
		}
		
	}

	public List<Corso> retrieveCorsiACuiEIscritto(int matricola) throws GestioneSegreteriaStudentiException{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Corso> corsi = new ArrayList<Corso>();
		
		try{
			c = ConnectDB.getConnection();
			String query = "SELECT c.codins, c.crediti, c.nome, c.pd " +
							"FROM corso c, iscrizione i " +
							"WHERE c.codins = i.codins " +
							"AND i.matricola = ? " +
							"ORDER BY nome, c.codins";
			
			ps = c.prepareStatement(query);
			ps.setInt(1, matricola);
			
			rs=ps.executeQuery();

			while(rs.next()){
				Corso corso = new Corso(rs.getString("codins"), rs.getString("nome"), rs.getInt("crediti"), rs.getInt("pd"));
				System.out.println("<retrieveDetailsFromMatricola> " + corso);
				corsi.add(corso);
				
			}
			
			if(corsi.isEmpty()){
				throw new StudentDoesNotAttendAnyCourseException("Lo studente con matricola " + matricola + " non frequenta corsi."); 
			}
				
			return corsi;
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new GestioneSegreteriaStudentiException("Errore Db: " + sqle.getMessage());

		}finally{
			ConnectDB.closeResources(c, ps, rs);
		}
		
		
		
		
	}
	
	
}

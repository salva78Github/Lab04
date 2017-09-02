package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		return false;
	}
}

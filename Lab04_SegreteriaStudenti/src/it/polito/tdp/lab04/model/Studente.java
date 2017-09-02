package it.polito.tdp.lab04.model;

public class Studente {
	private final int matricola;
	private final String nome;
	private final String cognome;
	private final String corsoDiStudi;
	
	/**
	 * @param matricola
	 * @param nome
	 * @param cognome
	 * @param corsoDiStudi
	 */
	public Studente(int matricola, String nome, String cognome, String corsoDiStudi) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.corsoDiStudi = corsoDiStudi;
	}
	/**
	 * @return the matricola
	 */
	public int getMatricola() {
		return matricola;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @return the corsoDiStudi
	 */
	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome + ", corsoDiStudi="
				+ corsoDiStudi + "]";
	}
	
	public String toString4TextArea() {
		return matricola + "\t \t" + nome + "\t \t" + cognome + "\t \t" + corsoDiStudi;
	}
	
	
}

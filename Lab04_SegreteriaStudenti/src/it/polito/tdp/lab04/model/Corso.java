package it.polito.tdp.lab04.model;

public class Corso implements Comparable<Corso>{
	private final String codice;
	private final String nome;
	private final int crediti;
	private final int periodoDidattico;
	
	/**
	 * @param codice
	 * @param nome
	 * @param crediti
	 * @param periodoDidattico
	 */
	public Corso(String codice, String nome, int crediti, int periodoDidattico) {
		this.codice = codice;
		this.nome = nome;
		this.crediti = crediti;
		this.periodoDidattico = periodoDidattico;
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the crediti
	 */
	public int getCrediti() {
		return crediti;
	}

	/**
	 * @return the periodoDidattico
	 */
	public int getPeriodoDidattico() {
		return periodoDidattico;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getCodice() + " - " + getNome() + " - crediti: " + getCrediti() + " - periodo: " + getPeriodoDidattico();
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
		Corso other = (Corso) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	@Override
	public int compareTo(Corso o) {
		// TODO Auto-generated method stub
		return getNome().compareTo(o.getNome());
	}

	public String toString4TextArea() {
		// TODO Auto-generated method stub
		return getCodice() + " \t \t " + getCrediti() + " \t \t " + getNome() + " \t \t " + getPeriodoDidattico();
	}


	
}

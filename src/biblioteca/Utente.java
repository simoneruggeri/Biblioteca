package biblioteca;

public class Utente {

	private String username;
	private ElencoLibri libriUtente;
	
	public Utente(String nome) {
		username = nome;
		libriUtente = new ElencoLibri();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ElencoLibri getLibriUtente() {
		return libriUtente;
	}
	
	public void prenotaUtente(Libro libro) {
		libriUtente.aggiungiTitolo(libro);
	}
	
	public void restituisciUtente(Libro libro) {
		libriUtente.rimuoviTitolo(libro);
	}
}

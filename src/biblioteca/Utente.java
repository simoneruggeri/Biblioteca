package biblioteca;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Utente {

	private String username;
	private ArrayList<Prenotazione> prenotazioni;
	
	public Utente(String nome) {
		username = nome;
		prenotazioni = new ArrayList<Prenotazione>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	public void prenotaUtente(Libro libro) {
		prenotazioni.add(new Prenotazione(libro));
		libro.prenota();
	}
	
	public void restituisciUtente(Libro libro) {
		Prenotazione pren = null;
		for (Prenotazione booking : prenotazioni)
			if (booking.getLibroPrenotato().equals(libro)) {
				pren = booking;
				pren.getLibroPrenotato().restituisci();
			}
		if (pren!=null)
			prenotazioni.remove(pren);
	}
	
	public boolean presente(Libro libro) {
		for (Prenotazione booking : prenotazioni)
			if (booking.getLibroPrenotato().equals(libro))
				return true;
		return false;
	}
	
	public ElencoLibri getLibriUtente(){
		ElencoLibri elenco = new ElencoLibri();
		for (Prenotazione booking : prenotazioni)
			elenco.aggiungiTitolo(booking.getLibroPrenotato());
		return elenco;
	}
	
	public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

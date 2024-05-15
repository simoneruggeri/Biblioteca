package biblioteca;

import java.time.LocalDate;

import com.google.gson.Gson;

public class Prenotazione {

	private Libro libroPrenotato;
	private Data scadenza;
	
	public Prenotazione(Libro libro) {
		this.libroPrenotato = libro;
		this.scadenza = new Data(LocalDate.now().getDayOfMonth(),LocalDate.now().plusMonths(1).getMonthValue(),LocalDate.now().getYear());
	}

	public Libro getLibroPrenotato() {
		return libroPrenotato;
	}

	public void setLibroPrenotato(Libro libroPrenotato) {
		this.libroPrenotato = libroPrenotato;
	}

	public Data getScadenza() {
		return scadenza;
	}
	
	/*public int getGiorni() {
		return 
	}*/
	
	public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}

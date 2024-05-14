package biblioteca;

import java.time.LocalDate;

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

	public Data getScadenza() {
		return scadenza;
	}
	
	/*public int getGiorni() {
		return 
	}*/
	
	
}

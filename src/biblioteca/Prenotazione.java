package biblioteca;

import java.time.LocalDate;

public class Prenotazione {

	private Libro libroPrenotato;
	private LocalDate scadenza;
	
	public Prenotazione(Libro libro) {
		this.libroPrenotato = libro;
		this.scadenza = LocalDate.now().plusMonths(1);
	}

	public Libro getLibroPrenotato() {
		return libroPrenotato;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}
	
	
}

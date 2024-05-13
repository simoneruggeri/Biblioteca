package biblioteca;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Libro {

	private String titolo;
	private String autore;
	private String genere;
	private int anno;
	private boolean stato;
	private boolean ricercato;
	
	public Libro(String titolo, String autore, String genere, int anno) {
		this.titolo = titolo;
		this.autore = autore;
		this.genere = genere;
		this.anno = anno;
		this.stato = true;
		this.ricercato = true;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	public boolean getStato() {
		return stato;
	}
	
	public boolean isRicercato() {
		return ricercato;
	}

	public void setRicercato(boolean ricercato) {
		this.ricercato = ricercato;
	}

	public void prenota() {
		stato = false;
	}
	
	public void restituisci() {
		stato = true;
	}

	public String toStringGenere() {
        return "Genere: " + genere;
    }

    // Metodo toString() per il titolo
    public String toStringTitolo() {
        return "Titolo: " + titolo;
    }

    // Metodo toString() per l'autore
    public String toStringAutore() {
        return "Autore: " + autore;
    }

    // Metodo toString() per l'anno
    public String toStringAnno() {
        return "Anno: " + anno;
    }
    
    public String toStringStato() {
    	if (stato)
    		return "Stato: Disponibile";
    	return "Stato: Non Disponibile";
    }
    
    public JPanel toPanel() {
    	JPanel panel = new JPanel(new GridLayout(0, 1));
    	JLabel etichettaTitolo = new JLabel(toStringTitolo());
    	panel.add(new JLabel("--------------------------"));
    	etichettaTitolo.setToolTipText(titolo);
    	panel.add(etichettaTitolo);
    	panel.add(new JLabel(toStringAutore()));
    	panel.add(new JLabel(toStringGenere()));
    	panel.add(new JLabel(toStringAnno()));
    	return panel;
    }
    
	@Override
	public String toString() {
		return "Titolo: " + titolo + "Autore:" + autore + "\nGenere: " + genere + "\nAnno: " + anno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anno, autore, genere, titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return anno == other.anno && Objects.equals(autore, other.autore) && Objects.equals(genere, other.genere)
				&& Objects.equals(titolo, other.titolo);
	}

	
	
	
}


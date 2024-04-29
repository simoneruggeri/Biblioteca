package biblioteca;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Libro {

	private String titolo;
	private String autore;
	private String genere;
	private int anno;
	private boolean stato;
	
	public Libro(String titolo, String autore, String genere, int anno) {
		this.titolo = titolo;
		this.autore = autore;
		this.genere = genere;
		this.anno = anno;
		this.stato = true;
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
    	panel.add(new JLabel("--------------------------"));
    	panel.add(new JLabel(toStringTitolo()));
    	panel.add(new JLabel(toStringAutore()));
    	panel.add(new JLabel(toStringGenere()));
    	panel.add(new JLabel(toStringAnno()));
    	return panel;
    }
    
	@Override
	public String toString() {
		return "Titolo: " + titolo + "Autore:" + autore + "\nGenere: " + genere + "\nAnno: " + anno;
	}

	
	
	
}


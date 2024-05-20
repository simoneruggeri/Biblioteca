package biblioteca;


import java.awt.GridLayout;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.google.gson.Gson;

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

	public String getAutore() {
		return autore;
	}

	public String getGenere() {
		return genere;
	}

	public int getAnno() {
		return anno;
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
    
    public String toStringTitolo() {
        return "Titolo: " + titolo;
    }

    public String toStringAutore() {
        return "Autore: " + autore;
    }

    public String toStringAnno() {
        return "Anno: " + anno;
    }

    public JPanel toPanel() {
    	JPanel panel = new JPanel(new GridLayout(0, 1));
    	JLabel etichettaTitolo = new JLabel(toStringTitolo());
    	panel.add(new JLabel(Impostazioni.SEPARATORE));
    	etichettaTitolo.setToolTipText(titolo);
    	panel.add(etichettaTitolo);
    	panel.add(new JLabel(toStringAutore()));
    	panel.add(new JLabel(toStringGenere()));
    	panel.add(new JLabel(toStringAnno()));
    	return panel;
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
	
	public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
	
	
}


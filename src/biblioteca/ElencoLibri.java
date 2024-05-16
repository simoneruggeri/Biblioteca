package biblioteca;

import java.awt.*;
import java.util.*;

import javax.swing.*;



public class ElencoLibri {
	
	private ArrayList<Libro> elenco;
    
    public ElencoLibri() {
    	elenco = new ArrayList<Libro>();
    }
    
    public void aggiungiTitolo(Libro libro) {
    	elenco.add(libro);
    }
    
    public void rimuoviTitolo(Libro libro) {
    	elenco.remove(libro);
    }
    
    public void ricerca(String cerca) {
    	if(cerca != null) {
    		boolean titolo = false;
        	boolean autore = false;
        	boolean genere = false;
        	for (Libro libro : elenco) {
        		if (libro.getTitolo().toLowerCase().contains(cerca))
        			titolo = true;
        		else titolo = false;
        		if (libro.getAutore().toLowerCase().contains(cerca))
        			autore = true;
        		else autore = false;
        		if (libro.getGenere().toLowerCase().contains(cerca))
        			genere = true;
        		else genere = false;
        		libro.setRicercato(titolo||genere||autore);
        	}
    	}else {
    		for (Libro libro : elenco)
    			libro.setRicercato(true);
    	}	
    }
    
    public void ordinaPerTitolo() {
    	Collections.sort(this.elenco, Comparator.comparing(Libro::getTitolo));
    }
    
    public void ordinaPerAutore() {
    	Collections.sort(this.elenco, Comparator.comparing(Libro::getAutore));
    }
    
    public void ordinaPerGenere() {
    	Collections.sort(this.elenco, Comparator.comparing(Libro::getGenere));
    }
    
    public void ordinaPerAnno() {
    	Collections.sort(this.elenco, Comparator.comparingInt(Libro::getAnno));
    }

	public ArrayList<Libro> getElenco() {
		return elenco;
	}

	@Override
	public String toString() {
		String s = "";
		for (Libro libro : elenco)
			s = s + "\n" + libro.getTitolo();
		return s;
	}
	
	public boolean presente(Libro check) {
		for (Libro libro : elenco)
			if (libro.equals(check))
				return true;
		return false;
	}
	
}
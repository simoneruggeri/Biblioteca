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
    
    
    /*public void perTitolo(String titolo) {
    	for (Libro libro : elenco)
    		if (libro.getTitolo().contains(titolo))
    			libro.setRicercato(true);
    		else libro.setRicercato(false);
    }
    
    public void perAutore(String autore) {
    	for (Libro libro : elenco)
    		if (libro.getAutore().contains(autore))
    			libro.setRicercato(true);
    		else libro.setRicercato(false);
    }
    
    public void perGenere(String genere) {
    	for (Libro libro : elenco)
    		if (libro.getGenere().contains(genere))
    			libro.setRicercato(true);
    		else libro.setRicercato(false);
    }*/
    
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
	
	
    
    
    //setPreferredSize(new Dimension(300, 200));
}
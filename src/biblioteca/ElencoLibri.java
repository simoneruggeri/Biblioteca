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
    
    public ArrayList<Libro> perTitolo(String titolo) {
    	ArrayList<Libro> listaPerTitolo = new ArrayList<Libro>();
    	for (Libro libro : elenco)
    		if (libro.getTitolo().contains(titolo))
    			listaPerTitolo.add(libro);
    	return listaPerTitolo;
    }
    
    public ArrayList<Libro> perAutore(String autore) {
    	ArrayList<Libro> listaPerAutore = new ArrayList<Libro>();
    	for (Libro libro : elenco)
    		if (libro.getAutore().contains(autore))
    			listaPerAutore.add(libro);
    	return listaPerAutore;
    }
    
    public ArrayList<Libro> perGenere(String genere) {
    	ArrayList<Libro> listaPerGenere = new ArrayList<Libro>();
    	for (Libro libro : elenco)
    		if (libro.getGenere().contains(genere))
    			listaPerGenere.add(libro);
    	return listaPerGenere;
    }

	public ArrayList<Libro> getElenco() {
		return elenco;
	}
    
    
    //setPreferredSize(new Dimension(300, 200));
}
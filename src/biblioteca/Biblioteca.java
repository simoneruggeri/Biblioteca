package biblioteca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Biblioteca{
	
	private ElencoLibri listaLibri;
	private ArrayList<Utente> listaUtenti;	
	
	public Biblioteca() {
		listaLibri = new ElencoLibri();
		listaUtenti = new ArrayList<Utente>();
	}
	
	public void aggiungiTitolo(Libro libro) {
		listaLibri.aggiungiTitolo(libro);
	}
	
	public void rimuoviTitolo(Libro libro) {
		listaLibri.rimuoviTitolo(libro);
	}
	
	public void ricerca(String s) {
		listaLibri.ricerca(s);
	}
	
	public void aggiungiUtente(Utente u) {
		listaUtenti.add(u);
	}

	public ElencoLibri getListaLibri() {
		return listaLibri;
	}

	public ArrayList<Utente> getListaUtenti() {
		return listaUtenti;
	}
	
	
}

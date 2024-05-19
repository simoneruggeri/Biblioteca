package biblioteca;

import java.util.*;
import java.util.ArrayList;

import com.google.gson.Gson;


public class Biblioteca{
	
	private ElencoLibri listaLibri;
	private ArrayList<Utente> listaUtenti;	
	
	public Biblioteca() {
		listaLibri = new ElencoLibri();
		listaUtenti = new ArrayList<Utente>();
	}
	
	public void aggiungiTitolo(Libro libro) {
		if (!listaLibri.presente(libro))
			listaLibri.aggiungiTitolo(libro);
	}
	
	public void rimuoviTitolo(Libro libro) {
		if (listaLibri.presente(libro))
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
	
	public String saveToJson() {
        List<String> libriJson = new ArrayList<>();
        for (Libro libro : listaLibri.getElenco()) {
            libriJson.add(libro.toJson());
        }
        List<String> utentiJson = new ArrayList<>();
        for (Utente utente : listaUtenti) {
            utentiJson.add(utente.toJson());
        }
        Gson gson = new Gson();
        return gson.toJson(List.of(libriJson, utentiJson));
    }
	
	public void loadFromJson(String json) {
        Gson gson = new Gson();
        List<List<String>> bibliotecaJson = gson.fromJson(json, List.class);
        List<String> libriJson = bibliotecaJson.get(0);
        List<String> utentiJson = bibliotecaJson.get(1);
        
        for (String libroJson : libriJson) {
            Libro libro = gson.fromJson(libroJson, Libro.class);
            listaLibri.aggiungiTitolo(libro);
        }
        
        for (String utenteJson : utentiJson) {
            Utente utente = gson.fromJson(utenteJson, Utente.class);
            for (Prenotazione prenotazione : utente.getPrenotazioni())
            	for (Libro libro : listaLibri.getElenco())
            		if (prenotazione.getLibroPrenotato().equals(libro))
            			prenotazione.setLibroPrenotato(libro);
            listaUtenti.add(utente);
        }
    }

	public Utente utentePresente(String username) {
		for (Utente utente : listaUtenti) {
            if (utente.getUsername().equals(username))
                return utente;
            }
        return null;
	}
		
	public void ordinaPerTitolo() {
		listaLibri.ordinaPerTitolo();
	}
	
	public void ordinaPerGenere() {
		listaLibri.ordinaPerGenere();
	}
		
	public void ordinaPerAutore() {
		listaLibri.ordinaPerAutore();
	}
	
	public void ordinaPerAnno() {
		listaLibri.ordinaPerAnno();
	}
	
}

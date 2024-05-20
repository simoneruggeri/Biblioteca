package GUI;

import java.awt.*;
import biblioteca.*;
import settings.Impostazioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BibliotecaMenu extends JPanel{

	public BibliotecaMenu(PannelloLibri pannello) {
	
		
		setLayout(new GridLayout(0,1));
		
		JButton visualizzaButton = new JButton(Impostazioni.VISUALIZZA_CATALOGO);
		visualizzaButton.setFocusable(false);
		JButton utentiButton = new JButton(Impostazioni.VISUALIZZA_UTENTI);
		utentiButton.setFocusable(false);
		JButton aggiungiButton = new JButton(Impostazioni.AGGIUNGI_TITOLO);
		aggiungiButton.setFocusable(false);
		JButton rimuoviButton = new JButton(Impostazioni.RIMUOVI_TITOLO);
		rimuoviButton.setFocusable(false);
		JButton prenotaButton = new JButton(Impostazioni.PRENOTA_LIBRO);
		prenotaButton.setFocusable(false);
		JButton restituisciButton = new JButton(Impostazioni.RESTITUISCI_LIBRO);
		restituisciButton.setFocusable(false);
		JButton esciButton = new JButton(Impostazioni.ESCI);
		esciButton.setFocusable(false);
		
		add(visualizzaButton);
		add(utentiButton);
		add(aggiungiButton);
		add(rimuoviButton);
		add(prenotaButton);
		add(restituisciButton);
		add(esciButton);
		
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.salva();
				System.exit(0);
				}
	    });
		
		prenotaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.prenota();
			}
	    });
		
		restituisciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.restituisci();
			}
	    });
		
		visualizzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.mostra();
			}
	    });
		
		aggiungiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.aggiungi();
			}
	    });
		
		rimuoviButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.rimuovi();
			}
	    });
		
		utentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pannello.utenti();
			}
	    });
		
		
	
	}
}

package biblioteca;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BibliotecaMenu extends JPanel{

	private PannelloLibri pannello;
	public BibliotecaMenu(PannelloLibri pannello) {
	
		this.pannello = pannello;
		setLayout(new GridLayout(0,1));
		
		JButton visualizzaButton = new JButton("Visualizza Catalogo");
		visualizzaButton.setFocusable(false);
		JButton utentiButton = new JButton("Visualizza Utenti");
		utentiButton.setFocusable(false);
		JButton aggiungiButton = new JButton("Aggiungi Titolo");
		aggiungiButton.setFocusable(false);
		JButton rimuoviButton = new JButton("Rimuovi Titolo");
		rimuoviButton.setFocusable(false);
		JButton prenotaButton = new JButton("Prenota Libro");
		prenotaButton.setFocusable(false);
		JButton restituisciButton = new JButton("Restituisci Libro");
		restituisciButton.setFocusable(false);
		JButton esciButton = new JButton("Esci");
		esciButton.setFocusable(false);
		
		add(visualizzaButton);
		add(utentiButton);
		add(aggiungiButton);
		add(rimuoviButton);
		add(prenotaButton);
		add(restituisciButton);
		add(esciButton);
		
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){System.exit(0);}
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

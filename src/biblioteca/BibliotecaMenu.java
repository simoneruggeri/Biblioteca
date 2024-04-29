package biblioteca;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BibliotecaMenu extends JPanel{

	public BibliotecaMenu(PannelloLibri pannello) {
	
		setLayout(new GridLayout(0,1));
		
		JButton ricercaButton = new JButton("Ricerca Titolo");
		JButton aggiungiButton = new JButton("Aggiungi Titolo");
		JButton rimuoviButton = new JButton("Rimuovi Titolo");
		JButton prenotaButton = new JButton("Prenota Libro");
		JButton restituisciButton = new JButton("Restituisci Libro");
		JButton esciButton = new JButton("Esci");
		
		add(ricercaButton);
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
	
	}
}

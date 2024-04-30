package biblioteca;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;

public class PannelloLibri extends JPanel {
    
	ArrayList<Libro> elenco;
    public PannelloLibri(ArrayList<Libro> elenco) {
    	this.elenco = elenco;
    	this.mostra();    	
        
    }
    
    public void prenota() {
    	removeAll();
    	revalidate();
    	repaint();
    	setLayout(new GridLayout(0, 2));
    	int pino = 0;
        for (Libro libro : elenco) {
        	if (libro.getStato()) {
        		pino++;
        		JPanel panLib = libro.toPanel();
        		panLib.setPreferredSize(new Dimension(10,80));
        		add(panLib);
        		JButton prenota = new JButton("Prenota");
        		prenota.setFocusable(false);
        		//prenota.setMaximumSize(new Dimension(10,10));
        		//prenota.setPreferredSize(new Dimension(10,10));
        		prenota.addActionListener(e -> {
        	        libro.prenota();
        	        prenota();
        	    });
        		JPanel pan = new JPanel();
            	pan.add(prenota);
            	//pan.setPreferredSize(new Dimension(10,10));
            	add(pan);
        	}
        }
        if (pino<5)
        	for (int i=0; i<6-pino; i++) {
        		JPanel vuoto = new JPanel();
        		vuoto.setPreferredSize(new Dimension(10,80));
        		add(vuoto);
        		add(vuoto);
        	}
    }
    
    public void restituisci() {
    	removeAll();
    	revalidate();
    	repaint();
    	setLayout(new GridLayout(0, 2));
        for (Libro libro : elenco) {
        	if (!libro.getStato()) {
        		add(libro.toPanel());
        		JButton restituisci = new JButton("Restituisci");
        		restituisci.setFocusable(false);
        		restituisci.addActionListener(e -> {
        	        libro.restituisci();
        	        restituisci();
        	    });
            	add(restituisci);;
        	}
        } 
    }
    
    public void mostra() {
    	setLayout(new GridLayout(0, 2));
        for (Libro libro : elenco) {
        	add(libro.toPanel());
        	if(libro.getStato()) {
        		JLabel discoverde = new JLabel(new ImageIcon(getClass().getResource("green.png")));
        		add(discoverde);
        	} 
        	else {
        		JLabel discorosso = new JLabel(new ImageIcon(getClass().getResource("red.png")));
        		add(discorosso);
        	}
        	} 
    }
}
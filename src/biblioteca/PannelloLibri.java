package biblioteca;

import java.awt.*;
import java.util.*;

import javax.swing.*;



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
        for (Libro libro : elenco) {
        	if (libro.getStato()) {
        		add(libro.toPanel());
        		JButton prenota = new JButton("Prenota");
        		prenota.addActionListener(e -> {
        	        libro.prenota();
        	        prenota();
        	    });
            	add(prenota);
        	}
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
        		restituisci.addActionListener(e -> {
        	        libro.restituisci();
        	        restituisci();
        	    });
            	add(restituisci);;
        	}
        } 
    }
    
    public void mostra() {
    	setLayout(new GridLayout(0, 1));
    	
        for (Libro libro : elenco) {
        	add(libro.toPanel());
        } 
    }
    
    //setPreferredSize(new Dimension(300, 200));

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> new ScrollablePageExample());
   // }
}
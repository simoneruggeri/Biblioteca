package biblioteca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import java.awt.*;

public class PannelloLibri extends JPanel {
    
	private ElencoLibri elenco;
	private String ricerca;
	private char mode;
	
    public PannelloLibri(ElencoLibri elenco) {
    	this.elenco = elenco;
    	this.mostra();    	
        
    }
    
    public void prenota() {
    	removeAll();
    	revalidate();
    	repaint();
    	mode = 'p';
    	setLayout(new GridLayout(0, 2));
    	int disp = 0;
    	elenco.ricerca(ricerca);
        for (Libro libro : elenco.getElenco()) {
        	if (libro.getStato() && libro.isRicercato()) {
        		disp++;
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
        if (disp<5)
        	for (int i=0; i<6-disp; i++) {
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
    	mode = 'r';
    	setLayout(new GridLayout(0, 2));
    	int disp = 0;
    	elenco.ricerca(ricerca);
        for (Libro libro : elenco.getElenco()) {
        	if (!libro.getStato() && libro.isRicercato()) {
        		disp++;
        		JPanel panLib = libro.toPanel();
        		panLib.setPreferredSize(new Dimension(10,80));
        		add(panLib);
        		JButton restituisci = new JButton("Restituisci");
        		restituisci.setFocusable(false);
        		//prenota.setMaximumSize(new Dimension(10,10));
        		//prenota.setPreferredSize(new Dimension(10,10));
        		restituisci.addActionListener(e -> {
        	        libro.restituisci();
        	        restituisci();
        	    });
        		JPanel pan = new JPanel();
            	pan.add(restituisci);
            	//pan.setPreferredSize(new Dimension(10,10));
            	add(pan);
        	}
        }
        if (disp<5)
        	for (int i=0; i<6-disp; i++) {
        		JPanel vuoto = new JPanel();
        		vuoto.setPreferredSize(new Dimension(10,80));
        		add(vuoto);
        		add(vuoto);
        	} 
    }
    
    public void mostra() {
    	removeAll();
    	revalidate();
    	repaint();
    	mode = 'm';
    	setLayout(new GridLayout(0, 2));
    	elenco.ricerca(ricerca);
    	int disp = 0;
        for (Libro libro : elenco.getElenco()) {
        	if(libro.isRicercato()) {
        		add(libro.toPanel());
        		disp++;
        		if(libro.getStato()) {
            		JLabel discoverde = new JLabel(new ImageIcon(getClass().getResource("green.png")));
            		discoverde.setToolTipText("Disponibile");
            		add(discoverde);
            	} 
            	else {
            		JLabel discorosso = new JLabel(new ImageIcon(getClass().getResource("red.png")));
            		discorosso.setToolTipText("Non Disponibile");
            		add(discorosso);
            	}
        	}
        } 
        if (disp<5)
        	for (int i=0; i<6-disp; i++) {
        		JPanel vuoto = new JPanel();
        		vuoto.setPreferredSize(new Dimension(10,80));
        		add(vuoto);
        		add(vuoto);
        	}
    }
    
    public void ricerca(String cerca) {
    	this.ricerca = cerca.toLowerCase();
    	switch(mode) {
	    	case 'm':
	    		mostra();
	    		break;
	    	case 'r':
	    		restituisci();
	    		break;
	    	case 'p':
	    		prenota();
	    		break;
	    	case 'a':
	    		break;
    	}
    }
    
    public void aggiungi() {
    	removeAll();
    	revalidate();
    	repaint();
    	mode = 'a';
    	String[] anni = new String[1025];
    	for (int i = 2024; i > 999; i--) {
    	    anni[2024-i] = Integer.toString(i);
    	}
    	setLayout(new GridLayout(0, 2));
    	add(new JLabel("Titolo:"));
    	JTextField titolo = new JTextField();
    	JPanel tpan = new JPanel();
    	titolo.setPreferredSize(new Dimension(206, 25));
    	tpan.add(titolo);
    	add(tpan);
    	add(new JLabel("Autore:"));
    	JTextField autore = new JTextField();
    	JPanel apan = new JPanel();
    	autore.setPreferredSize(new Dimension(206, 25));
    	apan.add(autore);
    	add(apan);
    	add(new JLabel("Genere:"));
    	JTextField genere = new JTextField();
    	JPanel gpan = new JPanel();
    	genere.setPreferredSize(new Dimension(206, 25));
    	gpan.add(genere);
    	add(gpan);
    	add(new JLabel("Anno:"));
    	JComboBox<String> combo = new JComboBox<>(anni);
    	//combo.setEditable(true);
    	JPanel cpan = new JPanel();
    	combo.setPreferredSize(new Dimension(206, 25));
    	cpan.add(combo);
    	add(cpan);
    	add(new JPanel());
    	JButton agg = new JButton("Aggiungi");
    	JPanel aggpan = new JPanel();
    	//agg.setPreferredSize(new Dimension(206, 25));
    	aggpan.add(agg);
    	add(aggpan);
    	agg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				elenco.aggiungiTitolo(new Libro(titolo.getText(), autore.getText(), genere.getText(), Integer.parseInt((String) combo.getSelectedItem())));
				mostra();
			}
	    });
    	add(new JPanel());
    	add(new JPanel());
    	add(new JPanel());
    	add(new JPanel());
    	add(new JPanel());
    	
    }
}
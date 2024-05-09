package biblioteca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PannelloLibri extends JPanel {
    
	private ElencoLibri elenco;
	private ArrayList<Utente> utenti;
	private String ricerca;
	private char mode;
	int sel=0;
	String[] tipi = {"titolo","autore","genere","anno"};
	
    public PannelloLibri(ElencoLibri elenco, ArrayList<Utente> utenti) {
    	this.elenco = elenco;
    	this.utenti = utenti;
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

        		
        		prenota.addActionListener(e -> {
        			removeAll();
        	    	revalidate();
        	    	repaint();
        	    	setLayout(new GridLayout(0, 2));
        	    	JPanel labelPanel = new JPanel();
        	    	JLabel eti = new JLabel("username:");
        	    	eti.setPreferredSize(new Dimension(206, 25));
        	    	labelPanel.add(eti);
        	    	add(labelPanel);
        	    	JComboBox<String> combo = new JComboBox<>();
        	    	combo.addItem("------------------------------------------");
        	    	for (Utente utente : utenti) {
        	            combo.addItem(utente.getUsername());
        	        }
        	    	JPanel pan = new JPanel();
        	    	combo.setPreferredSize(new Dimension(206, 25));
        	    	combo.addActionListener(new ActionListener() {
        	    		public void actionPerformed(ActionEvent e) {
        	    			String usernameSelezionato = (String) combo.getSelectedItem();
        	    			
        	    			Utente utenteSelezionato = null;
        	    			for (Utente utente : utenti) {
        	                    if (utente.getUsername().equals(usernameSelezionato)) {
        	                        utenteSelezionato = utente;
        	                        break;
        	                    }
        	                }
        	    			if (utenteSelezionato != null) {
     	                    	utenteSelezionato.prenotaUtente(libro);
         	                    libro.prenota();
         	                    prenota();
     	                    }
                	        
        	    		}
        	    	});
        	    	pan.add(combo);
        	    	add(pan);
        	        
        	    });
        		JPanel pan = new JPanel();
            	pan.add(prenota);
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
    	
    	
    	add(new JLabel("Ordina per:"));
    	
    	String[] sorted = new String[4];
    	for(int i = 0; i<4; i++) {
    		sorted[i] = tipi[(sel+i)%4];
    	}
    	
    	
    	JComboBox<String> combo1 = new JComboBox<>(sorted);
    	combo1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			switch ((String) combo1.getSelectedItem()) {
    			case "titolo":
    				elenco.ordinaPerTitolo();
    				sel=0;
    				mostra();
    				break;
    			case "autore":
    				elenco.ordinaPerAutore();
    				sel=1;
    				mostra();
    				break;
    			case "genere":
    				elenco.ordinaPerGenere();
    				sel=2;
    				mostra();
    				break;
    			case "anno":
    				elenco.ordinaPerAnno();
    				sel=3;
    				mostra();
    				break;
    			}
    		}
    	});

    	JPanel buttonPanel = new JPanel(new GridLayout(0,1));
    	buttonPanel.add(new JLabel());
    	buttonPanel.add(combo1);
    	buttonPanel.add(new JLabel());
    	add(buttonPanel);
    	//add(combo1);
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
	    	case 'c':
	    		rimuovi();
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
    	for (int i=0; i<5; i++)
    		add(new JPanel());
    	
    }
    
    public void rimuovi() {
    	removeAll();
    	revalidate();
    	repaint();
    	mode = 'c';
    	setLayout(new GridLayout(0, 2));
    	int disp = 0;
    	elenco.ricerca(ricerca);
        for (Libro libro : elenco.getElenco()) {
        	if (libro.getStato() && libro.isRicercato()) {
        		disp++;
        		JPanel panLib = libro.toPanel();
        		panLib.setPreferredSize(new Dimension(10,80));
        		add(panLib);
        		JButton rimuovi = new JButton("Rimuovi");
        		rimuovi.setFocusable(false);
        		//prenota.setPreferredSize(new Dimension(10,10));
        		rimuovi.addActionListener(e -> {
        	        elenco.rimuoviTitolo(libro);
        	        rimuovi();
        	    });
        		JPanel pan = new JPanel();
            	pan.add(rimuovi);
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
    
    public void utenti() {
    	removeAll();
    	revalidate();
    	repaint();
    	setLayout(new FlowLayout());
    	Object[][] data = {
                {"John", 25},
                {"Alice", 30},
                {"Bob", 35}
        };
    	Object dati [][] = new Object[utenti.size()][2];
    	int i = 0;
    	for (Utente utente : utenti) {
    		dati[i][0] = utente.getUsername();
    	    dati[i][1] = utente.getLibriUtente().toString();
    	    i++;
    	}
    	String[] columnNames = {"username", "Libri"};
    	JTable tab = new JTable(new DefaultTableModel(dati, columnNames));
    	JScrollPane sp = new JScrollPane(tab);
        add(sp);
    	//add(tab);
    	
    }
}
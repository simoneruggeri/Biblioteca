package biblioteca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PannelloLibri extends JPanel {
    
	private Biblioteca biblioteca;
	private String ricerca;
	private char mode;
	int sel=0;
	String[] tipi = {"titolo","autore","genere","anno"};
	
    public PannelloLibri(Biblioteca biblioteca) {
    	this.biblioteca = biblioteca;
    	this.mostra();
    }
    
    public void prenota() {
    	removeAll();
    	revalidate();
    	repaint();
    	mode = 'p';
    	setLayout(new GridLayout(0, 2));
    	int disp = 0;
    	biblioteca.ricerca(ricerca);
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
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
        	    	for (Utente utente : biblioteca.getListaUtenti()) {
        	            combo.addItem(utente.getUsername());
        	        }
        	    	JPanel pan = new JPanel();
        	    	combo.setPreferredSize(new Dimension(206, 25));
        	    	combo.addActionListener(new ActionListener() {
        	    		public void actionPerformed(ActionEvent e) {
        	    			String usernameSelezionato = (String) combo.getSelectedItem();
        	    			
        	    			Utente utenteSelezionato = null;
        	    			for (Utente utente : biblioteca.getListaUtenti()) {
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
    	biblioteca.ricerca(ricerca);
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
        	if (!libro.getStato() && libro.isRicercato()) {
        		disp++;
        		JPanel panLib = libro.toPanel();
        		panLib.setPreferredSize(new Dimension(10,80));
        		add(panLib);
        		JButton restituisci = new JButton("Restituisci");
        		restituisci.setFocusable(false);
        		String rest = "";
        		for (Utente utente : biblioteca.getListaUtenti())
        			if (utente.presente(libro))
        				rest = utente.getUsername();
        		restituisci.setToolTipText("In prestito a: " + rest);
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
    	biblioteca.ricerca(ricerca);
    	
    	
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
    				biblioteca.getListaLibri().ordinaPerTitolo();
    				sel=0;
    				mostra();
    				break;
    			case "autore":
    				biblioteca.getListaLibri().ordinaPerAutore();
    				sel=1;
    				mostra();
    				break;
    			case "genere":
    				biblioteca.getListaLibri().ordinaPerGenere();
    				sel=2;
    				mostra();
    				break;
    			case "anno":
    				biblioteca.getListaLibri().ordinaPerAnno();
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
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
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
            		String redTool = "";
            		for (Utente utente : biblioteca.getListaUtenti())
            			if (utente.presente(libro))
            				redTool = utente.getUsername();
            		discorosso.setToolTipText("In prestito a: " + redTool);
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
	    	default:
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
				biblioteca.aggiungiTitolo(new Libro(titolo.getText(), autore.getText(), genere.getText(), Integer.parseInt((String) combo.getSelectedItem())));
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
    	biblioteca.ricerca(ricerca);
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
        	if (libro.getStato() && libro.isRicercato()) {
        		disp++;
        		JPanel panLib = libro.toPanel();
        		panLib.setPreferredSize(new Dimension(10,80));
        		add(panLib);
        		JButton rimuovi = new JButton("Rimuovi");
        		rimuovi.setFocusable(false);
        		//prenota.setPreferredSize(new Dimension(10,10));
        		rimuovi.addActionListener(e -> {
        			biblioteca.rimuoviTitolo(libro);
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
    	mode = 'u';
    	setLayout(new FlowLayout());
    	int nlibri = 0;
    	for (Utente utente : biblioteca.getListaUtenti()) {
    		nlibri++;
    		if (utente.getLibriUtente().getElenco().size()!=0)
    			nlibri += utente.getLibriUtente().getElenco().size()-1;
    	}
    	Object dati [][] = new Object[nlibri][2];
    	int i = 0;
    	for (Utente utente : biblioteca.getListaUtenti()) {
    		dati[i][0] = utente.getUsername();
    		if (utente.getLibriUtente().getElenco().size()!=0)
	    		for (Libro libro : utente.getLibriUtente().getElenco()) {
	    			dati[i][1] = libro.getTitolo();
	    			i++;
	    		}
    		else i++;
    	}
    	String[] columnNames = {"username", "Libri"};
    	JTable tab = new JTable(new DefaultTableModel(dati, columnNames));
    	tab.setPreferredScrollableViewportSize(new Dimension(410,nlibri*16));
    	JScrollPane sp = new JScrollPane(tab);
    	setLayout(new BorderLayout());
    	//setLayout(new GridLayout(0,1));
    	JButton addUser = new JButton("Nuovo Utente");
    	addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				nuovoUtente();
			}
	    });
    	JPanel addUserPanel = new JPanel();
    	addUserPanel.add(addUser);
    	add(addUserPanel, BorderLayout.NORTH);
        add(sp,BorderLayout.CENTER);
    	//add(tab);
    }
    
    public void nuovoUtente() {
    	removeAll();
    	revalidate();
    	repaint();
    	mode = 'n';
    	setLayout(new GridLayout(0, 2));
    	add(new JLabel("Username:"));
    	JTextField username = new JTextField();
    	JPanel upan = new JPanel();
    	username.setPreferredSize(new Dimension(206, 25));
    	upan.add(username);
    	add(upan);
    	add(new JPanel());
    	JButton agg = new JButton("Aggiungi");
    	JPanel aggpan = new JPanel();
    	aggpan.add(agg);
    	add(aggpan);
    	agg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				boolean presente = false;
				for (Utente utente : biblioteca.getListaUtenti())
					presente = presente || utente.getUsername().equalsIgnoreCase(username.getText());
				if (username.getText().isBlank()) {
					JOptionPane.showMessageDialog(getParent(), "Lo username inserito non è valido", "Username non valido", JOptionPane.ERROR_MESSAGE);
				}else if (!presente) {
					biblioteca.aggiungiUtente(new Utente(username.getText()));
					utenti();
				}else JOptionPane.showMessageDialog(getParent(), "Lo username inserito è già in utilizzo", "Username non disponibile", JOptionPane.ERROR_MESSAGE);
			}
	    });
    	for (int i=0; i<9; i++)
    		add(new JPanel());
    }
    
    public void salva() {
    	FileManager filemanager = new FileManager();
		filemanager.scriviSuFile(biblioteca, Impostazioni.NOME_FILE_BIBLIOTECA);
    }
}
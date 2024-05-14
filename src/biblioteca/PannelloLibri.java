package biblioteca;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class PannelloLibri extends JPanel {
    

	
	private Biblioteca biblioteca;
	private String ricerca;
	private char mode;
	int sel=0;
	String[] tipi = {Impostazioni.TITOLO,Impostazioni.AUTORE,Impostazioni.GENERE,Impostazioni.ANNO};
	
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
        		panLib.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
        		add(panLib);
        		JButton prenota = new JButton(Impostazioni.TESTO_PULSANTE_PRENOTA);
        		prenota.setFocusable(false);

        		
        		prenota.addActionListener(e -> {
        			removeAll();
        	    	revalidate();
        	    	repaint();
        	    	setLayout(new GridLayout(0, 2));
        	    	JPanel labelPanel = new JPanel();
        	    	JLabel eti = new JLabel(Impostazioni.TESTO_ETICHETTA_USERNAME);
        	    	eti.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
        	    	labelPanel.add(eti);
        	    	add(labelPanel);
        	    	JComboBox<String> combo = new JComboBox<>();
        	    	combo.addItem(Impostazioni.SEPARATORE);
        	    	for (Utente utente : biblioteca.getListaUtenti()) {
        	            combo.addItem(utente.getUsername());
        	        }
        	    	JPanel pan = new JPanel();
        	    	combo.setPreferredSize(new Dimension(Impostazioni.WIDTH_206,Impostazioni.HEIGHT_25));
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
        		vuoto.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
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
        for (Utente rest : biblioteca.getListaUtenti()) {
        	for (Prenotazione pr : rest.getPrenotazioni())
	        	if (pr.getLibroPrenotato().isRicercato()) {
	        		disp++;
	        		JPanel panLib = pr.getLibroPrenotato().toPanel();
	        		panLib.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
	        		add(panLib);
	        		JButton restituisci = new JButton("Restituisci");
	        		restituisci.setFocusable(false);
	        		//String rest = "";
	        		restituisci.setToolTipText("In prestito a: " + rest.getUsername());
	        		restituisci.addActionListener(e -> {
	        			rest.restituisciUtente(pr.getLibroPrenotato());
	        	        restituisci();
	        	    });
	        		JPanel pan = new JPanel();
	            	pan.add(restituisci);
	            	add(pan);
	        	}
        }
        if (disp<5)
        	for (int i=0; i<6-disp; i++) {
        		JPanel vuoto = new JPanel();
        		vuoto.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
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
    			case Impostazioni.TITOLO:
    				biblioteca.getListaLibri().ordinaPerTitolo();
    				sel=0;
    				mostra();
    				break;
    			case Impostazioni.AUTORE:
    				biblioteca.getListaLibri().ordinaPerAutore();
    				sel=1;
    				mostra();
    				break;
    			case Impostazioni.GENERE:
    				biblioteca.getListaLibri().ordinaPerGenere();
    				sel=2;
    				mostra();
    				break;
    			case Impostazioni.ANNO:
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
        		vuoto.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
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
    	String[] anni = new String[1026];
    	anni[0] = Impostazioni.SEPARATORE;
    	for (int i = 2024; i > 999; i--) {
    	    anni[2025-i] = Integer.toString(i);
    	}
    	setLayout(new GridLayout(0, 2));
    	add(new JLabel("Titolo:"));
    	JTextField titolo = new JTextField();
    	JPanel tpan = new JPanel();
    	titolo.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
    	tpan.add(titolo);
    	add(tpan);
    	add(new JLabel("Autore:"));
    	JTextField autore = new JTextField();
    	JPanel apan = new JPanel();
    	autore.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
    	apan.add(autore);
    	add(apan);
    	add(new JLabel("Genere:"));
    	JTextField genere = new JTextField();
    	JPanel gpan = new JPanel();
    	genere.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
    	gpan.add(genere);
    	add(gpan);
    	add(new JLabel("Anno:"));
    	JComboBox<String> combo = new JComboBox<>(anni);
    	//combo.setEditable(true);
    	JPanel cpan = new JPanel();
    	combo.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
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
				if (!titolo.getText().isBlank() && !autore.getText().isBlank() && !genere.getText().isBlank() && !((String) combo.getSelectedItem()).equalsIgnoreCase(Impostazioni.SEPARATORE)) {
					biblioteca.aggiungiTitolo(new Libro(titolo.getText(), autore.getText(), genere.getText(), Integer.parseInt((String) combo.getSelectedItem())));
					mostra();
				}else JOptionPane.showMessageDialog(getParent(), "Compilare tutti i campi presenti", "Titolo non valido", JOptionPane.ERROR_MESSAGE);
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
        		panLib.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
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
        		vuoto.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
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
    		if (utente.getPrenotazioni().size()!=0)
    			nlibri += utente.getPrenotazioni().size()-1;
    	}
    	Object dati [][] = new Object[nlibri][2];
    	int i = 0;
    	for (Utente utente : biblioteca.getListaUtenti()) {
    		dati[i][0] = utente.getUsername();
    		if (utente.getPrenotazioni().size()!=0)
	    		for (Prenotazione prenotazione : utente.getPrenotazioni()) {
	    			int giorni = 2;
	    			String cella = prenotazione.getLibroPrenotato().getTitolo() + "(" + Integer.toString(giorni) + "giorni)";
	    			dati[i][1] = cella;
	    			i++;
	    		}
    		else i++;
    	}
    	String[] columnNames = {"username", "Libri(scadenza)"};
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
    	username.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
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
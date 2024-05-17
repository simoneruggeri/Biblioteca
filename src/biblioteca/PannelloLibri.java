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
	int selettoreOrdine=0;
	
    public PannelloLibri(Biblioteca biblioteca) {
    	this.biblioteca = biblioteca;
    	this.mostra();
    }
    
    public void prenota() {
    	
    	resetPannelli();
    	mode = 'p';
    	
    	setLayout(new GridLayout(0, 2));
    	biblioteca.ricerca(ricerca);
    	
    	int nLibri = 0;
    	
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
        	if (libro.getStato() && libro.isRicercato()) {
        		nLibri++;
        		JPanel pannelloLibro = libro.toPanel();
        		pannelloLibro.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
        		add(pannelloLibro);
        		JButton prenotaButton = new JButton(Impostazioni.TESTO_PULSANTE_PRENOTA);
        		prenotaButton.setFocusable(false);
        		prenotaButton.addActionListener(e -> {
        			resetPannelli();
        	    	setLayout(new GridLayout(0, 2));
        	    	JLabel etichettaUsername = new JLabel(Impostazioni.TESTO_ETICHETTA_USERNAME);
        	    	pannelloDimensionato(etichettaUsername);
        	    	JComboBox<String> sceltaUtenti = new JComboBox<>();
        	    	sceltaUtenti.addItem(Impostazioni.SEPARATORE);
        	    	for (Utente utente : biblioteca.getListaUtenti()) 
        	    		sceltaUtenti.addItem(utente.getUsername());
        	    	sceltaUtenti.addActionListener(new ActionListener() {
        	    		public void actionPerformed(ActionEvent e) {
        	    			String usernameSelezionato = (String) sceltaUtenti.getSelectedItem();
        	    			Utente utenteSelezionato = biblioteca.utentePresente(usernameSelezionato);
        	    			if (utenteSelezionato != null) 
     	                    	utenteSelezionato.prenotaUtente(libro);
        	    		}
        	    	});
        	    	pannelloDimensionato(sceltaUtenti);
        	    });
        		pannello(prenotaButton);
        	}
        }
        
        if (nLibri<5)
        	vuoto(2*(4-nLibri));
    }
    
    public void restituisci() {
    	resetPannelli();
    	mode = 'r';
    	
    	setLayout(new GridLayout(0, 2));
    	biblioteca.ricerca(ricerca);
    	
    	int nLibri = 0;
        for (Utente utente : biblioteca.getListaUtenti()) {
        	for (Prenotazione prenotazione : utente.getPrenotazioni())
	        	if (prenotazione.getLibroPrenotato().isRicercato()) {
	        		nLibri++;
	        		
	        		JPanel pannelloLibro = prenotazione.getLibroPrenotato().toPanel();
	        		pannelloLibro.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
	        		add(pannelloLibro);
	        		
	        		JButton restituisciButton = new JButton(Impostazioni.TESTO_PULSANTE_RESTITUISCI);
	        		restituisciButton.setFocusable(false);
	        		restituisciButton.setToolTipText(Impostazioni.TOOLTIP_PRESTITO + utente.getUsername());
	        		restituisciButton.addActionListener(e -> {
	        			utente.restituisciUtente(prenotazione.getLibroPrenotato());
	        	    });
	        		pannello(restituisciButton);
	        	}
        }
        
        if (nLibri<5)
        	vuoto(2*(4-nLibri));
    }
    
    public void mostra() {
    	
    	resetPannelli();
    	mode = 'm';
    	
    	setLayout(new GridLayout(0, 2));
    	biblioteca.ricerca(ricerca);

    	add(new JLabel(Impostazioni.ETICHETTA_ORDINA));
    	
    	String[] campiOrdinati = new String[4];
    	for(int i = 0; i<4; i++) {
    		campiOrdinati[i] = Impostazioni.CAMPI_ORDINA[(selettoreOrdine+i)%4];
    	}
    	
    	JComboBox<String> sceltaOrdine = new JComboBox<>(campiOrdinati);
    	sceltaOrdine.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String scelta = (String) sceltaOrdine.getSelectedItem();
    			switch (scelta) {
    			case Impostazioni.TITOLO:
    				biblioteca.ordinaPerTitolo();
    				break;
    			case Impostazioni.AUTORE:
    				biblioteca.ordinaPerAutore();
    				break;
    			case Impostazioni.GENERE:
    				biblioteca.ordinaPerGenere();
    				break;
    			case Impostazioni.ANNO:
    				biblioteca.ordinaPerAnno();
    				break;
    			}
    			for (int i = 0; i<4; i++)
    				if (scelta.equalsIgnoreCase(Impostazioni.CAMPI_ORDINA[i]))
    					selettoreOrdine = i;
    			mostra();
    		}
    	});

    	JPanel sceltaPanel = new JPanel(new GridLayout(0,1));
    	sceltaPanel.add(new JLabel());
    	sceltaPanel.add(sceltaOrdine);
    	sceltaPanel.add(new JLabel());
    	add(sceltaPanel);
    	
    	int nLibri = 0;
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
        	if(libro.isRicercato()) {
        		add(libro.toPanel());
        		nLibri++;
        		if(libro.getStato()) {
            		JLabel discoverde = new JLabel(new ImageIcon(getClass().getResource("green.png")));
            		discoverde.setToolTipText("Disponibile");
            		add(discoverde);
            	} 
            	else {
            		JLabel discorosso = new JLabel(new ImageIcon(getClass().getResource("red.png")));
            		String usernamePrestito = "";
            		for (Utente utente : biblioteca.getListaUtenti())
            			if (utente.presente(libro))
            				usernamePrestito = utente.getUsername();
            		discorosso.setToolTipText("In prestito a: " + usernamePrestito);
            		add(discorosso);
            	}
        	}
        }
        
        if (nLibri<5)
        	vuoto(2*(4-nLibri));
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
    	
    	resetPannelli();
    	mode = 'a';
    	
    	setLayout(new GridLayout(0, 2));
    	
    	JTextField sceltaTitolo = new JTextField();
    	JTextField sceltaAutore = new JTextField();
    	JTextField sceltaGenere = new JTextField();
    	JComboBox<String> sceltaAnni = new JComboBox<>(Impostazioni.ANNI);
    	JComponent scelte[] = {sceltaTitolo, sceltaAutore, sceltaGenere, sceltaAnni};
    	
    	for (int i = 0; i<4; i++) {
    		add(new JLabel(Impostazioni.ETICHETTE[i]));
    		pannelloDimensionato(scelte[i]);
    	}
   
    	vuoto(1);
    	
    	JButton aggiungiButton = new JButton(Impostazioni.AGGIUNGI);
    	pannello(aggiungiButton);
    	
    	aggiungiButton.addActionListener(new ActionListener() {
    		
			public void actionPerformed(ActionEvent e){
				
				if (!sceltaTitolo.getText().isBlank() && !sceltaAutore.getText().isBlank() && !sceltaGenere.getText().isBlank() && !((String) sceltaAnni.getSelectedItem()).equalsIgnoreCase(Impostazioni.SEPARATORE)) {
					biblioteca.aggiungiTitolo(new Libro(sceltaTitolo.getText(), sceltaAutore.getText(), sceltaGenere.getText(), Integer.parseInt((String) sceltaAnni.getSelectedItem())));
					mostra();
				}else JOptionPane.showMessageDialog(getParent(), Impostazioni.MESSAGGIO_ERRORE_COMPILAZIONE, Impostazioni.ERRORE_COMPILAZIONE, JOptionPane.ERROR_MESSAGE);
			
			}
			
	    });
    	
    	vuoto(5);
    	
    }
    
    public void rimuovi() {
    	
    	resetPannelli();
    	mode = 'c';
    	
    	setLayout(new GridLayout(0, 2));
    	biblioteca.ricerca(ricerca);
    	
    	int nLibri = 0;
        for (Libro libro : biblioteca.getListaLibri().getElenco()) {
        	
        	if (libro.getStato() && libro.isRicercato()) {
        		nLibri++;
        		
        		JPanel pannelloLibri = libro.toPanel();
        		pannelloLibri.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
        		add(pannelloLibri);
        		
        		JButton rimuoviButton = new JButton("Rimuovi");
        		rimuoviButton.setFocusable(false);
        		
        		rimuoviButton.addActionListener(e -> {
        			biblioteca.rimuoviTitolo(libro);
        	        rimuovi();
        	    });
        		pannello(rimuoviButton);
        	}
        }
        
        if (nLibri<5)
        	vuoto(2*(4-nLibri));
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
	    			int giorni = prenotazione.getScadenza().differenzaGiorni(new Data(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
	    			String scadenza = "";
	    			if (giorni>0) {
	    				scadenza = "(" + Integer.toString(giorni) + "giorni)";
	    			}else scadenza = "scaduto";
	    			String cella = prenotazione.getLibroPrenotato().getTitolo() + scadenza;
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
    	vuoto(9);
    }
    
    public void salva() {
    	FileManager filemanager = new FileManager();
		filemanager.scriviSuFile(biblioteca, Impostazioni.NOME_FILE_BIBLIOTECA);
    }
    
    public void vuoto(int n) {
    	for (int i=0; i<n; i++)
    		add(new JPanel()); 
    }
    
    public void pannello(JComponent oggetto) {
    	JPanel pannello = new JPanel();
    	pannello.add(oggetto);
    	add(pannello);
    }
    
    public void pannelloDimensionato(JComponent oggetto) {
    	JPanel pannello = new JPanel();
    	oggetto.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
    	pannello.add(oggetto);
    	add(pannello);
	}
    
    public void resetPannelli() {
    	removeAll();
    	revalidate();
    	repaint();
    }
}
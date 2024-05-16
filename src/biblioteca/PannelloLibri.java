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
        		
        		JPanel pannelloLibro = libro.toPanel();
        		pannelloLibro.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
        		add(pannelloLibro);
        		
        		JButton prenotaButton = new JButton(Impostazioni.TESTO_PULSANTE_PRENOTA);
        		prenotaButton.setFocusable(false);
        		prenotaButton.addActionListener(e -> {
        			removeAll();
        	    	revalidate();
        	    	repaint();
        	    	setLayout(new GridLayout(0, 2));
        	    	
        	    	JPanel labelPanel = new JPanel();
        	    	JLabel etichettaUsername = new JLabel(Impostazioni.TESTO_ETICHETTA_USERNAME);
        	    	etichettaUsername.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
        	    	labelPanel.add(etichettaUsername);
        	    	add(labelPanel);
        	    	
        	    	JComboBox<String> sceltaUtenti = new JComboBox<>();
        	    	sceltaUtenti.addItem(Impostazioni.SEPARATORE);
        	    	for (Utente utente : biblioteca.getListaUtenti()) 
        	    		sceltaUtenti.addItem(utente.getUsername());
        	 
        	    	sceltaUtenti.setPreferredSize(new Dimension(Impostazioni.WIDTH_206,Impostazioni.HEIGHT_25));
        	    	sceltaUtenti.addActionListener(new ActionListener() {
        	    		public void actionPerformed(ActionEvent e) {
        	    			String usernameSelezionato = (String) sceltaUtenti.getSelectedItem();
        	    			Utente utenteSelezionato = biblioteca.utentePresente(usernameSelezionato);
        	    			if (utenteSelezionato != null) 
     	                    	utenteSelezionato.prenotaUtente(libro);
        	    		}
        	    	});
        	    	
        	    	JPanel pannelloSceltaUtenti = new JPanel();
        	    	pannelloSceltaUtenti.add(sceltaUtenti);
        	    	add(pannelloSceltaUtenti);
        	        
        	    });
        		JPanel prenotaButtonPanel = new JPanel();
        		prenotaButtonPanel.add(prenotaButton);
            	add(prenotaButtonPanel);
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
    	
        for (Utente utente : biblioteca.getListaUtenti()) {
        	for (Prenotazione prenotazione : utente.getPrenotazioni())
	        	if (prenotazione.getLibroPrenotato().isRicercato()) {
	        		disp++;
	        		
	        		JPanel pannelloLibro = prenotazione.getLibroPrenotato().toPanel();
	        		pannelloLibro.setPreferredSize(new Dimension(Impostazioni.WIDTH_10,Impostazioni.HEIGHT_80));
	        		add(pannelloLibro);
	        		
	        		JButton restituisciButton = new JButton("Restituisci");
	        		restituisciButton.setFocusable(false);
	        		restituisciButton.setToolTipText("In prestito a: " + utente.getUsername());
	        		restituisciButton.addActionListener(e -> {
	        			utente.restituisciUtente(prenotazione.getLibroPrenotato());
	        	    });
	        		
	        		JPanel restituisciButtonPanel = new JPanel();
	        		restituisciButtonPanel.add(restituisciButton);
	            	add(restituisciButtonPanel);
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
    	
    	String[] campiOrdinati = new String[4];
    	for(int i = 0; i<4; i++) {
    		campiOrdinati[i] = Impostazioni.CAMPI_ORDINA[(selettoreOrdine+i)%4];
    	}
    	
    	
    	JComboBox<String> sceltaOrdine = new JComboBox<>(campiOrdinati);
    	sceltaOrdine.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			switch ((String) sceltaOrdine.getSelectedItem()) {
    			case Impostazioni.TITOLO:
    				biblioteca.ordinaPerTitolo();
    				selettoreOrdine=0;
    				mostra();
    				break;
    			case Impostazioni.AUTORE:
    				biblioteca.ordinaPerAutore();
    				selettoreOrdine=1;
    				mostra();
    				break;
    			case Impostazioni.GENERE:
    				biblioteca.ordinaPerGenere();
    				selettoreOrdine=2;
    				mostra();
    				break;
    			case Impostazioni.ANNO:
    				biblioteca.ordinaPerAnno();
    				selettoreOrdine=3;
    				mostra();
    				break;
    			}
    		}
    	});

    	JPanel sceltaPanel = new JPanel(new GridLayout(0,1));
    	sceltaPanel.add(new JLabel());
    	sceltaPanel.add(sceltaOrdine);
    	sceltaPanel.add(new JLabel());
    	add(sceltaPanel);
    	
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
            		String usernamePrestito = "";
            		for (Utente utente : biblioteca.getListaUtenti())
            			if (utente.presente(libro))
            				usernamePrestito = utente.getUsername();
            		discorosso.setToolTipText("In prestito a: " + usernamePrestito);
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
    	
    	String[] anni = Impostazioni.ANNI;
    	
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
    	JPanel cpan = new JPanel();
    	combo.setPreferredSize(new Dimension(Impostazioni.WIDTH_206, Impostazioni.HEIGHT_25));
    	cpan.add(combo);
    	add(cpan);
    	
    	add(new JPanel());
    	JButton agg = new JButton("Aggiungi");
    	JPanel aggpan = new JPanel();
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
    	
    	vuoto(5);
    	
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
}
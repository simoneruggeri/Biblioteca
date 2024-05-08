package biblioteca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Biblioteca extends JFrame{
	
	JPanel startPanel = new JPanel();
	JTextField casella;
	ElencoLibri bib = new ElencoLibri(); 
	
	public Biblioteca(String s) {
		super(s);
		setSize(600,400);
		
		casella = new JTextField();
		startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
	
		URL imageURL = getClass().getResource("png.png");
		ImageIcon icon = new ImageIcon(imageURL);
		int newWidth = 200; 
		int newHeight = 150; 
		Image image = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		ImageIcon scaledIcon = new ImageIcon(image);
		startPanel.add(new JLabel(scaledIcon));
		
		JLabel titolonz = new JLabel();
		
		titolonz.setText("BIBLIOTECA");
		//titolonz.setForeground(Color.DARK_GRAY);
		titolonz.setFont(new Font("Times new roman", Font.BOLD, 60));		
		startPanel.add(titolonz);
		JButton startButton = new JButton("Start");
		startButton.setPreferredSize(new Dimension(80,40));
		startButton.setFocusable(false);
		startPanel.add(startButton);
		add(startPanel);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//repaint();
				menu();
			}
	    });
		setResizable(false);
		
		
	}
	
	public void menu() {
		remove(startPanel);
		
		JPanel visual = new JPanel(new BorderLayout());
		visual.add(casella, BorderLayout.NORTH);
		
		
		Libro libro1 = new Libro("La Divina Commedia", "Dante Alighieri", "Commedia", 1215);
		Libro libro2 = new Libro("Viva La Pancia", "Marco Reus", "Giallo", 2027);
		Libro libro3 = new Libro("Kinder Pingui", "Magui Corceiro", "Drammatico", 1456);
		Libro libro4 = new Libro("Para Bailar La Bamba", "Spiedo", "Fantascienza", 2000);
		Libro libro5 = new Libro("Sei", "Spiedo", "Fantascienza", 2003);
		Libro libro6 = new Libro("Balooo!", "Mario Balotelli", "Horror", 2024);
		
		bib.aggiungiTitolo(libro1);
		bib.aggiungiTitolo(libro2);
		bib.aggiungiTitolo(libro3);
		bib.aggiungiTitolo(libro4);
		bib.aggiungiTitolo(libro5);
		bib.aggiungiTitolo(libro6);
		libro6.prenota();
		
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
		listaUtenti.add(new Utente("sslazio"));
		listaUtenti.add(new Utente("pinodaniele"));
		listaUtenti.add(new Utente("bingo"));
		
		PannelloLibri pan = new PannelloLibri(bib, listaUtenti);
		//pan.ricerca(casella.getText());
		casella.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	pan.ricerca(casella.getText());
            	System.out.println();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            	pan.ricerca(casella.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	pan.ricerca(casella.getText());
            }
		});
		
		JScrollPane scrollPane = new JScrollPane(pan);
		visual.add(scrollPane, BorderLayout.CENTER);
		
		
		BibliotecaMenu bpanel = new BibliotecaMenu(pan);
		add(bpanel, BorderLayout.WEST);
		
		
		add(visual, BorderLayout.CENTER);
		
		revalidate();
	}
	
	
	
		

	
}

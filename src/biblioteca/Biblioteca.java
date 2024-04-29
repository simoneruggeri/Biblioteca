package biblioteca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;


public class Biblioteca extends JFrame{
	
	JPanel startPanel = new JPanel();
	
	public Biblioteca(String s) {
		super(s);
		setSize(600,400);
		
		startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		startPanel.add(new JLabel(new ImageIcon(getClass().getResource("libri.png"))));
		JLabel titolonz = new JLabel();
		//titolonz.setIcon(new ImageIcon(getClass().getResource("libri.png")));
		titolonz.setText("BIBLIOTECA");
		titolonz.setFont(new Font("Arial", Font.PLAIN, 60));
		//titolonz.setVerticalTextPosition(JLabel.BOTTOM);
		//titolonz.setIconTextGap(30);
		startPanel.add(titolonz);
		JButton startButton = new JButton("Start");
		//startPanel.add(new JLabel("                                                        "));
		startPanel.add(startButton);
		add(startPanel);
		//pack();
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//repaint();
				menu();
			}
	    });
		setResizable(false);
		 
		grissinbon
		
	}
	
	public void menu() {
		remove(startPanel);
		
		ElencoLibri bib = new ElencoLibri(); 
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
		
		PannelloLibri pan = new PannelloLibri(bib.getElenco());
		JScrollPane scrollPane = new JScrollPane(pan);
		add(scrollPane, BorderLayout.CENTER);
		
		BibliotecaMenu bpanel = new BibliotecaMenu(pan);
		add(bpanel, BorderLayout.WEST);
		
		revalidate();
	}
	
	
	
		

	
}

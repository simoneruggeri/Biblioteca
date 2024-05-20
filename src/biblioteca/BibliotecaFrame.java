package biblioteca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.nio.file.Path;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class BibliotecaFrame extends JFrame{
	
	Biblioteca biblioteca; 
	FileManager filemanager;
	
	public BibliotecaFrame(String s, Biblioteca biblioteca) {
		super(s);
		setSize(600,400);
		
		this.biblioteca = biblioteca;
		filemanager = new FileManager();
		
		setResizable(false);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	salva();
            }
        });
		
		start();
	}
	
	public void start() {
		JPanel startPanel = new JPanel();
		startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Impostazioni.START_DISTANZA_X, Impostazioni.START_DISTANZA_Y));
		

		ImageIcon icon = new ImageIcon(Path.of(Impostazioni.CARTELLA_IMMAGINI,Impostazioni.NOME_IMMAGINE_LIBRO).toString());
		Image image = icon.getImage().getScaledInstance(Impostazioni.LARGHEZZA_IMMAGINE, Impostazioni.ALTEZZA_IMMAGINE, Image.SCALE_DEFAULT);
		ImageIcon scaledIcon = new ImageIcon(image);
		startPanel.add(new JLabel(scaledIcon));
		
		JLabel titoloLabel = new JLabel(Impostazioni.TITOLO_PROGRAMMA);
		titoloLabel.setFont(new Font(Impostazioni.FONT_TITOLO, Font.BOLD, Impostazioni.DIMENSIONE_TITOLO));		
		startPanel.add(titoloLabel);
		
		JButton startButton = new JButton(Impostazioni.TESTO_PULSANTE_START);
		startButton.setPreferredSize(new Dimension(Impostazioni.LARGHEZZA_PULSANTE_START, Impostazioni.ALTEZZA_PULSANTE_START));
		startButton.setFocusable(false);
		startPanel.add(startButton);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				remove(startPanel);
				menu();
			}
	    });
		
		add(startPanel);
	}
	
	public void salva() {
		filemanager.scriviSuFile(biblioteca, Impostazioni.NOME_FILE_BIBLIOTECA);
	}
	
	public void menu() {
		
		
		JTextField casella = new JTextField();
		JPanel pannelloCentrale = new JPanel(new BorderLayout());
		pannelloCentrale.add(casella, BorderLayout.NORTH);
		
		PannelloLibri pannelloLibri = new PannelloLibri(biblioteca);
		
		casella.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	pannelloLibri.ricerca(casella.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
            	pannelloLibri.ricerca(casella.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	pannelloLibri.ricerca(casella.getText());
            }
		});
		
		JScrollPane pannelloScorrevole = new JScrollPane(pannelloLibri);
		pannelloCentrale.add(pannelloScorrevole, BorderLayout.CENTER);
		add(pannelloCentrale, BorderLayout.CENTER);
		
		BibliotecaMenu pannelloMenu = new BibliotecaMenu(pannelloLibri);
		add(pannelloMenu, BorderLayout.WEST);
	
		revalidate();
	}
	
	
	
		

	
}

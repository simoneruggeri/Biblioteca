package application;

import javax.swing.JFrame;

import GUI.BibliotecaFrame;
import biblioteca.Biblioteca;
import settings.Impostazioni;

public class BibliotecaTest {

	public static void main(String[] a){
		FileManager filemanager = new FileManager();
		Biblioteca biblioteca = filemanager.leggiDaFile(Impostazioni.NOME_FILE_BIBLIOTECA);
		if (biblioteca == null)
			biblioteca = new Biblioteca();
		BibliotecaFrame frame = new BibliotecaFrame(Impostazioni.STRINGA_BIBLIOTECA, biblioteca);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}

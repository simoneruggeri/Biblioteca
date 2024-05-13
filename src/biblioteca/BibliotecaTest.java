package biblioteca;

import javax.swing.JFrame;



public class BibliotecaTest {

	public static void main(String[] a){
		BibliotecaFrame frame = new BibliotecaFrame("Biblioteca", new Biblioteca());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}

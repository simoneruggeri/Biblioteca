package biblioteca;

import java.awt.*;

import javax.swing.*;

public class ScrollablePageExample extends JFrame {
    
    public ScrollablePageExample() {
        super("Pagina Scorrevole");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creazione di un pannello con layout GridLayout
        JPanel panel = new JPanel(new GridLayout(0, 1)); // Una colonna, righe aggiunte dinamicamente
        
        // Aggiunta di molti componenti (etichette) al pannello
        for (int i = 0; i < 20; i++) {
        	Libro libro = new Libro("La Divina Commedia", "Dante Alighieri", "Commedia", 1215);
            //JLabel label = new JLabel(libro.toString());
            //panel.add(label);
        	panel.add(new JLabel("--------------------------"));
        	panel.add(new JLabel(libro.toStringTitolo()));
        	panel.add(new JLabel(libro.toStringAutore()));
        	panel.add(new JLabel(libro.toStringGenere()));
        	panel.add(new JLabel(libro.toStringAnno()));
        }
        
        // Creazione di uno JScrollPane contenente il pannello
        JScrollPane scrollPane = new JScrollPane(panel);
        
        // Impostazione delle dimensioni preferite dello JScrollPane
        scrollPane.setPreferredSize(new Dimension(300, 200));
        
        // Aggiunta dello JScrollPane al frame
        add(scrollPane);
        
        pack();
        setVisible(true);
    }

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(() -> new ScrollablePageExample());
   // }
}
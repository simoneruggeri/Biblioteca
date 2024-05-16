package biblioteca;

public class Impostazioni {

	private static final int NUMERO_ANNI = 999;
	private static final int ANNO_CORRENTE = 2024;
	public static final String NOME_FILE_BIBLIOTECA = "biblioteca.json";
	public static final String SEPARATORE = "------------------------------------------";
	public static final String ESCI = "Esci";
	public static final String RESTITUISCI_LIBRO = "Restituisci Libro";
	public static final String PRENOTA_LIBRO = "Prenota Libro";
	public static final String RIMUOVI_TITOLO = "Rimuovi Titolo";
	public static final String AGGIUNGI_TITOLO = "Aggiungi Titolo";
	public static final String VISUALIZZA_UTENTI = "Visualizza Utenti";
	public static final String VISUALIZZA_CATALOGO = "Visualizza Catalogo";
	public static final String STRINGA_BIBLIOTECA = "Biblioteca";
	public static final int HEIGHT_25 = 25;
	public static final int WIDTH_206 = 206;
	public static final int HEIGHT_80 = 80;
	public static final int WIDTH_10 = 10;
	public static final String TESTO_ETICHETTA_USERNAME = "username:";
	public static final String TESTO_PULSANTE_PRENOTA = "Prenota";
	public static final String ANNO = "anno";
	public static final String GENERE = "genere";
	public static final String AUTORE = "autore";
	public static final String TITOLO = "titolo";
	public static final String[] CAMPI_ORDINA = {TITOLO,AUTORE,GENERE,ANNO};
	public static final int DIMENSIONE_ANNI = ANNO_CORRENTE - NUMERO_ANNI + 1;
	public static final String[] ANNI = anni();
	
	public static String[] anni() {
		String[] anni = new String[DIMENSIONE_ANNI];
    	anni[0] = SEPARATORE;
    	for (int i = ANNO_CORRENTE; i > NUMERO_ANNI; i--) {
    	    anni[ANNO_CORRENTE+1-i] = Integer.toString(i);
    	}
    	return anni;
	}

}

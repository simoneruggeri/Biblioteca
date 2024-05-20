package settings;

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
	public static final String ANNO = "Anno";
	public static final String GENERE = "Genere";
	public static final String AUTORE = "Autore";
	public static final String TITOLO = "Titolo";
	public static final String[] CAMPI_ORDINA = {TITOLO,AUTORE,GENERE,ANNO};
	public static final int DIMENSIONE_ANNI = ANNO_CORRENTE - NUMERO_ANNI + 1;
	public static final String[] ANNI = anni();
	public static final String ETICHETTA_ANNO = ANNO + ":";
	public static final String ETICHETTA_AUTORE = AUTORE + ":";
	public static final String ETICHETTA_GENERE = GENERE + ":";
	public static final String ETICHETTA_TITOLO = TITOLO + ":";
	public static final String[] ETICHETTE = {ETICHETTA_TITOLO, ETICHETTA_AUTORE, ETICHETTA_GENERE, ETICHETTA_ANNO};
	public static final String AGGIUNGI = "Aggiungi";
	public static final String MESSAGGIO_ERRORE_COMPILAZIONE = "Compilare tutti i campi presenti";
	public static final String ERRORE_COMPILAZIONE = "Titolo non valido";
	public static final String TESTO_PULSANTE_RESTITUISCI = "Restituisci";
	public static final String TOOLTIP_PRESTITO = "In prestito a: ";
	public static final String ETICHETTA_ORDINA = "Ordina per:";
	public static final String GIORNI = " giorni)";
	public static final String SCADUTO = "scaduto";
	public static final String NOME_COLONNA_USERNAME = "username";
	public static final String NOME_COLONNA_LIBRI = "Libri (scadenza)";
	public static final String[] NOMI_COLONNE = {NOME_COLONNA_USERNAME, NOME_COLONNA_LIBRI};
	public static final int LARGHEZZA_TABELLA = 2*WIDTH_206 - 2;
	public static final int ALTEZZA_COLONNE = 16;
	public static final String MESSAGGIO_ERRORE_USERNAME_NON_VALIDO = "Lo username inserito non è valido";
	public static final String ERRORE_USERNAME_NON_VALIDO = "Username non valido";
	public static final String MESSAGGIO_ERRORE_USERNAME_UTILIZZATO = "Lo username inserito è già in utilizzo";
	public static final String NUOVO_UTENTE = "Nuovo Utente";
	public static final String RIMUOVI = "Rimuovi";
	public static final String CARICAMENTO_COMPLETATO = "Biblioteca caricata con successo da ";
	public static final String CARICAMENTO_FALLITO = "Errore durante il caricamento della biblioteca da ";
	public static final String SALVATAGGIO_COMPLETATO = "Biblioteca salvata con successo in ";
	public static final String SALVATAGGIO_FALLITO = "Errore durante il salvataggio della biblioteca in ";
	public static final int ALTEZZA_IMMAGINE = 150;
	public static final int LARGHEZZA_IMMAGINE = 200;
	public static final String TITOLO_PROGRAMMA = "BIBLIOTECA";
	public static final String FONT_TITOLO = "Times new roman";
	public static final int DIMENSIONE_TITOLO = 60;
	public static final String TESTO_PULSANTE_START = "Start";
	public static final int LARGHEZZA_PULSANTE_START = 80;
	public static final int ALTEZZA_PULSANTE_START = 40;
	public static final int START_DISTANZA_X = 100;
	public static final int START_DISTANZA_Y = 10;
	public static final String CARTELLA_IMMAGINI = "images";
	public static final String NOME_IMMAGINE_VERDE = "green.png";
	public static final String NOME_IMMAGINE_ROSSO = "red.png";
	public static final String DISPONIBILE = "Disponibile";
	public static final String NOME_IMMAGINE_LIBRO = "iconalibro.png";

	
	public static String[] anni() {
		String[] anni = new String[DIMENSIONE_ANNI];
    	anni[0] = SEPARATORE;
    	for (int i = ANNO_CORRENTE; i > NUMERO_ANNI; i--) {
    	    anni[ANNO_CORRENTE+1-i] = Integer.toString(i);
    	}
    	return anni;
	}

}

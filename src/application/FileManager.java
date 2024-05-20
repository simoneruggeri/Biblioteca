package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import biblioteca.Biblioteca;
import settings.Impostazioni;



public class FileManager {
    
    public FileManager() {
    }
    
    public Biblioteca leggiDaFile(String filename) {
    	try {
            Path filePath = Paths.get(Impostazioni.NOME_FILE_BIBLIOTECA);
            String json = Files.readString(filePath);
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.loadFromJson(json);
            System.out.println(Impostazioni.CARICAMENTO_COMPLETATO + Impostazioni.NOME_FILE_BIBLIOTECA);
            return biblioteca;
        } catch (IOException e) {
            System.err.println(Impostazioni.CARICAMENTO_FALLITO + Impostazioni.NOME_FILE_BIBLIOTECA + ": " + e.getMessage());
            return null;
        }
    }
    
    public void scriviSuFile(Biblioteca biblio, String filename) {
    	String json = biblio.saveToJson();
        try {
            Path filePath = Paths.get(Impostazioni.NOME_FILE_BIBLIOTECA);
            Files.writeString(filePath, json);
            System.out.println(Impostazioni.SALVATAGGIO_COMPLETATO + Impostazioni.NOME_FILE_BIBLIOTECA);
        } catch (IOException e) {
            System.err.println(Impostazioni.SALVATAGGIO_FALLITO + Impostazioni.NOME_FILE_BIBLIOTECA + ": " + e.getMessage());
        }
    }
}


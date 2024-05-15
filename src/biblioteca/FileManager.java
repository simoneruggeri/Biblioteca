package biblioteca;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileManager {
    private Gson gson;
    
    public FileManager() {
        this.gson = new Gson();
    }
    
    public Biblioteca leggiDaFile(String filename) {
    	try {
            Path filePath = Paths.get(Impostazioni.NOME_FILE_BIBLIOTECA);
            String json = Files.readString(filePath);
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.loadFromJson(json);
            System.out.println("Biblioteca caricata con successo da " + Impostazioni.NOME_FILE_BIBLIOTECA);
            return biblioteca;
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento della biblioteca da " + Impostazioni.NOME_FILE_BIBLIOTECA + ": " + e.getMessage());
            return null;
        }
    }
    
    public void scriviSuFile(Biblioteca biblio, String filename) {
    	String json = biblio.saveToJson();
        try {
            Path filePath = Paths.get(Impostazioni.NOME_FILE_BIBLIOTECA);
            Files.writeString(filePath, json);
            System.out.println("Biblioteca salvata con successo in " + Impostazioni.NOME_FILE_BIBLIOTECA);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio della biblioteca in " + Impostazioni.NOME_FILE_BIBLIOTECA + ": " + e.getMessage());
        }
    }
}


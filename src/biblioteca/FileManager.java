package biblioteca;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class FileManager {
    private Gson gson;
    
    public FileManager() {
        this.gson = new Gson();
    }
    
    public Biblioteca leggiDaFile(String filename) {
        TypeToken<Biblioteca> biblioType = new TypeToken<>() {};
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, biblioType.getType());
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            System.out.println("Errore durante la lettura del file:");
            System.out.println(e.getMessage());
            return new Biblioteca(); 
        }
    }
    
    public void scriviSuFile(Biblioteca biblio, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(biblio, writer);
        } catch (JsonIOException | IOException e) {
            System.out.println("Errore durante la scrittura su file:");
            System.out.println(e.getMessage());
        }
    }
}


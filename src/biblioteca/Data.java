package biblioteca;

import com.google.gson.Gson;

public class Data {

	int giorno;
	int mese;
	int anno;
	
	public Data(int giorno, int mese, int anno) {
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}
	
	public int differenzaGiorni(Data altraData) {
        return giorniTrascorsi(this) - giorniTrascorsi(altraData);
    }
	
	private int giorniTrascorsi(Data data) {
        int[] giorniPerMese = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int giorni = data.giorno;

        for (int i = 1; i < data.mese; i++) {
            giorni += giorniPerMese[i];
            if (i == 2 && data.anno % 4 == 0 && (data.anno % 100 != 0 || data.anno % 400 == 0)) {
                giorni++;
            }
        }

        giorni += data.anno * 365;

        giorni += (data.anno - 1) / 4 - (data.anno - 1) / 100 + (data.anno - 1) / 400;

        return giorni;
    }
	
	public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
	
	
}

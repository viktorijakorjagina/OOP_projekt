import java.util.Random;

// Klass Tegevus esindab ühte tegevust, mida õpilane saab valida
// Igal tegevusel on nimi, kirjeldus ja mõju teadmistele ning enesekindlusele
// Teadmiste muutus on juhuslik
public class Tegevus {

    private String nimi;
    private String kirjeldus;

    private int minTeadmised;         // minimaalne teadmiste kasv
    private int maxTeadmised;         // maksimaalne teadmiste kasv
    private int enesekindlusMuutus;   // enesekindluse muutus (fikseeritud)

    private Random random = new Random();

    // Konstruktor
    public Tegevus(String nimi, String kirjeldus, int minTeadmised, int maxTeadmised, int enesekindlusMuutus) {
        this.nimi = nimi;
        this.kirjeldus = kirjeldus;
        this.minTeadmised = minTeadmised;
        this.maxTeadmised = maxTeadmised;
        this.enesekindlusMuutus = enesekindlusMuutus;
    }

    // Get
    public String getNimi() {
        return nimi;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }

    public int getEnesekindlusMuutus() {
        return enesekindlusMuutus;
    }

    // Arvutab juhusliku teadmiste kasvu selle tegevuse jaoks
    // Tagastab väärtuse minTeadmised ja maxTeadmised vahel
    public int getTeadmistekasv() {
        if (minTeadmised == maxTeadmised) {
            return minTeadmised;
        }
        return minTeadmised + random.nextInt(maxTeadmised - minTeadmised + 1);
    }
}

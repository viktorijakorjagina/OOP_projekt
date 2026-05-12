// Klass Õpilane hoiab mängija andmed:
// nimi, teadmised, enesekindlus ja õppimise järjestus
public class Õpilane {

    private String nimi;
    private int teadmised;
    private int enesekindlus;

    // Näitab, mitu korda järjest mängija valis õppimise
    private int õppimisJärjestus;

    public Õpilane(String nimi) {
        this.nimi = nimi;
        this.teadmised = 0;
        this.enesekindlus = 0;
        this.õppimisJärjestus = 0;
    }

    public String getNimi() {
        return nimi;
    }

    public int getTeadmised() {
        return teadmised;
    }

    public int getEnesekindlus() {
        return enesekindlus;
    }

    // Tagastab õppimise järjestuse
    public int getÕppimisJärjestus() {
        return õppimisJärjestus;
    }

    // Muudab õppimise järjestust
    public void setÕppimisJärjestus(int õppimisJärjestus) {
        this.õppimisJärjestus = õppimisJärjestus;
    }

    // Lisa teadmisi, aga teadmised ei lähe alla nulli
    public void lisaTeadmised(int n) {
        this.teadmised += n;
        if (this.teadmised < 0) {
            this.teadmised = 0;
        }
    }

    // Lisa enesekindlust, aga enesekindlus ei lähe alla nulli
    public void lisaEnesekindlus(int n) {
        this.enesekindlus += n;
        if (this.enesekindlus < 0) {
            this.enesekindlus = 0;
        }
    }

    // Tagastab hetkeseisu tekstina
    public String getStatsText() {
        return "Teadmised: " + teadmised + " punkti\n" +
                "Enesekindlus: " + enesekindlus + " punkti";
    }
}

/**
 * Klass Õpilane hoiab mängija andmed:
 * teadmised (päris teadmised) ja enesekindlus (kui valmis ta arvab end olevat).
 */

public class Õpilane {

    private String nimi;
    private int teadmised;
    private int enesekindlus;

    public Õpilane(String nimi) {
        this.nimi = nimi;
        this.teadmised = 0;
        this.enesekindlus = 0;
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

    // Lisa teadmisi (ei lähe alla nulli)
    public void lisaTeadmised(int n) {
        this.teadmised += n;
        if (this.teadmised < 0) {
            this.teadmised = 0;
        }
    }

    // Lisa enesekindlust (ei lähe alla nulli)
    public void lisaEnesekindlus(int n) {
        this.enesekindlus += n;
        if (this.enesekindlus < 0) {
            this.enesekindlus = 0;
        }
    }

    // Tagastab hetkeseisu tekstina
    public String getStatsText() {
        return "Teadmised: " + teadmised + "punkti\nEnesekindlus: " + enesekindlus + " punkti";
    }
}

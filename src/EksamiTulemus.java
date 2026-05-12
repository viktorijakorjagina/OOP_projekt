// See klass hoiab eksami lõpptulemuse andmeid
// Siin ei arvutata midagi, vaid ainult salvestatakse juba arvutatud tulemus
public class EksamiTulemus {

    private int teadmised;
    private int enesekindluseBoonus;
    private int õnn;
    private int lõplikSkoor;
    private String tulemusTekst;

    public EksamiTulemus(int teadmised, int enesekindluseBoonus, int õnn, int lõplikSkoor, String tulemusTekst) {
        this.teadmised = teadmised;
        this.enesekindluseBoonus = enesekindluseBoonus;
        this.õnn = õnn;
        this.lõplikSkoor = lõplikSkoor;
        this.tulemusTekst = tulemusTekst;
    }

    public int getLõplikSkoor() {
        return lõplikSkoor;
    }
    public String getTulemusTekst() {
        return tulemusTekst;
    }

    // See meetod teeb eksami tulemusest pika teksti
    public String getTäisTekst() {
        return tulemusTekst + "\n\n" +
                "Teadmised: " + teadmised + "\n" +
                "Enesekindluse boonus: " + enesekindluseBoonus + "\n" +
                "Õnnetegur: " + õnn + "\n\n" +
                "Lõplik skoor: " + lõplikSkoor;
    }
}

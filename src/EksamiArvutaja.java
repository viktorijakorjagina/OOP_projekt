import java.util.Random;

// See klass arvutab eksami lõpptulemuse
public class EksamiArvutaja {
    private Random random = new Random();

    // Meetod võtab õpilase andmed ja tagastab eksami tulemuse
    public EksamiTulemus arvutaTulemus(Õpilane õpilane) {
        int teadmised = õpilane.getTeadmised();

        // enesekindlus annab väikese boonuse
        int enesekindluseBoonus = õpilane.getEnesekindlus() / 5;

        // õnnetegur võib olla -20 kuni +20
        int õnn = -20 + random.nextInt(41);

        int lõplikSkoor = teadmised + enesekindluseBoonus + õnn;

        String tulemusTekst;

        if (lõplikSkoor >= 50) {
            tulemusTekst = "SA LÄBISID EKSAMI!";
        } else {
            tulemusTekst = "SA KUKKUSID EKSAMI LÄBI...";
        }
        return new EksamiTulemus(teadmised, enesekindluseBoonus, õnn, lõplikSkoor, tulemusTekst);
    }
}

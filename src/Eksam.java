import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Klass Eksam korraldab lõpliku eksami.
 * Tulemus sõltub teadmistest, enesekindlusest ja juhuslikust õnnetegurist.
 * Läbimiseks on vaja 50+ punkti.
 */
public class Eksam {

    private static final int LÄBIMISE_PIIR = 50; // läbimiseks vajalik punktide arv
    private Random random = new Random();

    /**
     * Viib läbi lõpliku eksami ja näitab tulemust.
     * @param õpilane mängija, kes sooritab eksami
     */
    public void sooritaEksam(Õpilane õpilane) {

        // Näita eksamieelset infot
        JOptionPane.showMessageDialog(null,
                "EKSAMI PÄEV ON KÄES!\n\n" +
                        "Sinu teadmiste ja enesekindluse punktid:\n" +
                        õpilane.getStatsText(),
                "Eksami päev!",
                JOptionPane.INFORMATION_MESSAGE);

        int teadmised = õpilane.getTeadmised();
        int enesekindlus = õpilane.getEnesekindlus();

        // Õnnetegur: juhuslik -20 kuni + 20
        int õnn = -20 + random.nextInt(41);
        String õnneTekst = (õnn >= 0 ? "+" : "") + õnn;
        // Lõplik skoor
        int lõplikSkoor = teadmised + (enesekindlus / 5) + õnn;

        // Näita skooride arvutust
        JOptionPane.showMessageDialog(null,
                "Eksami tulemuste arvutamine...\n\n" +
                        "Teadmised: " + teadmised + " punkti\n" +
                        "Enesekindluse boonus: " + (enesekindlus / 5) + " punkti\n" +
                        "Õnnetegur: " + õnneTekst + " punkti\n\n" +
                        "LÕPLIK SKOOR: " + lõplikSkoor + " punkti\n" +
                        "(Läbimiseks vajad: " + LÄBIMISE_PIIR + " pumkti)",
                "Tulemused",
                JOptionPane.INFORMATION_MESSAGE);

        // Näita lõpptulemust
        if (lõplikSkoor >= LÄBIMISE_PIIR) {
            JOptionPane.showMessageDialog(null,
                    "SA LÄBISID EKSAMI!\n\n" + getLäbimiseSõnum(teadmised, õnn),
                    "Õnnitlused :)",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "SA KUKKUSID EKSAMI LÄBI...\n\n" + getLäbikukkumiseSünum(teadmised, õnn),
                    "Paraku...",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Tagastab läbimise sõnumi
    private String getLäbimiseSõnum(int teadmised, int õnn) {
        if (teadmised >= LÄBIMISE_PIIR) {
            return "Selgub, et õppimine ei olegi scam.";
        }
        else if (õnn >= 15){
            return "Sa teadsid peaaegu mitte midagi, aga õnn oli sinu poolel.";
        }
        else {
            return "Sa libisesid napilt läbi. Aga hei - läbitud on läbitud.";
        }
    }

    // Tagastab läbikukkumise sõnumi
    private String getLäbikukkumiseSünum(int teadmised, int õnn) {
        if (teadmised < 25) {
            return "Sa veetsid rohkem aega Netfliksis kui märkmetes.\nŠokeeriv tulemus. Tõeliselt šokeeriv.";
        }
        else if (teadmised >= LÄBIMISE_PIIR) {
            return "Kehv õnn. Sa proovisid päriselt, aga universum ütles ei.\nMitte sinu päev.";
        }
        else {
            return "Järgmine kord parem?";
        }
    }
}

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Random;

// See klass juhib kogu mängu loogikat
// See ühendab kõik teised klassid omavahel
public class MänguKontrollija {

    private Stage lava;
    private Õpilane õpilane;
    private MänguVaade mänguVaade;
    private NõuandeGeneraator nõuandeGeneraator = new NõuandeGeneraator();
    private Random random = new Random();
    private Tegevus[] tegevused;

    private int päevaNr = 1;
    private int valikNr = 1;

    // See näitab, kas mäng on juba lõppenud
    // Seda on vaja, et pärast eksamit ei saaks klaviatuuriga mängu edasi jätkata
    private boolean mängLõppenud = false;

    public MänguKontrollija(Stage lava) {
        this.lava = lava;
        tegevused = TegevusteLooja.looTegevused();
    }

    // Näitab algusakent
    public void naitaAlgusAkent() {
        AlgusVaade algusVaade = new AlgusVaade(this);
        Scene stseen = algusVaade.looStseen();
        lava.setTitle("Eksami simulaator");
        lava.setScene(stseen);
        lava.show();
    }

    // Käivitab mängu pärast nime sisestamist
    public void alustaMängu(String nimi) {
        try {
            if (nimi == null || nimi.trim().isEmpty()) {
                throw new IllegalArgumentException("Nimi ei tohi olla tühi.");
            }
            õpilane = new Õpilane(nimi.trim());

            // Uue mängu alguses paneme loendurid uuesti algusesse
            päevaNr = 1;
            valikNr = 1;
            mängLõppenud = false;

            FailiHaldur.alustaUutMangu(õpilane.getNimi());
            näitaMänguAlguseTeadet();
            avaMänguAken();

        } catch (IllegalArgumentException e) {
            näitaHoiatus("Vale sisend", e.getMessage());
        }
    }

    // Näitab teadet siis, kui mäng päriselt algab.
    private void näitaMänguAlguseTeadet() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mäng algab!");
        alert.setHeaderText("Tere tulemast, " + õpilane.getNimi() + "!");
        alert.setContentText("Eksamini on jäänud 3 päeva.\n" + "Palju edu!");
        alert.showAndWait();
    }

    // Avab põhilise mänguakna
    private void avaMänguAken() {
        mänguVaade = new MänguVaade(this);
        Scene stseen = mänguVaade.looStseen();
        lava.setTitle("Eksami Simulaator");
        lava.setScene(stseen);
        lava.show();
        uuendaEkraani();
    }

    // Töötleb mängija valikut
    public void teeValik(int valik) {
        try {
            // Kui mäng on juba lõppenud, siis uusi valikuid enam ei töötle
            if (mängLõppenud) {
                return;
            }

            if (õpilane == null) {
                throw new IllegalStateException("Mängijat ei ole loodud.");
            }

            if (valik < 1 || valik > 5) {
                throw new IllegalArgumentException("Valik peab olema 1 kuni 5.");
            }

            // Kui mängija õppis liiga palju järjest
            if (valik == 1 && õpilane.getÕppimisJärjestus() >= 3) {
                throw new IllegalArgumentException("Oled juba 3 korda järjest õppinud. Puhka natuke!");
            }

            Tegevus tegevus = tegevused[valik - 1];
            int teadmised = tegevus.getTeadmistekasv();
            int enesekindlus = tegevus.getEnesekindlusMuutus();
            String lisaTekst = "";

            if (valik == 4) {
                int episoodid = 1 + random.nextInt(3);

                if (episoodid > 1) {
                    enesekindlus -= (episoodid - 1) * 3;
                    lisaTekst = "Vaatasid " + episoodid + " episoodi. Ups.";
                } else {
                    lisaTekst = "Vaatasid ainult 1 episoodi. Muljetavaldav enesekontroll.";
                }
            }

            õpilane.lisaTeadmised(teadmised);
            õpilane.lisaEnesekindlus(enesekindlus);
            uuendaÕppimiseJärjestust(valik);
            näitaTegevuseTulemust(tegevus, teadmised, enesekindlus, lisaTekst);

            String logiRida = "Päev " + päevaNr +
                    ", valik " + valikNr +
                    ": " + tegevus.getNimi() +
                    " | teadmised " + teadmised +
                    ", enesekindlus " + enesekindlus +
                    ". " + lisaTekst;

            mänguVaade.lisaLogiTekst(logiRida);
            FailiHaldur.kirjutaLogisse(logiRida);

            liiguEdasi();

            // Ekraani uuendame ainult siis, kui mäng ei lõppenud eksamiga
            if (!mängLõppenud) {
                uuendaEkraani();
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            näitaHoiatus("Vale tegevus", e.getMessage());
        }
    }

    // Näitab pärast iga tegevust väikest tulemusakent
    private void näitaTegevuseTulemust(Tegevus tegevus, int teadmised, int enesekindlus, String lisaTekst) {
        String tekst =
                tegevus.getKirjeldus() + "\n" + lisaTekst + "\n\n" +
                "Teadmised: " + (teadmised >= 0 ? "+" : "") + teadmised + "\n" +
                "Enesekindlus: " + (enesekindlus >= 0 ? "+" : "") + enesekindlus;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tulemus");
        alert.setHeaderText(tegevus.getNimi());
        alert.setContentText(tekst);
        alert.showAndWait();
    }

    // Liigub järgmise päeva või eksamini
    private void liiguEdasi() {
        valikNr++;

        if (valikNr > 3) {
            näitaPäevaLõpuTeadet();

            String paevaLopuRida = "Päev " + päevaNr + " lõppes.";
            mänguVaade.lisaLogiTekst(paevaLopuRida);
            FailiHaldur.kirjutaLogisse(paevaLopuRida);

            // Kui lõppes kolmas päev, siis läheme kohe eksamile
            if (päevaNr == 3) {
                sooritaEksam();
                return;
            }

            päevaNr++;
            valikNr = 1;
        }
    }

    // Näitab päeva lõpus kokkuvõtet
    private void näitaPäevaLõpuTeadet() {
        String tekst =
                "Päev " + päevaNr + " on läbi!\n\n" +
                "Sinu praegune seisund:\n" + õpilane.getStatsText();

        if (päevaNr < 3) {
            tekst += "\n\nEksamini on jäänud " + (3 - päevaNr) + " päeva.";
        } else {
            tekst += "\n\nHomme on eksamipäev!";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Päeva lõpp");
        alert.setHeaderText("Päev " + päevaNr + " lõppes");
        alert.setContentText(tekst);
        alert.showAndWait();
    }

    // Arvutab eksami tulemuse
    private void sooritaEksam() {
        // Märgime mängu lõppenuks
        // Pärast seda teeValik() enam uusi valikuid ei töötle
        mängLõppenud = true;
        näitaEksamiPäevaTeadet();

        EksamiArvutaja arvutaja = new EksamiArvutaja();
        EksamiTulemus tulemus = arvutaja.arvutaTulemus(õpilane);

        mänguVaade.lisaLogiTekst("\n" + tulemus.getTäisTekst());

        FailiHaldur.kirjutaLogisse("LÕPPTULEMUS: " + õpilane.getNimi() + " - " + tulemus.getLõplikSkoor() + " punkti.");
        FailiHaldur.kirjutaLogisse(tulemus.getTulemusTekst());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eksami tulemus");
        alert.setHeaderText(tulemus.getTulemusTekst());
        alert.setContentText(
                tulemus.getTäisTekst() + "\n\n" +
                getLõpuSõnum(õpilane.getTeadmised(), tulemus.getÕnn(), tulemus.getLõplikSkoor())
        );
        alert.showAndWait();

        mänguVaade.keelaNupud();
    }

    // Valib lõpusõnumi selle järgi, kas mängija läbis eksami või kukkus läbi
    private String getLõpuSõnum(int teadmised, int õnn, int lõplikSkoor) {
        if (lõplikSkoor >= 50) {
            return getLäbimiseSõnum(teadmised, õnn);
        } else {
            return getLäbikukkumiseSõnum(teadmised, õnn);
        }
    }

    // Tagastab läbimise sõnumi
    private String getLäbimiseSõnum(int teadmised, int õnn) {
        if (teadmised >= 50) {
            return "Selgub, et õppimine ei olegi scam.";
        } else if (õnn >= 15) {
            return "Sa teadsid peaaegu mitte midagi, aga õnn oli sinu poolel.";
        } else {
            return "Sa libisesid napilt läbi. Aga hei - läbitud on läbitud.";
        }
    }

    // Tagastab läbikukkumise sõnumi.
    private String getLäbikukkumiseSõnum(int teadmised, int õnn) {
        if (teadmised < 25) {
            return "Sa veetsid rohkem aega Netflixis kui märkmetes.\n" +
                    "Šokeeriv tulemus. Tõeliselt šokeeriv.";
        } else if (teadmised >= 50) {
            return "Kehv õnn. Sa proovisid päriselt, aga universum ütles ei.\n" +
                    "Mitte sinu päev.";
        } else {
            return "Järgmine kord parem?";
        }
    }

    // Näitab teadet enne eksami arvutamist.
    private void näitaEksamiPäevaTeadet() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eksami päev!");
        alert.setHeaderText("EKSAMI PÄEV ON KÄES!");
        alert.setContentText("Sinu lõplikud statistikud:\n" + õpilane.getStatsText());
        alert.showAndWait();
    }

    // Uuendab õppimise järjestust
    private void uuendaÕppimiseJärjestust(int valik) {
        if (valik == 1) {
            õpilane.setÕppimisJärjestus(õpilane.getÕppimisJärjestus() + 1);
        } else {
            õpilane.setÕppimisJärjestus(0);
        }
    }

    // Uuendab ekraani andmed
    private void uuendaEkraani() {
        mänguVaade.uuendaAndmeid(
                õpilane,
                päevaNr,
                valikNr,
                nõuandeGeneraator.getNõuanne()
        );
    }

    // Viib kasutaja tagasi algusaknasse
    // Seda kasutatakse siis, kui mäng on lõppenud ja mängija tahab uuesti alustada
    public void alustaUuesti() {
        mängLõppenud = false;
        õpilane = null;
        mänguVaade = null;

        päevaNr = 1;
        valikNr = 1;

        naitaAlgusAkent();
    }

    // Näitab hoiatusakent
    private void näitaHoiatus(String pealkiri, String tekst) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(pealkiri);
        alert.setHeaderText(pealkiri);
        alert.setContentText(tekst);
        alert.showAndWait();
    }
}

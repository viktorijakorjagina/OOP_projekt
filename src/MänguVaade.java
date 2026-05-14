import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

// See klass loob põhilise mänguakna
// Siin on nupud, punktid, progressiribad ja mängu logi
public class MänguVaade {

    private MänguKontrollija kontrollija;
    private Label pealkiriLabel;
    private Label infoLabel;
    private Label statsLabel;
    private Label nõuanneLabel;
    private ProgressBar teadmisedRiba;
    private ProgressBar enesekindlusRiba;
    private TextArea logiAla;
    private Button õppimineNupp;
    private Button loengNupp;
    private Button ülesandedNupp;
    private Button netflixNupp;
    private Button puhkusNupp;

    // See nupp ilmub nähtavale alles siis, kui eksam on tehtud
    private Button uuestiNupp;

    public MänguVaade(MänguKontrollija kontrollija) {
        this.kontrollija = kontrollija;
    }

    // Loob ja tagastab mänguakna stseeni
    public Scene looStseen() {
        pealkiriLabel = new Label();
        Kujundus.pealkiri(pealkiriLabel);

        infoLabel = new Label();
        statsLabel = new Label();

        nõuanneLabel = new Label();
        nõuanneLabel.setWrapText(true);  // Lubame nõuande tekstil minna mitmele reale
        nõuanneLabel.setMaxWidth(480);

        Kujundus.tavalineTekst(infoLabel);
        Kujundus.tavalineTekst(statsLabel);
        Kujundus.tavalineTekst(nõuanneLabel);

        teadmisedRiba = new ProgressBar(0);
        enesekindlusRiba = new ProgressBar(0);
        teadmisedRiba.setMaxWidth(Double.MAX_VALUE);
        enesekindlusRiba.setMaxWidth(Double.MAX_VALUE);

        looNupud();

        VBox nuppudeKast = new VBox(
                10,
                õppimineNupp,
                loengNupp,
                ülesandedNupp,
                netflixNupp,
                puhkusNupp,
                uuestiNupp
        );

        logiAla = new TextArea();
        logiAla.setEditable(false);
        logiAla.setWrapText(true);
        logiAla.setPrefHeight(180);

        VBox vasakPool = looVasakPool();
        VBox paremPool = looParemPool(nuppudeKast);

        HBox sisu = new HBox(20, vasakPool, paremPool);
        sisu.setPadding(new Insets(25));

        vasakPool.setPrefWidth(320);
        paremPool.setPrefWidth(500);
        HBox.setHgrow(paremPool, Priority.ALWAYS); // Akna suuruse muutumisel sisu venitamine

        VBox juur = new VBox(sisu);
        VBox.setVgrow(sisu, Priority.ALWAYS);
        Kujundus.taustaStiil(juur);

        Scene stseen = new Scene(juur, 950, 620);
        lisaKlaviatuuriJuhtimine(stseen);

        return stseen;
    }

    // Loob mänguakna parema poole
    private VBox looParemPool(VBox nuppudeKast) {
        Label valikudPealkiri = new Label("Mida teed?");
        Kujundus.valikudPealkiri(valikudPealkiri);
        VBox paremPool = new VBox(
                22,
                valikudPealkiri,
                nuppudeKast,
                new Label("Mängu logi:"),
                logiAla
        );

        Kujundus.kaart(paremPool);
        return paremPool;
    }

    // Loob mänguakna vasaku poole
    private VBox looVasakPool() {
        VBox vasakPool = new VBox(
                14,
                pealkiriLabel,
                infoLabel,
                nõuanneLabel,
                new Label("Teadmised:"),
                teadmisedRiba,
                new Label("Enesekindlus:"),
                enesekindlusRiba,
                statsLabel
        );

        vasakPool.setMinWidth(200);

        Kujundus.kaart(vasakPool);
        return vasakPool;
    }

    // Loob kõik tegevuste nupud
    private void looNupud() {
        õppimineNupp = looValikuNupp("1 - Õppimine");
        loengNupp = looValikuNupp("2 - Loengu vaatamine");
        ülesandedNupp = looValikuNupp("3 - Ülesannete lahendamine");
        netflixNupp = looValikuNupp("4 - Ainult üks episood");
        puhkusNupp = looValikuNupp("5 - Puhkamine");

        // Uue mängu alustamise nupp
        uuestiNupp = looValikuNupp("Alusta uuesti");
        uuestiNupp.setVisible(false);
        uuestiNupp.setManaged(false);

        õppimineNupp.setOnAction(e -> kontrollija.teeValik(1));
        loengNupp.setOnAction(e -> kontrollija.teeValik(2));
        ülesandedNupp.setOnAction(e -> kontrollija.teeValik(3));
        netflixNupp.setOnAction(e -> kontrollija.teeValik(4));
        puhkusNupp.setOnAction(e -> kontrollija.teeValik(5));
        // Kui mäng on lõppenud, saab selle nupuga minna tagasi algusaknasse
        uuestiNupp.setOnAction(e -> kontrollija.alustaUuesti());
    }

    // Loob ühe nupu
    private Button looValikuNupp(String tekst) {
        Button nupp = new Button(tekst);
        nupp.setMaxWidth(Double.MAX_VALUE);
        nupp.setPrefHeight(60);
        Kujundus.nupp(nupp);
        return nupp;
    }

    // Lisab klaviatuuriga juhtimise
    public void lisaKlaviatuuriJuhtimine(Scene stseen) {
        stseen.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DIGIT1) kontrollija.teeValik(1);
            if (e.getCode() == KeyCode.DIGIT2) kontrollija.teeValik(2);
            if (e.getCode() == KeyCode.DIGIT3) kontrollija.teeValik(3);
            if (e.getCode() == KeyCode.DIGIT4) kontrollija.teeValik(4);
            if (e.getCode() == KeyCode.DIGIT5) kontrollija.teeValik(5);
        });
    }

    // Uuendab ekraanil olevaid andmeid
    public void uuendaAndmeid(Õpilane õpilane, int päevaNr, int valikNr, String nõuanne) {
        pealkiriLabel.setText("Päev " + päevaNr + " / 3");
        infoLabel.setText("Valik " + valikNr + " / 3");
        nõuanneLabel.setText("Nõuanne: " + nõuanne);
        statsLabel.setText("Mängija: " + õpilane.getNimi() + "\n" + õpilane.getStatsText());

        teadmisedRiba.setProgress(Math.min(õpilane.getTeadmised() / 100.0, 1));
        enesekindlusRiba.setProgress(Math.min(õpilane.getEnesekindlus() / 100.0, 1));

        õppimineNupp.setDisable(õpilane.getÕppimisJärjestus() >= 3);
    }

    // Lisab teksti logi tekstialasse
    public void lisaLogiTekst(String tekst) {
        logiAla.appendText(tekst + "\n");
    }

    // Pärast eksamit ei saa enam tavalisi tegevusi valida
    // Näitame uut nuppu, millega saab mängu uuesti alustada
    public void keelaNupud() {
        õppimineNupp.setDisable(true);
        loengNupp.setDisable(true);
        ülesandedNupp.setDisable(true);
        netflixNupp.setDisable(true);
        puhkusNupp.setDisable(true);

        uuestiNupp.setVisible(true);
        uuestiNupp.setManaged(true);
    }
}

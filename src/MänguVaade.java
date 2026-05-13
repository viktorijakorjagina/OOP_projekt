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

// See klass loob põhilise mänguakna.
// Siin on nupud, punktid, progressiribad ja mängu logi.
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
        Kujundus.tavalineTekst(infoLabel);
        Kujundus.tavalineTekst(statsLabel);
        Kujundus.tavalineTekst(nõuanneLabel);

        teadmisedRiba = new ProgressBar(0);
        enesekindlusRiba = new ProgressBar(0);
        teadmisedRiba.setMaxWidth(Double.MAX_VALUE);
        enesekindlusRiba.setMaxWidth(Double.MAX_VALUE);

        looNupud();

        VBox nuppudaKast = new VBox(10, õppimineNupp, loengNupp, ülesandedNupp, netflixNupp, puhkusNupp);

        logiAla = new TextArea();
        logiAla.setEditable(false);
        logiAla.setWrapText(true);

        VBox vasakPool = looVasakPool();
        VBox paremPool = looParemPool(nuppudaKast);

        HBox sisu = new HBox(20, vasakPool, paremPool);
        sisu.setPadding(new Insets(25));
        // Akna suuruse muutumisel sisu venitamine
        HBox.setHgrow(vasakPool, Priority.ALWAYS);
        HBox.setHgrow(paremPool, Priority.ALWAYS);

        VBox juur = new VBox(sisu);
        VBox.setVgrow(sisu, Priority.ALWAYS);
        Kujundus.taustaStiil(juur);

        Scene stseen = new Scene(juur, 950, 620);
        lisaKlaviatuuriJuhtimine(stseen);
        return stseen;
    }

    // Loob mänguakna parema poole
    private VBox looParemPool(VBox nuppudaKast) {
        VBox paremPool = new VBox(
                14,
                new Label("Valikud:"),
                nuppudaKast,
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

        õppimineNupp.setOnAction(e -> kontrollija.teeValik(1));
        loengNupp.setOnAction(e -> kontrollija.teeValik(2));
        ülesandedNupp.setOnAction(e -> kontrollija.teeValik(3));
        netflixNupp.setOnAction(e -> kontrollija.teeValik(4));
        puhkusNupp.setOnAction(e -> kontrollija.teeValik(5));
    }

    // Loob ühe nupu
    private Button looValikuNupp(String tekst) {
        Button nupp = new Button(tekst);
        nupp.setMaxWidth(Double.MAX_VALUE);
        nupp.setPrefHeight(45);
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

    // Pärast eksamit ei saa enam nuppe vajutada
    public void keelaNupud() {
        õppimineNupp.setDisable(true);
        loengNupp.setDisable(true);
        ülesandedNupp.setDisable(true);
        netflixNupp.setDisable(true);
        puhkusNupp.setDisable(true);
    }
}

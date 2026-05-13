import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

// See klass loeb mängu algusakna.
// Siin küsitakse mängija nime ja näidatakse lühikest juhendit.
public class AlgusVaade {

    private MänguKontrollija kontrollija;

    public AlgusVaade(MänguKontrollija kontrollija) {
        this.kontrollija = kontrollija;
    }

    // Loob ja tagastab algusakna stseeni
    public Scene looStseen() {
        Label pealkiri = new Label("EKSAMI SIMULAATOR");
        Kujundus.pealkiri(pealkiri);

        Label kirjeldus = new Label(
                "Sul on 3 päeva enne ekasmit.\n" +
                "Iga päev teed 3 valikut (õppimine, puhkamine jne).\n" +
                "Läbimiseks vajad 50+ punkti.\n" +
                "Juhtimine: \n" +
                "- hiirega saab vajutada nuppe\n" +
                "- klaviatuuril saab kasutada klahve 1, 2, 3, 4 ja 5"
        );
        Kujundus.tavalineTekst(kirjeldus);

        TextField nimiVäli = new TextField();
        nimiVäli.setPromptText("Sisesta oma nimi");
        nimiVäli.setMaxWidth(350);

        Button alustaNupp = new Button("Alusta mängu");
        Kujundus.rohelineNupp(alustaNupp);

        Label eelmineTulemus = new Label("Viimane logirida: " + FailiHaldur.loeViimaneRida());
        Kujundus.tavalineTekst(eelmineTulemus);

        VBox kaart = new VBox(18, pealkiri, kirjeldus, nimiVäli, alustaNupp, eelmineTulemus);
        kaart.setAlignment(Pos.CENTER);
        kaart.setMaxWidth(550);
        Kujundus.kaart(kaart);

        VBox juur = new VBox(kaart);
        juur.setAlignment(Pos.CENTER);
        juur.setPadding(new Insets(30));
        Kujundus.taustaStiil(juur);

        alustaNupp.setOnAction(e -> kontrollija.alustaMängu(nimiVäli.getText())); // mängu alustamine nupuga
        nimiVäli.setOnAction(e -> kontrollija.alustaMängu(nimiVäli.getText())); // Enter-klahviga

        return new Scene(juur, 850, 600);
    }
}

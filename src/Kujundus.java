import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

// See klass hoiab programmi kujunduse meetodeid
public class Kujundus {
    public static void taustaStiil(VBox juur) {
        juur.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #f6efff, #e6f4ff);" +
                        "-fx-font-family: 'Segoe UI';"
        );
    }
    public static void pealkiri(Label label) {
        label.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2d2457;"
        );
    }
    public static void tavalineTekst(Label label) {
        label.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-text-fill: #2f2f2f;"
        );
    }
    public static void nupp(Button nupp) {
        nupp.setStyle(
                "-fx-background-color: #6c63ff;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 14;" +
                        "-fx-padding: 10 18 10 18;"
        );
    }
    public static void rohelineNupp(Button nupp) {
        nupp.setStyle(
                "-fx-background-color: #38b26c;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 14;" +
                        "-fx-padding: 10 18 10 18;"
        );
    }
    public static void kaart(Region region) {
        region.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-padding: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.16), 14, 0, 0, 5);"
        );
    }
}

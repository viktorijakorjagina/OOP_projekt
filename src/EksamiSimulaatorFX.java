import javafx.application.Application;
import javafx.stage.Stage;

// See on programmi peaklass
public class EksamiSimulaatorFX extends Application {

    @Override
    public void start(Stage lava) throws Exception {
        MänguKontrollija kontrollija = new MänguKontrollija(lava);
        kontrollija.näitaAlgusAkent();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
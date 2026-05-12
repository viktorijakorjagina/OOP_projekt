import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// See klass tegeleb faili kirjutamise ja failist lugemisega

// Klass teeb järgmised tegevused:
// 1. kirjutab andmeid faili
// 2. loeb andmeid failist
public class FailiHaldur {

    private static final String failinimi = "mangulogid.txt";

    // Kirjutab ühe rea faili lõppu
    // true tähendab, et vana faili sisu ei kustutata ära
    public static void kirjutaLogisse(String tekst) {
        try (FileWriter kirjutaja = new FileWriter(failinimi, true)) {
            kirjutaja.write(tekst + "\n");
        } catch (IOException e) {
            System.out.println("Faili kirjutamisel tekkis viga: " + e.getMessage());
        }
    }

    // Loeb failist viimase rea
    // Seda kasutame mängu alguses, et näidata eelmist tulemust
    public static String loeViimaneRida() {
        File fail = new File(failinimi);

        // kui faili veel ei ole, siis tähendab see, et mängu pole varem mängitud
        if (!fail.exists()) {
            return "Varasemat mängu ei leitud.";
        }
        String viimaneRida = "Varasemat mängu ei leitud.";

        try (Scanner scanner = new Scanner(fail)) {
            while (scanner.hasNextLine()) {
                viimaneRida = scanner.nextLine();
            }
        } catch (IOException e) {
            return "Faili lugemisel tekkis viga.";
        }
        return viimaneRida;
    }

    // Lisab faili tühja rea ja uue mängu alguse
    public static void alustaUutMangu(String nimi) {
        kirjutaLogisse("");
        kirjutaLogisse("UUS MÄNG: " + nimi);
    }
}

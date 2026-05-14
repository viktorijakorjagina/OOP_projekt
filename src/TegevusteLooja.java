// See klass loob kõik mängus kasutatavad tegevused
// Eraldi klass on mugav, sest tegevuste nimekiri ei sega mängu juhtimise koodi
public class TegevusteLooja {
    
    // Meetod tagastab tegevuste massiivi
    public static Tegevus[] looTegevused() {
        return new Tegevus[] {

                // Tegevus(nimi, kirjeldus, minTeadmised, maxTeadmised, enesekindlusMuutus)
                new Tegevus(
                        "Õppimine",
                        "Avad märkmikud ja õpid päriselt.",
                        3,
                        20,
                        5
                ),
                new Tegevus(
                        "Loengu vaatamine",
                        "Vaatad loenguvideot ja loodad, et midagi jääb meelde.",
                        0,
                        10,
                        10
                ),
                new Tegevus(
                        "Ülesannete lahendamine",
                        "Lahendad ülesandeid ja saad targemaks.",
                        0,
                        20,
                        5
                ),
                new Tegevus(
                        "Ainult üks episood",
                        "Avad Netflixi ainult üheks episoodiks.",
                        0,
                        0,
                        -5
                ),
                new Tegevus(
                        "Puhkamine",
                        "Otsustad puhata. Aju vajab pausi.",
                        0,
                        0,
                        -5
                )
        };
    }
}

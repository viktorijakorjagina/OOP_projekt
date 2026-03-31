import java.util.Random;

// Klass NouandeGeneraator annab mängijale iga päev juhusliku nõuande
public class NouandeGeneraator {

    private Random random = new Random();

    // Nõuanded
    private String[] nõuanded = {
            "Kui professor küsib, ütle et õppisid. Tõestust ei nõua keegi.",
            "Õppimine on hea, aga magamine om parem. Valik on sinu.",
            "Mõned inimesed õpivad päev enne eksamit. Sina oled erilisem.",
            "Ära muretse. Muretsemine võtab aega, mida võiks veeta Netflixis.",
            "Kui eksam on raske, süüdista professorit.",
            "Proovi õppida und nähes. Ei tööta, aga proovi.",
            "Kui sa vastust ei tea, kirjuta see enesekindlalt. Äkki keegi ei kontrolli.",
            "Veel üks episood ei tee haiget.",
            "Kui midagi ei tööta, ära mine eksamile. Probleem lahendatud.",
            "Enesekindlus on võti. Teadmised on ka võti. Sa vajad mõlemat võtit.",
            "Uni on nõrkade jaoks.",
            "Kas oled proovinud lihtsalt... mitte läbi kukkuda?",
    };

    // Tagastab juhusliku nõuande
    public String getNõuanne() {
        int indeks = random.nextInt(nõuanded.length);
        return nõuanded[indeks];
    }
}
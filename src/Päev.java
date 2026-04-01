import javax.swing.JOptionPane;
import java.util.Random;

// Klass Paev korraldab ühe mängupäeva kulgu
// Iga päev teeb mängija 3 valikut, mida teha

// Klass jälgib, mitu korda järjest valiti "Õppimine" -
// kui mängija õpib rohkem kui 3 korda järjest, blokeeritakse see valik
public class Päev {

    // Päeva järjekorranumber (1 kuni 5)
    private int päevaNr;

    // Loendur - mitu korda järjest on mängija valinud "Õppimine"
    // Kui see jõuab 3-ni, blokeeritakse õppimine järgmisel korral
    private int õppimisJärjestus;

    // Massiiv kõigist võimalikest tegevustest, mida mängija saab valida
    private Tegevus[] tegevused;

    // Random objekt juhusliku episoodide arvu arvutamiseks
    private Random random = new Random();

    // Konstruktor - loob uue päeva ja seadistab kõik võimalikud tegevused
    // @param päevaNr mitmenda päevaga on tegemist (1-5)
    public Päev(int päevaNr) {
        this.päevaNr = päevaNr;
        this.õppimisJärjestus = 0; // päeva alguses pole keegi veel õppinud

        // Loome kõik 5 tegevust massiivi
        // Tegevus(nimi, kirjeldus, minTeadmised, maxTeadmised, enesekindlusMuutus)
        // minTeadmised ja maxTeadmised määravad juhusliku vahemiku teadmiste kasvuks
        tegevused = new Tegevus[] {

                // Valik 1: päris õppimine - annab kõige rohkem teadmisi
                new Tegevus("Õppimine", "Avad märkmikud ja õpid päriselt.",
                        10, 20, 5),

                // Valik 2: loengu vaatamine - annab rohkem enesekindlust kui teadmisi
                new Tegevus("Loengu vaatamine", "Leiad loenguvideo. Väga akadeemiline suhtumine.",
                        0, 10, 10),

                // Valik 3: ülesannete lahendamine - sama hea kui õppimine
                new Tegevus("Ülesannete lahendamine", "Istud maha ja lahendad ülesandeid.",
                        10, 20, 5),

                // Valik 4: Netflix - ei anna teadmisi, vähendab enesekindlust
                // Eriefekt: random otsustab mitu episoodi tegelikult vaadatakse
                new Tegevus("Ainult üks episood", "Avad Netflixi ainult üheks episoodiks.",
                        0, 0, -5),

                // Valik 5: puhkamine - ei anna mitte midagi
                new Tegevus("Puhkamine", "Otsustad puhata. Sa väärid seda.",
                        0, 0, -5)
        };
    }

    // Käivitab ühe täispäeva mängu
    // Mängija teeb 3 valikut järjest
    // Tagastab massiivi kus: [0] = teadmised kokku, [1] = enesekindlus kokku
    // @param õpilane mängija objekt, kelle statistikuid uuendatakse
    public int[] käivitaPäev(Õpilane õpilane) {
        int teadmisedKokku = 0;
        int enesekindlusKokku = 0;

        // Kordame 3 korda - iga kord küsime mängijalt ühe valiku
        for (int valik = 1; valik <= 3; valik++) {

            // teeÜksTegevus tagastab massiivi [teadmised, enesekindlus]
            int[] tulemus = teeÜksTegevus(õpilane, valik);

            // liidame tulemused päeva kogusummale
            teadmisedKokku += tulemus[0];
            enesekindlusKokku += tulemus[1];
        }
        // Tagastame päeva kokkuvõtte
        return new int[]{teadmisedKokku, enesekindlusKokku};
    }

    // Korraldab ühe tegevusvaliku - näitab menüüd, loeb sisendi, rakendab tulemuse
    // @param õpilane mängija objekt
    // @param valikNr mitmenda valikuga on tegemist (1, 2 või 3)
    // @return massiiv [teadmistekasv, enesekindluseMuutus]
    private int[] teeÜksTegevus(Õpilane õpilane, int valikNr) {

        // Koostame teksti, mida mängijale näitatakse aknas
        // Lisame hetkeseisu (teadmised ja enesekindlus) ja kõik valikud
        String valikuteTekst =
                "Päev " + päevaNr + "  |  Valik " + valikNr + "/3\n\n" +
                        õpilane.getStatsText() + "\n\n" +
                        "Mida teed?\n\n" +
                        "1 - Õppimine     (+10 kuni +20 teadmised, +5 enesekindlus)\n" +
                        "2 - Loengu vaatamine     (+0 kuni +10 teadmised, +10 enesekindlus)\n" +
                        "3 - Ülesannete lahendamine     (+10 kuni +20 teadmised, +5 enesekindlus)\n" +
                        "4 - 'Ainult üks episood'      (0 teadmised, -5 enesekindlus)\n" +
                        "5 - Puhkamine     (0 teadmised, -5 enesekindlus)"
                ;

        // Kui mängija on õppinud 3 korda järjest, lisame hoiatuse teksti
        if (õppimisJärjestus >= 3) {
            valikuteTekst += "\n\n Oled " + õppimisJärjestus + " korda järjest õppinud.\n"
                    + "Puhka natuke!";
        }

        // Kasutame while tsüklit - kordame kuni mängija sisestab kehtiva numbri
        int valitud = -1;
        while (true) {
            // Näitame sisestamise akent ja ootame mängija vastust
            String sisend = JOptionPane.showInputDialog(null,
                    valikuteTekst,
                    "Eksami Simulaator - Päev " + päevaNr,
                    JOptionPane.QUESTION_MESSAGE);

            // Kui mängija sulges akna (vajutas X), küsime kas ta tahab mängu lõpetada
            if (sisend == null) {
                int kinnitus = JOptionPane.showConfirmDialog(null,
                        "Kas tahad mängu lõpetada?",
                        "Lahkumine",
                        JOptionPane.YES_NO_OPTION);
                if (kinnitus == JOptionPane.YES_OPTION) {
                    System.exit(0);  // lõpetame programmi
                }
                continue;  // kui ei taha lõpetada, näitame menüüd uuesti
            }

            // Proovime sisestatu arvuks muuta
            // Kui sisend ei ole number, tekib NumberFormatException
            try {
                valitud = Integer.parseInt(sisend.trim());  // eemaldame tühikud
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Palun sisesta number 1 kuni 5.",
                        "Vale sisend",
                        JOptionPane.WARNING_MESSAGE);
                continue;  // palume uuesti sisestada
            }

            // Kontrollime kas number on lubatud vahemikus (1 kuni 5)
            if (valitud < 1 || valitud > 5) {
                JOptionPane.showMessageDialog(null,
                        "Palun sisesta number 1 kuni 5.",
                        "Vale sisend",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }

            // Kontrollime kas õppimine on blokeeritud
            if (valitud == 1 && õppimisJärjestus >= 3) {
                JOptionPane.showMessageDialog(null,
                        "Ei! Oled liiga kaua õppinud järjest!",
                        "Blokeeritud!",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
            break;  // kõik kontrollid läbitud - väljume tsüklist
        }

        // Võtame valitud tegevuse massiivist
        // Massiivi indeks on 1 vähem kui valik
        Tegevus tegevus = tegevused[valitud - 1];

        // Küsime tegevuselt kui palju teadmisi ja enesekindlust see annab
        // getTeadmistekasv() tagastab juhusliku arvu min ja max vahel
        int teadmistekasv = tegevus.getTeadmistekasv();
        int enesekindlusMuutus = tegevus.getEnesekindlusMuutus();

        // Eriefekt Netflixi valikule
        // Random otsustab kas vaadatakse 1, 2 või 3 episoodi
        String lisaSõnum = "";
        if (valitud == 4) {
            int episoodid = 1 + random.nextInt(3);
            if (episoodid > 1) {
                lisaSõnum = "\n Vaatasid " + episoodid + " episoodi. Ups.";
                enesekindlusMuutus -= (episoodid - 1) * 3;
            } else {
                lisaSõnum = "\n Vaatasid ainult 1 episoodi. Muljetavaldav enesekontroll.";
            }
        }

        // Rakendame mutused mängija objektile
        õpilane.lisaTeadmised(teadmistekasv);
        õpilane.lisaEnesekindlus(enesekindlusMuutus);

        // Uuendame õppimise järjestuse loenduri
        if (valitud == 1) {
            õppimisJärjestus++;
        } else {
            õppimisJärjestus = 0;
        }

        // Koostame ja näitame tulemuse akna
        String tulemusTekst =
                tegevus.getKirjeldus() + lisaSõnum + "\n\n" +
                        "Teadmised: +" + teadmistekasv + "\n" +
                        "Enesekindlus: " + (enesekindlusMuutus >= 0 ? "+" : "") +
                        enesekindlusMuutus;

        JOptionPane.showMessageDialog(null,
                tulemusTekst,
                "Tulemus",
                JOptionPane.INFORMATION_MESSAGE);

        return new int[] {teadmistekasv, enesekindlusMuutus};
    }
}

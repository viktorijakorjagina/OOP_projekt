import javax.swing.*;

/**
 * Peaklass - Eksami Simulaatori käivitamine.
 *
 * EKSAMI SIMULAATOR - Prokrastineeriva üliõpilase ellujäämismäng.
 * Sul on 3 päeva eksamiks valmistumiseks.
 * Õpi, prokrastineeri ja looda parimat.
 *
 * Autorid: Maria Elisa Vassiljeva, Viktorija Korjagina
 */
public class Peaklass {

    public static void main(String[] args) {

        // Tervitussõnum
        JOptionPane.showMessageDialog(null,
                "EKSAMI SIMULAATOR\n" +
                        "Prokrastineeriva Üliõpilase Ellujäämismäng\n\n" +
                        "KUIDAS MÄNGIDA:\n" +
                        "   - Sul on 3 päeva enne ekasmit.\n" +
                        "   - Iga päev teed 3 valikut (õppimine, puhkamine jne).\n" +
                        "   - Teadmised = mida sa tegelikult tead.\n" +
                        "   - Enesekindlus = mida sa arvad, et tead.\n" +
                        "   - Eksami päeval mängib rolli ka õnn.\n" +
                        "   - Läbimiseks vajad 50+ punkti.\n" +
                        "   - !Hoiatus!: õppida ei saa rohkem kui 3 korda järjest.\n" +
                        "   - Isegi aju vajab pausi.\n",
                "Tere tulemast!",
                JOptionPane.INFORMATION_MESSAGE);

        // Küsi mängija nime
        String nimi = JOptionPane.showInputDialog(null,
                "Sisesta oma nimi: ",
                "Kes sa oled?",
                JOptionPane.QUESTION_MESSAGE);

        // Kui nimi on tühi või aken suleti
        if (nimi == null | nimi.trim().isEmpty()) {
            nimi = "Anonüümne Üliõpilane";
        }

        Õpilane õpilane = new Õpilane(nimi.trim());
        NõuandeGeneraator nõuandeGeneraator = new NõuandeGeneraator();

        JOptionPane.showMessageDialog(null,
                "Tere tulemast, " + õpilane.getNimi() + "!\n\n" +
                        "Eksamini on jäänud 3 päeva.\nPalju edu!",
                "Mäng algab!",
                JOptionPane.INFORMATION_MESSAGE);

        // Käivita 3 päeva
        for (int päevaNr = 1; päevaNr <= 3; päevaNr++) {

            // Päeva alguse nõuanne
            JOptionPane.showMessageDialog(null,
                    "PÄEV " + päevaNr + "\n\n" +
                            "Päeva nõuanne:\n\"" + nõuandeGeneraator.getNõuanne() + "\"",
                    "Mäng algab!",
                    JOptionPane.INFORMATION_MESSAGE);

            // Päeva käivitamine
            Päev päev = new Päev(päevaNr);
            päev.käivitaPäev(õpilane);

            // Päeva lõpu kokkuvõte
            JOptionPane.showMessageDialog(null,
                    "Päev " + päevaNr + " on läbi!\n\n" +
                    "Sinu praegune seisund:\n" +
                    õpilane.getStatsText()+
                    (päevaNr < 3 ? "\n\nEksamini on jäänud " + (3 - päevaNr) + " päeva." : "\n\nHomme on eksamipäev!"),
                    "Päeva lõpp",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // Eksamipäev
        Eksam eksam = new Eksam();
        eksam.sooritaEksam(õpilane);

        JOptionPane.showMessageDialog(null,
                "Täname mängimist Eksami Simulaatoit!",
                "Mäng läbi",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
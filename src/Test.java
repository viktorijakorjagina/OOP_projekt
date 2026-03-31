public class Test {
    static void main() {
        Õpilane opilane = new Õpilane("Masha");

        System.out.println(opilane.getStatsText());

        opilane.lisaTeadmised(40);
        opilane.lisaEnesekindlus(10);

        System.out.println(opilane.getStatsText());

        Eksam eksam = new Eksam();

        eksam.sooritaEksam(opilane);
    }
}


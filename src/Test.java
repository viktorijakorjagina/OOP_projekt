public class Test {
    static void main() {
        Õpilane opilane = new Õpilane("Masha");

        System.out.println(opilane.getStatsText());

        opilane.lisaTeadmised(-20);
        opilane.lisaEnesekindlus(1000);

        System.out.println(opilane.getStatsText());
    }
}

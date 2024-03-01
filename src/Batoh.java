import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Batoh {
    private ArrayList<String> obsah;
    private int kapacita;
    private int damage;
    private int pocetKlicu;

    public Batoh(int kapacita, int damage, String... predmety) {
        this.kapacita = kapacita;
        this.damage = damage;
        obsah = new ArrayList<>(kapacita);
        inicializujObsah(predmety);
        pocetKlicu = 0;
    }

    private void inicializujObsah(String... predmety) {
        obsah.addAll(Arrays.asList(predmety));
        for (int i = obsah.size(); i < kapacita; i++) {
            obsah.add("X");
        }
    }

    public boolean pridejPredmet(String predmet) {
        for (int i = 0; i < kapacita; i++) {
            if (obsah.get(i).equals("X")) {
                obsah.set(i, predmet);
                if (predmet.equals("Klíč")) {
                    pocetKlicu++;
                    if (pocetKlicu >= 4) {
                        System.out.println("Gratuluji, vyhrál jsi!");
                        System.exit(0);
                    }
                }
                return true;
            }
        }
        return false;
    }


    public void vypisObsah() {
        System.out.println("Obsah baťohu:");
        for (String predmet : obsah) {
            if (predmet.equals("Rezavý meč")) {
                System.out.println(predmet + " Damage: " + damage);
            } else {
                System.out.println(predmet);
            }
        }
    }

}
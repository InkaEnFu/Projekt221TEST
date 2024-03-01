import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class  Mapa {
    private HashMap<Integer, Lokace> svet;

    public Mapa() {
        svet = new HashMap<>();
    }

    public void nactiSvetZSouboru(String cestaKsouboru) {
        try (BufferedReader br = new BufferedReader(new FileReader(cestaKsouboru))) {
            String radek;
            while ((radek = br.readLine()) != null) {
                if (radek.startsWith("id")) {
                    continue;
                }

                String[] polozky = radek.split(" ");
                if (polozky.length != 8) {
                    System.err.println("Chybný formát řádku: " + radek);
                    continue;
                }

                int id = Integer.parseInt(polozky[0]);
                String nazev = polozky[1];
                int sever = Integer.parseInt(polozky[2]);
                int jih = Integer.parseInt(polozky[3]);
                int vychod = Integer.parseInt(polozky[4]);
                int zapad = Integer.parseInt(polozky[5]);
                boolean obsahujeKlic = Boolean.parseBoolean(polozky[6]);
                boolean obsahujeTruhlu = Boolean.parseBoolean(polozky[7]); // Načtení hodnoty pro obsah truhly

                Lokace lokace = new Lokace(id, nazev, sever, jih, vychod, zapad, obsahujeKlic, obsahujeTruhlu, svet);
                svet.put(id, lokace);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void vypisObsahSouboru(String nazevSouboru) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nazevSouboru));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Nastala chyba při čtení ze souboru: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Lokace getLokaceById(int id) {
        return svet.get(id);
    }

    public void vygenerujPriseruVMistnosti() {
        Random random = new Random();
        ArrayList<Lokace> mistnostiSPriserou = new ArrayList<>();
        int pocetPriser = 0;
        for (Lokace lokace : svet.values()) {
            if (lokace.getId() == 1) continue;
            if (pocetPriser >= 8) break;
            if (!mistnostiSPriserou.contains(lokace)) {
                TypPrisery typPrisery = TypPrisery.values()[random.nextInt(TypPrisery.values().length)];
                Prisera prisera = new Prisera(typPrisery);
                lokace.setPrisera(prisera);
                mistnostiSPriserou.add(lokace);
                pocetPriser++;

            }
        }
    }

    public void attack(Hrac hrac, Lokace lokace) {
        if (lokace.getPrisera() != null) {
            Prisera prisera = lokace.getPrisera();
            int zivotyPrisery = prisera.getTypPrisery().getZivoty();
            int dmgHrace = hrac.getDamage();

            while (zivotyPrisery > 0 && hrac.getZivoty() > 0) {

                hrac.setZivoty(hrac.getZivoty() - prisera.getTypPrisery().getDmg());
                System.out.println("Příšera " + prisera.getTypPrisery().name() + " útočí na hráče! Zbývající HP hráče: " + hrac.getZivoty());


                zivotyPrisery -= dmgHrace;
            }

            if (zivotyPrisery <= 0) {
                System.out.println("Příšera " + prisera.getTypPrisery().name() + " byla poražena!");


                if (lokace.getObsahujeTruhlu()) {
                    TypMece[] mece = TypMece.values();
                    TypMece nahodnyMec = mece[new Random().nextInt(mece.length)];
                    String nazevMecu = nahodnyMec.getNazev();
                    int silaMecu = nahodnyMec.getSila();
                    System.out.println("Otevřel jsi truhlu a našel jsi " + nazevMecu + "!");

                    Batoh batoh = hrac.getBatoh();
                    batoh.pridejPredmet(nazevMecu);
                    System.out.println("Přidal jsi " + nazevMecu + " do svého batohu.");


                    hrac.setDamage(hrac.getDamage() + silaMecu);
                    System.out.println("Tvá síla se zvýšila o " + silaMecu + ". Aktuální síla: " + hrac.getDamage());
                }

                lokace.setPrisera(null);
            } else {
                System.out.println("Boj nebyl úspěšný, hráč ztratil životy. Zbývající HP hráče: " + hrac.getZivoty());
                if (hrac.getZivoty() <= 0) {
                    System.out.println("Hráč zemřel!");
                }
            }
        } else {
            System.out.println("V této místnosti není žádná příšera k útoku.");
        }
    }

    public boolean utek(Hrac hrac) {
        Random random = new Random();
        boolean uspech = random.nextDouble() < 0.5;

        if (uspech) {
            System.out.println("Úspěšně jsi utekl z místnosti!");
        } else {
            System.out.println("Bohužel se ti nepodařilo utéct, musíš bojovat!");
        }

        return uspech;
    }
}

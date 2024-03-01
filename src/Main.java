import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Mapa.vypisObsahSouboru("Pravidla");
        Mapa mapa = new Mapa();
        mapa.nactiSvetZSouboru("TextovaHra.txt");


        Lokace aktualniLokace = mapa.getLokaceById(1);
        Batoh batoh = new Batoh(9, 5, "Rezavý meč");
        Hrac hrac = new Hrac("Hráč", false, 5, batoh);
        Scanner scanner = new Scanner(System.in);
        int pocetKlicu = 0;
        mapa.vygenerujPriseruVMistnosti();
//region Smyčka
        while (true) {

            System.out.println("Pro otevření batohu napiš b");
            System.out.println("Ocitl jsi se v " + aktualniLokace.getNazev() + ", můžeš pokračovat do další místnosti:");
            System.out.println("Možné místnosti:");



            if (aktualniLokace.getSever() != 0) {
                System.out.println(aktualniLokace.getNazevSever() + " S");
            }
            if (aktualniLokace.getJih() != 0) {
                System.out.println(aktualniLokace.getNazevJih() + " J");
            }
            if (aktualniLokace.getVychod() != 0) {
                System.out.println(aktualniLokace.getNazevVychod() + " V");
            }
            if (aktualniLokace.getZapad() != 0) {
                System.out.println(aktualniLokace.getNazevZapad() + " Z");
            }

            System.out.println("Životy: " + hrac.getZivoty());
            System.out.print("Pro vstup do další místnosti napiš S / J / V / Z pro vstup: ");
            String volba = scanner.nextLine();


            Lokace novaLokace = null;

            switch (volba) {
                case "S":
                    novaLokace = mapa.getLokaceById(aktualniLokace.getSever());
                    break;
                case "J":
                    novaLokace = mapa.getLokaceById(aktualniLokace.getJih());
                    break;
                case "V":
                    novaLokace = mapa.getLokaceById(aktualniLokace.getVychod());
                    break;
                case "Z":
                    novaLokace = mapa.getLokaceById(aktualniLokace.getZapad());
                    break;
                case "B":

                    batoh.vypisObsah();
                    continue;
                default:
                    System.out.println("Neplatná volba. Zkus to znovu.");
                    continue;
            }

            if (novaLokace != null) {
                aktualniLokace = novaLokace;
            } else {
                System.out.println("Tato místnost neexistuje. Zkus to znovu.");
            }

            if (hrac.getZivoty() <= 0) {
                System.out.println("Bohužel, ztratil jsi všechny životy. Hra končí.");
                break;
            }


            if (aktualniLokace.obsahujeKlic()) {
                System.out.println("V místnosti jsi našel klíč!");
                batoh.pridejPredmet("Klíč");
                aktualniLokace.odeberKlic();
                pocetKlicu++;


                if (pocetKlicu == 4) {
                    System.out.println("Gratuluji, sesbíral jsi všechny klíče a vyhrál hru!");
                    return;
                }
            }

            if (aktualniLokace.getPrisera() != null) {
                Prisera prisera = aktualniLokace.getPrisera();
                TypPrisery typPrisery = prisera.getTypPrisery();
                System.out.println("V této místnosti se nachází příšera " + typPrisery.name() +
                        " s HP: " + typPrisery.getZivoty() + " a DMG: " + typPrisery.getDmg() + ".");
                System.out.print("Chceš zaútočit (A) nebo utéct (U)? ");
                String volbaBoje = scanner.nextLine();
                switch (volbaBoje) {
                    case "A":
                        mapa.attack(hrac, aktualniLokace);
                        break;
                    case "U":
                        mapa.utek(hrac);
                        break;
                    default:
                        System.out.println("Neplatná volba.");
                }
            }
        }
        //endregion
    }
}
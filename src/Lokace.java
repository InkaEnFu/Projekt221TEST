import java.util.HashMap;

public class Lokace {
    private int id;
    private String nazev;
    private int sever;
    private int jih;
    private int vychod;
    private int zapad;
    private boolean obsahujeKlic;
    private boolean obsahujeTruhlu;
    private HashMap<Integer, Lokace> svet;
    private Prisera prisera;
    public Lokace(int id, String nazev, int sever, int jih, int vychod, int zapad, boolean obsahujeKlic, boolean obsahujeTruhlu, HashMap<Integer, Lokace> svet) {
        this.id = id;
        this.nazev = nazev;
        this.sever = sever;
        this.jih = jih;
        this.vychod = vychod;
        this.zapad = zapad;
        this.obsahujeKlic = obsahujeKlic;
        this.obsahujeTruhlu = obsahujeTruhlu;
        this.svet = svet;
        this.prisera = null;
    }


    public boolean obsahujeTruhlu() {
        return obsahujeTruhlu;
    }
    public boolean getObsahujeTruhlu() {
        return obsahujeTruhlu;
    }



    public void setObsahujeTruhlu(boolean obsahujeTruhlu) {
        this.obsahujeTruhlu = obsahujeTruhlu;
    }



    public Prisera getPrisera() {
        return prisera;
    }


    public void setPrisera(Prisera prisera) {
        this.prisera = prisera;
    }



    public boolean obsahujeKlic() {
        return obsahujeKlic;
    }


    public void odeberKlic() {
        this.obsahujeKlic = false;
    }

    public int getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public int getSever() {
        return sever;
    }

    public int getJih() {
        return jih;
    }

    public int getVychod() {
        return vychod;
    }

    public int getZapad() {
        return zapad;
    }

    public boolean getObsahujeKlic() {
        return obsahujeKlic;
    }

    public String getNazevSever() {
        Lokace severniLokace = svet.get(sever);
        return (severniLokace != null) ? severniLokace.getNazev() : "Žádná místnost";
    }

    public String getNazevJih() {
        Lokace jizniLokace = svet.get(jih);
        return (jizniLokace != null) ? jizniLokace.getNazev() : "Žádná místnost";
    }

    public String getNazevVychod() {
        Lokace vychodniLokace = svet.get(vychod);
        return (vychodniLokace != null) ? vychodniLokace.getNazev() : "Žádná místnost";
    }

    public String getNazevZapad() {
        Lokace zapadniLokace = svet.get(zapad);
        return (zapadniLokace != null) ? zapadniLokace.getNazev() : "Žádná místnost";
    }
}

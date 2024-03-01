public class Hrac {
    private String name;
    private boolean isKey;
    private int zivoty;
    private int damage;
    private Batoh batoh;

    public Hrac(String name, boolean isKey, int damage, Batoh batoh) {
        this.name = name;
        this.isKey = isKey;
        this.zivoty = 500;
        this.damage = damage;
        this.batoh = batoh;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isKey() {
        return isKey;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

    public Batoh getBatoh() {
        return batoh;
    }

    public void setBatoh(Batoh batoh) {
        this.batoh = batoh;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

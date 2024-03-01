public enum TypPrisery {
    ZLOBR(25, 5),
    GRIFFIN(50, 10),
    GOBLIN(10, 10),
    DEMON(75, 15),
    KYKLOP(125, 2);

    private final int zivoty;
    private final int dmg;

    TypPrisery(int zivoty, int dmg) {
        this.zivoty = zivoty;
        this.dmg = dmg;
    }

    public int getZivoty() {
        return zivoty;
    }

    public int getDmg() {
        return dmg;
    }
}
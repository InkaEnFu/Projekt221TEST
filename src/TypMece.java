public enum         TypMece {
    Mec_Z_Ocely("Ocelový meč Damage: 7", 7),
    Mec_Z_Stribra("Stříbrný meč Damage: 12", 10),
    Mec_Z_Zlata("Zlatý meč Damage: 13", 13),
    Mec_Z_Diamantu("Diamantový meč Damage: 16", 16),
    Mec_Z_Rubinu("Rubínový meč Damage: 19", 19),
    Mec_Z_Smaragdu("Smaragdový meč Damage: 22", 22),
    Mec_Z_Draka("Drací meč Damage: 25", 25);

    private final String nazev;
    private final int sila;

    TypMece(String nazev, int sila) {
        this.nazev = nazev;
        this.sila = sila;
    }

    public String getNazev() {
        return nazev;
    }

    public int getSila() {
        return sila;
    }
}

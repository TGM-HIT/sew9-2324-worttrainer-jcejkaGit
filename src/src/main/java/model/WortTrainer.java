package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WortTrainer {
    private List<WortEintrag> liste;
    private int trueGuess;
    private int falseGuess;

    private WortEintrag aktPaar;

    public WortTrainer() {
        this.liste = new ArrayList<WortEintrag>();
        this.aktPaar = null;
        this.trueGuess = 0;
        this.falseGuess = 0;
    }

    /**
     * Initialisiert Attribute
     *
     * @param liste Liste wird zum verarbeiten übergeben
     */
    public WortTrainer(List<WortEintrag> liste) {
        this.liste = liste;
        this.aktPaar = null;
        this.trueGuess = 0;
        this.falseGuess = 0;
    }

    /**
     * Erzeugt eine zufällige Zahl im Bereich des Eintrags Arrays und gibt den Worteintrag dann zurück
     *
     * @return WortEintrag dem zufälligem Index nach
     */
    public WortEintrag guessWorteintrag() {
        Random gen = new Random();
        int show = gen.nextInt(liste.size());
        this.aktPaar = liste.get(show);
        return this.aktPaar;
    }

    public WortEintrag chooseWorteintrag(int index) {
        this.aktPaar = liste.get(index);
        return this.aktPaar;
    }

    /**
     * Überprüft ob das übergebene wort mit dem des aktuellen Worteintrags übereinstimmt
     *
     * @param wort
     * @return true wenn es übereinstimmt
     */
    public boolean check(String wort) {
        boolean sucess = false;
        if (this.aktPaar.getWort().equals(wort)) {
            sucess = true;
            //zählt jedes mal mit wenn ein kind das richtige Wort errät
        }
        statistik(sucess);
        return sucess;
    }

    /**
     * Überprüft ob das übergebene wort mit dem des aktuellen Worteintrags
     * übereinstimmt, ignoriert aber groß und kleinschreibung
     *
     * @param wort
     * @return true wenn es übereinstimmt
     */
    public boolean checkIgnoreCase(String wort) {
        boolean sucess = false;
        String gWort1 = this.aktPaar.getWort().toUpperCase();
        String gWort2 = wort.toUpperCase();

        if (gWort1.equals(gWort2)) {
            sucess = true;
            //zählt jedes mal mit wenn ein kind das richtige Wort errät
        }
        statistik(sucess);
        return sucess;
    }

    /**
     * ändert die statistik
     *
     * @param erraten
     */
    public void statistik(boolean erraten) {
        if (erraten)
            this.trueGuess++;
        else
            this.falseGuess++;
    }

    public String printStatistik() {
        return "Es wurde insgesamt" + this.trueGuess + " mal richtig und " + this.falseGuess + " mal falsch geraten!";
    }

}

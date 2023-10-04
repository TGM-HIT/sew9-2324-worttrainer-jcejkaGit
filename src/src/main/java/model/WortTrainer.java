package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WortTrainer {
    private List<WortEintrag> liste;
    private int trueGuess;
    private int falseGuess;
    private WortEintrag aktPaar;
    private Persistence persistenceStrategy;
    public void setPersistenceStrategy(Persistence persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }
    public void saveData(String filePath) {
        if (persistenceStrategy != null) {
            persistenceStrategy.save(this, filePath);
        } else {
            System.err.println("Persistence strategy not set. Data not saved.");
        }
    }
    public WortTrainer() {
        this.liste = new ArrayList<WortEintrag>();
        this.aktPaar = null;
        this.trueGuess = 0;
        this.falseGuess = 0;
        persistenceStrategy = new JsonPersistence();
    }

    /**
     * Initialisiert Attribute
     *
     * @param liste Liste wird zum verarbeiten übergeben
     */
    public WortTrainer(List<WortEintrag> liste, Persistence persistenceStrategy) {
        this.liste = liste;
        this.aktPaar = null;
        this.trueGuess = 0;
        this.falseGuess = 0;
        this.persistenceStrategy = persistenceStrategy;
    }
    public WortEintrag getAktPaar(){
        if(this.aktPaar != null)
            return this.aktPaar;
       throw new IllegalArgumentException("Kein Paar ausgewählt");
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
        return getAktPaar();
    }

    public WortEintrag chooseWorteintrag(int index) {
        if(liste!=null){
            this.aktPaar = liste.get(index);
        }

        return getAktPaar();
    }

    /**
     * Überprüft ob das übergebene wort mit dem des aktuellen Worteintrags übereinstimmt
     *
     * @param wort
     * @return true wenn es übereinstimmt
     */
    public boolean check(String wort) {
        boolean sucess = false;
        if (getAktPaar().getWort().equals(wort)) {
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
        String gWort1 = getAktPaar().getWort().toUpperCase();
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
        return "Es wurde insgesamt " + this.trueGuess + " mal richtig und " + this.falseGuess + " mal falsch geraten!";
    }

}

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * WordTrainer has a Wordentry list
 * It provides Functionality for checking adding and saving these entrys
 * @author jurij
 * @version 18-10-2023
 */
public class WortTrainer {
    private List<WortEintrag> liste;
    private int trueGuess;
    private int falseGuess;
    private WortEintrag aktPaar;
    private Persistence persistenceStrategy;
    public void setPersistenceStrategy(Persistence persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    /**
     * Saves data with current Presistence strategy
     * @param filePath to savefile location
     */
    public void saveData(String filePath) {
        if (persistenceStrategy != null) {
            persistenceStrategy.save(this, filePath);
        } else {
            System.err.println("Persistence strategy not set. Data not saved.");
        }
    }

    /**
     * Initializes Wordtrainer with empty list and JSONPersistence
     */
    public WortTrainer() {
        this.liste = new ArrayList<WortEintrag>();
        this.aktPaar = null;
        this.trueGuess = 0;
        this.falseGuess = 0;
        persistenceStrategy = new JsonPersistence();
    }

    /**
     * Initialised  Wordtrainer with given list and persistanceStrategy
     * @param persistenceStrategy that is used to persist Wordtrainer data
     * @param liste List is passed for processing
     */
    public WortTrainer(List<WortEintrag> liste, Persistence persistenceStrategy) {
        this.liste = liste;
        this.aktPaar = null;
        this.trueGuess = 0;
        this.falseGuess = 0;
        this.persistenceStrategy = persistenceStrategy;
    }

    /**
     * @return of currently selected Entry pair
     */
    public WortEintrag getAktPaar(){
        if(this.aktPaar != null)
            return this.aktPaar;
       throw new IllegalArgumentException("Kein Paar ausgewählt");
    }

    /**
     * @return all entrys
     */
    public List<WortEintrag> getWortListe(){
        if(this.liste.size() > 0)
            return this.liste;
        throw new IllegalArgumentException("Liste ist leer");
    }

    /**
     * @return current true guess number
     */
    public int getTrueGuess(){
        return this.trueGuess;
    }
    /**
     * @return current false guess number
     */
    public int getFalseGuess(){
        return this.falseGuess;
    }

    /**
     * Creates a random number in the range of the entry array and then returns the word entry
     * @return word entry to random index
     */
    public WortEintrag guessWorteintrag() {
        Random gen = new Random();
        int show = gen.nextInt(liste.size());
        this.aktPaar = liste.get(show);
        return getAktPaar();
    }

    /**
     * Selects wordentry per index given
     * @param index given
     * @return wordentry from index
     */
    public WortEintrag chooseWorteintrag(int index) {
        if(liste!=null){
            this.aktPaar = liste.get(index);
        }

        return getAktPaar();
    }

    /**
     * Checks whether the word passed matches that of the current word entry.
     * @param wort that is checkt
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
     * Checks whether the word passed matches that of the current word entry. matches,
     * but ignores upper and lower case letters
     * @param wort that is checked
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
     * changes the statistic
     * @param erraten indicator of successful guess
     */
    public void statistik(boolean erraten) {
        if (erraten)
            this.trueGuess++;
        else
            this.falseGuess++;
    }

    /**
     * Formated Sentence that displays current Statistic
     * @return formated String
     */
    public String printStatistik() {
        return "Es wurde insgesamt " + this.trueGuess + " mal richtig und " + this.falseGuess + " mal falsch geraten!";
    }

}

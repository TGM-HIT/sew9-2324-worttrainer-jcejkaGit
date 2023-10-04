package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WortEintrag {
    private String wort;
    private static final String URL_REGEX = "^(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|]";
    private String url;
    /**
     * Initialisiert die Attribute
     * @param wort
     * @param url
     */
    public WortEintrag(String wort, String url) {
        setWort(wort);
        setUrl(url);
    }
    public WortEintrag() {
        setWort("Dog");
        setUrl("https://img.freepik.com/free-vector/beagle-dog-cartoon-white-background_1308-75491.jpg?w=200");
    }
    /**
     * prüft ob die url den Anforderungen entspricht
     * @param url
     * @return wenn Anforderungen enstpricht true
     */
    public static boolean checkURL(String url) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }


    /**
     * gibt wort zurück
     * @return
     */
    public String getWort() {
        return this.wort;
    }
    /**
     * Überprüft wort ob es mind 2 Buchstaben hat, wenn nicht dann wird eine exception geworfen
     * @param wort
     * @throws IllegalArgumentException
     */
    public void setWort(String wort) throws IllegalArgumentException{
        if(checkWort(wort)) {
            this.wort = wort;
        }
        else {
            IllegalArgumentException exc = new IllegalArgumentException("geben sie ein wort mit mindestens 2 Buchstaben ein");
            throw exc;
        }

    }
    /**
     * Überprüft wort ob es mind 2 Buchstaben hat
     * @param wort
     * @return wenn nicht dann false
     */
    public boolean checkWort(String wort) {
        int trueLetter = 0;
        boolean proof = false;
        if(wort.length() < 2) {
            return proof;
        }
        for(int i= 0;i <2;i++) {
            if(wort.charAt(i) >= 'a' && wort.charAt(i) <= 'z' || wort.charAt(i) >= 'A' && wort.charAt(i) <= 'Z') {
                trueLetter++;
            }
        }
        if(trueLetter == 2) {
            proof = true;
        }
        return proof;
    }
    /**
     * gibt url Attribut zurück
     * @return url
     */
    public String getUrl() {
        return this.url;
    }
    /**
     * Überprüft ob Url den Anforderungen enstpricht
     * @param url
     */
    public void setUrl(String url) {
        if(WortEintrag.checkURL(url)) {
            this.url = url;

        }
        else {
            IllegalArgumentException exc = new IllegalArgumentException("geben sie eine valide URL ein");
            throw exc;
        }
    }
    /**
     * Überschreibt toString so wort;url
     */
    @Override
    public String toString() {
        String ausgabe = this.wort+";"+this.url;
        return ausgabe;
    }
}

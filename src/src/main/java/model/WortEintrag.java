package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WortEintrag {
    @JsonProperty("wort")
    private String wort;
    private static final String URL_REGEX = "^(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]*[-A-Za-z0-9+&@#/%=~_|]";
    @JsonProperty("url")
    private String url;
    /**
     * Initializes the Attributes
     * @param wort Word that is the right guess
     * @param url Url of the image that should be displayed
     */
    public WortEintrag(String wort, String url) {
        setWort(wort);
        setUrl(url);
    }

    /**
     * Sets a default hardcoded Value
     */
    public WortEintrag() {
        setWort("Dog");
        setUrl("https://img.freepik.com/free-vector/beagle-dog-cartoon-white-background_1308-75491.jpg?w=200");
    }
    /**
     * checks if the url meets the requirements
     *@param url Url of the image that should be displayed
     * @return if matches requirements true
     */
    public static boolean checkURL(String url) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }


    /**
     * @return word of the Entry
     */
    public String getWort() {
        return this.wort;
    }
    /**
     * Überprüft wort ob es mind 2 Buchstaben hat, wenn nicht dann wird eine exception geworfen
     * @param wort sets word
     * @throws IllegalArgumentException if word contains less than 2 Letters
     */
    //
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
     * Checks if word has more than 2 letters
     * @param wort that is checked
     * @return false when less than 2 letters
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
     * gives back URL of entry
     * @return url
     */
    public String getUrl() {
        return this.url;
    }
    /**
     * Sets URL
     * @param url that is set
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
     * Overrides toString to wort;url
     */
    @Override
    public String toString() {
        String ausgabe = this.wort+";"+this.url;
        return ausgabe;
    }
}

package model;

public class WortEintrag {
    private String wort;

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
    /**
     * prüft ob die url den Anforderungen entspricht
     * @param url
     * @return wenn Anforderungen enstpricht true
     */
    public static boolean checkURL(String url) {
        boolean sucess = false;
        int hs = 8;
        int h = 7;
        if(url.substring(0, hs).equals("https://")) {
            if(url.charAt(hs) >= 'a' && url.charAt(hs) <= 'z' || url.charAt(hs) >= 'A' && url.charAt(hs) <= 'Z') {
                if(url.charAt(hs+1) == '.') {
                    if(url.charAt(hs+2) >= 'a' && url.charAt(hs+2) <= 'z' || url.charAt(hs+2) >= 'A' && url.charAt(hs+2) <= 'Z') {
                        sucess = true;
                    }
                }
            }
        }
        else if (url.substring(0, h).equals("http://")) {
            if(url.charAt(h) >= 'a' && url.charAt(h) <= 'z' || url.charAt(h) >= 'A' && url.charAt(h) <= 'Z') {
                if(url.charAt(h+1) == '.') {
                    if(url.charAt(h+2) >= 'a' && url.charAt(h+2) <= 'z' || url.charAt(h+2) >= 'A' && url.charAt(h+2) <= 'Z') {
                        sucess = true;
                    }
                }
            }
        }
        return sucess;
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

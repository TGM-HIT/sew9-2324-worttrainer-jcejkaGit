package Control;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import View.ImageLoader;
import model.*;

import static View.ImageLoader.displayMessage;

/**
 * Cycles through all urls, displays, saves and ,loads them from a JSON
 */
public class Main {
    public static void main(String[] args) {
        //genrates Hardcoded path
        String defaultSavesPath = "C:\\Users\\jurij\\OneDrive\\Dokumente\\Jurij-tgm\\SEW\\SEW5-WiSe\\WortTrainer-Sew5\\src\\src\\main\\persistenceSaves\\saves1.json";
        //prepares JSON Persistance
        Persistence jsonPersistence = new JsonPersistence();
        WortTrainer trainer = null;
        //if the file is empty a new Trainer is generated with a new list
      /*if(jsonPersistence.load(defaultSavesPath) == null ){
            ArrayList<WortEintrag> liste = new ArrayList<>();
            String[] imgNames = {"Katze","Email","Hund","Glas","Rucksack"};
            String[] linkList ={ "https://img.freepik.com/vektoren-kostenlos/handgezeichnete-katzenumrissillustration_23-2149288548.jpg?w=",
                    "https://img.freepik.com/vektoren-kostenlos/umschlag-mit-e-mail-zeichen_1020-530.jpg?w=",
                    "https://img.freepik.com/vektoren-kostenlos/hand-gezeichnete-hundeentwurfsillustration_23-2149266707.jpg?w=",
                    "https://img.freepik.com/vektoren-kostenlos/aufkleber-glas-wasser-auf-weissem-hintergrund_1308-76439.jpg?w=",
                    "https://img.freepik.com/vektoren-premium/ein-vektor-eines-rucksacks-in-schwarz-weiss-farbgebung_75487-1707.jpg?w="

            };
            int width=250;
            for(int i =0;i< imgNames.length;i++){
                //img.freepick
                liste.add(new WortEintrag(imgNames[i],linkList[i]+width)); System.out.println(liste.get(i).toString());
            }
            trainer = new WortTrainer(liste,jsonPersistence);
      }*/
               // if the file is readable is loads the file into trainer
            trainer = jsonPersistence.load(defaultSavesPath);


        //cycles trough all entrys and displays it
        for(int i =0;i<trainer.getWortListe().size();i++){
            trainer.chooseWorteintrag(i);
            int counter=i+1;
            trainer.saveData(defaultSavesPath);
            String userGuess = ImageLoader.loadImageAndDisplay(trainer.getAktPaar().getUrl(),"bild"+counter);
            if(trainer.checkIgnoreCase(userGuess)){
                displayMessage("Gut geraten",true);
            }
            else{
                displayMessage("Leider nicht richtig :(",false);
            }

        }
        //Displays Statistic in the end
        displayMessage(trainer.printStatistik(),true);
    }
}

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


public class Main {
    public static void main(String[] args) {
        ArrayList<WortEintrag> liste = new ArrayList<>();
        String[] imgNames = {"Katze","Email","Hund","Glas","Rucksack"};
        String[] linkList ={
                "https://img.freepik.com/vektoren-kostenlos/handgezeichnete-katzenumrissillustration_23-2149288548.jpg?w=",
                "https://img.freepik.com/vektoren-kostenlos/umschlag-mit-e-mail-zeichen_1020-530.jpg?w=",
                "https://img.freepik.com/vektoren-kostenlos/hand-gezeichnete-hundeentwurfsillustration_23-2149266707.jpg?w=",
                "https://img.freepik.com/vektoren-kostenlos/aufkleber-glas-wasser-auf-weissem-hintergrund_1308-76439.jpg?w=",
                "https://img.freepik.com/vektoren-premium/ein-vektor-eines-rucksacks-in-schwarz-weiss-farbgebung_75487-1707.jpg?w="

        };
        int width=250;
        for(int i =0;i< imgNames.length;i++){
            //img.freepick
            liste.add(new WortEintrag(imgNames[i],linkList[i]+width));
            System.out.println(liste.get(i).toString());
        }
        WortTrainer trainer = new WortTrainer(liste,new JsonPersistence());

        for(int i =0;i<5;i++){
            trainer.chooseWorteintrag(i);
            int counter=i+1;
            System.out.println("Help");
            trainer.saveData("C:\\Users\\jurij\\OneDrive\\Dokumente\\Jurij-tgm\\SEW\\SEW5-WiSe\\WortTrainer-Sew5\\src\\src\\main\\persistenceSaves\\saves.json");
            String userGuess = ImageLoader.loadImageAndDisplay(trainer.getAktPaar().getUrl(),"bild"+counter);
            System.out.println("Help2");
            if(trainer.checkIgnoreCase(userGuess)){
                displayMessage("Gut geraten",true);
            }
            else{
                displayMessage("Leider nicht richtig :(",false);
            }

        }
        displayMessage(trainer.printStatistik(),true);
    }
}

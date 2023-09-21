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
public class Main {
    public static void main(String[] args) {
        ArrayList<WortEintrag> liste = new ArrayList<>();
        for(int i =0;i<5;i++){
            liste.add(new WortEintrag("bild"+i,"https://imgur.com/3DpmZZ2"));
        }
        WortTrainer trainer = new WortTrainer(liste);
        trainer.chooseWorteintrag(1);
        ImageLoader.loadImageAndDisplay(trainer.getAktPaar().getUrl(), trainer.getAktPaar().getWort());
    }
}

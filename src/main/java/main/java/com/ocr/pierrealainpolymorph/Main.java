package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Computer;
import players.Human;
import players.Player;

import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;

public class Main {
    private final static Logger logger = getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        Player joueur1 = new Human();
        Player joueur2 = new Computer();
        logger.info("--------------------------------------------------------------------------");
        System.out.println("bienvenue dans le jeu");
        Boolean continuer = true;
        joueur1.defineName();
        joueur2.defineName();
        while (continuer) {
            int choice = menu.choice();
            menu.displaySelectedGame(choice, joueur1, joueur2);
            if (choice < 5) {
                continuer = menu.recommencer();
            } else
                continuer = false;

        }
    }

}
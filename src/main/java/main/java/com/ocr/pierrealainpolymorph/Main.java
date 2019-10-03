package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Computer;
import players.Human;
import players.Player;

import static org.apache.log4j.Logger.getLogger;

public class Main {
    final static Logger logger = getLogger(Main.class);

    public static void main(String[] args) {
        Player joueur1 = new Human();
        Player joueur2 = new Computer();
        Menu menu = new Menu();
        logger.info("--------------------------------------------------------------------------");
        System.out.println("bienvenue dans le jeu");
        Boolean continuer = true;
        joueur1.defineName();
        joueur2.defineName();
        menu.displaySelectedGame(menu.choice(), joueur1, joueur2);


    }

}
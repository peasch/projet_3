package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import players.Computer;
import players.Human;
import players.Player;

import java.io.IOException;
import java.util.Collections;

import static org.apache.log4j.Logger.getLogger;

public class Main {
    private final static Logger logger = getLogger(Main.class);


    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        Menu menu = new Menu();
        Player joueurHumain = new Human();
        Player joueurComputer = new Computer();
        logger.info("--------------------------------------------------------------------------");
        Text.affichage(Collections.singletonList(Text.BIENVENUE_DANS_LE_JEU));
        Boolean continuer = true;
        joueurHumain.defineName();
        joueurComputer.defineName();
        while (continuer) {
            int choice = menu.choice();
            menu.displaySelectedGame(choice, joueurHumain, joueurComputer);
            if (choice < 5) {
                continuer = menu.recommencer();
            } else
                continuer = false;

        }
    }

}
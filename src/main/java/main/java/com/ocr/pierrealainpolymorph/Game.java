package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Player;

import static org.apache.log4j.Logger.getLogger;

public class Game {

    final static Logger logger = getLogger(Game.class);

    public void launchGame(int taille, Player attack, Player defense) {
        attack.defineGoal(taille);
        defense.defineGoal(taille);
    }
}

package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Player;

import java.io.IOException;
import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Game {

    private int taille;
    final static Logger logger = getLogger(Game.class);

    private int getTaille() {
        return taille;
    }

    private void setTaille(int taille) {
        this.taille = taille;
    }

    public int longueur() throws IOException {
        Menu menu = new Menu();
        String saisie = "";
        boolean choiceIsGood;
        boolean sortir;
        do {
            menu.affichage(Arrays.asList(Text.COMBIEN_DE_CHIFFRE_DOIT_CONTENIR_LA_COMBINAISON, "la combinaison contient " + ReadPropertyFile.extractProperties().getProperty("taille") + " chiffres par défaut"));
            choiceIsGood = true;
            try {
                Scanner sc = new Scanner(System.in);
                saisie = sc.nextLine();
                System.out.println(saisie);


            } catch (InputMismatchException e) {
                System.out.println("Ce n'est pas une bonne réponse");
                choiceIsGood = false;
                logger.error("Il faut choisir un nombre ");
            }
            if (saisie.equals("")) {
                this.setTaille(Integer.parseInt(ReadPropertyFile.extractProperties().getProperty("taille")));
            } else {
                this.setTaille(Integer.parseInt(saisie));
            }
            if (choiceIsGood && (this.getTaille() < 10)) {
                System.out.println("vous avez choisi une combinaison de " + this.getTaille() + " chiffres");
                logger.info("combinaison de " + this.getTaille() + " chiffres");
                sortir = true;
            } else {
                System.out.println("Il faut choisir un nombre inférieur ou égal a 10");
                logger.error("Taille de combinaison erronée ");
                sortir = false;
            }
        } while (!sortir);
        return this.getTaille();
    }


    /**
     * public void launchGame(int taille, Player attacker, Player defenser, int choice) {
     * int essai = 1;
     * boolean fin;
     * Menu menu = new Menu();
     * attacker.setCompare(attacker.definecompare(taille));
     * defenser.setGoal(defenser.defineGoal(taille));
     * logger.info("Objectif à deviner " + defenser.getGoal());
     * menu.cEstParti(choice, attacker, defenser);
     * do {
     * System.out.println("Essai n° " + essai);
     * int r = this.playerRound(attacker, defenser, essai);
     * essai = essai + 1;
     * fin = this.victory(r, essai, taille, defenser);
     * } while ((essai < 8) && (!fin));
     * this.resetGame(attacker, defenser);
     * }
     */

    public void launchGame(int taille, Player playerOne, Player playerTwo, int choice) throws IOException {
        int essai = 1;
        boolean fin1;
        boolean fin2 = false;
        Menu menu = new Menu();
        this.settingGame(choice, playerOne, playerTwo);
        menu.cEstParti(choice, playerOne, playerTwo);
        do {
            System.out.println("Essai n° " + essai);
            int rP1 = this.playerRound(playerOne, playerTwo, essai, choice);
            fin1 = this.victoryDuel(rP1, taille);
            if (choice == 3) {
                if (!fin1) {
                    int rP2 = this.playerRound(playerTwo, playerOne, essai, choice);
                    fin2 = this.victoryDuel(rP2, taille);
                }
            } else if (essai == (Integer.parseInt(ReadPropertyFile.extractProperties().getProperty("nombreEssai")))) {
                switch (choice) {
                    case 1:
                        fin1 = victory(rP1, essai, taille, playerTwo);
                        break;
                    case 2:
                        fin1 = victory(rP1, essai, taille, playerOne);
                        break;
                }
            }
            essai += 1;
        } while (!fin1 && !fin2);
        this.resetGame(playerOne, playerTwo);
    }


    public int symbolEquals(List compare, int xcombi) {
        int r = 0;
        for (int i = 0; i < xcombi; i++) {
            if (compare.get(i) == " = ") {
                r += 1;
            }
        }
        return r;
    }

    private Boolean victory(int r, int essai, int taille, Player defenser) {
        boolean fin = false;
        if (r == taille) {
            System.out.println("Félicitations vous avez cassé le code!!!");
            logger.info("Partie gagnée");
            fin = true;
        } else {
            System.out.println("Dommage ! c'est perdu !!");
            System.out.println("La combinaison était : ");
            System.out.println(defenser.getGoal());
            logger.info("Partie perdue");
            fin = true;

        }
        return fin;
    }


    public Boolean victoryDuel(int r, int taille) {
        boolean fin = false;
        if (r == taille) {
            System.out.println("Félicitations vous avez cassé le code en premier !!!");
            logger.info("Duel gagnée");
            fin = true;
        }
        return fin;
    }

    public void settingGame(int choice, Player joueur1, Player joueur2) {
        switch (choice) {
            case 1:
                joueur1.setCompare(joueur1.definecompare(taille));
                joueur2.setGoal(joueur2.defineGoal(taille));
                logger.info("Objectifs à deviner : " + joueur2.getGoal());
                break;
            case 2:
                joueur2.setCompare(joueur2.getCompare());
                joueur1.setGoal(joueur1.defineGoal(taille));
                logger.info("Objectifs à deviner : " + joueur1.getGoal());
                break;
            case 3:
                joueur1.setCompare(joueur1.definecompare(taille));
                joueur2.setCompare(joueur1.getCompare());
                joueur1.setGoal(joueur1.defineGoal(taille));
                joueur2.setGoal(joueur2.defineGoal(taille));
                logger.info("Objectifs à deviner : " + joueur1.getGoal() + " // " + joueur2.getGoal());
                break;
        }
    }

    public void resetGame(Player joueur1, Player joueur2) {
        joueur1.combinationClear();
        joueur2.combinationClear();
    }

    public int playerRound(Player joueur1, Player joueur2, int essai, int choice) {
        int r = 0;
        switch (choice) {
            case 1:
                System.out.println(joueur1.getName());
                joueur1.setTentative(joueur1.defineTentative(taille, joueur1.getCompare(), joueur1.getTentative()));
                System.out.println(joueur1.getTentative());
                logger.info("Tentative n°" + essai + joueur1.getTentative());
                joueur1.setCompare(joueur2.comparison(taille, joueur1.getTentative(), joueur2.getGoal()));
                System.out.println(joueur1.getCompare());
                r = this.symbolEquals(joueur1.getCompare(), taille);
                break;
            case 2:
                joueur2.setTentative(joueur2.defineTentative(taille, joueur2.getCompare(), joueur2.getTentative()));
                System.out.println(joueur2.getTentative());
                logger.info("Tentative n°" + essai + joueur1.getTentative());
                joueur2.setCompare(joueur1.comparison(taille, joueur2.getTentative(), joueur1.getGoal()));
                System.out.println(joueur2.getCompare());
                r = this.symbolEquals(joueur2.getCompare(), taille);
                break;

            case 3:
                System.out.println(joueur1.getName() + " commence :");
                joueur1.setTentative(joueur1.defineTentative(taille, joueur1.getCompare(), joueur1.getTentative()));
                System.out.println(joueur1.getTentative());
                logger.info("Tentative n°" + essai + joueur1.getTentative());
                joueur1.setCompare(joueur2.comparison(taille, joueur1.getTentative(), joueur2.getGoal()));
                System.out.println(joueur1.getCompare());
                r = this.symbolEquals(joueur1.getCompare(), taille);
                break;

        }
        return r;
    }
}

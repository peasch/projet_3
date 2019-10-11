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
            menu.affichage(Arrays.asList(Text.COMBIEN_DE_CHIFFRE_DOIT_CONTENIR_LA_COMBINAISON, Text.LA_COMBINAISON_CONTIENT + ReadPropertyFile.extractProperties().getProperty("taille") + Text.CHIFFRES_PAR_DEFAUT));
            choiceIsGood = true;
            try {
                Scanner sc = new Scanner(System.in);
                saisie = sc.nextLine();
                System.out.println(saisie);


            } catch (InputMismatchException e) {
                Menu.affichage(Collections.singletonList(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE));
                choiceIsGood = false;
                logger.error(Text.IL_FAUT_CHOISIR_UN_NOMBRE_);
            }
            if (saisie.equals("")) {
                this.setTaille(Integer.parseInt(ReadPropertyFile.extractProperties().getProperty("taille")));
            } else {
                this.setTaille(Integer.parseInt(saisie));
            }
            if (choiceIsGood && (this.getTaille() < 10)) {
                Menu.affichage(Collections.singletonList(Text.VOUS_AVEZ_CHOISI_UNE_COMBINAISON_DE + this.getTaille() + Text.CHIFFRES));
                logger.info("combinaison de " + this.getTaille() + " chiffres");
                sortir = true;
            } else {
                Menu.affichage(Collections.singletonList(Text.IL_FAUT_CHOISIR_UN_NOMBRE_INFERIEUR_OU_EGAL_A_10));
                logger.error("Taille de combinaison erronée ");
                sortir = false;
            }
        } while (!sortir);
        return this.getTaille();
    }


    public void launchGame(int taille, Player playerOne, Player playerTwo, int choice) throws IOException {
        int essai = 1;
        boolean fin1;
        boolean fin2 = false;
        Menu menu = new Menu();
        Tentative tentaJ1 = new Tentative();
        Tentative tentaJ2 = new Tentative();

        this.settingGame(choice, playerOne, playerTwo,tentaJ1,tentaJ2);
        menu.cEstParti(choice, playerOne, playerTwo);

        do {
            System.out.println("Essai n° " + essai);
            int rP1 = this.playerRound(playerOne, playerTwo, essai, choice,tentaJ1,tentaJ2);
            fin1 = this.victoryDuel(rP1, taille);
            if (choice == 3) {
                if (!fin1) {
                    int rP2 = this.playerRound(playerTwo, playerOne, essai, choice,tentaJ1,tentaJ2);
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


    public int symbolEquals(Tentative tentative, int xcombi) {
        int r = 0;
        for (int i = 0; i < xcombi; i++) {
            if (tentative.comparatif.get(i) == " = ") {
                r += 1;
            }
        }
        return r;
    }

    private Boolean victory(int r, int essai, int taille, Player defenser) {
        boolean fin = false;
        if (r == taille) {
            Menu.affichage(Collections.singletonList(Text.FELICITATIONS_VOUS_AVEZ_CASSE_LE_CODE));
            logger.info(Text.PARTIE_GAGNEE);
            fin = true;
        } else {
            System.out.println(Text.DOMMAGE_C_EST_PERDU);
            System.out.println(Text.LA_COMBINAISON_ETAIT);
            System.out.println(defenser.getGoal());
            logger.info(Text.PARTIE_PERDUE);
            fin = true;

        }
        return fin;
    }


    public Boolean victoryDuel(int r, int taille) {
        boolean fin = false;
        if (r == taille) {
            System.out.println(Text.FELICITATIONS_VOUS_AVEZ_CASSE_LE_CODE_EN_PREMIER);
            logger.info(Text.DUEL_GAGNEE);
            fin = true;
        }
        return fin;
    }

    public void settingGame(int choice, Player joueur1, Player joueur2,Tentative tentaJ1,Tentative tentaJ2) {
        switch (choice) {
            case 1:
                joueur2.setGoal(joueur2.defineGoal(taille));
                logger.info("Objectifs à deviner : " + joueur2.getGoal());
                break;
            case 2:
                joueur1.setGoal(joueur1.defineGoal(taille));
                logger.info("Objectifs à deviner : " + joueur1.getGoal());
                break;
            case 3:
                joueur1.setGoal(joueur1.defineGoal(taille));
                joueur2.setGoal(joueur2.defineGoal(taille));
                logger.info("Objectifs à deviner : " + joueur1.getGoal() + " // " + joueur2.getGoal());
                break;
        }
        for (int i=0;i<taille;i++){
            tentaJ1.comparatif.add(i,"x");
            tentaJ1.combi.add(i,11);
            tentaJ1.borneSup.add(i,9);
            tentaJ1.borneInf.add(i,0);
            tentaJ2.comparatif.add(i,"x");
            tentaJ2.combi.add(i,11);
            tentaJ2.borneSup.add(i,9);
            tentaJ2.borneInf.add(i,0);
        }
    }

    public void resetGame(Player joueur1, Player joueur2) {
        joueur1.combinationClear();
        joueur2.combinationClear();
    }

    public int playerRound(Player joueur1, Player joueur2, int essai, int choice,Tentative tentaJ1,Tentative tentaJ2) {
        int r = 0;
        switch (choice) {
            case 1:
                System.out.println(joueur1.getName());
                joueur1.setTentative(joueur1.defineTentative(taille,tentaJ1));
                System.out.println(joueur1.getTentative());
                logger.info(Text.TENTATIVE_N + essai + joueur1.getTentative());
                tentaJ1.setComparatif(joueur2.comparison(taille,tentaJ1, joueur2.getGoal()));
                System.out.println(tentaJ1.getComparatif());
                r = this.symbolEquals(tentaJ1, taille);
                break;
            case 2:
                joueur2.setTentative(joueur2.defineTentative(taille,tentaJ2));
                System.out.println(joueur2.getTentative());
                logger.info(Text.TENTATIVE_N + essai + joueur2.getTentative());
                joueur2.setCompare(joueur1.comparison(taille, tentaJ2, joueur1.getGoal()));
                System.out.println(joueur2.getCompare());
                r = this.symbolEquals(tentaJ2, taille);
                break;

            case 3:
                System.out.println(joueur1.getName() + " commence :");
                joueur1.defineTentative(taille, tentaJ1);
                System.out.println(joueur1.getTentative());
                logger.info(Text.TENTATIVE_N + essai + joueur1.getTentative());
                joueur2.comparison(taille,tentaJ1, joueur2.getGoal());
                System.out.println(joueur1.getCompare());
                r = this.symbolEquals(tentaJ1, taille);
                break;

        }
        return r;
    }
}

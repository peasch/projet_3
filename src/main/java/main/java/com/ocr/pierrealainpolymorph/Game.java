package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

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
        String saisie = "";
        int saisieNumber = 0;
        boolean choiceIsGood;
        boolean sortir;
        do {
            Text.affichage(Arrays.asList(Text.COMBIEN_DE_CHIFFRE_DOIT_CONTENIR_LA_COMBINAISON, Text.LA_COMBINAISON_CONTIENT + ReadPropertyFile.extractProperties().getProperty("taille") + Text.CHIFFRES_PAR_DEFAUT));
            Text.showString(Text.VOTRE_SAISIE_ENTER);
            choiceIsGood = true;
            Scanner sc = new Scanner(System.in);
            saisie = sc.nextLine();
            if (saisie.equals("")) {
                this.setTaille(Integer.parseInt(ReadPropertyFile.extractProperties().getProperty("taille")));
            } else {
                try {
                    saisieNumber = Integer.parseInt(saisie);
                } catch (NumberFormatException e) {
                    Text.affichage(Collections.singletonList(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE));
                    choiceIsGood = false;
                    logger.error(Text.IL_FAUT_CHOISIR_UN_NOMBRE_);
                }
                this.setTaille(saisieNumber);
            }
            if (choiceIsGood && (this.getTaille() < 10)) {
                Text.affichage(Collections.singletonList(Text.VOUS_AVEZ_CHOISI_UNE_COMBINAISON_DE + this.getTaille() + Text.CHIFFRES));
                logger.info("combinaison de " + this.getTaille() + " chiffres");
                sortir = true;
            } else {
                Text.affichage(Collections.singletonList(Text.IL_FAUT_CHOISIR_UN_NOMBRE_INFERIEUR_OU_EGAL_A_10));
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
        boolean modeDev;
        Menu menu = new Menu();
        Tentative tentaJ1 = new Tentative();
        Tentative tentaJ2 = new Tentative();
        int nbTry = this.settingGame(choice, playerOne, playerTwo, tentaJ1, tentaJ2);
        modeDev = menu.cEstParti(choice, playerOne, playerTwo);
        do {
            Text.showString(Text.ESSAI_N + essai);
            int rP1 = this.playerRound(playerOne, playerTwo, essai, choice, tentaJ1, tentaJ2, modeDev);
            fin1 = this.victory(rP1, taille,playerTwo,essai,nbTry);
            if (choice == 3) {
                if (!fin1) {
                    int rP2 = this.playerRound(playerTwo, playerOne, essai, choice, tentaJ2, tentaJ1, modeDev);
                    fin2 = this.victoryDuel(rP2, taille);
                }
            } else if (essai == nbTry) {
                switch (choice) {
                    case 1:
                        fin1 = victory(rP1, taille, playerTwo,essai, nbTry);
                        break;
                    case 2:
                        fin1 = victory(rP1, taille, playerOne,essai,nbTry);
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
            if (tentative.comparatif.get(i).equals(Text.Kegale)) {
                r += 1;
            }
        }
        return r;
    }

    private Boolean victory(int r, int taille, Player defenser,int essai, int nbEssai) {
        boolean fin = false;
        if (r == taille) {
            Text.affichage(Collections.singletonList(Text.FELICITATIONS_VOUS_AVEZ_CASSE_LE_CODE));
            logger.info(Text.PARTIE_GAGNEE);
            fin=true;
        } else if (essai==nbEssai){
            Text.affichage(Arrays.asList(Text.DOMMAGE_C_EST_PERDU, Text.LA_COMBINAISON_ETAIT));
            Text.showString(defenser.getGoal().toString());
            logger.info(Text.PARTIE_PERDUE);
            fin=true;
        }
        return fin;
    }


    public Boolean victoryDuel(int r, int taille) {
        boolean fin = false;
        if (r == taille) {
            Text.showString(Text.FELICITATIONS_VOUS_AVEZ_CASSE_LE_CODE_EN_PREMIER);
            logger.info(Text.DUEL_GAGNEE);
            fin = true;
        }
        return fin;
    }

    public int settingGame(int choice, Player joueur1, Player joueur2, Tentative tentaJ1, Tentative tentaJ2) throws IOException {
        int nbEssai=(Integer.parseInt(ReadPropertyFile.extractProperties().getProperty("nombreEssai")));
        switch (choice) {
            case 1:
               nbEssai=this.nombreDessais();
                joueur2.setGoal(joueur2.defineGoal(taille));
                logger.info(Text.OBJECTIFS_A_DEVINER + joueur2.getGoal());
                break;
            case 2:
               nbEssai=this.nombreDessais();
                joueur1.setGoal(joueur1.defineGoal(taille));
                logger.info(Text.OBJECTIFS_A_DEVINER + joueur1.getGoal());
                break;
            case 3:
                joueur1.setGoal(joueur1.defineGoal(taille));
                joueur2.setGoal(joueur2.defineGoal(taille));
                logger.info(Text.OBJECTIFS_A_DEVINER + joueur1.getGoal() + " // " + joueur2.getGoal());
                break;
        }
        for (int i = 0; i < taille; i++) {
            tentaJ1.comparatif.add(i, "x");
            tentaJ1.combi.add(i, 11);
            tentaJ1.borneSup.add(i, 9);
            tentaJ1.borneInf.add(i, 0);
            tentaJ2.comparatif.add(i, "x");
            tentaJ2.combi.add(i, 11);
            tentaJ2.borneSup.add(i, 9);
            tentaJ2.borneInf.add(i, 0);
        }
        return nbEssai;
    }

    public void resetGame(Player joueur1, Player joueur2) {
        joueur1.combinationClear();
        joueur2.combinationClear();
    }

    public int playerRound(Player joueur1, Player joueur2, int essai, int choice, Tentative tentaJ1, Tentative tentaJ2, boolean modeDev) {
        int r = 0;
        switch (choice) {
            case 1:
                Text.showString(joueur1.getName());
                if (modeDev) {
                    Text.showString(Text.MODE_DEVELOPPEUR + joueur2.getGoal().toString());
                }
                joueur1.setTentative(joueur1.defineTentative(taille, tentaJ1));
                Text.showString(joueur1.getTentative().toString());
                logger.info(Text.TENTATIVE_N + essai + joueur1.getTentative());
                tentaJ1.setComparatif(joueur2.comparison(taille, tentaJ1, joueur2.getGoal()));
                Text.showString(tentaJ1.getComparatif().toString());
                r = this.symbolEquals(tentaJ1, taille);
                break;
            case 2:
                if (modeDev) {
                    Text.showString(Text.MODE_DEVELOPPEUR + joueur1.getGoal().toString());
                }
                joueur2.setTentative(joueur2.defineTentative(taille, tentaJ2));
                Text.showString(joueur2.getTentative().toString());
                logger.info(Text.TENTATIVE_N + essai + joueur2.getTentative());
                joueur2.setCompare(joueur1.comparison(taille, tentaJ2, joueur1.getGoal()));
                Text.showString(joueur2.getCompare().toString());
                r = this.symbolEquals(tentaJ2, taille);
                break;

            case 3:
                Text.showString(joueur1.getName() + Text.COMMENCE);
               if (joueur1.getTentative().size()!=0) {
                   Text.showString(Text.TENTATIVE_ET_RÉSULTAT_PRÉCÉDENTS);
                   Text.showString(joueur1.getTentative().toString());
                   Text.showString(tentaJ1.getComparatif().toString());
                   Text.showString(Text.TRAITS);
               }
                if (modeDev) {
                    Text.showString(Text.MODE_DEVELOPPEUR + joueur2.getGoal().toString());
                }
                joueur1.setTentative(joueur1.defineTentative(taille, tentaJ1));
                Text.showString(joueur1.getTentative().toString());
                logger.info(Text.TENTATIVE_N + essai + joueur1.getTentative());
                tentaJ1.setComparatif(joueur2.comparison(taille, tentaJ1, joueur2.getGoal()));
                Text.showString(tentaJ1.getComparatif().toString());
                r = this.symbolEquals(tentaJ1, taille);
                break;

        }
        return r;
    }

    public int nombreDessais() throws IOException {
        int nbEssai=0;
        boolean nbEssaiJuste=false;
        do {
            Text.showString(Text.A_COMBIEN_D_ESSAIS_AVEZ_VOUS_DROIT_PAR_DEFAUT + ReadPropertyFile.extractProperties().getProperty("nombreEssai"));
            Text.showString(Text.VOTRE_SAISIE_ENTER);
            Scanner sc = new Scanner(System.in);
            String saisieEssai = sc.nextLine();

            if (saisieEssai.equals("")) {
                nbEssai=(Integer.parseInt(ReadPropertyFile.extractProperties().getProperty("nombreEssai")));
                nbEssaiJuste = true;

            } else {
                try {
                    nbEssai = Integer.parseInt(saisieEssai);
                    nbEssaiJuste = true;
                } catch (NumberFormatException e) {
                    Text.affichage(Collections.singletonList(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE));
                    logger.error(Text.IL_FAUT_CHOISIR_UN_NOMBRE_);
                }

            }
        } while (!nbEssaiJuste);
        return nbEssai;
    }
}

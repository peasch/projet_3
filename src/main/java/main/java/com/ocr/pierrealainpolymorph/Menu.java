package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.apache.log4j.Logger.getLogger;

public class Menu {

    final static Logger logger = getLogger(Menu.class);
    Game partie = new Game();
    int taille;


    public void displayAvailableGame() {
        this.affichage(Arrays.asList("Quel mode de jeu voulez-vous choisir ? ", "1. Mode challenger.", "2. Mode défenseur", "3. Mode Duel", "4. Description des modes de jeu", "5.Quitter"));
    }

    public void displaySelectedGame(int gameChoice, Player joueur1, Player joueur2) throws IOException {
        switch (gameChoice) {
            case 1:
                this.affichage(Arrays.asList("Vous avez choisi le mode Challenger", "----------------------------------------------------------", joueur1.getName() + " vous essayez de deviner la combinaison de " + joueur2.getName()));
                partie.launchGame(partie.longueur(), joueur1, joueur2,gameChoice);
                break;
            case 2:
                this.affichage(Arrays.asList("Vous avez choisi le mode Défenseur", "----------------------------------------------------------", joueur2.getName() + " vous devez protéger votre combinaison contre " + joueur1.getName()));
                partie.launchGame(partie.longueur(), joueur2, joueur1, gameChoice);
                break;
            case 3:
                this.affichage(Arrays.asList("Vous avez choisi le mode Duel", "----------------------------------------------------------", "Vous tentez de deviner la combinaison de l'ordinateur, ", "avant qu'il ne découvre la votre !"));
                partie.launchDuel(partie.longueur(), joueur1, joueur2, gameChoice);
                break;
            case 4:
                this.affichage(Arrays.asList("Description des modes de jeu", "challenger : vous essayez de trouver la combinaison de l'ordinateur", "défenseur : L'ordinateur essaie de trouver votre combinaison", "Duel : IA et utlisateur essaient chacun leur tour de trouver en premier la combinaison adverse"));
                break;
            case 5:
                System.out.println("Au revoir et à bientôt !");
                break;

        }
    }

    public int choice() {
        boolean responseIsGood;
        int gameChoice = 0;
        do {
            try {
                this.displayAvailableGame();
                Scanner sc = new Scanner(System.in);
                gameChoice = sc.nextInt();
                responseIsGood = (gameChoice >= 1 && gameChoice <= 5);

            } catch (InputMismatchException e) {
                System.out.println("ce n'est pas une bonne réponse");
                responseIsGood = false;
            }
            if (responseIsGood) {
                responseIsGood = true;
            } else {
                this.affichage(Arrays.asList("il faut choisir un chiffre entre 1 & 5 ", "----------------------------------------------------------"));
                logger.error("mauvais choix de menu " + gameChoice + " au lieu de 1,2,3,4 ou 5");
                responseIsGood = false;
                gameChoice = 0;

            }

        } while (responseIsGood == false);

        return gameChoice;
    }

    public void affichage(List<String> textes) {
        for (String texte : textes) {
            System.out.println(texte);
        }
    }

    public void cEstParti(int choice, Player joueur1, Player joueur2) {
        String triche = "";
        this.affichage(Arrays.asList("------------------------------------------------------", "------------------------------------------------------", "Allez c'est parti !! On commence !", "------------------------------------------------------", "Êtes-vous un tricheur ?"));
        Scanner sc = new Scanner(System.in);
        triche = sc.nextLine();
        if (triche.equals("oui")) {
            if (choice < 3) {
                System.out.println(joueur2.getGoal());

                logger.info("mode tricheur activé");
            } else{
                System.out.println(joueur1.getGoal());
            System.out.println(joueur2.getGoal());}
        }
    }

    public Boolean recommencer() {
        Boolean continuer;
        String encore = "";
        Boolean sortir;

        do {
            this.affichage(Arrays.asList("voulez-vous refaire une partie ?", " oui ||  non"));

            try {
                Scanner sc = new Scanner(System.in);
                encore = sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("ce n'est pas une bonne réponse");
                sortir = false;
                logger.error("mauvaise saisie de réponse pour continuer, oui ou non");
            }
            if (encore.equals("oui")) {
                sortir = true;
                continuer = true;
                logger.info("partie continuée");
            } else {
                System.out.println("Au revoir et à bientôt !!!");
                encore = null;
                continuer = false;
                sortir = true;
                logger.info(" Partie terminée");
            }
        } while (sortir == false);
        return continuer;
    }

    /** public void howMuchPlayer(Player joueur1, Player joueur2) {
     boolean goodResponse = false;
     int numberPlayer = 0;
     do {
     this.affichage(Arrays.asList("Voulez vous jouer : ", "1. Contre l'ordinateur", "2. Contre un autre joueur"));
     try {
     Scanner scHMP = new Scanner(System.in);
     numberPlayer = scHMP.nextInt();
     } catch (InputMismatchException e) {
     System.out.println("ce n'est pas une bonne réponse");
     }
     goodResponse = (numberPlayer >= 1 && numberPlayer <= 2);
     } while (goodResponse == false);
     if (goodResponse) {
     switch (numberPlayer) {
     case 1:
     joueur1 = new Human();
     joueur2 = new Computer();
     joueur1.defineName();
     logger.info("nom joueur 1 :" + joueur1.getName() + " contre l'ordinateur");
     joueur2.defineName();
     break;
     case 2:
     joueur1 = new Human();
     joueur2 = new Human();
     System.out.print("joueur 1 : ");
     joueur1.defineName();
     System.out.print("joueur 2 : ");
     joueur2.defineName();
     logger.info("nom joueur 1 : " + joueur1.getName() + " contre joueur 2 : " + joueur2.getName());
     break;
     }

     } else {
     this.affichage(Arrays.asList("il faut choisir un chiffre entre 1 & 2 ", "----------------------------------------------------------"));
     logger.error("InputMismatchException au lieu de 1 ou 2");


     }


     }*/


}

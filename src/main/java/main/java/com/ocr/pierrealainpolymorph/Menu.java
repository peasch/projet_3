package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Computer;
import players.Human;
import players.Player;

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
        this.affichage(Arrays.asList("quel mode de jeu voulez-vous choisir ? ", "1. Mode challenger.", "2. Mode defenseur", "3. Mode Duel", "4. Despcription des modes de jeu", "5.Quitter"));
    }

    public void displaySelectedGame(int gameChoice, Player joueur1, Player joueur2) {
        switch (gameChoice) {
            case 1:
                this.affichage(Arrays.asList("Vous avez choisi comme mode : Challenger", "----------------------------------------------------------", joueur1 + " vous essayez de deviner la combinaison de " + joueur2));
                partie.launchGame(taille, joueur1, joueur2);
                break;
            case 2:
                this.affichage(Arrays.asList("Vous avez choisi comme mode : Défenseur", "----------------------------------------------------------", joueur2 + " vous devez protéger votre combinaison de " + joueur1));
                partie.launchGame(taille, joueur2, joueur1);
                break;
            case 3:
                this.affichage(Arrays.asList("Vous avez choisi comme mode : Duel", "----------------------------------------------------------", "Vous tentez de deviner la combinaison de l'ordinateur, ", "avant qu'il ne découvre la votre !"));
                break;
            case 4:
                this.affichage(Arrays.asList("description des modes de jeu", "challenger : vous essayez de trouver la combinaison de l'IA", "defenseur : L'IA essaie de trouver votre combinaison", "Duel : IA et utlisateur essaient chacun leur tour de trouver en premier la combinaison adverse"));
                break;
            case 5:
                System.out.println("Au revoir !");
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

    public void howMuchPlayer(Player joueur1, Player joueur2) {
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


    }


}

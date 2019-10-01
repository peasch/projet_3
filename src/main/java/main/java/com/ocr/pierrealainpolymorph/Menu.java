package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.apache.log4j.Logger.getLogger;

public class Menu {
    final static Logger logger = getLogger(Main.class);
    public void displayAvailableGame() {
        this.affichage(Arrays.asList("quel mode de jeu voulez-vous choisir ? ", "1. Mode challenger.", "2. Mode defenseur", "3. Mode Duel", "4. Despcription des modes de jeu", "5.Quitter"));
    }

    public void displaySelectedGame(int gameChoice) {
        switch (gameChoice) {
            case 1:
                this.affichage(Arrays.asList("Vous avez choisi comme mode : Challenger", "----------------------------------------------------------", "vous essayez de deviner la combinaison de l'ordinateur"));
                break;
            case 2:
                this.affichage(Arrays.asList("Vous avez choisi comme mode : Défenseur", "----------------------------------------------------------", "vous devez protéger votre combinaison de l'ordinateur"));
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
                this.displaySelectedGame(gameChoice);
                responseIsGood = true;
            } else {
                this.affichage(Arrays.asList("il faut choisir un chiffre entre 1 & 5 ","----------------------------------------------------------"));
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

    public static class Main {

        public static void main(String[] args) {
        // write your code here
        }
    }
}

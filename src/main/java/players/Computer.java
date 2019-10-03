package players;

import main.java.com.ocr.pierrealainpolymorph.Main;
import main.java.com.ocr.pierrealainpolymorph.Menu;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.apache.log4j.Logger.getLogger;

public class Computer extends Player {
    final static Logger logger = getLogger(Computer.class);
    Menu menu = new Menu();
    int numComputer;

    @Override
    public void defineName() {
        boolean goodResponse;
        do {
            System.out.println("Choisissez votre adversaire : ");
        menu.affichage(Arrays.asList("1. Jon Snow", "2. Bernard Madoff", "3. Franck Ribéry"));

            try {
                Scanner scHMP = new Scanner(System.in);
                numComputer = scHMP.nextInt();
                goodResponse = (numComputer >= 1 && numComputer <= 3);

            } catch (InputMismatchException e) {
                System.out.println("ce n'est pas une bonne réponse");
                goodResponse = false;

            }

        if (goodResponse) {
            switch (numComputer) {
                case 1:
                    name = "Jon Snow";
                    break;
                case 2:
                    name = "Bernard Madoff";
                    break;
                case 3:
                    name = " Franck Ribéry";
                    break;
            }
        } else {
            menu.affichage(Arrays.asList("il faut choisir un chiffre entre 1 & 3 ", "----------------------------------------------------------"));
            logger.error("InputMismatchException au lieu de 1,2 ou 3");
        }
    }while (goodResponse == false);

    }
}


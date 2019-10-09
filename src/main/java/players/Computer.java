package players;

import main.java.com.ocr.pierrealainpolymorph.Menu;
import org.apache.log4j.Logger;

import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Computer extends Player {
    final static Logger logger = getLogger(Computer.class);
    Menu menu = new Menu();
    int numComputer;

    private List<Integer> memoire = new ArrayList<Integer>();

    private List<Integer> tenta = new ArrayList<Integer>();

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
                System.out.println("Ce n'est pas une bonne réponse");
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
                menu.affichage(Arrays.asList("Il faut choisir un chiffre entre 1 & 3 ", "----------------------------------------------------------"));
                logger.error("InputMismatchException au lieu de 1,2 ou 3");
            }
        } while (goodResponse == false);

    }


    @Override
    public List<Integer> defineGoal(int taille) {
        goal.clear();
        for (int i = 0; i < taille; i++) {
            goal.add(smallerThan(10));
        }
        return goal;
    }

    public int smallerThan(int maxValue) {
        Random randGen = new Random();
        int randNum = randGen.nextInt(maxValue);
        return randNum;


    }

    @Override
    public List<Integer> defineTentative(int taille, List compare, List tenta) {
        if (tenta.size() == 0) {
            for (int i = 0; i < taille; i++) {
                tenta.add(smallerThan(10));
                ;
            }
        } else {
            for (int i = 0; i < taille; i++) {
                Integer a = (Integer) tenta.get(i);
                if (compare.get(i) == " - ") {
                    tenta.set(i, between(a, 9));
                } else if (compare.get(i) == " + ") {
                    tenta.set(i, between(0, a));
                } else if (compare.get(i) == " = ") {
                    tenta.set(i, a);

                }
            }
        }
        return tenta;
    }

    public int between(int minValue, int maxValue) {
        Random randGen = new Random();
        int max = maxValue - minValue + 1;
        int randNum = randGen.nextInt(max);
        randNum += minValue;
        return randNum;
    }

    @Override
    public List<String> definecompare(int taille) {
        for (int i = 0; i < taille; i++) {
            compare.add("x");
        }
        return compare;
    }

    @Override
    public List<String> comparison(int taille, List<Integer> tentative, List<Integer> goal) {
        List<String> comparer = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            comparer.add(" ");
            Integer a = tentative.get(i);
            Integer b = goal.get(i);
            int comparisonresult = a.compareTo(b);
            if (comparisonresult > 0) {
                comparer.set(i, " + ");
            } else if (comparisonresult < 0) {
                comparer.set(i, " - ");
            } else if (comparisonresult == 0) {
                comparer.set(i, " = ");
            }
        }
        return comparer;
    }

    @Override
    public void combinationClear(){

        this.tentative.clear();
        this.goal.clear();
        this.compare.clear();


    }
}


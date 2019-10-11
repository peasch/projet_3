package players;

import main.java.com.ocr.pierrealainpolymorph.Menu;
import main.java.com.ocr.pierrealainpolymorph.Tentative;
import main.java.com.ocr.pierrealainpolymorph.Text;
import org.apache.log4j.Logger;

import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Computer extends Player {
    final static Logger logger = getLogger(Computer.class);
    public static final String Kmoins = "-";
    public static final String Kplus = "+";
    public static final String Kegale = "=";

    Menu menu = new Menu();
    int numComputer;


    @Override
    public void defineName() {
        boolean goodResponse;
        do {
            System.out.println(Text.CHOISISSEZ_VOTRE_ADVERSAIRE);
            menu.affichage(Arrays.asList(Text.JON_SNOW1, Text.BERNARD_MADOFF2, Text.FRANCK_RIBERY3));

            try {
                Scanner scHMP = new Scanner(System.in);
                numComputer = scHMP.nextInt();
                goodResponse = (numComputer >= 1 && numComputer <= 3);

            } catch (InputMismatchException e) {
                System.out.println(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE);
                goodResponse = false;

            }

            if (goodResponse) {
                switch (numComputer) {
                    case 1:
                        name = Text.JON_SNOW;
                        break;
                    case 2:
                        name = Text.BERNARD_MADOFF;
                        break;
                    case 3:
                        name = Text.FRANCK_RIBERY;
                        break;
                }
            } else {
                menu.affichage(Arrays.asList(Text.IL_FAUT_CHOISIR_UN_CHIFFRE_ENTRE_1_3, Text.TRAITS));
                logger.error(Text.INPUT_MISMATCH_EXCEPTION_AU_LIEU_DE_1_2_OU_3);
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

    /**
     * @Override public List<Integer> defineTentative(int taille, Tentative tentaComputer) {
     * <p>
     * for (int i = 0; i < taille; i++) {
     * Integer a = tentaComputer.combi.get(i);
     * if (compare.get(i) == " - ") {
     * tenta.set(i, between(a, 9));
     * } else if (compare.get(i) == " + ") {
     * tenta.set(i, between(0, a));
     * } else if (compare.get(i) == " = ") {
     * tenta.set(i, a);
     * <p>
     * }
     * }
     * <p>
     * return tenta;
     * }
     */

    public int between(int minValue, int maxValue) {
        Random randGen = new Random();
        int max = maxValue - minValue + 1;
        int randNum = randGen.nextInt(max);
        randNum += minValue;
        return randNum;
    }

    /**
     * @Override public Tentative definecompare(int taille,Tentative tentative) {
     * for (int i = 0; i < taille; i++) {
     * <p>
     * tentative.comparatif.add("x");
     * }
     * return tentative;
     * }
     */


    @Override
    public void combinationClear() {

        this.tentative.clear();
        this.goal.clear();
        this.compare.clear();


    }

    @Override
    public List comparison(int taille, Tentative tentaC, List<Integer> goal) {
        for (int i = 0; i < taille; i++) {
            Integer a = tentaC.combi.get(i);
            Integer b = goal.get(i);
            int comparisonresult = a.compareTo(b);
            if (comparisonresult > 0) {
                tentaC.comparatif.set(i, " + ");
            } else if (comparisonresult < 0) {
                tentaC.comparatif.set(i, " - ");
            } else if (comparisonresult == 0) {
                tentaC.comparatif.set(i, " = ");
            }
        }
        return tentaC.comparatif;
    }


    @Override
    public List defineTentative(int taille, Tentative tentatio) {

        for (int i = 0; i < taille; i++) {
            if (tentatio.combi.get(i) == 11) {
                tentatio.combi.set(i, smallerThan(10));
            }else if (tentatio.comparatif.get(i) == " - ") {
                if((tentatio.borneInf.get(i)+1) < tentatio.borneSup.get(i)){
                tentatio.borneInf.set(i, (tentatio.combi.get(i)+1));}
                tentatio.combi.set(i, between(tentatio.borneInf.get(i), tentatio.borneSup.get(i)));
            } else if (tentatio.comparatif.get(i) == " + ") {
                if((tentatio.borneSup.get(i)-1) > tentatio.borneInf.get(i)){
                tentatio.borneSup.set(i, (tentatio.combi.get(i)-1));}
                tentatio.combi.set(i, between(tentatio.borneInf.get(i), tentatio.borneSup.get(i)));
            }
        }
        return tentatio.combi;
    }
}





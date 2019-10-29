package players;

import main.java.com.ocr.pierrealainpolymorph.Tentative;
import main.java.com.ocr.pierrealainpolymorph.Text;
import org.apache.log4j.Logger;

import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Computer extends Player {
    private final static Logger logger = getLogger(Computer.class);
    private int numComputer;


    @Override
    public void defineName() {
        boolean goodResponse;
        do {
            System.out.println(Text.CHOISISSEZ_VOTRE_ADVERSAIRE);
            Text.affichage(Arrays.asList(Text.JON_SNOW1, Text.BERNARD_MADOFF2, Text.FRANCK_RIBERY3));
            Text.showString(Text.VOTRE_SAI_ENTER);
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
                Text.affichage(Arrays.asList(Text.IL_FAUT_CHOISIR_UN_CHIFFRE_ENTRE_1_3, Text.TRAITS));
                logger.error(Text.INPUT_MISMATCH_EXCEPTION_AU_LIEU_DE_1_2_OU_3);
            }
        } while (!goodResponse);

    }


    @Override
    public List<Integer> defineGoal(int taille) {
        goal.clear();
        for (int i = 0; i < taille; i++) {
            goal.add(smallerThan());
        }
        return goal;
    }

    private int smallerThan() {
        Random randGen = new Random();
        return randGen.nextInt(10);
    }


    private int between(int minValue, int maxValue) {
        Random randGen = new Random();
           int max = maxValue - minValue;
        int randNum = randGen.nextInt(max+1);
        randNum += minValue;
        return randNum;
    }

    @Override
    public void combinationClear() {
        this.tentative.clear();
        this.goal.clear();
        this.compare.clear();
    }

    @Override
    public List<String> comparison(int taille, Tentative tentaC, List<Integer> goal) {
        for (int i = 0; i < taille; i++) {
            Integer a = tentaC.combi.get(i);
            Integer b = goal.get(i);
            int comparisonresult = a.compareTo(b);
            if (comparisonresult > 0) {
                tentaC.comparatif.set(i, Text.Kmoins);
            } else if (comparisonresult < 0) {
                tentaC.comparatif.set(i, Text.Kplus);
            } else {
                tentaC.comparatif.set(i, Text.Kegale);
            }
        }
        return tentaC.comparatif;
    }


    @Override
    public List<Integer> defineTentative(int taille, Tentative tentatio) {
        for (int i = 0; i < taille; i++) {
            if (tentatio.combi.get(i) == 11) {
                tentatio.combi.set(i, smallerThan());
            }else if (tentatio.comparatif.get(i).equals(Text.Kplus)) {
                if((tentatio.borneSup.get(i)-(tentatio.combi.get(i)+1))>0 ) {
                    tentatio.borneInf.set(i, (tentatio.combi.get(i)+1));
                    tentatio.combi.set(i, between(tentatio.borneInf.get(i), tentatio.borneSup.get(i)));
                }else {

                    tentatio.combi.set(i, between(tentatio.borneInf.get(i), tentatio.borneSup.get(i)));
                }
            } else if (tentatio.comparatif.get(i).equals(Text.Kmoins)) {
                if((((tentatio.borneSup.get(i)-1)-(tentatio.combi.get(i)))>0 )) {
                    tentatio.borneSup.set(i, (tentatio.combi.get(i)-1));
                    tentatio.combi.set(i, between(tentatio.borneInf.get(i), tentatio.borneSup.get(i)));
                }else{

                    tentatio.combi.set(i, between(tentatio.borneInf.get(i), tentatio.borneSup.get(i)));
                }
            }
        }
        return tentatio.combi;
    }
}





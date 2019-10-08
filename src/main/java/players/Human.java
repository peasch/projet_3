package players;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.apache.log4j.Logger.getLogger;

public class Human extends Player {

    final static Logger logger = getLogger(Human.class);

    private ArrayList saisiePlayer = new ArrayList<String>();

    public List<Integer> getGoal() {
        return goal;
    }

    public void setGoal(List<Integer> goal) {
        this.goal = goal;
    }



    public List<Integer> getTentative() {
        return tentative;
    }

    public void setTentative(List<Integer> tentative) {
        this.tentative = tentative;
    }

    public List<String> getCompare() {
        return compare;
    }

    public void setCompare(List<String> compare) {
        this.compare = compare;
    }


    @Override
    public void defineName() {
        System.out.println("Quel est votre pseudo ?");
        Scanner scL = new Scanner(System.in);
        this.name = scL.nextLine();

    }


    @Override
    public List<Integer> defineGoal(int taille) {
        boolean saisie;
        String saisieUser;
        do {
            System.out.println("Veuillez saisir votre combinaison !( " + taille + " chiffres)");
            Scanner sc = new Scanner(System.in);
            saisieUser = sc.nextLine();
            for (int i = 0; i < taille; i++) {
                saisiePlayer.add(saisieUser.charAt(i));
            }
            if (this.isInteger(saisieUser)) {
                if (saisieUser.length() != taille) {
                    System.out.println("la combinaison ne fait pas la bonne taille");
                    saisiePlayer.clear();
                    saisie = false;
                } else {
                    for (int i = 0; i < saisieUser.length(); i++) {
                        goal.add(Integer.parseInt(String.valueOf(saisiePlayer.get(i))));
                    }
                    saisie = true;
                }
            } else {
                System.out.println("il faut saisir une combinaison de chiffres");
                logger.error(" combinaison /chiffres" + saisiePlayer);
                saisie = false;
                saisiePlayer.clear();
            }

        } while (saisie == false);
        saisiePlayer.clear();
       return goal;
    }

    public boolean isInteger(String s) {

        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            logger.error("ce ne sont pas des chiffres");
            return false;
        }
        return true;
    }

    @Override
    public  List<Integer> defineTentative (int taille, List compare,List tentative) {
        boolean saisie = false;
        String saisieUser;
        tentative.clear();
        do {
            System.out.println("Veuillez saisir votre combinaison !( " + taille + " chiffres)");
            Scanner sc = new Scanner(System.in);
            saisieUser = sc.nextLine();
            for (int i = 0; i < taille; i++) {
                saisiePlayer.add(saisieUser.charAt(i));
            }

            if (isInteger(saisieUser)) {
                if (saisieUser.length() != taille) {
                    System.out.println("la combinaison ne fait pas la bonne taille");
                    logger.error("Mauvaise taille de combinaison");
                    saisie = false;
                } else {
                    for (int i = 0; i < saisieUser.length(); i++) {
                        tentative.add(Integer.parseInt(String.valueOf(saisiePlayer.get(i))));
                    }
                    saisie = true;
                }
            } else {
                System.out.println("il faut saisir une combinaison de chiffres");
                logger.error("saisie /chiffres");
                saisie = false;
                saisiePlayer.clear();
            }
        } while (saisie == false);
        saisiePlayer.clear();
        saisieUser = null;
        return tentative;
    }

    @Override
    public List<String> definecompare(int taille){
        for (int i=0;i<taille;i++){
            compare.add("x");
        }
        return compare;
    }

    @Override
    public void combinationClear(){

            this.tentative.clear();
            this.goal.clear();
            this.compare.clear();


    }
}

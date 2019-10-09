package players;


import main.java.com.ocr.pierrealainpolymorph.Menu;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Human extends Player {


    private final static Logger logger = getLogger(Human.class);

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
    public void defineName() throws IOException {
        String saisie = "";
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("C:/Users/peasc/IdeaProjects/escape-polymorph/src/main/resources/config.properties");
        prop.load(ip);
        System.out.println("Quel est votre pseudo ?");
        Scanner scL = new Scanner(System.in);
        saisie = scL.nextLine();
        if (saisie.equals("")) {
            this.name = (prop.getProperty("utilisateur"));
        } else
            this.name = saisie;

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
    public List<Integer> defineTentative(int taille, List compare, List tentative) {
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
    public List<String> comparison(int taille, List<Integer> tentative, List<Integer> goal) {
        List comparer = new ArrayList();
        boolean ok = false;
        int compare = 0;
        Menu.affichage(Arrays.asList("Comparez la tentative à votre combinaison à deviner : "));

        for (int i = 0; i < taille; i++) {
            do {
                System.out.println("------------------------------------------------------------");
                System.out.println(tentative.get(i));
                System.out.println(goal.get(i));
                Menu.affichage(Arrays.asList("------------------------------------------------------------", "la valeur est-elle ? : ", "1. Au dessus ", "2. en dessous", "3. Egale"));

                try {
                    Scanner sc = new Scanner(System.in);
                    compare = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ce n'est pas une bonne réponse");
                }

                if (compare < 4 && compare > 0) {
                    ok = true;
                } else {
                    System.out.println("Mauvais choix de réponse");
                    ok = false;
                }
            } while (!ok);
            switch (compare) {
                case 1:
                    comparer.add(i, " + ");
                    break;
                case 2:
                    comparer.add(i, " - ");
                    break;
                case 3:
                    comparer.add(i, " = ");
                    break;
            }
        }
        return comparer;
    }


    @Override
    public List<String> definecompare(int taille) {
        for (int i = 0; i < taille; i++) {
            compare.add("x");
        }
        return compare;
    }


    @Override
    public void combinationClear() {

        this.tentative.clear();
        this.goal.clear();
        this.compare.clear();


    }
}

package players;


import main.java.com.ocr.pierrealainpolymorph.Menu;
import main.java.com.ocr.pierrealainpolymorph.Tentative;
import main.java.com.ocr.pierrealainpolymorph.Text;
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


    @Override
    public void defineName() throws IOException {
        String saisie = "";
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("C:/Users/peasc/IdeaProjects/escape-polymorph/src/main/resources/config.properties");
        prop.load(ip);
        Text.affichage(Collections.singletonList(Text.QUEL_EST_VOTRE_PSEUDO));
        Text.showString(Text.VOTRE_SAISIE_ENTER);
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
            Text.affichage(Arrays.asList(Text.VEUILLEZ_SAISIR_VOTRE_COMBINAISON + taille + Text.CHIFFRES ));
            Text.showString(Text.VOTRE_SAI_ENTER);
            Scanner sc = new Scanner(System.in);
            saisieUser = sc.nextLine();
            try{
                for (int i = 0; i < taille; i++) {
                    saisiePlayer.add(saisieUser.charAt(i));

                }
            }catch (StringIndexOutOfBoundsException e) {
                logger.error(Text.LA_COMBINAISON_NE_FAIT_PAS_LA_BONNE_TAILLE);
                Text.showString(Text.LA_COMBINAISON_NE_FAIT_PAS_LA_BONNE_TAILLE);
                saisie=false;
            }

            if (this.isInteger(saisieUser)) {
                if (saisieUser.length() != taille) {
                    Text.showString(Text.LA_COMBINAISON_NE_FAIT_PAS_LA_BONNE_TAILLE);
                    saisiePlayer.clear();
                    saisie = false;
                } else {
                    for (int i = 0; i < saisieUser.length(); i++) {
                        goal.add(Integer.parseInt(String.valueOf(saisiePlayer.get(i))));
                    }
                    saisie = true;
                }
            } else {
                Text.affichage(Collections.singletonList(Text.IL_FAUT_SAISIR_UNE_COMBINAISON_DE_CHIFFRES));
                logger.error(" combinaison /" + Text.CHIFFRES + saisiePlayer);
                saisie = false;
                saisiePlayer.clear();
            }

        } while (!saisie);
        saisiePlayer.clear();
        return goal;
    }

    public boolean isInteger(String s) {

        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            logger.error("ce ne sont pas des " + Text.CHIFFRES);
            return false;
        }
        return true;
    }

    @Override
    public List<Integer> defineTentative(int taille, Tentative tentaHumain) {
        boolean saisie;
        String saisieUser;
        do {
            Text.affichage(Arrays.asList(Text.VEUILLEZ_SAISIR_VOTRE_COMBINAISON + taille + Text.CHIFFRES));
            Text.showString(Text.VOTRE_SAI_ENTER);
            Scanner sc = new Scanner(System.in);
            saisieUser = sc.nextLine();
            for (int i = 0; i < taille; i++) {
                saisiePlayer.add(saisieUser.charAt(i));
            }


            if (isInteger(saisieUser)) {
                if (saisieUser.length() != taille) {
                    Text.affichage(Collections.singletonList(Text.LA_COMBINAISON_NE_FAIT_PAS_LA_BONNE_TAILLE));
                    logger.error("Mauvaise taille de combinaison");
                    saisie = false;
                } else {
                    for (int i = 0; i < saisieUser.length(); i++) {
                        tentaHumain.combi.set(i, Integer.parseInt(String.valueOf(saisiePlayer.get(i))));
                    }
                    saisie = true;
                }
            } else {
                Text.affichage(Collections.singletonList(Text.IL_FAUT_SAISIR_UNE_COMBINAISON_DE_CHIFFRES));
                logger.error("saisie /" + Text.CHIFFRES);
                saisie = false;
                saisiePlayer.clear();
            }
        } while (!saisie);
        saisiePlayer.clear();
        return tentaHumain.combi;
    }

    @Override
    public List<String> comparison(int taille, Tentative tentaH, List<Integer> goal) {
        List comparer = new ArrayList();
        boolean ok = false;
        int comparo = 0;
        Text.affichage(Arrays.asList(Text.COMPAREZ_LA_TENTATIVE_A_VOTRE_COMBINAISON_A_DEVINER));

        for (int i = 0; i < taille; i++) {
            do {
                System.out.println(Text.TRAITS);
                System.out.println(tentaH.combi.get(i));
                System.out.println(goal.get(i));
                Text.affichage(Arrays.asList(Text.TRAITS, Text.LA_VALEUR_EST_ELLE, Text.EN_DESSOUS));
                Text.showString(Text.VOTRE_SAI_ENTER);

                try {
                    Scanner sc = new Scanner(System.in);
                    comparo = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE);
                }

                if (comparo < 4 && comparo > 0) {
                    ok = true;
                } else {
                    System.out.println(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE);
                    ok = false;
                }
            } while (!ok);
            switch (comparo) {

                case 1:
                    if (tentaH.comparatif.size() == taille) {
                        tentaH.comparatif.set(i, " - ");
                    } else
                        tentaH.comparatif.add(i, " - ");

                    break;
                case 2:
                    if (tentaH.comparatif.size() == taille) {
                        tentaH.comparatif.set(i, " = ");
                    } else
                        tentaH.comparatif.add(i, " = ");
                    break;
                case 3:
                    if (tentaH.comparatif.size() == taille) {
                        tentaH.comparatif.set(i, " + ");
                    } else
                        tentaH.comparatif.add(i, " + ");
                    break;
            }
        }
        return tentaH.comparatif;
    }

    @Override
    public void combinationClear() {
        this.tentative.clear();
        this.goal.clear();
        this.compare.clear();
    }

}

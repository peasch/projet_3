package main.java.com.ocr.pierrealainpolymorph;

import org.apache.log4j.Logger;
import players.Player;

import java.io.IOException;
import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Menu {

    private final static Logger logger = getLogger(Menu.class);
    private Game partie = new Game();
    int taille;

    private void displayAvailableGame() {
        Text.affichage(Arrays.asList(Text.QUEL_MODE_DE_JEU_VOULEZ_VOUS_CHOISIR, Text.MODE_CHALLENGER_1, Text.MODE_DEFENSEUR_2, Text.MODE_DUEL_3, Text.DESCRIPTION_DES_MODES_DE_JEU, Text.QUITTER));
        Text.showString(Text.VOTRE_SAI_ENTER);
    }

    public void displaySelectedGame(int gameChoice, Player joueur1, Player joueur2) throws IOException {
        switch (gameChoice) {
            case 1:
                Text.affichage(Arrays.asList(Text.VOUS_AVEZ_CHOISI_LE_MODE_CHALLENGER, Text.TRAITS, joueur1.getName() + Text.VOUS_ESSAYEZ_DE_DEVINER_LA_COMBINAISON_DE + joueur2.getName()));
                partie.launchGame(partie.longueur(), joueur1, joueur2, gameChoice);
                break;
            case 2:
                Text.affichage(Arrays.asList(Text.VOUS_AVEZ_CHOISI_LE_MODE_DEFENSEUR, Text.TRAITS, joueur1.getName() + Text.VOUS_ESSAYEZ_DE_DEVINER_LA_COMBINAISON_DE + joueur2.getName()));
                partie.launchGame(partie.longueur(), joueur1, joueur2, gameChoice);
                break;
            case 3:
                Text.affichage(Arrays.asList(Text.VOUS_AVEZ_CHOISI_LE_MODE_DUEL, Text.TRAITS, Text.VOUS_TENTEZ_DE_DEVINER_LA_COMBINAISON_DE_L_ORDINATEUR_AVANT_QU_IL_NE_DECOUVRE_LA_VOTRE));
                partie.launchGame(partie.longueur(), joueur1, joueur2, gameChoice);
                break;
            case 4:
                Text.affichage(Arrays.asList(Text.DESCRIPTION_DES_MODES_DE_JEU1, Text.CHALLENGER_VOUS_ESSAYEZ_DE_TROUVER_LA_COMBINAISON_DE_L_ORDINATEUR, Text.DEFENSEUR_L_ORDINATEUR_ESSAIE_DE_TROUVER_VOTRE_COMBINAISON, Text.DUEL_IA_ET_UTLISATEUR_ESSAIENT_CHACUN_LEUR_TOUR_DE_TROUVER_EN_PREMIER_LA_COMBINAISON_ADVERSE));
                break;
            case 5:
                Text.showString(Text.AU_REVOIR);
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
                Text.affichage(Collections.singletonList(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE));
                responseIsGood = false;
            }
            if (responseIsGood) {
                responseIsGood = true;
            } else {
                Text.affichage(Arrays.asList(Text.IL_FAUT_CHOISIR_UN_CHIFFRE_ENTRE_1_5, Text.TRAITS));
                logger.error(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE + gameChoice + Text.AU_LIEU_DE_1_2_3_4_OU_5);
                responseIsGood = false;
                gameChoice = 0;

            }

        } while (responseIsGood == false);

        return gameChoice;
    }



    public boolean cEstParti(int choice, Player joueur1, Player joueur2) {
        String triche = "";
        boolean dev=false;
        Text.affichage(Arrays.asList(Text.TRAITS, Text.ALLEZ_C_EST_PARTI_ON_COMMENCE, Text.ETES_VOUS_UN_TRICHEUR));
        Text.showString((Text.VOTRE_SAISIE_ENTER));
        Scanner sc = new Scanner(System.in);
        triche = sc.nextLine();
        if (triche.equals("oui")) {
                logger.info(Text.MODE_TRICHEUR_ACTIVE);
                dev = true;

        }
        return dev;

    }

    public Boolean recommencer() {
        Boolean continuer;
        String encore = "";
        Boolean sortir;

        do {
            Text.affichage(Arrays.asList(Text.VOULEZ_VOUS_REFAIRE_UNE_PARTIE, Text.OUI_NON));

            try {
                Scanner sc = new Scanner(System.in);
                encore = sc.nextLine();

            } catch (InputMismatchException e) {
                Text.showString(Text.CE_N_EST_PAS_UNE_BONNE_REPONSE);
                sortir = false;
                logger.error(Text.MAUVAISE_SAISIE_DE_REPONSE_POUR_CONTINUER_OUI_OU_NON);
            }
            if (encore.equals("oui")) {
                sortir = true;
                continuer = true;
                logger.info(Text.PARTIE_CONTINUEE);
            } else {
                System.out.println(Text.AU_REVOIR);
                encore = null;
                continuer = false;
                sortir = true;
                logger.info(Text.PARTIE_TERMINEE);
            }
        } while (sortir == false);
        return continuer;
    }


}

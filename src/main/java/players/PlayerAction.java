package players;

import main.java.com.ocr.pierrealainpolymorph.Tentative;

import java.io.IOException;
import java.util.List;

public interface PlayerAction {
    void defineName() throws IOException;

    List<Integer> defineGoal(int taille);

    List<Integer> defineTentative(int taille,Tentative tentatio);

  List<String> comparison(int taille, Tentative tentatio, List<Integer> goal) ;

    void combinationClear();
}

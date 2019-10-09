package players;

import java.io.IOException;
import java.util.List;

public interface PlayerAction {
    void defineName() throws IOException;

    List<Integer> defineGoal(int taille);

    List<Integer> defineTentative(int taille, List compare, List tentative);

    List<String> definecompare(int taille);

    List<String> comparison(int taille, List<Integer> tentative, List<Integer> goal);

    void combinationClear();
}

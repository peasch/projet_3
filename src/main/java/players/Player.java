package players;

        import org.apache.log4j.Logger;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import static org.apache.log4j.Logger.getLogger;

public abstract class Player implements PlayerAction{
    final static Logger logger = getLogger(Player.class);
    protected String name;
    protected List<Integer> goal = new ArrayList<Integer>();
    protected List<Integer> tentative = new ArrayList<Integer>();
    protected List<Integer> memoire = new ArrayList<>();
    protected List<String> compare = new ArrayList<>();

    public List<String> getCompare() {
        return compare;
    }

    public void setCompare(List<String> compare) {
        this.compare = compare;
    }

    public List<Integer> getGoal() {
        return goal;
    }

    public List<Integer> getTentative() {
        return tentative;
    }

    public void setTentative(List<Integer> tentative) {
        this.tentative = tentative;
    }

    public void setGoal(List<Integer> goal) {
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getMemoire() {
        return memoire;
    }

    public void setMemoire(List<Integer> memoire) {
        this.memoire = memoire;
    }
}

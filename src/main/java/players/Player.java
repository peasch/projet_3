package players;

        import org.apache.log4j.Logger;
        import java.util.ArrayList;
        import java.util.List;
        import static org.apache.log4j.Logger.getLogger;

public abstract class Player implements PlayerAction{

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


}

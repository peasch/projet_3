package players;

        import java.util.ArrayList;
        import java.util.List;

public abstract class Player implements PlayerAction{

    protected String name;
    protected List<Integer> goal = new ArrayList<Integer>();
    protected List<Integer> tentative = new ArrayList<Integer>();
    protected List<Integer> memoire = new ArrayList<>();
    protected List<String> compare = new ArrayList<>();


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

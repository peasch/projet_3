package players;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

public abstract class Player {
    final static Logger logger = getLogger(Player.class);
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void defineName();

    public abstract void defineGoal(int taille);

}

package leaderboard;

import java.io.Serializable;

public class LeaderBoardItem implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String name;
    private int points;

    public LeaderBoardItem(String name, int points){
        this.name=name;
        this.points=points;
    }


    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}

package leaderboard;

import java.io.Serializable;

public class LeaderBoardItem implements Serializable {
    private String name;
    private int points;

    public LeaderBoardItem(String name, int points){
        this.name=name;
        this.points=points;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

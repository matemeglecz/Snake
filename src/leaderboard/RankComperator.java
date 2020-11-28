package leaderboard;

import java.util.Comparator;

public class RankComperator implements Comparator<LeaderBoardItem> {
    @Override
    public int compare(LeaderBoardItem o1, LeaderBoardItem o2) {
        return Integer.compare(o1.getPoints(), o2.getPoints());
    }
}

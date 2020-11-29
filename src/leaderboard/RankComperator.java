package leaderboard;

import java.util.Comparator;

/**
 * két LeaderBoardRankItem pont szerinti összehasonlítására szolgáló osztály
 */
public class RankComperator implements Comparator<LeaderBoardItem> {
    @Override
    public int compare(LeaderBoardItem o1, LeaderBoardItem o2) {
        return Integer.compare(o1.getPoints(), o2.getPoints());
    }
}

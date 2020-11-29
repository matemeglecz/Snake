package leaderboard;

import java.io.Serializable;

/**
 * a renglistában található egy elemet reprezentál
 */
public class LeaderBoardItem implements Serializable {
    /**
     * ne legyen probléma a serializálásból, ha különböző gépeken történik
     */
    private static final long serialVersionUID = 6529685098267757690L;
    /**
     * az eredményhez tárolt név
     */
    private final String name;
    /**
     * az eredményhez tárolt pontszám
     */
    private final int points;

    /**
     * inicializálja az osztály attribútumait
     *
     * @param name az eredményhez tárolni kívánt név
     * @param points az eredményhez tárolni kívánt pontszám
     */
    public LeaderBoardItem(String name, int points){
        this.name=name;
        this.points=points;
    }


    /**
     * @return visszatér az eredményhez tartozó pontszámmal
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return visszatér az eredményhez tartozó pontszámmal
     */
    public String getName() {
        return name;
    }
}

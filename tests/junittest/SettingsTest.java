package junittest;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;
import org.junit.Assert;
import org.junit.Test;

/**
 * a Settings osztály néhány metódusának tesztelésére szolgáló osztály
 */
public class SettingsTest {

    /**
     * teszteli, hogy rangsorolható beállításoknál az isRankable() fgv helyesen működik-e
     *
     * @throws InvalidSettingsException dobódik, ha nem hozható létre a paraamétereknek megfelelő beállítás
     */
    @Test
    public void testRankable() throws InvalidSettingsException {
        Settings settings=new Settings(GameModes.SINGLEPLAYER, 30, 5, 5, 120000, 500);
        Assert.assertTrue(settings.isRankable());
    }

    /**
     * teszteli, hogy nem rangsorolható beállításoknál az isRankable() fgv helyesen működik-e
     *
     * @throws InvalidSettingsException dobódik, ha nem hozható létre a paraamétereknek megfelelő beállítás
     */
    @Test
    public void testNotRankable() throws InvalidSettingsException {
        Settings settings=new Settings(GameModes.playerMULTIPLAYER, 30, 5, 5, 120000, 500);
        Assert.assertFalse(settings.isRankable());
    }

    /**
     * teszteli, hogy ha több dolgot akarunk felhelyezni a pályára, mint amennyi elfér rajta dob-e kivételt
     *
     * @throws InvalidSettingsException dobódik, ha nem hozható létre a paraamétereknek megfelelő beállítás
     */
    @Test (expected = InvalidSettingsException.class)
    public void testInvalidSettingsTooManyApples() throws InvalidSettingsException {
        new Settings(GameModes.SINGLEPLAYER, 30, 30*30, 5, 120000, 500);
    }

    /**
     * teszteli, hogy ha kisebb pályát akarunk létrehozni, mint amire kígyó lehetne egyáltalán felhelyezni dob-e kivételt
     *
     * @throws InvalidSettingsException dobódik, ha nem hozható létre a paraamétereknek megfelelő beállítás
     */
    @Test (expected = InvalidSettingsException.class)
    public void testInvalidSettingsTooSmallSize() throws InvalidSettingsException {
        new Settings(GameModes.playerMULTIPLAYER, 1, 0, 0, 120000, 500);
    }

}

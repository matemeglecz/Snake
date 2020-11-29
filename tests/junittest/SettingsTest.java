package junittest;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;
import org.junit.Assert;
import org.junit.Test;

public class SettingsTest {

    @Test
    public void testRankable() throws InvalidSettingsException {
        Settings settings=new Settings(GameModes.SINGLEPLAYER, 30, 5, 5, 120000, 500);
        Assert.assertTrue(settings.isRankable());
    }

    @Test
    public void testNotRankable() throws InvalidSettingsException {
        Settings settings=new Settings(GameModes.playerMULTIPLAYER, 30, 5, 5, 120000, 500);
        Assert.assertFalse(settings.isRankable());
    }

    @Test (expected = InvalidSettingsException.class)
    public void testInvalidSettingsTooManyApples() throws InvalidSettingsException {
        Settings settings=new Settings(GameModes.SINGLEPLAYER, 30, 30*30, 5, 120000, 500);
    }

    @Test (expected = InvalidSettingsException.class)
    public void testInvalidSettingsTooSmallSize() throws InvalidSettingsException {
        Settings settings=new Settings(GameModes.playerMULTIPLAYER, 1, 0, 0, 120000, 500);
    }

}

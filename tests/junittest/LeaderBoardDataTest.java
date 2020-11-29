package junittest;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;
import leaderboard.LeaderboardData;
import leaderboard.NotRankableSetting;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class LeaderBoardDataTest {


    /**
     * @throws InvalidSettingsException
     * @throws NotRankableSetting
     */
    @Test (expected = NotRankableSetting.class)
    public void testmakeFilenameNotRankable() throws InvalidSettingsException, NotRankableSetting {
        Settings settings=new Settings(GameModes.playerMULTIPLAYER, 30, 5, 5, 120000, 500);
        LeaderboardData leaderboardData=new LeaderboardData();
        leaderboardData.makeFilename(settings);
    }

    @Test (expected = FileNotFoundException.class)
    public void testleaderBoardInitNoFile() throws InvalidSettingsException, FileNotFoundException {
        // File (or directory) with old name
        File file = new File("leaderboards" + System.getProperty("file.separator") + "sff.ser");

        // File (or directory) with new name
        File file2 = new File("leaderboards" + System.getProperty("file.separator") + "sff-temp.ser");
        file.renameTo(file2);

        LeaderboardData leaderboardData= new LeaderboardData();
        leaderboardData.leaderBoardInit(
                new Settings(GameModes.SINGLEPLAYER, Settings.getDefaultSize(), Settings.getDefaultFewapple(),
                        Settings.getDefaultFewbomb(), Settings.getDefaultTimelimit(), Settings.getDefaultSlowspeed()));



        // File (or directory) with new name
        File file3 = new File("leaderboards" + System.getProperty("file.separator") + "sff.ser");
        file.renameTo(file3);

    }
}

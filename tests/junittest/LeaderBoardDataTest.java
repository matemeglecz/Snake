package junittest;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;
import leaderboard.LeaderboardData;
import leaderboard.NotRankableSetting;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * a LeaderboardData osztály néhány metódusának tesztelésére szolgáló osztály
 */
public class LeaderBoardDataTest {


    /**
     * makeFile() fgv-t teszteli, hogy nem rangsorolható beállításokhoz dob-e NotRankableSetting kivételt
     *
     * @throws NotRankableSetting akkor dobódik, ha nem rangsorolható beállításokhoz akarunk ranglistát találni
     */
    @Test (expected = NotRankableSetting.class)
    public void testmakeFilenameNotRankable() throws NotRankableSetting {
        try {
            Settings settings = new Settings(GameModes.playerMULTIPLAYER, 30, 5, 5, 120000, 500);
            LeaderboardData leaderboardData=new LeaderboardData();
            leaderboardData.makeFilename(settings);
        } catch (InvalidSettingsException e) {
            e.printStackTrace();
        }
    }

    /**
     * leaderBoard inicializáló fgv-ét teszteli, hogy nem létező fájl esetén dob-e FileNotFoundException-t
     *
     * @throws FileNotFoundException akkor dobódik, ha nem található a keresett fájl
     */
    @Test (expected = FileNotFoundException.class)
    public void testleaderBoardInitNoFile() throws FileNotFoundException {
        // File (or directory) with old name
        File file = new File("leaderboards" + System.getProperty("file.separator") + "sff.ser");

        // File (or directory) with new name
        File file2 = new File("leaderboards" + System.getProperty("file.separator") + "sff-temp.ser");
        boolean success=file.renameTo(file2);

        LeaderboardData leaderboardData= new LeaderboardData();
        try {
            leaderboardData.leaderBoardInit(
                    new Settings(GameModes.SINGLEPLAYER, Settings.getDefaultSize(), Settings.getDefaultFewapple(),
                            Settings.getDefaultFewbomb(), Settings.getDefaultTimelimit(), Settings.getDefaultSlowspeed()));
        } catch (InvalidSettingsException e) {
            e.printStackTrace();
        }
        if(success) {
            File file3 = new File("leaderboards" + System.getProperty("file.separator") + "sff.ser");
            file.renameTo(file3);
        }
    }
}

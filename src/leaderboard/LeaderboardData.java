package leaderboard;

import game.GameModes;
import game.Settings;

import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardData extends AbstractTableModel {
    List<LeaderBoardItem> leaderBoard=new ArrayList<LeaderBoardItem>();

    @Override
    public int getRowCount() {
        return leaderBoard.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LeaderBoardItem item = leaderBoard.get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex;
            case 1: return item.getName();
            default: return item.getPoints();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Rank";
            case 1: return "Name";
            default: return "Points";
        }
    }

    /*public void setLeaderBoard(List<LeaderBoardItem> leaderBoard){
        this.leaderBoard=leaderBoard;
    }*/
    @SuppressWarnings("unchecked")
    public void leaderBoardInit(Settings settings) throws FileNotFoundException {
        String filename;
        if(settings.getMode()!= GameModes.SINGLEPLAYER
                || settings.getTimelimit()!=120000
                ||settings.getN()!=30){
            return;
        }

        switch (settings.getSpeed()){
            case 700: filename="s"; break;
            case 500: filename="n"; break;
            case 200: filename="f"; break;
            default: return;
        }

        switch (settings.getApplenum()){
            case 5: filename=filename + "f"; break;
            case 15: filename=filename + "n"; break;
            case 50: filename=filename + "p"; break;
            default: return;
        }

        switch (settings.getBombnum()){
            case 5: filename=filename + "f"; break;
            case 15: filename=filename + "n"; break;
            case 50: filename=filename + "p"; break;
            default: return;
        }

        filename=filename + ".ser";

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            leaderBoard=(List<LeaderBoardItem>)ois.readObject();
            ois.close();
        } catch(FileNotFoundException noFile) {
            throw noFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}

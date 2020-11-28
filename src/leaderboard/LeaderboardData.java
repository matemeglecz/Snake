package leaderboard;

import game.GameModes;
import game.NotSavableRank;
import game.Settings;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
            case 0: return rowIndex+1;
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

        try {
            filename=makeFilename(settings);
        } catch (NotRankableSetting notRankableSetting) {
            return;
        }


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
        //Collections.sort(leaderBoard, new RankComperator(), Collections.reverseOrder());
        leaderBoard.sort(new RankComperator());
        Collections.reverse(leaderBoard);
    }

    public void addRank(String name, int points, Settings settings){
        leaderBoard.add(new LeaderBoardItem(name, points));
        leaderBoard.sort(new RankComperator());
        Collections.reverse(leaderBoard);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(makeFilename(settings)));
            oos.writeObject(leaderBoard);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private String makeFilename(Settings settings) throws NotRankableSetting {
        String filename;
        filename="leaderboards" + System.getProperty("file.separator");
        if(!settings.isRankable()){
            throw new NotRankableSetting();
        }

        if(settings.getSpeed()==Settings.getDefaultSlowspeed()){
            filename=filename + "s";
        } else if(settings.getSpeed()==Settings.getDefaultNormalspeed()){
            filename=filename + "n";
        } else if(settings.getSpeed()==Settings.getDefaultFastspeed()){
            filename=filename + "f";
        } else {
            throw new NotRankableSetting();
        }

        if(settings.getApplenum()==Settings.getDefaultFewapple()){
            filename=filename + "f";
        } else if(settings.getApplenum()==Settings.getDefaultNormalapple()){
            filename=filename + "n";
        } else if(settings.getApplenum()==Settings.getDefaultPlentyapple()){
            filename=filename +"p";
        } else {
            throw new NotRankableSetting();
        }

        if(settings.getBombnum()==Settings.getDefaultFewbomb()){
            filename=filename +"f";
        } else if(settings.getBombnum()==Settings.getDefaultNormalbomb()){
            filename=filename +"n";
        } else if(settings.getBombnum()==Settings.getDefaultPlentybomb()){
            filename=filename +"p";
        } else {
            throw new NotRankableSetting();
        }

        return (filename + ".ser");

    }


}
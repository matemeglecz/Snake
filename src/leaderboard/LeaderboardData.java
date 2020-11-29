package leaderboard;

import game.Settings;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * egy beállításhoz tartozó ranglistát tárol, AbstractTable model leszármazottja, táblázatot valósítja meg
 */
public class LeaderboardData extends AbstractTableModel {
    /**
     * az eredményeket tároló lista
     */
    private List<LeaderBoardItem> leaderBoard= new ArrayList<>();

    /**
     * @return visszaadja, hogy hány sorból áll a táblázat
     */
    @Override
    public int getRowCount() {
        return leaderBoard.size();
    }

    /**
     * @return visszatér a táblázat oszlopainak a számával
     */
    @Override
    public int getColumnCount() {
        return 3;
    }

    /**
     * @param rowIndex hányadik sorból szeretnénk az értéket
     * @param columnIndex hányadik oszlopból szeretnénk az értéket
     * @return az adott cella értéke a táblázatban
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LeaderBoardItem item = leaderBoard.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> rowIndex + 1;
            case 1 -> item.getName();
            default -> item.getPoints();
        };
    }

    /**
     * @param column az oszlop, aminek a nevét kérjük le
     * @return az adott oszlop neve
     */
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Rank";
            case 1 -> "Name";
            default -> "Points";
        };
    }

    /**
     * beolvassa a kapott beállításokhoz a ranglistát, majd pontok szerint növekvő sorrendbe rendezi
     *
     * @param settings a beállítás, amelyikhez a táblázatot létre akarjuk hozni
     * @throws FileNotFoundException akkor dobódik, ha nem létezik a fájl, amiből be szeretnénk olvasni
     */
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        leaderBoard.sort(new RankComperator());
        Collections.reverse(leaderBoard);
    }

    /**
     * hozzáad egy új eredményt a ranglistához, majd pontok szerint növekvő sorrendbe rendezi
     *
     * @param name a menteni kívánt név
     * @param points a menteni kívánt pont
     * @param settings a beállítás, amihez az eredményt menteni szeretnénk
     */
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

    /**
     * legenerálja a kapott beállításhoz tartozó file nevét
     *
     * @param settings a beállítás, amihez a file nevét szeretnénk generálni
     * @return a keresett file neve
     * @throws NotRankableSetting ha a kapott beállításhoz nem tartozik ranglista
     */
    public String makeFilename(Settings settings) throws NotRankableSetting {
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

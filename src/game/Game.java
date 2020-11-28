package game;

import gamegui.*;
import leaderboard.LeaderboardData;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Game {
    private final ArrayList<Player> players=new ArrayList<>();
    private final Apple[] apples;
    private final Maze maze;
    private final GameModes gameMode;
    private final double timeLimit;
    private final int refreshRate;
    private final Settings settings;

    public Game(Settings settings){
        this.settings=settings;
        maze=new Maze(settings.getN(), settings.getN());
        refreshRate=settings.getSpeed();
        gameMode=settings.getMode();
        timeLimit=settings.getTimelimit();
        apples=new Apple[settings.getApplenum()];
        placeApples(settings.getApplenum());
        placeBombs(settings.getBombnum());
        if(settings.getMode()== GameModes.SINGLEPLAYER){
            Snake snake=new Snake(2, Color.BLUE,maze);
            maze.addSnake(snake, maze.getWidth()/2, maze.getHeight() / 2);
            players.add(new livePlayer(snake, VK_W, VK_S, VK_D, VK_A));
            //new Snake(this);
        } else if(settings.getMode()== GameModes.playerMULTIPLAYER){
            Snake snake1=new Snake(2, Color.ORANGE,maze);
            Snake snake2=new Snake(2, Color.BLUE,maze);
            maze.addSnake(snake1, maze.getWidth()/3, maze.getHeight()/2);
            maze.addSnake(snake2, (maze.getWidth()*2)/3, maze.getHeight()/2);
            players.add(new livePlayer(snake1, VK_W, VK_S, VK_D, VK_A));
            players.add(new livePlayer(snake2, VK_UP, VK_DOWN, VK_RIGHT, VK_LEFT));
            //new GamePanel(this);
        } else if(settings.getMode()== GameModes.robotMULTIPLAYER) {
            Snake snake1 = new Snake(2, Color.ORANGE, maze);
            Snake snake2 = new Snake(2, Color.BLUE, maze);
            maze.addSnake(snake1, maze.getWidth() / 3, maze.getHeight() / 2);
            maze.addSnake(snake2, (maze.getWidth() * 2) / 3, maze.getHeight() / 2);
            players.add(new livePlayer(snake1, VK_W, VK_S, VK_D, VK_A));
            players.add(new robotPlayer(snake2));
        }
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public Maze getMaze(){
        return maze;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public GameModes getGameMode(){
        return gameMode;
    }

    public double getTimeLimit(){ return timeLimit;}

    private void placeBombs(int num){
        for(int i=0; i< num; i++){
            placeOneThing(new Bomb());
        }
    }

    private void placeApples(int num){
        for(int i=0; i< num; i++){
            apples[i]=(Apple) placeOneThing(new Apple());
        }
    }

    private boolean allFieldsOccupied(){
        for(int h=0; h<maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                if(maze.getFields()[w][h].getOnFiled()==null){
                    return false;
                }
            }
        }
        return true;
    }

    public void refreshApples(){
        for (Apple apple : apples) {
            if(allFieldsOccupied()) return;
            if (apple.isEaten()) {
                apple.recycleApple();
                placeOneThing(apple);
            }
        }
    }

    public void playersMove(){
        for(Player p: players){
            p.moveSnake();
            if(p.isLost()) return;
        }
    }

    private Thing placeOneThing(Thing t){
        int x=0;
        int y=0;
        boolean failed=true;
        while(failed){
            x=getRandomInteger(0, maze.getWidth()-1);
            y=getRandomInteger(0, maze.getHeight()-1);
            if(maze.getFields()[x][y].getOnFiled()==null) failed=false;
        }
        maze.addThing(t, x, y);
        return t;
    }

    public static int getRandomInteger(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }

    public Settings getSettings() {
        return settings;
    }

    public boolean isRankable() {
        return settings.isRankable();
    }

    public void saveRank(String name) throws NotSavableRank{
        if(!isRankable()) throw new NotSavableRank();
        LeaderboardData leaderboard=new LeaderboardData();
        try {
            leaderboard.leaderBoardInit(settings);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        leaderboard.addRank(name, players.get(0).getPoints(), settings);


    }
}

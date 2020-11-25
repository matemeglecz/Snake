package game;

import gamegui.*;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Game {
    private final ArrayList<Player> players=new ArrayList<>();
    private final Apple[] apples;
    private final Maze maze;
    private final GameModes gameMode;
    private final double timeLimit;
    private final int refreshRate;

    public Game(GameModes mode, int x, int y, int appleNum, int bombNum, double tl, int speed){
        maze=new Maze(x, y);
        refreshRate=speed;
        gameMode=mode;
        timeLimit=tl;
        apples=new Apple[appleNum];
        placeApples(appleNum);
        placeBombs(bombNum);
        if(mode== GameModes.SINGLEPLAYER){
            Snake snake=new Snake(2, Color.BLUE,maze);
            maze.addSnake(snake, 5, 5);
            players.add(new Player(snake, VK_W, VK_S, VK_D, VK_A));
            //new Snake(this);
        } else if(mode== GameModes.playerMULTIPLAYER){
            Snake snake1=new Snake(2, Color.ORANGE,maze);
            Snake snake2=new Snake(2, Color.BLUE,maze);
            maze.addSnake(snake1, maze.getWidth()/3, maze.getHeight()/2);
            maze.addSnake(snake2, (maze.getWidth()*2)/3, maze.getHeight()/2);
            players.add(new Player(snake1, VK_W, VK_S, VK_D, VK_A));
            players.add(new Player(snake2, VK_UP, VK_DOWN, VK_RIGHT, VK_LEFT));
            //new GamePanel(this);
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

    public void refreshApples(){
        for (Apple apple : apples) {
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

    private int getRandomInteger(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }
}

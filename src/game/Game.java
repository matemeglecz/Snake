package game;

import gamegui.*;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players=new ArrayList<>();
    private Apple[] apples;
    private final Maze maze;
    private final GameModes gameMode;

    public Game(GameModes mode, int x, int y, int appleNum, int bombNum){
        maze=new Maze(x, y);
        gameMode=mode;
        if(mode== GameModes.SINGLEPLAYER){
            apples=new Apple[appleNum];
            placeApples(appleNum);
            placeBombs(bombNum);
            Snake snake=new Snake(10, maze);
            maze.addSnake(snake, 5, 5);
            players.add(new Player(snake, 'w', 's', 'd', 'a'));
            GameFrame f=new GameFrame(this, 500);
        }
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

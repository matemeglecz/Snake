package game;

import java.awt.*;

public class livePlayer extends Player{
    //private final Snake snake;
    private final int upKey;
    private final int downKey;
    private final int rightKey;
    private final int leftKey;
    /*private Direction MovingDir;
    private boolean lost;
    private int points;
    private final int originalLength;*/

    public livePlayer(Snake s, int up, int down, int right, int left){
        super(s);
        upKey=up;
        downKey=down;
        rightKey=right;
        leftKey=left;
    }

    public void keyPressed(int key){
        if(key==upKey){
            MovingDir=Direction.UP;
        } else if(key==downKey){
            MovingDir=Direction.DOWN;
        } else if(key==rightKey){
            MovingDir=Direction.RIGHT;
        } else if(key==leftKey){
            MovingDir=Direction.LEFT;
        }
    }

    /*public void moveSnake(){
        snake.move(MovingDir);
        points=snake.getLength()-originalLength;
        if(snake.isDead()){
            lost=true;
        }
    }

    public boolean isLost(){
        return lost;
    }

    public void lose(){
        lost=true;
    }

    public int getPoints(){
        return points;
    }

    public Color getColor(){
        return snake.getColor();
    }*/
}

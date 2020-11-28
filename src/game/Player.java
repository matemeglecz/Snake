package game;

import java.awt.*;

public abstract class Player {
    private final Snake snake;
    protected Direction MovingDir;
    private boolean lost;
    private int points;

    public Player(Snake s){
        snake=s;
        points=0;
        MovingDir=Direction.UP; // default
        lost=false;
    }

    public abstract void keyPressed(int key);

    public Field moveSnake(){
        Field next=snake.move(MovingDir);
        points=snake.getLength()-snake.getOriginalLength();
        if(snake.isDead()){
            lost=true;
        }
        return next;
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
    }

}

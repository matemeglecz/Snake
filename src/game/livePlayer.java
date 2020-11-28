package game;

import java.awt.*;

public class livePlayer extends Player{
    private final int upKey;
    private final int downKey;
    private final int rightKey;
    private final int leftKey;


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
}

package game;
public class Player {
    private final Snake snake;
    private final char upKey;
    private final char downKey;
    private final char rightKey;
    private final char leftKey;
    private Direction MovingDir;
    private boolean lost;
    private int points;
    private final int originalLength;

    public Player(Snake s, char up, char down, char right, char left){
        snake=s;
        originalLength=s.getLength();
        points=0;
        upKey=up;
        downKey=down;
        rightKey=right;
        leftKey=left;
        MovingDir=Direction.UP; // default
        lost=false;
    }

    public void keyPressed(char key){
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

    public void moveSnake(){
        snake.move(MovingDir);
        points=snake.getLength()-originalLength;
        if(snake.isDead()){
            lost=true;
        }
    }

    public boolean isLost(){
        return lost;
    }

    public int getPoints(){
        return points;
    }








}

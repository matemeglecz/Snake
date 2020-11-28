package game;

public class robotPlayer extends Player{

    public robotPlayer(Snake s){
        super(s);
    }

    @Override
    public void keyPressed(int key) { }

    @Override
    public void moveSnake(){
        switch (Game.getRandomInteger(1,4)) {
            case 1 -> MovingDir = Direction.DOWN;
            case 2 -> MovingDir = Direction.RIGHT;
            case 3 -> MovingDir = Direction.LEFT;
            default -> MovingDir = Direction.UP;
        }
        super.moveSnake();
    }
}

package game;
import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<SnakePart> snakeQueue;
    private final Maze maze;
    private final int originalLength;
    private Direction previousDir;
    private boolean dead;
    private final Color color;

    public Snake(int size, Color c, Maze m){
        maze=m;
        snakeQueue= new LinkedList<>();
        originalLength=size;
        color=c;
        for(int i=0; i<size;i++) {
            snakeQueue.add(new SnakePart(this));
        }

        previousDir=Direction.UP;
        dead=false;

    }

    public int getLength(){
        return snakeQueue.size();
    }

    public LinkedList<SnakePart> getSnakeQueue(){
        return snakeQueue;
    }

    public Field move(Direction d){
        Field nextField=snakeQueue.get(0).getPosition().getNeighbours().get(d);

        //magába nem tud menni
        //ha a következő field null akkor biztos kimegyünk
        if(nextField!=null && nextField.equals(snakeQueue.get(1).getPosition())){
            nextField=snakeQueue.get(0).getPosition().getNeighbours().get(previousDir);
        } else previousDir=d;

        if(nextField==null){
            Die();
            return null;
        }

        //magába nem tud menni itt volt

        if(nextField.getOnFiled()!=null){
            nextField.getOnFiled().HitBy(this);
            return nextField;
        }

        SnakePart sp=new SnakePart(this);
        snakeQueue.addFirst(sp);

        maze.addThing(snakeQueue.get(0), nextField);
        nextField.setThing(snakeQueue.get(0));

        maze.removeThing(snakeQueue.removeLast());
        return nextField;

    }

    public void Die(){
        dead=true;
    }

    boolean isDead(){
        return dead;
    }

    public void addLength(Field f){
        snakeQueue.addFirst(new SnakePart(this));
        maze.addThing(snakeQueue.get(0), f);
    }

    public Color getColor(){
        return color;
    }

    public int getOriginalLength() {
        return originalLength;
    }
}

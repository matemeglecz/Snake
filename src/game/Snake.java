package game;
import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakePart> snakeQueue;
    private Maze maze;
    private int length;
    private Direction previousDir;
    private boolean dead;

    public Snake(int size, Maze m){
        maze=m;
        snakeQueue= new LinkedList<>();
        length=size;
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

    public void move(Direction d){
        Field nextField=snakeQueue.get(0).getPosition().getNeighbours().get(d);
        if(nextField==null){
            Die();
            return;
        }

        //magába nem tud menni
        if(nextField.equals(snakeQueue.get(1).getPosition())){
            nextField=snakeQueue.get(0).getPosition().getNeighbours().get(previousDir);
        } else previousDir=d;

        if(nextField.getOnFiled()!=null){
            nextField.getOnFiled().HitBy(this);
            return;
        }

        SnakePart sp=new SnakePart(this);
        snakeQueue.addFirst(sp);
/*
        System.out.println(snakeQueue.size());
        System.out.println(snakeQueue.get(1));
        System.out.println(snakeQueue.get(1).getPosition());
*/
        maze.addThing(snakeQueue.get(0), nextField);
        nextField.setThing(snakeQueue.get(0));

        System.out.println(snakeQueue.get(0).getPosition());
        System.out.println(nextField);

        maze.removeThing(snakeQueue.removeLast());

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
}

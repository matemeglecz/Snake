package game;
import java.awt.*;
import java.util.LinkedList;

/**
 * a játékban egy kígyót reprezentáló osztály, amit játékosok irányítanak
 */
public class Snake {
    /**
     * a kígyó részi
     */
    private final LinkedList<SnakePart> snakeQueue;
    /**
     * a pálya, amin mozog
     */
    private final Maze maze;
    /**
     * a kígyó kezdeti hossza
     */
    private final int originalLength;
    /**
     * az előző mozgás iránya
     */
    private Direction previousDir;
    /**
     * ha a kígyó meghalt, akkor true, egyébként false
     */
    private boolean dead;
    /**
     * a kígyó színe
     */
    private final Color color;

    /**
     * létrehoz ehy size hosszúságú kígyót,
     * a previousDir-t UP-ra állítja,
     * élővé teszi a kígyót(dead=false)
     *
     * @param size a kígyó kezdeti hossza
     * @param c a kígyó színe
     * @param m a pálya, amin mozog
     */
    public Snake(int size, Color c, Maze m){
        maze=m;
        snakeQueue= new LinkedList<>();
        originalLength=size;
        color=c;
        for(int i=0; i<size;i++) {
            snakeQueue.add(new SnakePart(color));
        }

        previousDir=Direction.UP;
        dead=false;

    }

    /**
     * @return visszatér a kígyó aktuális hosszával
     */
    public int getLength(){
        return snakeQueue.size();
    }

    /**
     * @return visszatér a kígyó részeivel
     */
    public LinkedList<SnakePart> getSnakeQueue(){
        return snakeQueue;
    }

    /**
     * mozgatja a kígyót
     * ha abba az irányba szeretne menni, amiből jött akkor az új iránya az előző irány lesz,
     * ha a kígyó nekimegy a pálya szélének akkor megöli,
     * ütközteti a kígyót a mezőn elhelyezkedő dologgal, amire a kígyó lép
     *
     * @param d az irány, amibe a játékos mozgatni szeretné a kígyót
     * @return visszatér a mezővel amire a mozgás történt
     */
    public Field move(Direction d){
        Field nextField=snakeQueue.get(0).getPosition().getNeighbour(d);

        //magába nem tud menni
        //ha a következő field null akkor biztos kimegyünk
        if(nextField!=null && nextField.equals(snakeQueue.get(1).getPosition())){
            nextField=snakeQueue.get(0).getPosition().getNeighbour(previousDir);
        } else previousDir=d;

        if(nextField==null){
            Die();
            return null;
        }

        //magába nem tud menni itt volt

        if(nextField.getOnField()!=null){
            nextField.getOnField().HitBy(this);
            return nextField;
        }

        SnakePart sp=new SnakePart(color);
        snakeQueue.addFirst(sp);

        maze.addThing(snakeQueue.get(0), nextField);
        //nextField.setThing(snakeQueue.get(0));

        maze.removeThing(snakeQueue.removeLast());
        return nextField;

    }

    /**
     * megöli a kígyót
     */
    public void Die(){
        dead=true;
    }

    /**
     * @return visszatér a dead értékével
     */
    boolean isDead(){
        return dead;
    }

    /**
     * @param f megnőveli a kígyó hosszát egyel
     */
    public void addLength(Field f){
        snakeQueue.addFirst(new SnakePart(color));
        maze.addThing(snakeQueue.get(0), f);
    }

    /**
     * @return visszatér a kígyó hosszával
     */
    public Color getColor(){
        return color;
    }

    /**
     * @return visszatér a kígyó kezdeti hosszával
     */
    public int getOriginalLength() {
        return originalLength;
    }
}

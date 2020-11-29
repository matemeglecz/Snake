package game;

import java.awt.*;

/**
 * a játék egy játékosát reprezentáló osztály
 */
public abstract class Player {
    /**
     * a kígyó amit a játékos irányít
     */
    private final Snake snake;
    /**
     * az irány, amibe a következő mozgáskor a játékos mozogni akar
     */
    protected Direction MovingDir;
    /**
     * igaz, ha a játékos vesztett, hamis különben
     */
    private boolean lost;
    /**
     * a játékos pontjai, ami kiírásra kerül
     */
    private int points;

    /**
     * alaphelyzetbe állítja a játékost
     *
     * @param s a játékos kígyója
     */
    public Player(Snake s){
        snake=s;
        points=0;
        MovingDir=Direction.UP; // default
        lost=false;
    }

    /**
     * gombnyomás hatására meghívódik
     *
     * @param key lenyomott gomb
     */
    public abstract void keyPressed(int key);

    /**
     * mozgatja a kígyót a MovingDir értékének irányába,
     * frissíti a játékos pontjainak számát,
     * ha a kígyó meghalt, akkor a lost értékét igazra állítja
     *
     * @return visszatér a mezővel, amire a mozgás történt
     */
    public Field moveSnake(){
        Field next=snake.move(MovingDir);
        points=snake.getLength()-snake.getOriginalLength();
        if(snake.isDead()){
            lost=true;
        }
        return next;
    }

    /**
     * @return visszatér a lost értékével
     */
    public boolean isLost(){
        return lost;
    }

    /**
     * igazra állítja a lose értékét
     */
    public void lose(){
        lost=true;
    }

    /**
     * @return visszatér a points értékével
     */
    public int getPoints(){
        return points;
    }

    /**
     * @return visszatér a kígyó színével
     */
    public Color getColor(){
        return snake.getColor();
    }

}

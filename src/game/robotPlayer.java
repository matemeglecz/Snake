package game;

/**
 * A Player osztály leszármazottja, egy gépi játékost reprezentál,
 * random mozgatja a kígyót
 */
public class robotPlayer extends Player{

    /**
     * meghívja a  ősosztály konstruktorát
     *
     * @param s a játékoshoz tartozó kígyó
     */
    public robotPlayer(Snake s){
        super(s);
    }

    /**
     * nem csinál semmit
     *
     * @param key lenyomott gomb
     */
    @Override
    public void keyPressed(int key) { }

    /**
     * mozgatja a kígyót a MovingDir értékének irányába, amit random generál
     * frissíti a játékos pontjainak számát,
     * ha a kígyó meghalt, akkor a lost értékét igazra állítja
     *
     * @return visszatér a mezővel, amire a mozgás történt
     */
    @Override
    public Field moveSnake(){
        switch (Game.getRandomInteger(1,4)) {
            case 1 -> MovingDir = Direction.DOWN;
            case 2 -> MovingDir = Direction.RIGHT;
            case 3 -> MovingDir = Direction.LEFT;
            default -> MovingDir = Direction.UP;
        }
        return super.moveSnake();
    }
}

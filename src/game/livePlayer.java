package game;

/**
 * A Player osztály leszármazottja, egy valódi játékost reprezentál,
 * aki a billentyűzeten keresztül képes irányítani a kígyó mozgását
 */
public class livePlayer extends Player{
    /**
     * felfelé mozgásnak megfelelő billentyű
     */
    private final int upKey;
    /**
     * lefelé mozgásnak megfelelő billentyű
     */
    private final int downKey;
    /**
     * jobbra mozgásnak megfelelő billentyű
     */
    private final int rightKey;
    /**
     * balra mozgásnak megfelelő billentyű
     */
    private final int leftKey;


    /**
     * beállítja a játékos kígyóját és a mozgáshoz szükséges billentyűket
     *
     * @param s a játékoshoz tartozó kígyó
     * @param up felfelé mozgásnak megfelelő billentyű
     * @param down lefelé mozgásnak megfelelő billentyű
     * @param right jobbra mozgásnak megfelelő billentyű
     * @param left balra mozgásnak megfelelő billentyű
     */
    public livePlayer(Snake s, int up, int down, int right, int left){
        super(s);
        upKey=up;
        downKey=down;
        rightKey=right;
        leftKey=left;
    }

    /**
     * ha a játékos valamelyik mozgáshoz szükséges billentyűje lett lenyomva,
     * akkor a MovingDir-t a megfelelő irányra állítja
     *
     * @param key lenyomott gomb
     */
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

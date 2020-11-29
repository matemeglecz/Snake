package game;
import javax.swing.*;

/**
 * Egy dolgot reprezentál a mezőn, ami interakcióba tud lépni egy kígyóval
 */
public abstract class Thing {
    /**
     * a mező, amin elhelyezkedik
     */
    protected Field position;
    /**
     * A grafikus megjelenítéshez szükséges JPanel
     */
    protected JPanel thingPanel;

    /**
     * @return visszatér a grafikus megjelenítéshez szükséges JPanel-el
     */
    public JPanel getPanel(){
        return thingPanel;
    }

    /**
     * átállítja a position értékét
     *
     * @param f a Field, a position új értéke lesz
     */
    public void setPosition(Field f){
        position=f;
    }

    /**
     * @return visszatér a position értékével
     */
    public Field getPosition(){
        return position;
    }

    /**
     * ütköztet a kígyóval
     *
     * @param s a kígyó amivel ütközik
     */
    public abstract void HitBy(Snake s);

}

package game;

import javax.swing.*;
import java.awt.*;


/**
 * Thing absztrakt osztály leszármazottja, egy almát reprezentál a pályán,
 * amivel a kígyó interakcióba lép ha a Fieldhez ér, amin az alma elhelyezkedik
 * interakció hatására a kígyó hossza megnő
 */
public class Apple extends Thing {
    /**
     * ha az almát megette egy kígyó true, különben false,
     * ha az eaten true, akkor az alma lényegében invalid, újat kell lehelyezni a pályán helyette
     */
    private boolean eaten;

    /**
     * létrehozza az megjelenítését az almának(JPanel)
     * és érvényessé teszi az almát(eaten=false)
     */
    public Apple(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.GREEN);
        eaten=false;
    }


    /**
     * megnöveli a kígyó hosszát egyel, a pozívcióját null-ra állítja(nincs a pályán),
     * érvényteleníti az almát(eaten=true)
     *
     * @param s a snake, amivel az alma interakcióba kerül
     */
    @Override
    public void HitBy(Snake s) {
        s.addLength(position);
        position=null;
        eaten=true;
    }

    /**
     * validálja az almát
     */
    public void recycleApple(){
        eaten=false;
    }

    /**
     * @return visszatér az eaten értékével
     */
    public boolean isEaten(){
        return eaten;
    }

}

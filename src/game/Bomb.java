package game;

import javax.swing.*;
import java.awt.*;

/**
 * Thing absztrakt osztály leszármazottja, egy bombát reprezentál a pályán,
 * amivel a kígyó interakcióba lép ha a Fieldhez ér, amin a bomba elhelyezkedik
 * interakció hatására a kígyó meghal
 */
public class Bomb extends Thing {
    /**
     * létrehozza az megjelenítését a bombának(JPanel)
     */
    public Bomb(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.BLACK);
    }


    /**
     * Megöli a paraméterként kapott kígyót
     *
     * @param s a snake, amivel a bomba interakcióba kerül
     */
    @Override
    public void HitBy(Snake s) {
        s.Die();
    }

}

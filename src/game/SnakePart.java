package game;
import javax.swing.*;
import java.awt.*;

/**
 * Thing absztrakt osztály leszármazottja, a kígyó egy darabját reprezentálja a pályán,
 * amivel a kígyó interakcióba lép ha a Fieldhez ér, amin a kígyórész elhelyezkedik
 * interakció hatására a kígyó meghal, ami beleütközik
 */
public class SnakePart extends Thing{

    /**
     * létrehozza az megjelenítését a kígyórésznek(JPanel), a kígyó színével
     *
     * @param c a kígyó színe, ami létrehozza
     */
    public SnakePart(Color c){
        thingPanel=new JPanel();
        thingPanel.setBackground(c);

    }

    /**
     * Megöli a paraméterként kapott kígyót
     *
     * @param s a snake, amivel interakcióba kerül
     */
    @Override
    public void HitBy(Snake s) {
        s.Die();
    }



}

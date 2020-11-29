package game;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

/**
 * A pálya egy mezőjét reprezentáló osztály, amiken a kígyó közlekedik és amiken az almák és a bombák vannak
 */
public class Field {
    /**
     * A mezőn elhelyezkedő SnakePart/Apple/Bomb
     * ha üres, akkor az értéke null
     */
    private Thing onField;
    /**
     * egy mező szomszédos mezői, a kulcsok a szomszédságok iránya
     */
    private final TreeMap<Direction, Field> neighbours;


    /**
     * A Field konstruktora, létrehozza a neighbourst, de nem tölti fel
     */
    public Field(){
        neighbours=new TreeMap<>();
    }

    /**
     * hozzáad egy mezőt a neighbours-hoz
     *
     * @param d az irány amihez mazőt akarunk adni
     * @param f a maző amit a Map-hez szeretnénk adni
     */
    public void addNeighbour(Direction d, Field f){
        neighbours.put(d, f);
    }

    /**
     * @return ha van a mezőn valami akkor visszatér az ahhoz tartozó JPanel-el, ha üres akkor egy szürke JPanel-el tér vissza
     */
    public JPanel getPanel(){
        if(onField ==null){
            JPanel panel=new JPanel();
            panel.setBackground(Color.GRAY);
            return panel;
        } else return onField.getPanel();
    }

    /**
     * @param d az irány amihez a szomszédot szeretnénk
     * @return visszatér a mező megfelelő szomszédjával
     */
    public Field getNeighbour(Direction d){
        return neighbours.get(d);
    }

    /**
     * hozzáad egy dolgot a mezőhöz, a dolog pozicióját a sajátmagára állítja
     *
     * @param t a Thing, ami rákerül a mezőre
     */
    public void accept(Thing t){
        onField =t;
        t.setPosition(this);
    }

    /**
     * üressé teszi a mezőt
     */
    public void removeThing(){
        if(onField!=null) {
            onField.setPosition(null);
            onField = null;
        }
    }

    /**
     * @return visszatér a mezőn elhelyezkedő dologgal
     */
    public Thing getOnField(){
        return onField;
    }


}

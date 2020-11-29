package junittest;

import game.Apple;
import game.Field;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * a Field osztály néhány metódusának tesztelésére szolgáló osztály
 */
public class FieldTest {

    /**
     * teszteli, hogy ha a Fielden nincs Thing, akkor a JPanel-nek a színe szürke-e
     */
    @Test
    public void testGetPanelEmpty(){
        Field f= new Field();
        Assert.assertEquals(Color.gray, f.getPanel().getBackground());
    }

    /**
     * teszteli, hogy ha a Fielden van Thing, akkor a getPanel() a Thing JPanel-ét adja-e vissza
     */
    @Test
    public void testGetPanelApple(){
        Field f= new Field();
        Apple apple=new Apple();
        f.accept(apple);
        Assert.assertEquals(apple.getPanel(), f.getPanel());
    }

    /**
     * teszteli, hogy az acceot() hatására az onField értéke az accept()-nek átadott Thing lett-e
     */
    @Test
    public void testAcceptThing(){
        Field f= new Field();
        Apple apple=new Apple();
        f.accept(apple);
        Assert.assertEquals(apple ,f.getOnField());
    }

    /**
     * teszteli, a removeThing() fgv eltávolította-e a Thinget mezőről
     */
    @Test
    public void testRemoveThing(){
         Field f= new Field();
         Apple apple=new Apple();
         f.accept(apple);
         f.removeThing();
         Assert.assertNull(f.getOnField());
    }
}

package junittest;

import game.Apple;
import game.Field;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class FieldTest {

    @Test
    public void testGetPanelEmpty(){
        Field f= new Field();
        Assert.assertEquals(Color.gray, f.getPanel().getBackground());
    }

    @Test
    public void testGetPanelApple(){
        Field f= new Field();
        Apple apple=new Apple();
        f.accept(apple);
        Assert.assertEquals(apple.getPanel(), f.getPanel());
    }

    @Test
    public void testAcceptThing(){
        Field f= new Field();
        Apple apple=new Apple();
        f.accept(apple);
        Assert.assertEquals(apple ,f.getOnField());
    }

    @Test
    public void testRemoveThing(){
         Field f= new Field();
         Apple apple=new Apple();
         f.accept(apple);
         f.removeThing();
         Assert.assertNull(f.getOnField());
    }
}

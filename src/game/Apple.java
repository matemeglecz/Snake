package game;

import javax.swing.*;
import java.awt.*;

public class Apple extends Thing {
    private boolean eaten;

    public Apple(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.GREEN);
        eaten=false;
    }


    @Override
    public void HitBy(Snake s) {
        s.addLength(position);
        position=null;
        eaten=true;
    }

    public void setPosition(Field f){
        position=f;
    }

    public void recycleApple(){
        eaten=false;
    }

    public boolean isEaten(){
        return eaten;
    }

}

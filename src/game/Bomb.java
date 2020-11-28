package game;

import javax.swing.*;
import java.awt.*;

public class Bomb extends Thing {
    public Bomb(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.BLACK);
    }


    @Override
    public void HitBy(Snake s) {
        s.Die();
    }

}

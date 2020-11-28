package game;
import javax.swing.*;

public class SnakePart extends Thing{

    public SnakePart(Snake s){
        thingPanel=new JPanel();
        thingPanel.setBackground(s.getColor());

    }


    @Override
    public void HitBy(Snake s) {
        s.Die();
    }



}

import javax.swing.*;
import java.awt.*;

public class Apple extends Thing {
    private Field position;
    private final JPanel thingPanel;
    private boolean eaten;

    public Apple(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.GREEN);
        eaten=false;
    }
    public JPanel getPanel(){
        return thingPanel;
    }

    @Override
    public void HitBy(Snake s) {
        s.addLength(position);
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

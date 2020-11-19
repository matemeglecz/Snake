import javax.swing.*;
import java.awt.*;

public class Apple extends Thing {
    private Field position;
    private JPanel thingPanel;

    public Apple(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.GREEN);
    }
    public JPanel getPanel(){
        return thingPanel;
    }

    @Override
    public void HitBy(Snake s) {
        s.addLength(position);
    }

    public void setPosition(Field f){
        position=f;
    }

}

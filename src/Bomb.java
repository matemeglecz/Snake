import javax.swing.*;
import java.awt.*;

public class Bomb extends Thing {
    private Field position;
    private final JPanel thingPanel;

    public Bomb(){
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.BLACK);
    }
    public JPanel getPanel(){
        return thingPanel;
    }

    @Override
    public void HitBy(Snake s) {
        s.Die();
    }

}

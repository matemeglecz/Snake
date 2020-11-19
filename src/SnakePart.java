import javax.swing.*;
import java.awt.*;

public class SnakePart extends Thing{
    private Field position;
    private JPanel thingPanel;
    private Snake snake;

    public SnakePart(Snake s){
        snake=s;
        thingPanel=new JPanel();
        thingPanel.setBackground(Color.BLUE);

    }
    public JPanel getPanel(){
        return thingPanel;
    }

    @Override
    public void HitBy(Snake s) {
        s.Die();
    }

    public void CollideWith(Thing t){
        t.HitBy(snake);
    }

    /*public Snake getSnake(){
        return snake;
    }*/


}

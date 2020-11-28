package game;
import javax.swing.*;

public abstract class Thing {
    protected Field position;
    protected JPanel thingPanel;

    public JPanel getPanel(){
        return thingPanel;
    }

    public void setPosition(Field f){
        position=f;
    }

    public Field getPosition(){
        return position;
    }

    public abstract void HitBy(Snake s);

}

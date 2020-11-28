package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Field {
    private Thing onFiled;
    private final TreeMap<Direction, Field> neighbours;


    public Field(){
        neighbours=new TreeMap<>();
    }

    public void addNeighbour(Direction d, Field f){
        neighbours.put(d, f);
    }

    public JPanel getPanel(){
        if(onFiled==null){
            JPanel panel=new JPanel();
            panel.setBackground(Color.GRAY);
            return panel;
        } else return onFiled.getPanel();
    }

    public TreeMap<Direction, Field> getNeighbours(){
        return neighbours;
    }

    public void accept(Thing t){
        onFiled=t;
        t.setPosition(this);
    }

    public void removeThing(){
        onFiled.setPosition(null);
        onFiled=null;
    }

    public void setThing(Thing t){
        onFiled=t;
    }
    public Thing getOnFiled(){
        return onFiled;
    }


}

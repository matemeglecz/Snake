package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Field {
    //private ArrayList<Thing> things=new ArrayList<Thing>();
    private Thing onFiled;
    private TreeMap<Direction, Field> neighbours;
    //private JPanel displayedPanel;

    public Field(){
        neighbours=new TreeMap<>();
    }

    public void addNeighbour(Direction d, Field f){
        neighbours.put(d, f);
    }

    public JPanel getPanel(){
        //if(things.size()==0){
        if(onFiled==null){
            JPanel panel=new JPanel();
            panel.setBackground(Color.GRAY);
            return panel;
        } else return onFiled.getPanel();
            //return things.get(0).getPanel();
    }

    public TreeMap<Direction, Field> getNeighbours(){
        return neighbours;
    }

    public void accept(Thing t){
        //things.add(t);
        onFiled=t;
        t.setPosition(this);
        //ütköztet
    }

    public void removeThing(){
        onFiled.setPosition(null);
        onFiled=null;
        //if(things.size()==0) displayedPanel.setBackground(Color.GRAY);
    }

    public void setThing(Thing t){
        onFiled=t;
    }
    public Thing getOnFiled(){
        return onFiled;
    }


}

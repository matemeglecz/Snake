package game;

public class Maze {
    private Field[][] fields;
    private int width;
    private int height;

    public Maze(int x, int y){
        fields=new Field[x][y];
        width=x;
        height=y;

        for(int h=0; h<y; h++) {
            for (int w = 0; w < x; w++) {
                fields[w][h] = new Field();
            }
        }

        for(int h=0; h<y; h++){
            for(int w=0; w<x; w++){
                if(h>0) fields[w][h].addNeighbour(Direction.UP, fields[w][h-1]);
                if(h<y-2) fields[w][h].addNeighbour(Direction.DOWN, fields[w][h+1]);
                if(w>0) fields[w][h].addNeighbour(Direction.LEFT, fields[w-1][h]);
                if(w<x-2) fields[w][h].addNeighbour(Direction.RIGHT, fields[w+1][h]);
            }
        }

    }

    public Field[][] getFields(){
        return fields;
    }

    public void addThing(Thing t, int x, int y){
        fields[x][y].accept(t);
    }

    public void addThing(Thing t, Field f){
        for(int h=0; h<height; h++){
            for(int w=0; w<width; w++) {
                if(fields[w][h].equals(f)){
                    fields[w][h].accept(t);
                    return;
                }
            }
        }
        //exception
    }

    public void removeThing(Thing t){
        t.getPosition().removeThing();
    }

    public void addSnake(Snake s, int x, int y){ //head position
        for(int i=0; i<s.getLength(); i++){
            fields[x][y+i].accept(s.getSnakeQueue().get(i));
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}

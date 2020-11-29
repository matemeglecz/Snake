package game;

/**
 * A pályát reprezentáló osztály a játékban
 */
public class Maze {
    /**
     * a mezők, amikből a pálya áll
     */
    private final Field[][] fields;
    /**
     * a pálya szélessége
     */
    private final int width;
    /**
     * a pálya magassága
     */
    private final int height;

    /**
     * létrehozza a pálya mezőit,
     * beállítja a mezők közötti szomszédságokat
     *
     * @param x a pálya szélessége
     * @param y a pálya magassága
     */
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
                if(h<y-1) fields[w][h].addNeighbour(Direction.DOWN, fields[w][h+1]);
                if(w>0) fields[w][h].addNeighbour(Direction.LEFT, fields[w-1][h]);
                if(w<x-1) fields[w][h].addNeighbour(Direction.RIGHT, fields[w+1][h]);
            }
        }

    }

    /**
     * @return visszatér a pálya mezőivel
     */
    public Field[][] getFields(){
        return fields;
    }

    /**
     * hozzáad egy dolgot a megfelelő mezőre,
     *
     * @throws InvalidCoordinatesException kordináták érvénytelenek akkor InvalidCoordinatesException-t dob
     * @param t a dolog ami rákerül a pályára
     * @param x az x kordináta, ahova kerül
     * @param y az y kordináta ahova kerül
     */
    public void addThing(Thing t, int x, int y) throws InvalidCoordinatesException {
        try {
            fields[x][y].accept(t);
        }catch(ArrayIndexOutOfBoundsException e){
            throw new InvalidCoordinatesException();
        }
    }

    /**
     * hozzáad egy dolgot a megfelelő mezőre
     *
     * @param t a dolog ami rákerül a pályára
     * @param f a mező amire rákerül
     */
    public void addThing(Thing t, Field f){
        for(int h=0; h<height; h++){
            for(int w=0; w<width; w++) {
                if(fields[w][h].equals(f)){
                    fields[w][h].accept(t);
                    return;
                }
            }
        }
    }

    /**
     * leveszi a paraméterként kapott dolgot a pályáról
     *
     * @param t a dolog, amit le szeretnénk venni a pályáról
     */
    public void removeThing(Thing t){
        t.getPosition().removeThing();
    }

    /**
     * elhelyez egy kígyót a pályán, az adott mezőkre
     *
     * @param s a kígyó, ami rákerül a pályára
     * @param x a kígyó fejének x kordinátája
     * @param y a kígyó fejének y kordinátája
     */
    public void addSnake(Snake s, int x, int y) {
        for(int i=0; i<s.getLength(); i++){
            fields[x][y + i].accept(s.getSnakeQueue().get(i));
        }
    }

    /**
     * @return visszatér a pálya szélességével
     */
    public int getWidth(){
        return width;
    }

    /**
     * @return visszatér a pálya magasságával
     */
    public int getHeight(){
        return height;
    }

}

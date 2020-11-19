import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players=new ArrayList<>();
    Maze maze;

    public Game(GameModes mode, int x, int y){
        maze=new Maze(x, y);
        if(mode==GameModes.SINGLEPLAYER){
            placeApples(15);
            placeBombs(15);
            //maze.addThing(new Apple(), 1, 1);
            Snake snake=new Snake(10, maze);
            maze.addSnake(snake, 5, 5);
            //maze.addThing(new Bomb(), 3, 3);
            players.add(new Player(snake, 'w', 's', 'd', 'a'));
            GameFrame f=new GameFrame(this, 500);
        }
    }

    public Maze getMaze(){
        return maze;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    private void placeBombs(int num){
        boolean failed;
        int x=0;
        int y=0;
        for(int i=0; i< num; i++){
            failed=true;
            while(failed){
                x=getRandomInteger(0, maze.getWidth()-1);
                y=getRandomInteger(0, maze.getHeight()-1);
                if(maze.getFields()[x][y].getOnFiled()==null) failed=false;
            }
            maze.addThing(new Bomb(), x, y);
        }
    }

    private void placeApples(int num){
        boolean failed;
        int x=0;
        int y=0;
        for(int i=0; i< num; i++){
            failed=true;
            while(failed){
                x=getRandomInteger(0, maze.getWidth()-1);
                y=getRandomInteger(0, maze.getHeight()-1);
                if(maze.getFields()[x][y].getOnFiled()==null) failed=false;
            }
            maze.addThing(new Apple(), x, y);
        }
    }

    private int getRandomInteger(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }
}

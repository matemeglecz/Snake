import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players=new ArrayList<Player>();
    Maze maze;

    public Game(GameModes mode, int x, int y){
        maze=new Maze(x, y);
        if(mode==GameModes.SINGLEPLAYER){
            maze.addThing(new Apple(), 1, 1);
            Snake snake=new Snake(10, maze);
            maze.addSnake(snake, 5, 5);
            players.add(new Player(snake, 'w', 's', 'd', 'a'));
            GameFrame f=new GameFrame(this, 750);
        }
    }

    public Maze getMaze(){
        return maze;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }
}

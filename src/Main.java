import game.Game;
import game.GameModes;

public class Main {

    public static void main(String[] args0){
        //Game g=new Game(GameModes.SINGLEPLAYER, 30, 30, 15, 15, 120000);
        Game g=new Game(GameModes.playerMULTIPLAYER, 30, 30, 15, 15, 30000);
    }
}

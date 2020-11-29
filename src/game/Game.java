package game;

import leaderboard.LeaderboardData;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

/**
 * a játkot reprezentáló osztály, a játék irányításához szükséges fgv-ek itt találhatóak
 */
public class Game {
    /**
     * a játékban részt vevő játékosok
     */
    private final ArrayList<Player> players=new ArrayList<>();
    /**
     * a játékban levő almák
     */
    private final Apple[] apples;
    /**
     * a játékhoz tartozó pálya
     */
    private final Maze maze;
    /**
     * a játék beállításai
     */
    private final Settings settings;
    /**
     * igaz ha véget ért a játék, egyébként hamis
     */
    private boolean gameOver;

    /**
     * létrehozza a paraméterként kapott beállításoknak megfelelő játékot
     *
     * @param settings a beállítások, amik alapján a játékot létre kell hozni
     */
    public Game(Settings settings){
        gameOver=false;
        this.settings=settings;
        maze=new Maze(settings.getN(), settings.getN());
        apples=new Apple[settings.getApplenum()];
        placeApples(settings.getApplenum());
        placeBombs(settings.getBombnum());
        if(settings.getMode()== GameModes.SINGLEPLAYER){
            Snake snake=new Snake(2, Color.BLUE,maze);
            maze.addSnake(snake, (maze.getWidth()/2)-1, (maze.getHeight() / 2)-1);
            players.add(new livePlayer(snake, VK_W, VK_S, VK_D, VK_A));
        } else if(settings.getMode()== GameModes.playerMULTIPLAYER){
            Snake snake1=new Snake(2, Color.ORANGE,maze);
            Snake snake2=new Snake(2, Color.BLUE,maze);
            maze.addSnake(snake1, (int)Math.ceil(maze.getWidth() / 3.0)-1, (maze.getHeight() / 2)-1);
            maze.addSnake(snake2, (int)Math.ceil((maze.getWidth() * 2) / 3.0)-1, (maze.getHeight() / 2)-1);
            players.add(new livePlayer(snake1, VK_W, VK_S, VK_D, VK_A));
            players.add(new livePlayer(snake2, VK_UP, VK_DOWN, VK_RIGHT, VK_LEFT));
        } else if(settings.getMode()== GameModes.robotMULTIPLAYER) {
            Snake snake1 = new Snake(2, Color.ORANGE, maze);
            Snake snake2 = new Snake(2, Color.BLUE, maze);
            maze.addSnake(snake1, (int)Math.ceil(maze.getWidth() / 3.0)-1, (maze.getHeight() / 2)-1);
            maze.addSnake(snake2, (int)Math.ceil((maze.getWidth() * 2) / 3.0)-1, (maze.getHeight() / 2)-1);
            players.add(new livePlayer(snake1, VK_W, VK_S, VK_D, VK_A));
            players.add(new robotPlayer(snake2));
        }
    }

    /**
     * @return a kígyó sebbesség(ilyen gyakran mozog)
     */
    public int getRefreshRate() {
        return settings.getSpeed();
    }

    /**
     * @return visszatér a pályával
     */
    public Maze getMaze(){
        return maze;
    }

    /**
     * @return visszatér a játékban résztvevő játékosokkal
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * @return visszatér a játék módjával
     */
    public GameModes getGameMode(){
        return settings.getMode();
    }

    /**
     * @return visszatér a játék időlimitjével
     */
    public double getTimeLimit(){ return settings.getTimelimit();}

    /**
     * lehelyez bombákat a pályára random helyekre
     *
     * @param num lehelyezendő bombák száma
     */
    private void placeBombs(int num){
        for(int i=0; i< num; i++){
            placeOneThing(new Bomb());
        }
    }

    /**
     * lehelyez almákat a pályára random helyekre
     *
     * @param num lehelyezendő almák száma
     */
    private void placeApples(int num){
        for(int i=0; i< num; i++){
            apples[i]=(Apple) placeOneThing(new Apple());
        }
    }

    /**
     * @return ha minden mezőn van valami a pályán akkor igaz, egyébként hamis
     */
    private boolean allFieldsOccupied(){
        for(int h=0; h<maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                if(maze.getFields()[w][h].getOnField()==null){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * akkor hívódik, amikor a kígyó megeszik egy almát, az almát lehelyzi egy új helyre
     */
    public void refreshApples(){
        for (Apple apple : apples) {
            if(allFieldsOccupied()) return;
            if (apple.isEaten()) {
                apple.recycleApple();
                placeOneThing(apple);
            }
        }
    }

    /**
     * a játékosok mozgatják a kígyóikat a pályán,
     * kezeli azt az esetet, amikor frontálisan ütközik két kígyó(egy mezőre akarnak lépni egy körben)
     */
    public void playersMove(){
        Field[] nextfields=new Field[players.size()];
        int i=0;
        for(Player p: players){
            nextfields[i]= p.moveSnake();
            if(nextfields[i]!=null) {
                for (int j = 0; j < nextfields.length; j++) {
                    if(i==j) break;
                    if (nextfields[i] == nextfields[j]) {
                        players.get(i).lose();
                        players.get(j).lose();
                        nextfields[i].getPanel().setBackground(Color.red);
                    }
                }
            }
            i++;
        }
    }


    /**
     * lehelyez egy dolgot egy random helyre, ahol nincs másik dolog a pályán, amennyiben nincs tele a pálya
     *
     * @param t lehelyezendő dolog
     * @return a dolog ami le lett helyezve
     */
    private Thing placeOneThing(Thing t){
        int x;
        int y;
        boolean failed=true;
        while(failed){
            x=getRandomInteger(0, maze.getWidth()-1);
            y=getRandomInteger(0, maze.getHeight()-1);
            if(maze.getFields()[x][y].getOnField()==null) {
                failed = false;
                try {
                    maze.addThing(t, x, y);
                } catch (InvalidCoordinatesException e) {
                    failed=true;
                }
            }
        }
        return t;
    }

    /**
     * @param min a tartomány alsó határa
     * @param max a tartomány felső határa
     * @return random generált Integer a tartományban
     */
    public static int getRandomInteger(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }

    /**
     * @return igaz ha a játék beállításai rangsorolhatóak, hamis ha nem
     */
    public boolean isRankable() {
        return settings.isRankable();
    }

    /**
     * a megadott névvel elmeni a játékos eredményét a ranglistába a játék végén
     *
     * @param name a játékos neve
     * @throws NotSavableRank dobódik ha, a beállítások nem menthetőek
     */
    public void saveRank(String name) throws NotSavableRank{
        if(!isRankable()) throw new NotSavableRank();
        LeaderboardData leaderboard=new LeaderboardData();
        try {
            leaderboard.leaderBoardInit(settings);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        leaderboard.addRank(name, players.get(0).getPoints(), settings);
    }

    /**
     * meghívódik ha vége a játéknak, többjátékos módban kiválasztja a nyertest ha letelt az idő
     *
     * @param timeLeft a játékból hátralevő idő
     */
    public void gameOver(double timeLeft){
        gameOver=true;
        if(!(timeLeft>0)) {
            if (settings.getMode() != GameModes.SINGLEPLAYER) {
                if (players.get(0).getPoints() < players.get(1).getPoints()) {
                    players.get(0).lose();
                } else if (players.get(0).getPoints() == players.get(1).getPoints()) {
                    for (Player p : players) {
                        p.lose();
                    }
                } else players.get(1).lose();
            }
        }
    }

    /**
     * @return gameOver értéke
     */
    public boolean isGameOver() {
        return gameOver;
    }
}

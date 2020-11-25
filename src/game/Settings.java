package game;

public class Settings {
    private GameModes mode;
    private int x;
    private int y;
    private int applenum;
    private int bombnum;
    private double timelimit;
    private int speed;

    public Settings(GameModes mode, int x, int y, int appleNum, int bombNum, double tl, int speed){
        this.mode=mode;
        this.x=x;
        this.y=y;
        this.applenum=appleNum;
        this.bombnum=bombNum;
        this.timelimit=tl;
        this.speed=speed;
    }
}

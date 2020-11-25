package game;

public class Settings {
    private final GameModes mode;
    private final int x;
    private final int y;
    private final int applenum;
    private final int bombnum;
    private final double timelimit;
    private final int speed;

    public Settings(GameModes mode, int x, int y, int appleNum, int bombNum, double tl, int speed){
        this.mode=mode;
        this.x=x;
        this.y=y;
        this.applenum=appleNum;
        this.bombnum=bombNum;
        this.timelimit=tl;
        this.speed=speed;
    }

    public GameModes getMode(){
        return mode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getApplenum() {
        return applenum;
    }

    public int getBombnum() {
        return bombnum;
    }

    public double getTimelimit() {
        return timelimit;
    }

    public int getSpeed() {
        return speed;
    }
}

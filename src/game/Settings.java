package game;

import gamegui.SingleplayerHeader;

public class Settings {
    private GameModes mode;
    private int n;
    private int applenum;
    private int bombnum;
    private double timelimit;
    private int speed;

    public Settings(){
        this.mode=GameModes.SINGLEPLAYER;
        this.n=30;
        this.applenum=15;
        this.bombnum=15;
        this.timelimit=120000;
        this.speed=500;
    }

    public Settings(GameModes mode, int n, int appleNum, int bombNum, double tl, int speed) throws InvalidSettingsException{
        setMode(mode);
        setN(n);
        setApplenum(appleNum);
        setBombnum(bombNum);
        if((mode==GameModes.SINGLEPLAYER && (appleNum+bombNum)>(n*n-2))
                || (mode!=GameModes.SINGLEPLAYER && (appleNum+bombNum)>(n*n-4))){
            throw new InvalidSettingsException("Too many objects on the field");
        }
        setTimelimit(tl);
        setSpeed(speed);
    }

    public GameModes getMode(){
        return mode;
    }

    public void setMode(GameModes mode) {
        this.mode=mode;
    }

    public int getN() {
        return n;
    }

    public void setN(int n){
        this.n=n;
    }

    public int getApplenum() {
        return applenum;
    }

    public void setApplenum(int a) throws InvalidSettingsException{
        if((mode==GameModes.SINGLEPLAYER && (n*n-2)<applenum) || (mode!=GameModes.SINGLEPLAYER && (n * n - 4) < applenum)){
                throw new InvalidSettingsException("Number of apples is invalid");
            }
        applenum=a;
    }

    public int getBombnum() {
        return bombnum;
    }

    public void setBombnum(int n) throws InvalidSettingsException{
        if((mode==GameModes.SINGLEPLAYER && (n*n-2)<bombnum) || (mode!=GameModes.SINGLEPLAYER && (n * n - 4) < bombnum)){
            throw new InvalidSettingsException("Number of apples is invalid");
        }
        bombnum=n;
    }

    public double getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(double t) {
        timelimit=t;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int s){
        speed=s;
    }
}

package game;

import gamegui.SingleplayerHeader;

public class Settings {
    //default values
    private static final int DEFAULT_SIZE=30;
    private static final int DEFAULT_TIMELIMIT=3000;
    private static final int DEFAULT_SLOWSPEED=700;
    private static final int DEFAULT_NORMALSPEED=500;
    private static final int DEFAULT_FASTSPEED=200;
    private static final int DEFAULT_FEWAPPLE=5;
    private static final int DEFAULT_NORMALAPPLE=15;
    private static final int DEFAULT_PLENTYAPPLE=50;
    private static final int DEFAULT_FEWBOMB=5;
    private static final int DEFAULT_NORMALBOMB=15;
    private static final int DEFAULT_PLENTYBOMB=50;


    private GameModes mode;
    private int n;
    private int applenum;
    private int bombnum;
    private double timelimit;
    private int speed;

    public Settings(){
        this.mode=GameModes.SINGLEPLAYER;
        this.n=DEFAULT_SIZE;
        this.applenum=DEFAULT_NORMALAPPLE;
        this.bombnum=DEFAULT_NORMALBOMB;
        this.timelimit=DEFAULT_TIMELIMIT;
        this.speed=DEFAULT_NORMALSPEED;
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

    public boolean isRankable(){
        if (mode == GameModes.SINGLEPLAYER && timelimit==DEFAULT_TIMELIMIT && n==DEFAULT_SIZE) {
            return (speed == Settings.getDefaultSlowspeed() ||
                    speed == Settings.getDefaultNormalspeed() ||
                    speed == Settings.getDefaultFastspeed())
                    && (applenum == Settings.getDefaultFewapple() ||
                    applenum == Settings.getDefaultNormalapple() ||
                    applenum == Settings.getDefaultPlentyapple())
                    && (applenum == Settings.getDefaultFewbomb() ||
                    bombnum == Settings.getDefaultNormalbomb() ||
                    bombnum == Settings.getDefaultPlentybomb());
        }
        return false;
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
        if((mode==GameModes.SINGLEPLAYER && (n*n-2)<a) || (mode!=GameModes.SINGLEPLAYER && (n * n - 4) < a)){
                throw new InvalidSettingsException("Number of apples is invalid");
            }
        applenum=a;
    }

    public int getBombnum() {
        return bombnum;
    }

    public void setBombnum(int b) throws InvalidSettingsException{
        if((mode==GameModes.SINGLEPLAYER && (n*n-2)<b) || (mode!=GameModes.SINGLEPLAYER && (n * n - 4) < b)){
            throw new InvalidSettingsException("Number of bombs is invalid");
        }
        bombnum=b;
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

    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public static int getDefaultTimelimit() {
        return DEFAULT_TIMELIMIT;
    }

    public static int getDefaultSlowspeed() {
        return DEFAULT_SLOWSPEED;
    }

    public static int getDefaultNormalspeed() {
        return DEFAULT_NORMALSPEED;
    }

    public static int getDefaultFastspeed() {
        return DEFAULT_FASTSPEED;
    }

    public static int getDefaultFewapple() {
        return DEFAULT_FEWAPPLE;
    }

    public static int getDefaultNormalapple() {
        return DEFAULT_NORMALAPPLE;
    }

    public static int getDefaultPlentyapple() {
        return DEFAULT_PLENTYAPPLE;
    }

    public static int getDefaultFewbomb() {
        return DEFAULT_FEWBOMB;
    }

    public static int getDefaultNormalbomb() {
        return DEFAULT_NORMALBOMB;
    }

    public static int getDefaultPlentybomb() {
        return DEFAULT_PLENTYBOMB;
    }
}

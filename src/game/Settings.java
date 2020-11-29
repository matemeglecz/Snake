package game;

/**
 * a játék beállításait tároló osztály
 */
public class Settings {
    /**
     * a pálya méretének alapértelmezett értéke
     */
    private static final int DEFAULT_SIZE=30;
    /**
     * a játék időlimitének alapértelmezett értéke
     */
    private static final int DEFAULT_TIMELIMIT=120000;
    /**
     * a kígyó lassú sebességének alapértelmezett értéke
     */
    private static final int DEFAULT_SLOWSPEED=700;
    /**
     * a kígyó normál sebességének alapértelmezett értéke
     */
    private static final int DEFAULT_NORMALSPEED=500;
    /**
     * a kígyó gyors sebességének alapértelmezett értéke
     */
    private static final int DEFAULT_FASTSPEED=200;
    /**
     * a pályán található almák alapértelmezett kevés értéke
     */
    private static final int DEFAULT_FEWAPPLE=5;
    /**
     * a pályán található almák alapértelmezett normál értéke
     */
    private static final int DEFAULT_NORMALAPPLE=15;
    /**
     * a pályán található almák alapértelmezett sok értéke
     */
    private static final int DEFAULT_PLENTYAPPLE=50;
    /**
     * a pályán található bombák alapértelmezett kevés értéke
     */
    private static final int DEFAULT_FEWBOMB=5;
    /**
     * a pályán található bombák alapértelmezett normál értéke
     */
    private static final int DEFAULT_NORMALBOMB=15;
    /**
     * a pályán található bombák alapértelmezett sok értéke
     */
    private static final int DEFAULT_PLENTYBOMB=50;


    /**
     * a járék módja
     */
    private GameModes mode;
    /**
     * a pálya oldalának mérete(a játék nxn-es)
     */
    private int n;
    /**
     * az almák száma a pályán
     */
    private int applenum;
    /**
     * a bombák száma a pályán
     */
    private int bombnum;
    /**
     * az időlimit a játékhoz(ms)
     */
    private double timelimit;
    /**
     * a kígyó sebessége(ms)
     */
    private int speed;

    /**
     * a konstruktor paraméterek nélküli hívása
     * a settings minden értékét az alapértelmezett normál beállításokra állítja
     */
    public Settings(){
        this.mode=GameModes.SINGLEPLAYER;
        this.n=DEFAULT_SIZE;
        this.applenum=DEFAULT_NORMALAPPLE;
        this.bombnum=DEFAULT_NORMALBOMB;
        this.timelimit=DEFAULT_TIMELIMIT;
        this.speed=DEFAULT_NORMALSPEED;
    }

    /**
     * az adott paraméterekkel létrehozza a settingset
     *
     * @param mode a játék módja
     * @param n a pálya oldalának a mérete
     * @param appleNum a pályán található almák száma
     * @param bombNum a pályán található bombák száma
     * @param tl a játék időlimite(ms)
     * @param speed a kígyó sebessége(ms)
     * @throws InvalidSettingsException akkor dobódik, ha nem lehet ilyen beállításokhoz játékot létrehozni
     */
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

    /**
     * Egy beállítás rangsorolható, ha az attribútumai a default értékekkel megegyeznek
     *
     * @return igaz, ha a beállítás rangsorolható, hamis ha nem
     */
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

    /**
     * @return visszatér a játk módjával
     */
    public GameModes getMode(){
        return mode;
    }

    /**
     * beállítja a játék módját
     *
     * @param mode a játék új módja
     */
    public void setMode(GameModes mode) {
        this.mode=mode;
    }

    /**
     * @return a pálya oldalának a mérete
     */
    public int getN() {
        return n;
    }

    /**
     * beállítja a pálya oldalának a méretét
     *
     * @param n a pálya oldalának az új mérete
     * @throws InvalidSettingsException akkor dobódik, ha a túl kicsi pálya jönne létre az adott játékmódhoz
     */
    public void setN(int n) throws InvalidSettingsException{
        if((mode==GameModes.SINGLEPLAYER && (n*n)<2) || (mode!=GameModes.SINGLEPLAYER && (n * n) < 4))
            throw new InvalidSettingsException("Too small size");
        this.n=n;
    }

    /**
     * @return a pályán levő almák száma
     */
    public int getApplenum() {
        return applenum;
    }

    /**
     * beállítja az almák számát
     *
     * @param a az almák új száma
     * @throws InvalidSettingsException akkor dobódik, ha a pályára nem lehet kitenni ennyi almát, az adoot játékmódnál
     */
    public void setApplenum(int a) throws InvalidSettingsException{
        if((mode==GameModes.SINGLEPLAYER && (n*n-2)<a) || (mode!=GameModes.SINGLEPLAYER && (n * n - 4) < a)){
                throw new InvalidSettingsException("Number of apples is invalid");
            }
        applenum=a;
    }

    /**
     * @return a pályán levő bombák száma
     */
    public int getBombnum() {
        return bombnum;
    }

    /**
     * beállítja a bombák számát
     *
     * @param b a bombák új száma
     * @throws InvalidSettingsException akkor dobódik, ha a pályára nem lehet kitenni ennyi bombát, az adoot játékmódnál
     */
    public void setBombnum(int b) throws InvalidSettingsException{
        if((mode==GameModes.SINGLEPLAYER && (n*n-2)<b) || (mode!=GameModes.SINGLEPLAYER && (n * n - 4) < b)){
            throw new InvalidSettingsException("Number of bombs is invalid");
        }
        bombnum=b;
    }

    /**
     * @return a játék időlimitje(ms)
     */
    public double getTimelimit() {
        return timelimit;
    }

    /**
     * beállítja a játék új időlimitjét
     *
     * @param t az új időlimit(ms)
     */
    public void setTimelimit(double t) {
        timelimit=t;
    }

    /**
     * @return a kígyó sebessége(ms)
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * beállítja a kígyó új sebességét
     *
     * @param s a kígyó új sebessége(ms)
     */
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

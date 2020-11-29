package game;

/**
 * Exception osztály leszármazottja, ha érvénytelen paraméterekkel akarunk létrehozni egy beállítást ez dobódik,
 * dobáskor message-ként meg kell adni a dobás okát
 */
public class InvalidSettingsException extends Exception{
    public InvalidSettingsException(String s){
        super(s);
    }
}

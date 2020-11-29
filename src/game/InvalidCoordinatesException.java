package game;

/**
 * Exception osztály leszármazottja, ha érvénytelen paraméterekkel akarunk hozzáadni egy dolgot a pályához, akkor ez dobódik
 */
public class InvalidCoordinatesException extends Exception{
    public InvalidCoordinatesException(){}
}

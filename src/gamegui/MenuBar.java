package gamegui;

import javax.swing.*;
import javax.swing.event.ChangeListener;


/**
 * JMenuBar leszármazottja, MenuBar a framen, amin 3 gomb van(New Game, Leaderboard, Settings),
 * menüpontra való kattintásra megváltoztatja a frameben a view értékét, ami által megváltozik a megjelenített panel
 */
public class MenuBar extends JMenuBar {

    public MenuBar(SnakeFrame snakeFrame){
        this.add(Box.createHorizontalGlue());
        JMenuItem newGame= new JMenuItem("New Game");
        JMenuItem leaderBoard= new JMenuItem("Leaderboard");
        JMenuItem settings= new JMenuItem("Settings");
        ChangeListener menuListener= e -> {
            if(e.getSource().equals(newGame)){
                snakeFrame.setView(View.NEW_GAME);
            } else if(e.getSource().equals(leaderBoard)){
                snakeFrame.setView(View.LEADERBOARD);
            } else if(e.getSource().equals(settings)){
                snakeFrame.setView(View.SETTINGS);
            }
        };
        newGame.addChangeListener(menuListener);
        add(newGame);
        leaderBoard.addChangeListener(menuListener);
        add(leaderBoard);
        settings.addChangeListener(menuListener);
        add(settings);

    }

}

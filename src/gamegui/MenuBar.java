package gamegui;

import game.Snake;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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

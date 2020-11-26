package gamegui;

import game.Snake;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    public MenuBar(SnakeFrame snakeFrame){
        JMenu newGame= new JMenu("New Game");
        JMenu leaderBoard= new JMenu("Leaderboard");
        JMenu settings= new JMenu("Settings");
        MenuListener menuListener=new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                if(e.getSource().equals(newGame)){
                    snakeFrame.setView(View.NEW_GAME);
                    System.out.println("haho");
                } else if(e.getSource().equals(leaderBoard)){
                    snakeFrame.setView(View.LEADERBOARD_SETTINGS);
                } else if(e.getSource().equals(settings)){
                    snakeFrame.setView(View.SETTINGS);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        };
        newGame.addMenuListener(menuListener);
        add(newGame);
        leaderBoard.addMenuListener(menuListener);
        add(leaderBoard);
        settings.addMenuListener(menuListener);
        add(settings);
    }

}

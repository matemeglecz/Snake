package gamegui;

import game.Game;
import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {
    static Game game;
    static Settings settings;
    private JPanel mainPanel;

    static {
        try {
            settings = new Settings(GameModes.playerMULTIPLAYER, 30, 15, 15, 30000, 500);
        } catch (InvalidSettingsException e) {
            e.printStackTrace();
        }
    }

    private View view=View.SETTINGS;


    public SnakeFrame() {
        super("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(new MenuBar(this));

        mainPanel=new SettingsPanel();
        add(mainPanel);

        //game=new Game(settings);
        //add(new GamePanel());
        this.pack();
        setVisible(true);
    }

    public void refreshView(){
        if(view==View.NEW_GAME){
            remove(mainPanel);
            game=new Game(settings);
            mainPanel=new GamePanel();
            add(mainPanel);
            setVisible(true);
            view=View.GAME;
        } else if(view==View.SETTINGS){
            remove(mainPanel);
            mainPanel=new SettingsPanel();
            add(mainPanel);
            setVisible(true);
        }

    }

    public void setView(View v){
        if(view!=v){
            view=v;
            refreshView();
        }
    }

    /*private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gp=new GamePanel();
            mp.remove(testP);
            mp.add(new GamePanel(), BorderLayout.CENTER);
            //loadGamePanel();
            //remove(testP);
            revalidate();
        }
    }

    private void loadGamePanel(){
        this.add(new GamePanel(), BorderLayout.CENTER);
        this.revalidate();
    }*/
}

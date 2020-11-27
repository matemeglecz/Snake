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
    private MenuBar menuBar;

    static {
        try {
            settings = new Settings(GameModes.playerMULTIPLAYER, 30, 15, 15, 30000, 500);
        } catch (InvalidSettingsException e) {
            e.printStackTrace();
        }
    }

    private View view=View.GAME;


    public SnakeFrame() {
        super("Snake");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuBar=new MenuBar(this);
        setJMenuBar(menuBar);

        game=new Game(settings);
        mainPanel=new GamePanel();
        add(mainPanel);

        //g
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
            this.pack();
            setVisible(true);
            mainPanel.requestFocusInWindow();

            view=View.GAME;
        } else if(view==View.SETTINGS){
            remove(mainPanel);
            mainPanel=new SettingsPanel();
            add(mainPanel);
            this.pack();
            setVisible(true);
        } else if(view==View.LEADERBOARD){
            remove(mainPanel);
            mainPanel=new LeaderBoardPanel();
            add(mainPanel);
            this.pack();
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

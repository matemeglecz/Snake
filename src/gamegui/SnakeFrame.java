package gamegui;

import game.Game;
import game.Settings;
import javax.swing.*;


public class SnakeFrame extends JFrame {
    static Game game;
    static Settings settings;
    private JPanel mainPanel;
    private View view=View.GAME;


    public SnakeFrame() {
        super("Snake");
        ImageIcon icon = new ImageIcon("images" + System.getProperty("file.separator") + "snakeicon.png");
        setIconImage(icon.getImage());
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MenuBar menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        settings = new Settings();
        game=new Game(settings);
        mainPanel=new GamePanel(this);
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
            mainPanel=new GamePanel(this);
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
}

package gamegui;

import game.Game;
import game.Settings;
import javax.swing.*;


/**
 * A játékhoz létrejövő ablak, itt történik a nézetek közötti váltás
 */
public class SnakeFrame extends JFrame {
    /**
     * a játék, ami megjelenik
     */
    static Game game;
    /**
     * beállítások, ami alapján új játék létrejön
     */
    static Settings settings;
    /**
     * a középső panel a frame-n
     */
    private JPanel mainPanel;
    /**
     * az állapot, ez alapján váltódnak a nézetek
     */
    private View view=View.GAME;


    /**
     * létrehozza a frame-t, alaphelyzetben egy játék jelenik meg default Settings-el
     * megváltoztatja a frame icon-át, nevét és hozzáad egy menubar-t
     */
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


        this.pack();
        setVisible(true);
    }

    /**
     * frissíti a játék nézetét, egy állapotgép(kicseréli a panelt)
     */
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

    /**
     * átállítja a frame nézetét
     *
     * @param v új nézet
     */
    public void setView(View v){
        if(view!=v){
            view=v;
            refreshView();
        }
    }
}

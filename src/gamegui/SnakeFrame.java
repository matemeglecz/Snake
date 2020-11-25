package gamegui;

import game.Game;
import game.GameModes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {
    static Game game;
    private View view=View.GAME;


    public SnakeFrame() {
        super("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(new MenuBar(this));




        game=new Game(GameModes.playerMULTIPLAYER, 30, 30, 15, 15, 30000, 500);
        add(new GamePanel());
        this.pack();
        setVisible(true);
    }

    public void refreshView(){

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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame{

    private final Game game;
    private final int INTERVAL;
    private final Timer timer;
    private final JPanel mainPanel;


    public GameFrame(Game g, int i){
        super("Snake");
        INTERVAL=i;
        game=g;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(800, 800);
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(30, 30));

        for(int h=0; h<g.getMaze().getHeight(); h++){
            for(int w=0; w<g.getMaze().getWidth(); w++) {
                mainPanel.add(game.getMaze().getFields()[w][h].getPanel(), h*game.getMaze().getWidth()+w);
            }
        }

        super.add(mainPanel, BorderLayout.CENTER);

        timer = new Timer(INTERVAL, new TimerListener());
        KeyListener listener = new MoveKeyListener();
        addKeyListener(listener);
        setFocusable(true);

        super.pack();
        timer.start();
        super.setVisible(true);


    }

    private class TimerListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            playersMove();
            mainPanel.removeAll();
            //Refresh the panel
            for(int h=0; h<game.getMaze().getHeight(); h++){
                for(int w=0; w<game.getMaze().getWidth(); w++) {
                    mainPanel.add(game.getMaze().getFields()[w][h].getPanel(), h*30+w);
                }
            }
            for(Player p: game.getPlayers()) {
                if (p.isLost()) {
                    timer.stop();
                }
            }

            mainPanel.revalidate();
        }
    }

    private class MoveKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            for(Player p: game.getPlayers()){
                p.keyPressed((char)e.getKeyChar());
            }
            System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        }
    }

    private void playersMove(){
        for(Player p: game.getPlayers()){
            p.moveSnake();
            if(p.isLost()) return;
        }
    }







}

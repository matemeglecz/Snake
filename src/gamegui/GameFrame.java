package gamegui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.*;

import static java.awt.event.KeyEvent.VK_ENTER;

public class GameFrame extends JFrame{

    static Game game;
    private final Timer gameTimer;
    private final Header header;
    private final JPanel mainPanel;


    public GameFrame(Game g, int interval){
        super("Snake");
        setLayout(new BorderLayout());
        game=g;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel= new GameBoardPanel();
        JPanel mainPConstrain = new JPanel(new GridBagLayout()); //hogy resizolható legyen
        mainPConstrain.setBackground(new Color(43, 43, 43));
        mainPConstrain.add(mainPanel);
        this.add(mainPConstrain, BorderLayout.CENTER);

        //Header
        if(game.getGameMode().equals(GameModes.SINGLEPLAYER)) {
            header = new SingleplayerHeader();
        } else {
            header= new MultiplayerHeader();
        }

        this.add(header, BorderLayout.NORTH);


        gameTimer = new Timer(interval, new GameTimerListener());
        KeyListener listener = new MoveKeyListener();
        addKeyListener(listener);
        setFocusable(true);

        StartKeyListener start=new StartKeyListener();
        addKeyListener(start);

        /*JPanel westPanel=new JPanel();
        add(westPanel, BorderLayout.WEST);
        westPanel.setBackground(new Color(43, 43, 43));

        JPanel eastPanel=new JPanel();
        add(eastPanel, BorderLayout.WEST);
        eastPanel.setBackground(new Color(43, 43, 43));*/

        this.pack();
        this.setVisible(true);


    }

    private class GameBoardPanel extends JPanel {

        public GameBoardPanel() {
            setLayout(new GridBagLayout());
            setBackground(new Color(43, 43, 43));
            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < game.getMaze().getHeight(); row++) {
                for (int col = 0; col < game.getMaze().getWidth(); col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;
                    gbc.weightx=1;
                    gbc.weighty=1;
                    gbc.fill = GridBagConstraints.BOTH;
                    add(game.getMaze().getFields()[col][row].getPanel(), gbc);
                }
            }
        }

        @Override
        public final Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            Dimension prefSize;
            Component c = getParent();
            if (c.getWidth()>d.getWidth() && c.getHeight()>d.getHeight()) {
                prefSize = c.getSize();
            } else {
                prefSize = d;
            }
            int w = (int) prefSize.getWidth();
            int h = (int) prefSize.getHeight();
            // the smaller of the two sizes
            int s = (Math.min(w, h));
            return new Dimension(s,s);
        }

    }

    private class GameTimerListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            game.playersMove();
            game.refreshApples();
            refreshMainPanel();
            if(header.getTime()<=0) gameTimer.stop();
            for(Player p: game.getPlayers()) {
                if (p.isLost()) {
                    gameTimer.stop();
                }
            }

        }
    }

    private void refreshMainPanel(){
        mainPanel.removeAll();
        //Refresh the panel
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.insets=new Insets(1,1,1,1);
        for(int h=0; h<game.getMaze().getHeight(); h++){
            for(int w=0; w<game.getMaze().getWidth(); w++) {
                gbc.gridx=w;
                gbc.gridy=h;
                gbc.weightx=1;
                gbc.weighty=1;
                gbc.fill = GridBagConstraints.BOTH;
                mainPanel.add(game.getMaze().getFields()[w][h].getPanel(), gbc);
            }
        }

        mainPanel.revalidate();
    }

    private class MoveKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            for(Player p: game.getPlayers()){
                p.keyPressed(e.getKeyCode());
            }
            System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        }
    }

    private class StartKeyListener implements  KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==VK_ENTER) {
                gameTimer.start();
                header.headerTimerStart();
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }









}

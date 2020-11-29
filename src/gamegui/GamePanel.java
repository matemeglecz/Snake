package gamegui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import game.*;

import static java.awt.event.KeyEvent.VK_ENTER;

/**
 * JPanel leszármazottja, a panel, amin a játék történik
 */
public class GamePanel extends JPanel{

    /**
     * a SnakeFrame, amin a panel megjelenik
     */
    private final SnakeFrame snakeFrame;
    /**
     * timer, ami alapján a képfrissítés és a kígyók mozgatása történik
     */
    private final Timer gameTimer;
    /**
     * a borderlayout északi panelje, játékkal kapcsolatos információkat jelenít meg
     */
    private final Header header;
    /**
     * a borderlayout center panelje, a pálya itt jelenik meg
     */
    private final JPanel mainPanel;
    /**
     * a borderlayout west panelje, singleplayer módban nincs rá szükség,
     * többjátékos módokban a játékosok pontjai jelennek meg itt
     */
    private SidePanel leftPanel=null;
    /**
     * a borderlayout east panelje, singleplayer módban nincs rá szükség,
     * többjátékos módokban a játékosok pontjai jelennek meg itt
     */
    private SidePanel rightPanel=null;
    /**
     * a játék végén, ha a játék rangsorolható ide kéri be a program a játékos által menteni kívánt nevet
     */
    private JTextField nameTextfield;


    /**
     * ferakja a megfelelő paneleket és létrehozza a timereket és a listenereket
     *
     * @param sf SnakeFrame, amin a panel megjelenik
     */
    public GamePanel(SnakeFrame sf){

        snakeFrame=sf;
        setLayout(new BorderLayout());

        mainPanel= new GameBoardPanel();
        JPanel mainPConstrain = new JPanel(new GridBagLayout()); //hogy resizolható legyen
        mainPConstrain.setBackground(new Color(43, 43, 43));
        mainPConstrain.add(mainPanel);
        this.add(mainPConstrain, BorderLayout.CENTER);

        //Header
        if(SnakeFrame.game.getGameMode().equals(GameModes.SINGLEPLAYER)) {
            header = new SingleplayerHeader();
        } else {
            header= new MultiplayerHeader();
            leftPanel= new SidePanel(SnakeFrame.game.getPlayers().get(0));
            rightPanel= new SidePanel(SnakeFrame.game.getPlayers().get(1));
            this.add(leftPanel, BorderLayout.WEST);
            this.add(rightPanel, BorderLayout.EAST);
        }

        this.add(header, BorderLayout.NORTH);

        gameTimer = new Timer(SnakeFrame.game.getRefreshRate(), new GameTimerListener());
        KeyListener listener = new MoveKeyListener();
        addKeyListener(listener);
        setFocusable(true);

        StartKeyListener start=new StartKeyListener();
        addKeyListener(start);

        this.setVisible(true);


    }

    /**
     * a panel, amin a pálya megejelenik és a játék folyik
     */
    private class GameBoardPanel extends JPanel {

        /**
         * megjeleníti a pálya alap állapotát
         */
        public GameBoardPanel() {
            setLayout(new GridBagLayout());
            setBackground(new Color(43, 43, 43));
            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < SnakeFrame.game.getMaze().getHeight(); row++) {
                for (int col = 0; col < SnakeFrame.game.getMaze().getWidth(); col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;
                    gbc.weightx=1;
                    gbc.weighty=1;
                    gbc.fill = GridBagConstraints.BOTH;
                    add(SnakeFrame.game.getMaze().getFields()[col][row].getPanel(), gbc);
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

        @Override
        public void revalidate(){

        }

    }

    private class GameTimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SnakeFrame.game.playersMove();
            SnakeFrame.game.refreshApples();
            refreshMainPanel();
            if(header.getTime()<=0) {
                gameTimer.stop();
                SnakeFrame.game.gameOver(header.getTime());
            }

            for(Player p: SnakeFrame.game.getPlayers()) {
                if (p.isLost()) {
                    gameTimer.stop();
                    SnakeFrame.game.gameOver(header.getTime());
                    }
            }
            if(SnakeFrame.game.isGameOver()) {
                JPanel bottomPanel = new JPanel();
                bottomPanel.setBackground(new Color(43, 43, 43));
                JButton nextButton = new JButton();
                if (SnakeFrame.game.isRankable() && !SnakeFrame.game.getPlayers().get(0).isLost()) {
                    JLabel nameLabel=new JLabel("Name:");
                    nameLabel.setForeground(Color.white);
                    bottomPanel.add(nameLabel);
                    nameTextfield = new JTextField();
                    nameTextfield.setColumns(15);
                    bottomPanel.add(nameTextfield);
                    nextButton.setText("Next");
                    nextButton.setActionCommand("Next");
                } else {
                    nextButton.setText("New Game");
                    nextButton.setActionCommand("New game");
                }
                nextButton.addActionListener(new NameButtonListener());
                bottomPanel.add(nextButton);
                add(bottomPanel, BorderLayout.SOUTH);
                refreshMainPanel();

            }
        }
    }

    private class NameButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Next")){
                try {
                    SnakeFrame.game.saveRank(nameTextfield.getText());
                } catch (NotSavableRank notSavableRank) {
                    notSavableRank.printStackTrace();
                }
                JButton source=(JButton) e.getSource();
                source.setText("New Game");
                source.setActionCommand("New game");
                nameTextfield.setEditable(false);
                refreshMainPanel();
            } else if(e.getActionCommand().equals("New game")){
                snakeFrame.setView(View.NEW_GAME);
            }

        }
    }

    /*private void refreshMainPanel(){
        mainPanel.removeAll();
        //Refresh the panel
        GridBagConstraints gbc = new GridBagConstraints();
        for(int h=0; h<SnakeFrame.game.getMaze().getHeight(); h++){
            for(int w=0; w<SnakeFrame.game.getMaze().getWidth(); w++) {
                gbc.gridx=w;
                gbc.gridy=h;
                gbc.weightx=1;
                gbc.weighty=1;
                gbc.fill = GridBagConstraints.BOTH;
                mainPanel.add(SnakeFrame.game.getMaze().getFields()[w][h].getPanel(), gbc);
            }
        }

        mainPanel.revalidate();
    }*/

    private class MoveKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            for(Player p: SnakeFrame.game.getPlayers()){
                p.keyPressed(e.getKeyCode());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
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
                if(SnakeFrame.game.getGameMode()!=GameModes.SINGLEPLAYER) {
                    rightPanel.sidePanelTimerStart();
                    leftPanel.sidePanelTimerStart();
                }
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

}

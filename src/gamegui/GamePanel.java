package gamegui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import game.*;

import static java.awt.event.KeyEvent.VK_ENTER;

public class GamePanel extends JPanel{

    private final SnakeFrame snakeFrame;
    private final Game game;
    private final Timer gameTimer;
    private final Header header;
    private final JPanel mainPanel;
    private SidePanel leftPanel=null;
    private SidePanel rightPanel=null;
    private JTextField nameTextfield;


    public GamePanel(SnakeFrame sf){

        snakeFrame=sf;
        setLayout(new BorderLayout());
        game=SnakeFrame.game;

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
            leftPanel= new SidePanel(game.getPlayers().get(0));
            rightPanel= new SidePanel(game.getPlayers().get(1));
            this.add(leftPanel, BorderLayout.WEST);
            this.add(rightPanel, BorderLayout.EAST);
        }

        this.add(header, BorderLayout.NORTH);



        gameTimer = new Timer(game.getRefreshRate(), new GameTimerListener());
        KeyListener listener = new MoveKeyListener();
        addKeyListener(listener);
        setFocusable(true);

        StartKeyListener start=new StartKeyListener();
        addKeyListener(start);

        /*this.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                System.out.println("Focus GAINED:"+e);
            }
            public void focusLost(FocusEvent e){
                System.out.println("Focus LOST:"+e);

                // FIX FOR GNOME/XWIN FOCUS BUG
                e.getComponent().requestFocus();
            }
        });*/

        /*JPanel westPanel=new JPanel();
        add(westPanel, BorderLayout.WEST);
        westPanel.setBackground(new Color(43, 43, 43));

        JPanel eastPanel=new JPanel();
        add(eastPanel, BorderLayout.WEST);
        eastPanel.setBackground(new Color(43, 43, 43));*/


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
            if(header.getTime()<=0) {
                gameTimer.stop();
                if (game.getGameMode() != GameModes.SINGLEPLAYER) {
                    if (game.getPlayers().get(0).getPoints() < game.getPlayers().get(1).getPoints()) {
                        game.getPlayers().get(0).lose();
                    } else if (game.getPlayers().get(0).getPoints() == game.getPlayers().get(1).getPoints()) {
                        for (Player p : SnakeFrame.game.getPlayers()) {
                            p.lose();
                        }
                    } else game.getPlayers().get(1).lose();
                } else if (game.isRankable() && !game.getPlayers().get(0).isLost()){
                    JPanel namePanel=new JPanel();
                    namePanel.add(new JLabel("Name:"));
                    nameTextfield =new JTextField();
                    nameTextfield.setColumns(15);
                    namePanel.add(nameTextfield);
                    JButton nameButton=new JButton("Next");
                    nameButton.setActionCommand("Next");
                    nameButton.addActionListener(new NameButtonListener());
                    namePanel.add(nameButton);
                    add(namePanel, BorderLayout.SOUTH);
                    refreshMainPanel();
                    return;
                }
            }
            for(Player p: game.getPlayers()) {
                if (p.isLost()) {
                    gameTimer.stop();
                    }
            }
        }
    }

    private class NameButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Next")){
                try {
                    game.saveRank(nameTextfield.getText());
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
                if(game.getGameMode()!=GameModes.SINGLEPLAYER) {
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

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
        setLayout(new BorderLayout());
        INTERVAL=i;
        game=g;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        //setPreferredSize(new Dimension((int)width/3, (int)height/2));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(800, 800);
        /*mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(30, 30));*/

        mainPanel= new GameBoardPanel();
        //mainPanel.setPreferredSize(new Dimension((int)width/3, (int)height/3));

        /*for(int h=0; h<g.getMaze().getHeight(); h++){
            for(int w=0; w<g.getMaze().getWidth(); w++) {
                mainPanel.add(game.getMaze().getFields()[w][h].getPanel(), h*game.getMaze().getWidth()+w);
            }
        }*/
        JPanel mainPConstrain = new JPanel(new GridBagLayout());
        mainPConstrain.add(mainPanel);


        super.add(mainPConstrain, BorderLayout.CENTER);

        timer = new Timer(INTERVAL, new TimerListener());
        KeyListener listener = new MoveKeyListener();
        addKeyListener(listener);
        setFocusable(true);

        super.pack();
        timer.start();
        super.setVisible(true);


    }

    private class GameBoardPanel extends JPanel {

        public GameBoardPanel() {
            setLayout(new GridBagLayout());
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

    private class TimerListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            game.playersMove();
            game.refreshApples();
            refreshMainPanel();
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
        for(Player p: game.getPlayers()) {
            if (p.isLost()) {
                timer.stop();
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
                p.keyPressed(e.getKeyChar());
            }
            System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        }
    }









}

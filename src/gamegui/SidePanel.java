package gamegui;

import game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Többjátékos módban a panel két oldalán elhelyezkedő panelek
 */
public class SidePanel extends JPanel {
    /**
     * a játékos pontjait megjelenítő label
     */
    private final JLabel pointsLabel= new JLabel("0");
    /**
     * a játékos, akinek az adatait a panel megjeleníti
     */
    private final Player player;
    /**
     * a panelhez tartozó időzítő
     */
    private final Timer timer;
    /**
     * időzítő frissítési gyakorisága
     */
    private final static int refreshRate= SnakeFrame.game.getRefreshRate();

    /**
     * létrehozza a panelt és a hozzátartozó timert
     *
     * @param p a player akinek az adatai megjelennek
     */
    SidePanel(Player p){
        player=p;

        setBackground(new Color(43 ,43 ,43 ));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();

        gbc.gridwidth=2;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weighty=1;

        JLabel apple= new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images"+ System.getProperty("file.separator") +"apple3.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        apple.setIcon(imageIcon);

        add(apple, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.weighty=1;
        add(pointsLabel, gbc);
        pointsLabel.setForeground(Color.WHITE);

        JPanel snakeColor= new JPanel();
        snakeColor.setBackground(p.getColor());

        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridheight=3;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.weighty=5;
        add(snakeColor, gbc);

        timer=new Timer(refreshRate, new SidePanelTimer());
    }

    /**
     * oldalsó panelhez tartozó timer
     */
    private class SidePanelTimer implements ActionListener {

        /**
         * frissíti a pontok számát tickenként, és ha vég a játéknak jelzi, hogy a játékos nyert/vesztett
         *
         * @param e bekövetkezett event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (SnakeFrame.game.isGameOver()) {
                timer.stop();
                if(player.isLost()){
                    setBackground(Color.RED);
                } else if(!player.isLost())setBackground(Color.GREEN);
                    revalidate();
                }

            pointsLabel.setText(String.valueOf(player.getPoints()));
            revalidate();
        }
    }

    /**
     * elindítja a panel időzítőjét
     */
    public void sidePanelTimerStart(){
        timer.start();
    }
}

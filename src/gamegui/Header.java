package gamegui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


/**
 * a GamePanel felső részén megjelenő panel
 */
public abstract class Header extends JPanel {
    /**
     * hátralevő idő a játékból
     */
    private double time;
    /**
     * az időt megjelenítő label
     */
    protected final JLabel timeLabel= new JLabel();
    /**
     * a játék által kiírt üzenetet megjelenítő label
     */
    protected final JLabel displayedText= new JLabel("Press Enter to start");
    /**
     * a header időzítője, itt csökkenti a játékból hátralevő időt
     */
    protected Timer headerTimer;

    /**
     * időzítő frissítési gyakorisága
     */
    protected static final int refreshRate = SnakeFrame.game.getRefreshRate();

    /**
     * létrehozza az időzítőt és inicializálja a time-ot
     */
    public Header(){
        time= SnakeFrame.game.getTimeLimit();
        headerTimer= new Timer(refreshRate, new HeaderTimerListener());
    }

    /**
     * headertimer-hez tartozó actionlistener
     */
    protected class HeaderTimerListener implements ActionListener{

        /**
         * csökkenti a hátralevő időt, frissíti a timelabelt,
         * kiírja hogy gameover, ha a játéknak vége
         *
         * @param e bekövetkezett event
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (SnakeFrame.game.isGameOver()) {
                headerTimer.stop();
                displayedText.setText("Game over");
                revalidate();
                return;
            }

            time-=refreshRate;
            SimpleDateFormat df=new SimpleDateFormat("mm:ss");
            timeLabel.setText(df.format(time));

            revalidate();
        }
    }

    /**
     * elindítja a headertimert, és leveszi a kezdeti szöveget a displayedText-ről
     */
    public void headerTimerStart(){
        displayedText.setText("");
        revalidate();
        headerTimer.start();
    }

    /**
     * @return visszatér a hátralevő idővel
     */
    public double getTime(){
        return time;
    }
}

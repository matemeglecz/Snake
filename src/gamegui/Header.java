package gamegui;

import game.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


public abstract class Header extends JPanel {
    private double time;
    protected final JLabel timeLabel= new JLabel();
    protected final JLabel displayedText= new JLabel("Press Enter to start");
    protected Timer headerTimer;

    protected static final int refreshRate = SnakeFrame.game.getRefreshRate();

    public Header(){
        time= SnakeFrame.game.getTimeLimit();
        headerTimer= new Timer(refreshRate, new HeaderTimerListener());
    }

    protected class HeaderTimerListener implements ActionListener{

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

    public void headerTimerStart(){
        displayedText.setText("");
        revalidate();
        headerTimer.start();
    }

    public double getTime(){
        return time;
    }
}

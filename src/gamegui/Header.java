package gamegui;

import gamegui.*;
import game.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


public abstract class Header extends JPanel {
    //protected final Game game;
    private double time;
    protected final JLabel timeLabel= new JLabel();
    protected final JLabel displayedText= new JLabel("Press Enter to start");
    protected Timer headerTimer;

    protected static final int refreshRate = 100;

    public Header(){
        time=GameFrame.game.getTimeLimit();
        headerTimer= new Timer(refreshRate, new HeaderTimerListener());
    }

    protected class HeaderTimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(Player p: GameFrame.game.getPlayers()) {
                if (p.isLost() || time<=0) {
                    headerTimer.stop();
                    displayedText.setText("Game over");
                    revalidate();
                    return;
                }
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

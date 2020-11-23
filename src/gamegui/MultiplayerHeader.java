package gamegui;
import game.Game;
import game.Player;
import gamegui.Header;
import gamegui.SingleplayerHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class MultiplayerHeader extends Header {

    public MultiplayerHeader(){
        super();
        setLayout(new GridLayout(2, 1));

        JPanel upperPanel= new JPanel();
        JPanel lowerPanel= new JPanel();

        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
        timeLabel.setText(df.format(GameFrame.game.getTimeLimit()));
        upperPanel.add(timeLabel);
        lowerPanel.add(displayedText);

        headerTimer= new Timer(refreshRate, new MpHeaderTimerListener());

        add(upperPanel);
        add(lowerPanel);

        upperPanel.setBackground(new Color(43, 43, 43));
        lowerPanel.setBackground(new Color(43, 43, 43));

        timeLabel.setForeground(Color.WHITE);
        displayedText.setForeground(Color.WHITE);


    }

    private class MpHeaderTimerListener extends HeaderTimerListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            super.actionPerformed(e);
        }
    }

}

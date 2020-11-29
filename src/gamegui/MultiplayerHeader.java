package gamegui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * többjátékos módokhoz tartozó header
 */
public class MultiplayerHeader extends Header {

    /**
     * létrehozza a többjátékos módokhoz tartozó headert
     */
    public MultiplayerHeader(){
        super();
        setLayout(new GridLayout(2, 1));

        JPanel upperPanel= new JPanel();
        JPanel lowerPanel= new JPanel();

        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
        timeLabel.setText(df.format(SnakeFrame.game.getTimeLimit()));
        upperPanel.add(timeLabel);
        lowerPanel.add(displayedText);

        headerTimer= new Timer(refreshRate, new HeaderTimerListener());

        add(upperPanel);
        add(lowerPanel);

        upperPanel.setBackground(new Color(43, 43, 43));
        lowerPanel.setBackground(new Color(43, 43, 43));

        timeLabel.setForeground(Color.WHITE);
        displayedText.setForeground(Color.WHITE);
    }

}

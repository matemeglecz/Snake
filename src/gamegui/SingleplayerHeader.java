package gamegui;
import game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SingleplayerHeader extends Header {

    //private final Game game;
    //private double time;
    //private final JLabel timeLabel= new JLabel();

    //private final JLabel displayedText= new JLabel("Press Enter to start");
    //private final Timer headerTimer;

    //private final int refreshRate = 100;

    private final JLabel pointsLabel= new JLabel("0");



    public SingleplayerHeader(){
        super();
        setLayout(new GridLayout(1, 3));


        JLabel apple= new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/apple3.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        apple.setIcon(imageIcon);

        JPanel leftPanel= new JPanel();
        JPanel centerPanel= new JPanel();
        JPanel rightPanel= new JPanel();

        leftPanel.add(apple);

        leftPanel.add(pointsLabel);

        rightPanel.add(timeLabel);
        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
        timeLabel.setText(df.format(GameFrame.game.getTimeLimit()));
        centerPanel.add(displayedText);

        headerTimer= new Timer(100, new SpHeaderTimerListener());

        add(leftPanel);
        add(centerPanel);
        add(rightPanel);
        //leftPanel.setOpaque(true);
        leftPanel.setBackground(new Color(43, 43, 43));
        centerPanel.setBackground(new Color(43, 43, 43));
        rightPanel.setBackground(new Color(43, 43, 43));
        timeLabel.setForeground(Color.WHITE);
        displayedText.setForeground(Color.WHITE);
        pointsLabel.setForeground(Color.WHITE);

    }

    private class SpHeaderTimerListener extends HeaderTimerListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pointsLabel.setText(String.valueOf(GameFrame.game.getPlayers().get(0).getPoints()));
            super.actionPerformed(e);

        }
    }





}

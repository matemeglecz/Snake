package gamegui;

import game.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class SingleplayerHeader extends Header {
    private final JLabel pointsLabel= new JLabel("0");

    public SingleplayerHeader(){
        super();
        setLayout(new GridLayout(1, 3));


        JLabel apple= new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images"+ System.getProperty("file.separator") +"apple3.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        apple.setIcon(imageIcon);

        JPanel leftPanel= new JPanel();
        JPanel centerPanel= new JPanel();
        JPanel rightPanel= new JPanel();

        leftPanel.add(apple);

        leftPanel.add(pointsLabel);

        rightPanel.add(timeLabel);
        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
        timeLabel.setText(df.format(SnakeFrame.game.getTimeLimit()));
        centerPanel.add(displayedText);

        headerTimer= new Timer(refreshRate, new SpHeaderTimerListener());

        add(leftPanel);
        add(centerPanel);
        add(rightPanel);
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
            pointsLabel.setText(String.valueOf(SnakeFrame.game.getPlayers().get(0).getPoints()));
            super.actionPerformed(e);

        }
    }





}

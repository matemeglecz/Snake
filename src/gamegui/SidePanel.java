package gamegui;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private final JLabel pointsLabel= new JLabel("0");

    SidePanel(Color c){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridheight=3;
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weighty=1;

        JLabel apple= new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/apple3.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        apple.setIcon(imageIcon);

        add(apple, gbc);

        gbc.gridy=1;
        gbc.weighty=1;
        add(pointsLabel, gbc);

        JPanel snakeColor= new JPanel();
        snakeColor.setBackground(c);

        gbc.gridy=2;
        gbc.weighty=5;
        add(snakeColor, gbc);

    }


}

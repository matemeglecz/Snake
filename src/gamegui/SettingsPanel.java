package gamegui;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class SettingsPanel extends JPanel {
    //modes
    private final JRadioButton singleMode;
    private final JRadioButton playerMode;
    private final JRadioButton robotMode;

    //size
    private final JNumberTextField mazeSizetf;
    private final JRadioButton defaultSize;
    private final JRadioButton customSize;

    //timelimit
    private final JRadioButton defaultTime;
    private final JRadioButton customTime;
    private final JNumberTextField timeLimittf;

    //speed
    private final JRadioButton slowSpeed;
    private final JRadioButton normalSpeed;
    private final JRadioButton fastSpeed;
    private final JRadioButton customSpeed;
    private final JNumberTextField speedtf;

    //apples
    private final JRadioButton fewApple;
    private final JRadioButton normalApple;
    private final JRadioButton plentyApple;
    private final JRadioButton customApple;
    private final JNumberTextField appletf;

    //bombs
    private final JRadioButton fewBomb;
    private final JRadioButton normalBomb;
    private final JRadioButton plentyBomb;
    private final JRadioButton customBomb;
    private final JNumberTextField bombtf;

    //save
    private final JButton saveButton;
    private final JLabel warninglabel=new JLabel("");

    public SettingsPanel(){
        setLayout(new GridLayout(7, 1));

        SettingsListener settingsListener=new SettingsListener();

        //Mode
        JPanel panel1=new JPanel();
        panel1.add(new JLabel("Mode:"));
        ButtonGroup modeButtonGroup= new ButtonGroup();
        singleMode=new JRadioButton("Singleplayer", SnakeFrame.settings.getMode().equals(GameModes.SINGLEPLAYER));
        //singleButton.setActionCommand("singleplayer");
        playerMode=new JRadioButton("Player-player", SnakeFrame.settings.getMode().equals(GameModes.playerMULTIPLAYER));
        //playerButton.setActionCommand("player");
        robotMode=new JRadioButton("Player-robot", SnakeFrame.settings.getMode().equals(GameModes.robotMULTIPLAYER));
        //robotButton.setActionCommand("robot");

        modeButtonGroup.add(singleMode);
        modeButtonGroup.add(playerMode);
        modeButtonGroup.add(robotMode);
        panel1.add(singleMode);
        panel1.add(playerMode);
        panel1.add(robotMode);
        add(panel1);


        //maze size
        JPanel panel2=new JPanel();
        ButtonGroup sizeButtonGroup= new ButtonGroup();
        defaultSize=new JRadioButton("Default(" + Settings.getDefaultSize() + "x" + Settings.getDefaultSize() + ")",
                SnakeFrame.settings.getN()==Settings.getDefaultSize());
        //defaultSize.setActionCommand("defaultSize");
        defaultSize.addActionListener(settingsListener);
        customSize=new JRadioButton("Custom:", !defaultSize.isSelected());
        //customSize.setActionCommand("customSize");
        customSize.addActionListener(settingsListener);
        sizeButtonGroup.add(defaultSize);
        sizeButtonGroup.add(customSize);
        panel2.add(new JLabel("Field size:"));
        mazeSizetf= new JNumberTextField(3);
        mazeSizetf.setText(String.valueOf(SnakeFrame.settings.getN()));
        //mazeSizetf.setColumns(4);
        if(defaultSize.isSelected()){
            mazeSizetf.setEditable(false);
        }
        panel2.add(defaultSize);
        panel2.add(customSize);
        panel2.add(mazeSizetf);
        add(panel2);

        //timelimit
        JPanel panel3=new JPanel();
        ButtonGroup timeButtonGroup= new ButtonGroup();
        defaultTime=new JRadioButton("Default("+ Settings.getDefaultTimelimit()/1000 + "s)",
                SnakeFrame.settings.getTimelimit()==Settings.getDefaultTimelimit());
        //defaultTime.setActionCommand("defaultTime");
        defaultTime.addActionListener(settingsListener);
        customTime=new JRadioButton("Custom:", !defaultTime.isSelected());
        //customTime.setActionCommand("customTime");
        customTime.addActionListener(settingsListener);
        timeButtonGroup.add(defaultTime);
        timeButtonGroup.add(customTime);
        panel3.add(new JLabel("Time limit:"));
        timeLimittf= new JNumberTextField(4);
        timeLimittf.setText(String.valueOf((int)SnakeFrame.settings.getTimelimit()/1000));
        //timeLimittf.setColumns(4);
        if(defaultTime.isSelected()){
            timeLimittf.setEditable(false);
        }
        panel3.add(defaultTime);
        panel3.add(customTime);
        panel3.add(timeLimittf);
        add(panel3);

        //snake speed
        JPanel panel4=new JPanel();
        ButtonGroup speedButtonGroup= new ButtonGroup();
        slowSpeed=new JRadioButton("Slow(" + Settings.getDefaultSlowspeed() + "ms)",
                SnakeFrame.settings.getSpeed()==Settings.getDefaultSlowspeed());
        normalSpeed=new JRadioButton("Normal("+ Settings.getDefaultNormalspeed() +"ms)",
                SnakeFrame.settings.getSpeed()==Settings.getDefaultNormalspeed());
        fastSpeed=new JRadioButton("Fast("+ Settings.getDefaultFastspeed() +"ms)",
                SnakeFrame.settings.getSpeed()==Settings.getDefaultFastspeed());
        customSpeed=new JRadioButton("Custom(ms):", !slowSpeed.isSelected() && !normalSpeed.isSelected() && !fastSpeed.isSelected());
        slowSpeed.addActionListener(settingsListener);
        normalSpeed.addActionListener(settingsListener);
        fastSpeed.addActionListener(settingsListener);
        customSpeed.addActionListener(settingsListener);
        speedButtonGroup.add(slowSpeed);
        speedButtonGroup.add(normalSpeed);
        speedButtonGroup.add(fastSpeed);
        speedButtonGroup.add(customSpeed);
        speedtf= new JNumberTextField(4);
        speedtf.setText(String.valueOf(SnakeFrame.settings.getSpeed()));
        //speedtf.setColumns(4);
        if(!customSpeed.isSelected()){
            speedtf.setEditable(false);
        }
        panel4.add(new JLabel("Snake speed:"));
        panel4.add(slowSpeed);
        panel4.add(normalSpeed);
        panel4.add(fastSpeed);
        panel4.add(customSpeed);
        panel4.add(speedtf);
        add(panel4);

        //apples
        JPanel panel5=new JPanel();
        ButtonGroup appleButtonGroup= new ButtonGroup();
        fewApple=new JRadioButton("Few("+ Settings.getDefaultFewapple() +")",
                SnakeFrame.settings.getApplenum()==Settings.getDefaultFewapple());
        normalApple=new JRadioButton("Normal("+ Settings.getDefaultNormalapple()+")",
                SnakeFrame.settings.getApplenum()==Settings.getDefaultNormalapple());
        plentyApple=new JRadioButton("Plenty("+ Settings.getDefaultPlentyapple()+")",
                SnakeFrame.settings.getApplenum()==Settings.getDefaultPlentyapple());
        customApple=new JRadioButton("Custom:", !fewApple.isSelected() && !normalApple.isSelected() && !plentyApple.isSelected());
        fewApple.addActionListener(settingsListener);
        normalApple.addActionListener(settingsListener);
        plentyApple.addActionListener(settingsListener);
        customApple.addActionListener(settingsListener);
        appleButtonGroup.add(fewApple);
        appleButtonGroup.add(normalApple);
        appleButtonGroup.add(plentyApple);
        appleButtonGroup.add(customApple);
        appletf= new JNumberTextField(6);
        appletf.setText(String.valueOf(SnakeFrame.settings.getApplenum()));
        if(!customApple.isSelected()){
            appletf.setEditable(false);
        }
        panel5.add(new JLabel("Number of apples:"));
        panel5.add(fewApple);
        panel5.add(normalApple);
        panel5.add(plentyApple);
        panel5.add(customApple);
        panel5.add(appletf);
        add(panel5);

        //bombs
        JPanel panel6=new JPanel();
        ButtonGroup bombButtonGroup= new ButtonGroup();
        fewBomb=new JRadioButton("Few("+ Settings.getDefaultFewbomb()+")",
                SnakeFrame.settings.getBombnum()==Settings.getDefaultFewbomb());
        normalBomb=new JRadioButton("Normal("+ Settings.getDefaultNormalbomb()+")",
                SnakeFrame.settings.getBombnum()==Settings.getDefaultNormalbomb());
        plentyBomb=new JRadioButton("Plenty("+ Settings.getDefaultPlentybomb()+")",
                SnakeFrame.settings.getBombnum()==Settings.getDefaultPlentybomb());
        customBomb=new JRadioButton("Custom:", !fewBomb.isSelected() && !normalBomb.isSelected() && !plentyBomb.isSelected());
        fewBomb.addActionListener(settingsListener);
        normalBomb.addActionListener(settingsListener);
        plentyBomb.addActionListener(settingsListener);
        customBomb.addActionListener(settingsListener);
        bombButtonGroup.add(fewBomb);
        bombButtonGroup.add(normalBomb);
        bombButtonGroup.add(plentyBomb);
        bombButtonGroup.add(customBomb);
        bombtf= new JNumberTextField(6);
        bombtf.setText(String.valueOf(SnakeFrame.settings.getBombnum()));
        if(!customBomb.isSelected()){
            bombtf.setEditable(false);
        }
        panel6.add(new JLabel("Number of bombs:"));
        panel6.add(fewBomb);
        panel6.add(normalBomb);
        panel6.add(plentyBomb);
        panel6.add(customBomb);
        panel6.add(bombtf);
        add(panel6);

        //save button and warning
        JPanel panel7=new JPanel();
        panel7.add(warninglabel);
        saveButton=new JButton("Save");
        panel7.add(saveButton);
        saveButton.addActionListener(settingsListener);
        add(panel7);

    }

    private class SettingsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //size
            if(e.getSource().equals(defaultSize)){
                mazeSizetf.setEditable(false);
            } else if(e.getSource().equals(customSize)){
                mazeSizetf.setEditable(true);
            }
            //timelimit
            if(e.getSource().equals(defaultTime)){
                timeLimittf.setEditable(false);
            } else if(e.getSource().equals(customTime)){
                timeLimittf.setEditable(true);
            }
            //speed
            if(e.getSource().equals(customSpeed)){
                speedtf.setEditable(true);
            } else if (!customSpeed.isSelected()){
                speedtf.setEditable(false);
            }
            //apples
            if(e.getSource().equals(customApple)){
                appletf.setEditable(true);
            } else if (!customApple.isSelected()){
                appletf.setEditable(false);
            }
            //bombs
            if(e.getSource().equals(customBomb)){
                bombtf.setEditable(true);
            } else if (!customBomb.isSelected()){
                bombtf.setEditable(false);
            }
            //save button
            if(e.getSource().equals(saveButton)) {
                GameModes newMode=GameModes.SINGLEPLAYER;
                int newTimeLimit=Settings.getDefaultTimelimit();
                int newApplenum=Settings.getDefaultNormalapple();
                int newBombnum=Settings.getDefaultNormalbomb();
                int newSpeed=Settings.getDefaultNormalspeed();
                int newSize=Settings.getDefaultSize();
                Settings newSettings;

                if (singleMode.isSelected()) {
                    newMode=GameModes.SINGLEPLAYER;
                } else if (playerMode.isSelected()) {
                    newMode=GameModes.playerMULTIPLAYER;
                } else if (robotMode.isSelected()) {
                    newMode=GameModes.robotMULTIPLAYER;
                }
                if(defaultSize.isSelected()){
                    newSize=Settings.getDefaultSize();
                } else if (customSize.isSelected()){
                    newSize=mazeSizetf.getNumber();
                }
                if(defaultTime.isSelected()){
                    newTimeLimit=Settings.getDefaultTimelimit();
                } else if(customTime.isSelected()){
                    newTimeLimit=timeLimittf.getNumber()*1000;
                }
                if(slowSpeed.isSelected()){
                    newSpeed=Settings.getDefaultSlowspeed();
                } else if(normalSpeed.isSelected()){
                    newSpeed=Settings.getDefaultNormalspeed();
                } else if(fastSpeed.isSelected()){
                    newSpeed=Settings.getDefaultFastspeed();
                } else if(customSpeed.isSelected()){
                    newSpeed=speedtf.getNumber();
                }
                if(fewApple.isSelected()){
                    newApplenum=Settings.getDefaultFewapple();
                } else if(normalApple.isSelected()){
                    newApplenum=Settings.getDefaultNormalapple();
                } else if(plentyApple.isSelected()){
                    newApplenum=Settings.getDefaultPlentyapple();
                } else if(customApple.isSelected()){
                    newApplenum=appletf.getNumber();System.out.println(newApplenum);
                }
                if(fewBomb.isSelected()){
                    newBombnum=Settings.getDefaultFewbomb();
                } else if(normalBomb.isSelected()){
                    newBombnum=Settings.getDefaultNormalbomb();
                } else if(plentyBomb.isSelected()){
                    newBombnum=Settings.getDefaultPlentybomb();
                } else if(customBomb.isSelected()){
                    newBombnum=bombtf.getNumber();
                }

                try {
                    newSettings= new Settings(newMode, newSize, newApplenum, newBombnum, newTimeLimit, newSpeed);
                } catch (InvalidSettingsException invalidSettingsException) {
                    warninglabel.setForeground(Color.red);
                    warninglabel.setText(invalidSettingsException.getMessage());
                    return;
                }

                SnakeFrame.settings=newSettings;
                warninglabel.setForeground(new Color(90, 164, 105));
                warninglabel.setText("Successful save");
            }
        }

    }

    private static class JNumberTextField extends JTextField {
        private final int columns;

        public JNumberTextField(int limit){
            super();
            columns=limit;
            setColumns(columns);
        }

        @Override
        public void processKeyEvent(KeyEvent ev) {
            char c = ev.getKeyChar();

            if ((Character.isDigit(c)&& this.getText().length()<columns) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) { //|| (this.getText().length()  4)
                super.processKeyEvent(ev);
            }
            ev.consume();
        }

        public int getNumber() {
            int result = 0;
            String text = getText();
            if (text != null && !"".equals(text)) {
                result = Integer.parseInt(text);
            }
            return result;
        }
    }

}

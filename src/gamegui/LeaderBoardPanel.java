package gamegui;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;
import leaderboard.LeaderBoardItem;
import leaderboard.LeaderboardData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LeaderBoardPanel extends JPanel {
    private final JPanel centerPanel = new JPanel();
    private final JPanel bottomPanel= new JPanel();
    private final JPanel tablePanel= new JPanel();
    private final JPanel noFilePanel= new JPanel();
    private final LeaderboardData data;

    //speed
    private JRadioButton slowSpeed;
    private JRadioButton normalSpeed;
    private JRadioButton fastSpeed;

    //apples
    private JRadioButton fewApple;
    private JRadioButton normalApple;
    private JRadioButton plentyApple;

    //bombs
    private JRadioButton fewBomb;
    private JRadioButton normalBomb;
    private JRadioButton plentyBomb;

    private JButton saveButton;

    public LeaderBoardPanel(){
        setLayout(new BorderLayout());

        noFilePanel.add(new JLabel("No data"));
        bottomPanelInit();
        add(bottomPanel, BorderLayout.SOUTH);
        data=new LeaderboardData();
        add(centerPanel, BorderLayout.CENTER);
        tablePanelInit();
        try {
            if(SnakeFrame.game.isRankable()) {
                data.leaderBoardInit(SnakeFrame.settings);
            }else data.leaderBoardInit(new Settings());
        } catch (FileNotFoundException e) {
            centerPanel.add(noFilePanel);
            return;
        }
        centerPanel.add(tablePanel);

    }

    private void tablePanelInit(){
        JTable table = new JTable();
        table.setFillsViewportHeight(true);
        table.setModel(data);
        JScrollPane scrollPane=new JScrollPane(table);
        tablePanel.add(scrollPane);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0; i<table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void bottomPanelInit(){
        bottomPanel.setLayout(new GridLayout(4,1));
        SettingsListener settingsListener=new SettingsListener();

        //snake speed
        JPanel speedPanel=new JPanel();
        ButtonGroup speedButtonGroup= new ButtonGroup();
        slowSpeed=new JRadioButton("Slow(0.7s)", SnakeFrame.settings.getSpeed()==700);
        normalSpeed=new JRadioButton("Normal(0.5s)", SnakeFrame.settings.getSpeed()==500);
        fastSpeed=new JRadioButton("Fast(0.2s)", SnakeFrame.settings.getSpeed()==200);
        slowSpeed.addActionListener(settingsListener);
        normalSpeed.addActionListener(settingsListener);
        fastSpeed.addActionListener(settingsListener);
        speedButtonGroup.add(slowSpeed);
        speedButtonGroup.add(normalSpeed);
        speedButtonGroup.add(fastSpeed);
        speedPanel.add(new JLabel("Snake speed:"));
        speedPanel.add(slowSpeed);
        speedPanel.add(normalSpeed);
        speedPanel.add(fastSpeed);
        bottomPanel.add(speedPanel);

        //apples
        JPanel applePanel=new JPanel();
        ButtonGroup appleButtonGroup= new ButtonGroup();
        fewApple=new JRadioButton("Few(5)", SnakeFrame.settings.getApplenum()==5);
        normalApple=new JRadioButton("Normal(15)", SnakeFrame.settings.getApplenum()==15);
        plentyApple=new JRadioButton("Plenty(50)", SnakeFrame.settings.getApplenum()==50);
        fewApple.addActionListener(settingsListener);
        normalApple.addActionListener(settingsListener);
        plentyApple.addActionListener(settingsListener);
        appleButtonGroup.add(fewApple);
        appleButtonGroup.add(normalApple);
        appleButtonGroup.add(plentyApple);
        applePanel.add(new JLabel("Number of apples:"));
        applePanel.add(fewApple);
        applePanel.add(normalApple);
        applePanel.add(plentyApple);
        bottomPanel.add(applePanel);

        //bombs
        JPanel bombPanel=new JPanel();
        ButtonGroup bombButtonGroup= new ButtonGroup();
        fewBomb=new JRadioButton("Few(5)", SnakeFrame.settings.getBombnum()==5);
        normalBomb=new JRadioButton("Normal(15)", SnakeFrame.settings.getBombnum()==15);
        plentyBomb=new JRadioButton("Plenty(50)", SnakeFrame.settings.getBombnum()==50);
        fewBomb.addActionListener(settingsListener);
        normalBomb.addActionListener(settingsListener);
        plentyBomb.addActionListener(settingsListener);
        bombButtonGroup.add(fewBomb);
        bombButtonGroup.add(normalBomb);
        bombButtonGroup.add(plentyBomb);
        bombPanel.add(new JLabel("Number of bombs:"));
        bombPanel.add(fewBomb);
        bombPanel.add(normalBomb);
        bombPanel.add(plentyBomb);
        bottomPanel.add(bombPanel);

        //save button
        saveButton=new JButton("Save");
        saveButton.addActionListener(settingsListener);
        bottomPanel.add(saveButton);
    }

    private class SettingsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //save button
            if(e.getSource().equals(saveButton)) {
                GameModes newMode=GameModes.SINGLEPLAYER;
                int newTimeLimit=Settings.getDefaultTimelimit();
                int newApplenum=Settings.getDefaultNormalapple();
                int newBombnum=Settings.getDefaultNormalbomb();
                int newSpeed=Settings.getDefaultNormalspeed();
                int newSize=Settings.getDefaultSize();
                Settings newSettings;

                if(slowSpeed.isSelected()){
                    newSpeed=Settings.getDefaultSlowspeed();
                } else if(normalSpeed.isSelected()){
                    newSpeed=Settings.getDefaultNormalspeed();
                } else if(fastSpeed.isSelected()){
                    newSpeed=Settings.getDefaultFastspeed();
                }
                if(fewApple.isSelected()){
                    newApplenum=Settings.getDefaultFewapple();
                } else if(normalApple.isSelected()){
                    newApplenum=Settings.getDefaultNormalapple();
                } else if(plentyApple.isSelected()){
                    newApplenum=Settings.getDefaultPlentyapple();
                }
                if(fewBomb.isSelected()){
                    newBombnum=Settings.getDefaultFewbomb();
                } else if(normalBomb.isSelected()){
                    newBombnum=Settings.getDefaultNormalbomb();
                } else if(plentyBomb.isSelected()){
                    newBombnum=Settings.getDefaultPlentybomb();
                }

                try {
                    newSettings= new Settings(newMode, newSize, newApplenum, newBombnum, newTimeLimit, newSpeed);
                } catch (InvalidSettingsException invalidSettingsException) {
                    invalidSettingsException.printStackTrace();
                    return;
                }
                centerPanel.removeAll();
                repaint();
                try {
                    data.leaderBoardInit(newSettings);
                } catch (FileNotFoundException fileNotFoundException) {
                    centerPanel.add(noFilePanel);
                    revalidate();
                    return;
                }
                centerPanel.add(tablePanel);
                revalidate();
            }
        }
    }

}

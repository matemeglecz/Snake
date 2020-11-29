package gamegui;

import game.GameModes;
import game.InvalidSettingsException;
import game.Settings;
import leaderboard.LeaderboardData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * a leaderboardok megtekintéséhez szükséges panel
 */
public class LeaderBoardPanel extends JPanel {
    /**
     * a középső panel, amin a táblázat van vagy a No data feliratú label
     */
    private final JPanel centerPanel = new JPanel();
    /**
     * az ranglista beállításainak kiválasztásához tartozó panel
     */
    private final JPanel bottomPanel= new JPanel();
    /**
     * a panel, amin a táblázat van
     */
    private final JPanel tablePanel= new JPanel();
    /**
     * a panel, amin a No data feliratú label van
     */
    private final JPanel noFilePanel= new JPanel();
    /**
     * a táblázatban megjelenő adatok
     */
    private final LeaderboardData data;

    //speed
    /**
     * lassú kígyósebbeséghez tartozó radiobutton
     */
    private JRadioButton slowSpeed;
    /**
     * normál kígyósebbeséghez tartozó radiobutton
     */
    private JRadioButton normalSpeed;
    /**
     * gyors kígyósebbeséghez tartozó radiobutton
     */
    private JRadioButton fastSpeed;

    //apples
    /**
     * kevés almához tartozó radiobutton
     */
    private JRadioButton fewApple;
    /**
     * normál mennyiségű almához tartozó radiobutton
     */
    private JRadioButton normalApple;
    /**
     * sok almához tartozó radiobutton
     */
    private JRadioButton plentyApple;

    //bombs
    /**
     * kevés bombához tartozó radiobutton
     */
    private JRadioButton fewBomb;
    /**
     * normál mennyiségű bombához tartozó radiobutton
     */
    private JRadioButton normalBomb;
    /**
     * sok bombához tartozó radiobutton
     */
    private JRadioButton plentyBomb;

    /**
     * mentéshez tartozó gomb
     */
    private JButton saveButton;

    /**
     * felrakja a panelre a középső panelt és az alsó panelt,
     * a kezdetben megjelenő eredmények az előző játék beállításához tartozó eredmények,
     * amennyiben a játék rangsorolható volt
     * ha nem volt rangsorolható akkor minden beállítás normál értéken áll
     */
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

    /**
     * létrehozza a táblázathoz tartozó panelt,
     * inicializálja a táblázatot az adatokkal
     */
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

    /**
     * felrakja az alsó panelre a beállításokhoz szükséges gombokat
     */
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

    /**
     * a beállítások gombjait figyelő Listener
     */
    private class SettingsListener implements ActionListener {

        /**
         * Save gomb megnyomásának hatására megjelníti az új beállításokhoz tartozó eredményeket
         *
         * @param e bekövetkezett event
         */
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

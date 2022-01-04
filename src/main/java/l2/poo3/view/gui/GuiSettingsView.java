package l2.poo3.view.gui;

import l2.poo3.controller.gui.UpdateBackgroundImageWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GuiSettingsView extends JFrame {

    private final ButtonGroup playerNum = new ButtonGroup();
    private final ButtonGroup player1Type41 = new ButtonGroup();
    private final ButtonGroup player1Type42 = new ButtonGroup();
    private final ButtonGroup player1Type43 = new ButtonGroup();
    private final ButtonGroup player1Type44 = new ButtonGroup();

    private final JButton btnNewButton = new JButton("Start");

    private final JPanel playerFourFour = new JPanel();

    private final JRadioButton threePlayerRadioButton = new JRadioButton("3");
    private final JRadioButton fourPlayerRadioButton = new JRadioButton("4");

    private final JPanel panelFourPlayer = new JPanel();

    private final JSpinner spinnerX = new JSpinner();
    private final JSpinner spinnerY = new JSpinner();
    private final JSpinner spinnerPV = new JSpinner();

    private final ImageIcon background=new ImageIcon(ClassLoader.getSystemResource("background/GuiViewBackground3.jpg"));

    private final Color textArroundColor = new Color(255, 255, 255);


    private final JLabel back = new JLabel();

    public GuiSettingsView(){
        initFrame();
    }

    public JPanel getPlayerFourFour() {
        return playerFourFour;
    }


    public ButtonGroup getPlayer1Type41() {
        return player1Type41;
    }

    public ButtonGroup getPlayer1Type42() {
        return player1Type42;
    }

    public ButtonGroup getPlayer1Type43() {
        return player1Type43;
    }

    public ButtonGroup getPlayer1Type44() {
        return player1Type44;
    }

    public JButton getBtnNewButton() {
        return btnNewButton;
    }

    public ButtonGroup getPlayerNum() {
        return playerNum;
    }

    public JRadioButton getThreePlayerRadioButton() {
        return threePlayerRadioButton;
    }

    public JRadioButton getFourPlayerRadioButton() {
        return fourPlayerRadioButton;
    }


    public JSpinner getSpinnerX() {
        return spinnerX;
    }

    public JSpinner getSpinnerY() {
        return spinnerY;
    }

    public JSpinner getSpinnerPV() {
        return spinnerPV;
    }




    private void initFrame() {
        setMinimumSize(new Dimension(766, 539));
        setTitle("Groupe 48 - Game Settings");
        setBounds(100, 100, 766, 539);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon(ClassLoader.getSystemResource("catan-universe.png")).getImage());

        UpdateBackgroundImageWorker updateBackgroundImageWorker = new UpdateBackgroundImageWorker(back, background, getWidth(), getHeight());
        updateBackgroundImageWorker.execute();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                UpdateBackgroundImageWorker updateBackgroundImageWorker = new UpdateBackgroundImageWorker(back, background, getWidth(), getHeight());
                updateBackgroundImageWorker.execute();
            }
        });

        setContentPane(back);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        getContentPane().add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel settingsAll = new JPanel();
        settingsAll.setOpaque(false);
        settingsAll.setLayout(new BoxLayout(settingsAll, BoxLayout.Y_AXIS));

        Component verticalGlue_1 = Box.createVerticalGlue();
        settingsAll.add(verticalGlue_1);

        JPanel devBy = new JPanel();
        settingsAll.add(devBy);
        devBy.setOpaque(false);
        devBy.setBorder(new TitledBorder(new LineBorder(textArroundColor, 4, true), "Developpe par", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        devBy.setLayout(new GridLayout(0, 2, 0, 5));

        JPanel garg = new JPanel();
        garg.setOpaque(false);
        garg.setBorder(new TitledBorder(new LineBorder(textArroundColor, 1, true), "Gargaun Illia", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        devBy.add(garg);
        garg.setLayout(new BoxLayout(garg, BoxLayout.Y_AXIS));

        JLabel lblInfo = new JLabel("INFO-5");
        lblInfo.setForeground(textArroundColor);
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        garg.add(lblInfo);
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNetudiant = new JLabel("N-Etudiant: 00000000");
        lblNetudiant.setForeground(textArroundColor);
        lblNetudiant.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNetudiant.setAlignmentX(Component.CENTER_ALIGNMENT);
        garg.add(lblNetudiant);
        lblNetudiant.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel legr = new JPanel();
        legr.setOpaque(false);
        legr.setBorder(new TitledBorder(new LineBorder(textArroundColor, 1, true), "Legrand Lilian", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        devBy.add(legr);
        legr.setLayout(new BoxLayout(legr, BoxLayout.Y_AXIS));

        JLabel lblInfoInfojap = new JLabel("INFO-2 INFO-JAP");
        lblInfoInfojap.setForeground(textArroundColor);
        lblInfoInfojap.setFont(new Font("Arial", Font.PLAIN, 13));
        lblInfoInfojap.setAlignmentX(Component.CENTER_ALIGNMENT);
        legr.add(lblInfoInfojap);
        lblInfoInfojap.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNetudiant_1 = new JLabel("N-Etudiant: 00000000");
        lblNetudiant_1.setForeground(textArroundColor);
        lblNetudiant_1.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNetudiant_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        legr.add(lblNetudiant_1);
        lblNetudiant_1.setHorizontalAlignment(SwingConstants.CENTER);

        Component verticalGlue_4 = Box.createVerticalGlue();
        settingsAll.add(verticalGlue_4);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 4, true), "Parametres", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        settingsAll.add(settingsPanel);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        panelFourPlayer.setOpaque(false);
        panelFourPlayer.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Type De Joueur", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        panelFourPlayer.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel playerOneFour = new JPanel();
        playerOneFour.setOpaque(false);
        playerOneFour.setBorder(new TitledBorder(new LineBorder(Color.RED, 1, true), "Joueur Rouge", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 12), textArroundColor));
        panelFourPlayer.add(playerOneFour);
        playerOneFour.setLayout(new GridLayout(1, 0, 0, 0));

        JRadioButton rdbtnPlayer41 = new JRadioButton("Humain");
        rdbtnPlayer41.setForeground(textArroundColor);
        rdbtnPlayer41.setOpaque(false);
        rdbtnPlayer41.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type41.add(rdbtnPlayer41);
        rdbtnPlayer41.setSelected(true);
        rdbtnPlayer41.setActionCommand("P41");
        rdbtnPlayer41.setFont(new Font("Arial", Font.PLAIN, 13));
        playerOneFour.add(rdbtnPlayer41);

        JRadioButton rdbtnAi41 = new JRadioButton("Ai");
        rdbtnAi41.setForeground(textArroundColor);
        rdbtnAi41.setOpaque(false);
        rdbtnAi41.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type41.add(rdbtnAi41);
        rdbtnAi41.setFont(new Font("Arial", Font.PLAIN, 13));
        rdbtnAi41.setActionCommand("A41");
        playerOneFour.add(rdbtnAi41);

        JPanel playerTwoFour = new JPanel();
        playerTwoFour.setOpaque(false);
        playerTwoFour.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 0),1, true), "Joueur Jaune", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 12), textArroundColor));
        panelFourPlayer.add(playerTwoFour);
        playerTwoFour.setLayout(new GridLayout(1, 0, 0, 0));

        JRadioButton rdbtnPlayer42 = new JRadioButton("Humain");
        rdbtnPlayer42.setForeground(textArroundColor);
        rdbtnPlayer42.setOpaque(false);
        rdbtnPlayer42.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type42.add(rdbtnPlayer42);
        rdbtnPlayer42.setSelected(true);
        rdbtnPlayer42.setActionCommand("P42");
        rdbtnPlayer42.setFont(new Font("Arial", Font.PLAIN, 13));
        playerTwoFour.add(rdbtnPlayer42);

        JRadioButton rdbtnAi42 = new JRadioButton("Ai");
        rdbtnAi42.setForeground(textArroundColor);
        rdbtnAi42.setOpaque(false);
        rdbtnAi42.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type42.add(rdbtnAi42);
        rdbtnAi42.setFont(new Font("Arial", Font.PLAIN, 13));
        rdbtnAi42.setActionCommand("A42");
        playerTwoFour.add(rdbtnAi42);

        JPanel playerThreeFour = new JPanel();
        playerThreeFour.setOpaque(false);
        playerThreeFour.setBorder(new TitledBorder(new LineBorder(Color.GREEN, 1, true), "Joueur Vert", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 12), textArroundColor));
        panelFourPlayer.add(playerThreeFour);
        playerThreeFour.setLayout(new GridLayout(1, 0, 0, 0));

        JRadioButton rdbtnPlayer43 = new JRadioButton("Humain");
        rdbtnPlayer43.setForeground(textArroundColor);
        rdbtnPlayer43.setOpaque(false);
        rdbtnPlayer43.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type43.add(rdbtnPlayer43);
        rdbtnPlayer43.setSelected(true);
        rdbtnPlayer43.setActionCommand("P43");
        rdbtnPlayer43.setFont(new Font("Arial", Font.PLAIN, 13));
        playerThreeFour.add(rdbtnPlayer43);

        JRadioButton rdbtnAi43 = new JRadioButton("Ai");
        rdbtnAi43.setForeground(textArroundColor);
        rdbtnAi43.setOpaque(false);
        rdbtnAi43.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type43.add(rdbtnAi43);
        rdbtnAi43.setActionCommand("A43");
        rdbtnAi43.setFont(new Font("Arial", Font.PLAIN, 13));
        playerThreeFour.add(rdbtnAi43);

        playerFourFour.setOpaque(false);
        playerFourFour.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 1, true), "Joueur Bleu", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 12), textArroundColor));
        panelFourPlayer.add(playerFourFour);
        playerFourFour.setLayout(new GridLayout(1, 0, 0, 0));

        JRadioButton rdbtnPlayer44 = new JRadioButton("Humain");
        rdbtnPlayer44.setForeground(textArroundColor);
        rdbtnPlayer44.setEnabled(false);
        rdbtnPlayer44.setOpaque(false);
        rdbtnPlayer44.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type44.add(rdbtnPlayer44);
        rdbtnPlayer44.setSelected(true);
        rdbtnPlayer44.setActionCommand("P44");
        rdbtnPlayer44.setFont(new Font("Arial", Font.PLAIN, 13));
        playerFourFour.add(rdbtnPlayer44);

        JRadioButton rdbtnAi44 = new JRadioButton("Ai");
        rdbtnAi44.setForeground(textArroundColor);
        rdbtnAi44.setEnabled(false);
        rdbtnAi44.setOpaque(false);
        rdbtnAi44.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        player1Type44.add(rdbtnAi44);
        rdbtnAi44.setActionCommand("A44");
        rdbtnAi44.setFont(new Font("Arial", Font.PLAIN, 13));
        playerFourFour.add(rdbtnAi44);


        JPanel panel = new JPanel();
        panel.setOpaque(false);


        panel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Nombre De Joueur", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        panel.setLayout(new GridLayout(1, 0, 0, 0));

        Component horizontalGlue_2 = Box.createHorizontalGlue();
        panel.add(horizontalGlue_2);

        threePlayerRadioButton.setForeground(textArroundColor);
        threePlayerRadioButton.setOpaque(false);
        threePlayerRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        threePlayerRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        threePlayerRadioButton.setActionCommand("3");
        threePlayerRadioButton.setFont(new Font("Arial", Font.PLAIN, 13));

        panel.add(threePlayerRadioButton);
        threePlayerRadioButton.setSelected(true);
        playerNum.add(threePlayerRadioButton);

        Component horizontalGlue_1 = Box.createHorizontalGlue();
        panel.add(horizontalGlue_1);

        fourPlayerRadioButton.setForeground(textArroundColor);
        fourPlayerRadioButton.setOpaque(false);
        fourPlayerRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fourPlayerRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        fourPlayerRadioButton.setActionCommand("4");
        fourPlayerRadioButton.setFont(new Font("Arial", Font.PLAIN, 13));


        panel.add(fourPlayerRadioButton);
        playerNum.add(fourPlayerRadioButton);
        settingsPanel.add(panel);

        Component horizontalGlue_5 = Box.createHorizontalGlue();
        panel.add(horizontalGlue_5);

        Component verticalGlue = Box.createVerticalGlue();
        settingsPanel.add(verticalGlue);
        settingsPanel.add(panelFourPlayer);

        Component verticalGlue_2 = Box.createVerticalGlue();
        settingsPanel.add(verticalGlue_2);

        JPanel otherPanel = new JPanel();
        otherPanel.setOpaque(false);
        settingsPanel.add(otherPanel);
        otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.X_AXIS));

        JPanel platteauxSettingsPanel = new JPanel();
        platteauxSettingsPanel.setOpaque(false);
        otherPanel.add(platteauxSettingsPanel);
        platteauxSettingsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        platteauxSettingsPanel.setLayout(new BoxLayout(platteauxSettingsPanel, BoxLayout.X_AXIS));

        JPanel dimensionsSettings = new JPanel();
        dimensionsSettings.setOpaque(false);
        dimensionsSettings.setAlignmentY(Component.TOP_ALIGNMENT);
        dimensionsSettings.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Plateaux", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        platteauxSettingsPanel.add(dimensionsSettings);
        dimensionsSettings.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JLabel labelX = new JLabel("X");
        labelX.setForeground(textArroundColor);
        labelX.setHorizontalAlignment(SwingConstants.RIGHT);
        dimensionsSettings.add(labelX);

        ((JSpinner.DefaultEditor) spinnerX.getEditor()).getTextField().setForeground(textArroundColor);
        spinnerX.setOpaque(false);
        spinnerX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelX.setLabelFor(spinnerX);
        dimensionsSettings.add(spinnerX);
        spinnerX.setModel(new SpinnerNumberModel(4, 4, 3446, 1));
        spinnerX.getEditor().setOpaque(false);
        ((JSpinner.NumberEditor)spinnerX.getEditor()).getTextField().setOpaque(false);
        ((JSpinner.NumberEditor)spinnerX.getEditor()).getTextField().setForeground(textArroundColor);
        spinnerX.setBorder(new LineBorder(textArroundColor, 1, true));

        Component horizontalGlue = Box.createHorizontalGlue();
        dimensionsSettings.add(horizontalGlue);

        JLabel labelY = new JLabel("Y");
        labelY.setForeground(textArroundColor);
        labelY.setHorizontalAlignment(SwingConstants.RIGHT);
        dimensionsSettings.add(labelY);


        spinnerY.setOpaque(false);
        spinnerY.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelY.setLabelFor(spinnerY);
        spinnerY.setModel(new SpinnerNumberModel(4, 4, 9999, 1));
        spinnerY.getEditor().setOpaque(false);
        ((JSpinner.NumberEditor)spinnerY.getEditor()).getTextField().setOpaque(false);
        ((JSpinner.NumberEditor)spinnerY.getEditor()).getTextField().setForeground(textArroundColor);
        spinnerY.setBorder(new LineBorder(textArroundColor, 1, true));
        dimensionsSettings.add(spinnerY);

        JPanel pvPanel = new JPanel();
        pvPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        pvPanel.setOpaque(false);
        otherPanel.add(pvPanel);
        pvPanel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Point de victoire", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        pvPanel.setLayout(new FlowLayout());

        spinnerPV.setAlignmentY(Component.TOP_ALIGNMENT);
        spinnerPV.setMaximumSize(new Dimension(32767, 20));
        spinnerPV.setOpaque(false);
        spinnerPV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        spinnerPV.setModel(new SpinnerNumberModel(10, 1, 9999, 1));
        spinnerPV.getEditor().setOpaque(false);
        ((JSpinner.NumberEditor)spinnerPV.getEditor()).getTextField().setOpaque(false);
        ((JSpinner.NumberEditor)spinnerPV.getEditor()).getTextField().setForeground(textArroundColor);

        spinnerPV.setBorder(new LineBorder(textArroundColor, 1, true));
        pvPanel.add(spinnerPV);

        Component verticalGlue_5 = Box.createVerticalGlue();
        settingsAll.add(verticalGlue_5);

        JPanel btnContainer = new JPanel();
        btnContainer.setOpaque(false);
        settingsAll.add(btnContainer);
        btnContainer.setLayout(new GridLayout(0, 3, 0, 0));

        Component horizontalGlue_3 = Box.createHorizontalGlue();
        btnContainer.add(horizontalGlue_3);

        btnNewButton.setOpaque(false);
        btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4)));
        btnNewButton.setForeground(textArroundColor);
        btnNewButton.setBackground(new Color(0, 0, 0,0));
        btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
        getRootPane().setDefaultButton(btnNewButton);
        btnContainer.add(btnNewButton);

        Component horizontalGlue_4 = Box.createHorizontalGlue();
        btnContainer.add(horizontalGlue_4);

        Component horizontalGlue_6 = Box.createHorizontalGlue();
        mainPanel.add(horizontalGlue_6);

        Component verticalGlue_6 = Box.createVerticalGlue();
        settingsAll.add(verticalGlue_6);

        mainPanel.add(settingsAll);

        Component horizontalGlue_7 = Box.createHorizontalGlue();
        mainPanel.add(horizontalGlue_7);

    }
}

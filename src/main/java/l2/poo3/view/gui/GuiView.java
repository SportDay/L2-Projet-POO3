package l2.poo3.view.gui;

import l2.poo3.controller.gui.GuiViewAdapter;
import l2.poo3.controller.gui.GuiController;
import l2.poo3.controller.gui.UpdateBackgroundImageWorker;
import l2.poo3.model.CaseModel;
import l2.poo3.model.CaseType.*;
import l2.poo3.model.Enum.CartesDev;
import l2.poo3.model.Enum.Pcolor;
import l2.poo3.model.Enum.Resources;
import l2.poo3.model.PlateauxModel;
import l2.poo3.model.PlayerModel;
import l2.poo3.view.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

public class GuiView extends JFrame implements ViewModel {

    private GuiController controller;
    private PlateauxModel plateaux;

    private final ButtonGroup buildRadio = new ButtonGroup();

    private final JLabel label_1 = new JLabel();
    private final JLabel label_3 = new JLabel();
    private final JLabel label_5 = new JLabel();
    private final JLabel label_7 = new JLabel();
    private final JLabel label_9 = new JLabel();
    private final JLabel label_11 = new JLabel();
    private final JLabel label_13 = new JLabel();
    private final JLabel label_15 = new JLabel();
    private final JLabel label_17 = new JLabel();
    private final JLabel label_19 = new JLabel();
    private final JLabel label_21 = new JLabel();
    private final JLabel label_23 = new JLabel();
    private final JLabel pvLabel = new JLabel();
    private final JLabel numCol = new JLabel();
    private final JLabel numVil = new JLabel();
    private final JLabel numLargRoad = new JLabel();
    private final JLabel boisNum = new JLabel();
    private final JLabel wheatNum = new JLabel();
    private final JLabel argileNum = new JLabel();
    private final JLabel minNum = new JLabel();
    private final JLabel mouNum = new JLabel();
    private final JLabel dice1 = new JLabel();
    private final JLabel dice2 = new JLabel();
    private final JLabel roadLabel = new JLabel();
    private final JLabel colLabel = new JLabel();
    private final JLabel vilLabel = new JLabel();
    private final JLabel label_34 = new JLabel();
    private final JLabel label_32 = new JLabel();
    private final JLabel label_30 = new JLabel();
    private final JLabel label_28 = new JLabel();
    private final JLabel lblNewLabel_2 = new JLabel();
    private final JLabel back = new JLabel();
    private final JLabel grandeRoute = new JLabel();
    private final JLabel grandeArme = new JLabel();

    private final JTextArea logsArea = new JTextArea();

    private final JButton btnLancerDe = new JButton("Lancer De");
    private final JButton btnFinirLeTour = new JButton("Finir le tour");
    private final JButton btnDevelopement = new JButton("Achat cartes de devlopement");
    private final JButton useChevalier = new JButton("Utiliser");
    private final JButton useMonopole = new JButton("Utiliser");
    private final JButton useRoute = new JButton("Utiliser");
    private final JButton useInvention = new JButton("Utiliser");
    private final JButton usePort = new JButton("Utiliser Port");

    private JPanel element = new JPanel();

    private final JPanel otherPanel = new JPanel();
    private final JPanel plateauxPanel = new JPanel();
    private final JPanel panel_28 = new JPanel();
    private final JPanel panel_27 = new JPanel();
    private final JPanel panel_33 = new JPanel();
    private final JPanel panel_32 = new JPanel();

    private LinkedList<JPanel> caseResource = new LinkedList<JPanel>();
    private LinkedList<JButton> caseRoute = new LinkedList<>();
    private LinkedList<JButton> caseCol = new LinkedList<>();
    private LinkedList<JButton> caseVil = new LinkedList<>();
    private LinkedList<JPanel> casePort = new LinkedList<>();

    private final JRadioButton vilRadio = new JRadioButton();
    private final JRadioButton colRadio = new JRadioButton();
    private final JRadioButton roadRadio = new JRadioButton();

    private boolean one = true;

    private final Color textArroundColor = new Color(255, 255, 255);

    private final ImageIcon background=new ImageIcon("src/main/resources/background/GuiViewBackground2.jpg");

    public JButton getUsePort() {
        return usePort;
    }

    public ButtonGroup getBuildRadio() {
        return buildRadio;
    }

    public GuiController getController() {
        return controller;
    }

    public JRadioButton getVilRadio() {
        return vilRadio;
    }

    public JRadioButton getColRadio() {
        return colRadio;
    }

    public JRadioButton getRoadRadio() {
        return roadRadio;
    }

    public JButton getBtnLancerDe() {
        return btnLancerDe;
    }

    public JButton getBtnFinirLeTour() {
        return btnFinirLeTour;
    }

    public JPanel getOtherPanel() {
        return otherPanel;
    }

    public JButton getBtnDevelopement() {
        return btnDevelopement;
    }

    public void setController(GuiController controller) {
        this.controller = controller;
    }

    public void setPlateaux(PlateauxModel plateaux) {
        this.plateaux = plateaux;
    }

    public JLabel getDice1() {
        return dice1;
    }

    public JLabel getDice2() {
        return dice2;
    }

    public JButton getUseChevalier() {
        return useChevalier;
    }

    public JButton getUseMonopole() {
        return useMonopole;
    }

    public JButton getUseRoute() {
        return useRoute;
    }

    public JButton getUseInvention() {
        return useInvention;
    }

    public LinkedList<JPanel> getCaseResource() {
        return caseResource;
    }

    public LinkedList<JButton> getCaseRoute() {
        return caseRoute;
    }

    public LinkedList<JButton> getCaseCol() {
        return caseCol;
    }

    public LinkedList<JButton> getCaseVil() {
        return caseVil;
    }

    public LinkedList<JPanel> getCasePort() {
        return casePort;
    }

    public void addLogs(String text) {
        logsArea.append(text);
        logsArea.append("\n");
    }

    public void addInfo(String text) {
        logsArea.append("\n");
        logsArea.append("[INFO] " + text);
        logsArea.append("\n");
    }

    public void clearLogs(){
        logsArea.setText("");
    }

    public void updateView() {
        PlayerModel[] players = controller.getPlayers();
        label_1.setText(players[0].getPointDeVic() + "");
        label_3.setText(players[0].getNbrCol() + "");
        label_5.setText(players[0].getNbrVil() + "");
        label_7.setText(players[0].getLargestRoad() + "");

        label_9.setText(players[1].getPointDeVic() + "");
        label_11.setText(players[1].getNbrCol() + "");
        label_13.setText(players[1].getNbrVil() + "");
        label_15.setText(players[1].getLargestRoad() + "");

        label_17.setText(players[2].getPointDeVic() + "");
        label_19.setText(players[2].getNbrCol() + "");
        label_21.setText(players[2].getNbrVil() + "");
        label_23.setText(players[2].getLargestRoad() + "");

        if (controller.getPlayerNum() == 4) {
            pvLabel.setText(players[3].getPointDeVic() + "");
            numCol.setText(players[3].getNbrCol() + "");
            numVil.setText(players[3].getNbrVil() + "");
            numLargRoad.setText(players[3].getLargestRoad() + "");
        }

        if(plateaux.getBiggestRoad() != null){
            if(!panel_33.isVisible()){
                panel_33.setVisible(true);
            }
            grandeRoute.setText(plateaux.getBiggestRoad().getColor()+"");
        }

        if(plateaux.getBiggestKnight() != null){
            if(!panel_32.isVisible()){
                panel_32.setVisible(true);
            }
            grandeArme.setText(plateaux.getBiggestKnight().getColor()+"");
        }

        PlayerModel playerQuijoue = controller.getPlayerQuiJoue();

        boisNum.setText(playerQuijoue.getResources().get(Resources.BOIS).toString());
        wheatNum.setText(playerQuijoue.getResources().get(Resources.BLE).toString());
        argileNum.setText(playerQuijoue.getResources().get(Resources.ARGILE).toString());
        minNum.setText(playerQuijoue.getResources().get(Resources.MINERAI).toString());
        mouNum.setText(playerQuijoue.getResources().get(Resources.MOUTON).toString());

        lblNewLabel_2.setText(playerQuijoue.getCartesDev().get(CartesDev.Chevalier).toString());
        label_28.setText(playerQuijoue.getCartesDev().get(CartesDev.Monopole).toString());
        label_30.setText(playerQuijoue.getCartesDev().get(CartesDev.PointVictoire).toString());
        label_32.setText(playerQuijoue.getCartesDev().get(CartesDev.Route).toString());
        label_34.setText(playerQuijoue.getCartesDev().get(CartesDev.Invention).toString());

        if(playerQuijoue.getCartesDev().get(CartesDev.Chevalier) > 0){
            getUseChevalier().setEnabled(true);
        }else{
            getUseChevalier().setEnabled(false);
        }

        if(playerQuijoue.getCartesDev().get(CartesDev.Route) > 0){
            getUseRoute().setEnabled(true);
        }else{
            getUseRoute().setEnabled(false);
        }

        if(playerQuijoue.getCartesDev().get(CartesDev.Invention) > 0){
            getUseInvention().setEnabled(true);
        }else{
            getUseInvention().setEnabled(false);
        }

        if(playerQuijoue.getCartesDev().get(CartesDev.Monopole) > 0){
            getUseMonopole().setEnabled(true);
        }else{
            getUseMonopole().setEnabled(false);
        }

        roadLabel.setIcon(new ImageIcon(playerToColorImage("wall",playerQuijoue)));
        colLabel.setIcon(new ImageIcon(playerToColorImage("village",playerQuijoue)));
        vilLabel.setIcon(new ImageIcon(playerToColorImage("city",playerQuijoue)));


        if (plateaux != null) {
            plateauxPanel.remove(element);
            element = new JPanel();
            element.setOpaque(false);
            element.setLayout(new BoxLayout(element, BoxLayout.Y_AXIS));
            plateauxPanel.add(element);
            caseResource = new LinkedList<JPanel>();
            caseRoute = new LinkedList<>();
            caseCol = new LinkedList<>();
            caseVil = new LinkedList<>();
            casePort = new LinkedList<>();
            for (int y = 0; y < plateaux.getPlateaux().length; y++) {
                JPanel yElementType1 = new JPanel();
                yElementType1.setOpaque(false);
                element.add(yElementType1);
                FlowLayout fl_yElementType1 = (FlowLayout) yElementType1.getLayout();
                fl_yElementType1.setVgap(0);
                fl_yElementType1.setHgap(0);
                for (int x = 0; x < plateaux.getPlateaux()[y].length; x++) {
                    if (plateaux.getPlateaux()[y][x] != null) {
                        CaseModel cases = plateaux.getPlateaux()[y][x];
                        if (cases instanceof Route) {
                            JButton btnNewButton = new JButton(cases.getName());
                            yElementType1.add(btnNewButton);
                            btnNewButton.setEnabled(false);
                            btnNewButton.setActionCommand(x+"!"+y);
                            btnNewButton.setBackground(new Color(229, 198, 134));
                            btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            btnNewButton.setFocusPainted(false);
                            btnNewButton.setRolloverEnabled(false);
                            btnNewButton.setRequestFocusEnabled(false);
                            btnNewButton.setMargin(new Insets(0, 0, 0, 0));
                            btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
                            btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                            btnNewButton.setBorder(null);
                            if (y % 2 == 0) {
                                btnNewButton.setPreferredSize(new Dimension(25, 64));
                            } else {
                                btnNewButton.setPreferredSize(new Dimension(64, 25));
                            }

                            if (((Route) cases).getPlayer() != null) {
                                if (((Route) cases).getPlayer().getColor() == Pcolor.Rouge) {
                                    btnNewButton.setBackground(new Color(190, 2, 14));
                                } else if (((Route) cases).getPlayer().getColor() == Pcolor.Jaune) {
                                    btnNewButton.setBackground(new Color(255, 211, 0));
                                } else if (((Route) cases).getPlayer().getColor() == Pcolor.Vert) {
                                    btnNewButton.setBackground(new Color(0, 149, 0));
                                } else if (((Route) cases).getPlayer().getColor() == Pcolor.Bleu) {
                                    btnNewButton.setBackground(new Color(93, 171, 210));
                                }
                            }

                            caseRoute.add(btnNewButton);
                        } else if (cases instanceof Batiment) {
                            JButton btnNewButton = new JButton(cases.getName());
                            yElementType1.add(btnNewButton);
                            btnNewButton.setBackground(new Color(192, 169, 115));
                            btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            btnNewButton.setFocusPainted(false);
                            btnNewButton.setActionCommand(x+"!"+y);
                            btnNewButton.setRolloverEnabled(false);
                            btnNewButton.setRequestFocusEnabled(false);
                            btnNewButton.setMargin(new Insets(0, 0, 0, 0));
                            btnNewButton.setPreferredSize(new Dimension(25, 25));
                            btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));

                            btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            btnNewButton.setEnabled(false);

                            btnNewButton.setBorder(null);

                            if (((Batiment) cases).getPlayer() != null) {
                                if (((Batiment) cases).getPlayer().getColor() == Pcolor.Rouge) {
                                    btnNewButton.setBackground(new Color(220, 32, 44));
                                } else if (((Batiment) cases).getPlayer().getColor() == Pcolor.Jaune) {
                                    btnNewButton.setBackground(new Color(255, 196, 12));
                                } else if (((Batiment) cases).getPlayer().getColor() == Pcolor.Vert) {
                                    btnNewButton.setBackground(new Color(29, 179, 30));
                                } else if (((Batiment) cases).getPlayer().getColor() == Pcolor.Bleu) {
                                    btnNewButton.setBackground(new Color(123, 201, 240));
                                }
                            }


                            if(cases.getName().contains("C")){
                                caseVil.add(btnNewButton);
                            }else {
                                caseCol.add(btnNewButton);
                            }

                        } else if (cases instanceof Port) {

                            JPanel panel_22 = new JPanel();
                            panel_22.setName(x+"!"+y);
                            FlowLayout flowLayout_2 = (FlowLayout) panel_22.getLayout();
                            flowLayout_2.setVgap(0);
                            flowLayout_2.setHgap(0);
                            yElementType1.add(panel_22);

                            Color textColor = Color.WHITE;


                            JLabel case1 = new JLabel();
                            case1.setIcon(new ImageIcon("src/main/resources/boat3.png"));
                            panel_22.setBackground(new Color(52, 152, 219));
                            panel_22.add(case1);
                            yElementType1.add(panel_22);
                            case1.setBorder(null);
                            case1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            case1.setPreferredSize(new Dimension(64, 64));
                            case1.setLayout(new BoxLayout(case1, BoxLayout.Y_AXIS));
                            case1.setForeground(textColor);


                            Component verticalGlue = Box.createVerticalGlue();
                            case1.add(verticalGlue);

                            JLabel lblMinerai = new JLabel(cases.getName());
                            lblMinerai.setAlignmentX(Component.CENTER_ALIGNMENT);
                            lblMinerai.setHorizontalTextPosition(SwingConstants.CENTER);
                            lblMinerai.setHorizontalAlignment(SwingConstants.CENTER);
                            lblMinerai.setFont(new Font("Arial", Font.BOLD, 12));
                            case1.add(lblMinerai);
                            lblMinerai.setForeground(textColor);


                            JLabel label_24;
                            if (cases.getNature() == null) {
                                label_24 = new JLabel("");
                            } else {
                                label_24 = new JLabel(cases.getNature() + "");
                            }
                            label_24.setAlignmentX(Component.CENTER_ALIGNMENT);
                            label_24.setHorizontalTextPosition(SwingConstants.CENTER);
                            label_24.setHorizontalAlignment(SwingConstants.CENTER);
                            label_24.setFont(new Font("Arial", Font.BOLD, 12));
                            case1.add(label_24);
                            label_24.setForeground(textColor);


                            JLabel label_29= new JLabel(((Port) cases).getPrix());
                            label_29.setAlignmentX(Component.CENTER_ALIGNMENT);
                            label_29.setFont(new Font("Arial", Font.BOLD, 12));
                            case1.add(label_29);
                            label_29.setForeground(textColor);


                            Component verticalGlue_1 = Box.createVerticalGlue();
                            case1.add(verticalGlue_1);

                            casePort.add(panel_22);

                        } else if (cases instanceof Vide) {
                            JPanel case1 = new JPanel();
                            yElementType1.add(case1);
                            case1.setBackground(new Color(52, 152, 219));
                            case1.setLayout(new BoxLayout(case1, BoxLayout.Y_AXIS));
                            if (y % 2 != 0) {
                                case1.setPreferredSize(new Dimension(64, 25));
                            } else if (x % 2 != 0) {
                                case1.setPreferredSize(new Dimension(25, 64));
                            } else {
                                case1.setPreferredSize(new Dimension(64, 64));
                            }
                        } else {
                            JPanel panel_22 = new JPanel();
                            panel_22.setName(x+"!"+y);
                            FlowLayout flowLayout_2 = (FlowLayout) panel_22.getLayout();
                            flowLayout_2.setVgap(0);
                            flowLayout_2.setHgap(0);
                            yElementType1.add(panel_22);

                            JLabel case1 = new JLabel();
                            case1.setName(x+"!"+y);
                            panel_22.add(case1);
                            case1.setBorder(null);
                            case1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            case1.setPreferredSize(new Dimension(64, 64));
                            case1.setLayout(new BoxLayout(case1, BoxLayout.Y_AXIS));


                            Component verticalGlue = Box.createVerticalGlue();
                            case1.add(verticalGlue);

                            Color textColor = Color.WHITE;

                            JLabel lblMinerai = new JLabel(cases.getName());
                            lblMinerai.setAlignmentX(Component.CENTER_ALIGNMENT);
                            lblMinerai.setHorizontalTextPosition(SwingConstants.CENTER);
                            lblMinerai.setHorizontalAlignment(SwingConstants.CENTER);
                            lblMinerai.setFont(new Font("Arial", Font.BOLD, 12));
                            lblMinerai.setForeground(textColor);
                            case1.add(lblMinerai);

                            JLabel label_24 = new JLabel(cases.getNumber() + "");
                            label_24.setAlignmentX(Component.CENTER_ALIGNMENT);
                            label_24.setHorizontalTextPosition(SwingConstants.CENTER);
                            label_24.setHorizontalAlignment(SwingConstants.CENTER);
                            label_24.setFont(new Font("Arial", Font.BOLD, 12));
                            label_24.setForeground(textColor);
                            case1.add(label_24);
                            Component verticalGlue_1 = Box.createVerticalGlue();



                            JLabel lblVoleur = new JLabel("Voleur");
                            lblVoleur.setAlignmentX(Component.CENTER_ALIGNMENT);
                            lblVoleur.setFont(new Font("Arial", Font.BOLD, 12));
                            lblVoleur.setForeground(textColor);
                            case1.add(lblVoleur);

                            if (cases.thiefPresent()) {
                                lblVoleur.setVisible(true);
                            } else {
                                lblVoleur.setVisible(false);
                            }

                            case1.add(verticalGlue_1);

                            caseResource.add(panel_22);

                            if (cases instanceof Deserts) {
                                panel_22.setBackground(new Color(188, 162, 95));
                            } else if (cases instanceof Forests) {
                                case1.setIcon(new ImageIcon("src/main/resources/res/woodHarbor.png"));
                                panel_22.setBackground(new Color(68, 89, 50));
                            } else if (cases instanceof Hills) {
                                case1.setIcon(new ImageIcon("src/main/resources/res/brickHarbor.png"));
                                panel_22.setBackground(new Color(160, 61, 12));
                            } else if (cases instanceof Mountain) {
                                case1.setIcon(new ImageIcon("src/main/resources/res/oreHarbor.png"));
                                panel_22.setBackground(new Color(53, 58, 62));
                            } else if (cases instanceof Pastures) {
                                case1.setIcon(new ImageIcon("src/main/resources/res/sheepHarbor.png"));
                                panel_22.setBackground(new Color(147, 203, 115));
                            } else if (cases instanceof Wheat) {
                                case1.setIcon(new ImageIcon("src/main/resources/res/wheatHarbor.png"));
                                panel_22.setBackground(new Color(203, 172, 29));
                            }
                        }
                    }
                }
            }
        }
        controller.updateListener();

        repaint();
    }

    public String playerToColorImage(String type, PlayerModel player){
        String to_return = "src/main/resources/batiment/"+type+"_$replace.png";
        if(player.getColor() == Pcolor.Rouge) {
            to_return = to_return.replace("$replace","red");
        }else if(player.getColor() == Pcolor.Jaune) {
            to_return = to_return.replace("$replace","yellow");
        }else if(player.getColor() == Pcolor.Vert) {
            to_return = to_return.replace("$replace","green");
        }else if(player.getColor() == Pcolor.Bleu) {
            to_return = to_return.replace("$replace","blue");
        }
        return to_return;
    }

    public void initialize() {
        setBounds(100, 100, 1400, 850);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new GuiViewAdapter(controller));
        getContentPane().setLayout(new BorderLayout(0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/catan-universe.png"));

        UIManager.put("Button.disabledText", new ColorUIResource(textArroundColor));

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
        GridBagLayout gbl_mainPanel = new GridBagLayout();
        gbl_mainPanel.rowHeights = new int[] {600, 150};
        gbl_mainPanel.columnWeights = new double[]{1.0, 0.0};
        gbl_mainPanel.rowWeights = new double[]{1.0, 0.0};
        mainPanel.setLayout(gbl_mainPanel);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setOpaque(false);
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        scrollPane_1.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Plateaux", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 0;
        scrollPane_1.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane_1.getHorizontalScrollBar().setUnitIncrement(15);
        mainPanel.add(scrollPane_1, gbc_scrollPane_1);

        scrollPane_1.setViewportView(plateauxPanel);
        scrollPane_1.getViewport().setOpaque(false);
        plateauxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        plateauxPanel.setOpaque(false);

        plateauxPanel.add(element);
        element.setOpaque(false);
        element.setLayout(new BoxLayout(element, BoxLayout.Y_AXIS));


        otherPanel.setOpaque(false);
        otherPanel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Tour 1", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        GridBagConstraints gbc_otherPanel = new GridBagConstraints();
        gbc_otherPanel.fill = GridBagConstraints.BOTH;
        gbc_otherPanel.insets = new Insets(0, 0, 5, 0);
        gbc_otherPanel.gridx = 1;
        gbc_otherPanel.gridy = 0;
        mainPanel.add(otherPanel, gbc_otherPanel);
        otherPanel.setLayout(new BorderLayout(0, 0));

        JPanel playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        otherPanel.add(playerPanel, BorderLayout.NORTH);

        JPanel redPlayer = new JPanel();
        redPlayer.setOpaque(false);
        redPlayer.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2, true), "Joueur Rouge", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));
        playerPanel.add(redPlayer);
        redPlayer.setLayout(new BoxLayout(redPlayer, BoxLayout.Y_AXIS));

        JPanel panel_7 = new JPanel();
        panel_7.setOpaque(false);
        redPlayer.add(panel_7);

        JLabel label = new JLabel("Point de victore:");
        label.setForeground(textArroundColor);
        label.setFont(new Font("Arial", Font.PLAIN, 12));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel_7.add(label);

        label_1.setForeground(textArroundColor);
        label_1.setFont(new Font("Arial", Font.PLAIN, 12));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_7.add(label_1);

        JPanel panel_8 = new JPanel();
        panel_8.setOpaque(false);
        redPlayer.add(panel_8);

        JLabel label_2 = new JLabel("Nombre colonie:");
        label_2.setForeground(textArroundColor);
        label_2.setFont(new Font("Arial", Font.PLAIN, 12));
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        panel_8.add(label_2);

        label_3.setForeground(textArroundColor);
        label_3.setFont(new Font("Arial", Font.PLAIN, 12));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel_8.add(label_3);

        JPanel panel_9 = new JPanel();
        panel_9.setOpaque(false);
        redPlayer.add(panel_9);

        JLabel label_4 = new JLabel("Nombre ville:");
        label_4.setForeground(textArroundColor);
        label_4.setFont(new Font("Arial", Font.PLAIN, 12));
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        panel_9.add(label_4);

        label_5.setForeground(textArroundColor);
        label_5.setFont(new Font("Arial", Font.PLAIN, 12));
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        panel_9.add(label_5);

        JPanel panel_10 = new JPanel();
        panel_10.setOpaque(false);
        redPlayer.add(panel_10);

        JLabel label_6 = new JLabel("Longeur de la route:");
        label_6.setForeground(textArroundColor);
        label_6.setFont(new Font("Arial", Font.PLAIN, 12));
        label_6.setHorizontalAlignment(SwingConstants.CENTER);
        panel_10.add(label_6);

        label_7.setFont(new Font("Arial", Font.PLAIN, 12));
        label_7.setForeground(textArroundColor);
        label_7.setHorizontalAlignment(SwingConstants.CENTER);
        panel_10.add(label_7);

        JPanel yelowPlayer = new JPanel();
        yelowPlayer.setOpaque(false);
        yelowPlayer.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 0), 2, true), "Joueur Jaune", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));
        playerPanel.add(yelowPlayer);
        yelowPlayer.setLayout(new BoxLayout(yelowPlayer, BoxLayout.Y_AXIS));

        JPanel panel_6 = new JPanel();
        panel_6.setOpaque(false);
        yelowPlayer.add(panel_6);

        JLabel label_8 = new JLabel("Point de victore:");
        label_8.setFont(new Font("Arial", Font.PLAIN, 12));
        label_8.setForeground(textArroundColor);
        label_8.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(label_8);

        label_9.setFont(new Font("Arial", Font.PLAIN, 12));
        label_9.setForeground(textArroundColor);
        label_9.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(label_9);

        JPanel panel_11 = new JPanel();
        panel_11.setOpaque(false);
        yelowPlayer.add(panel_11);

        JLabel label_10 = new JLabel("Nombre colonie:");
        label_10.setForeground(textArroundColor);
        label_10.setFont(new Font("Arial", Font.PLAIN, 12));
        label_10.setHorizontalAlignment(SwingConstants.CENTER);
        panel_11.add(label_10);

        label_11.setFont(new Font("Arial", Font.PLAIN, 12));
        label_11.setForeground(textArroundColor);
        label_11.setHorizontalAlignment(SwingConstants.CENTER);
        panel_11.add(label_11);

        JPanel panel_12 = new JPanel();
        panel_12.setOpaque(false);
        yelowPlayer.add(panel_12);

        JLabel label_12 = new JLabel("Nombre ville:");
        label_12.setForeground(textArroundColor);
        label_12.setFont(new Font("Arial", Font.PLAIN, 12));
        label_12.setHorizontalAlignment(SwingConstants.CENTER);
        panel_12.add(label_12);

        label_13.setFont(new Font("Arial", Font.PLAIN, 12));
        label_13.setForeground(textArroundColor);
        label_13.setHorizontalAlignment(SwingConstants.CENTER);
        panel_12.add(label_13);

        JPanel panel_13 = new JPanel();
        panel_13.setOpaque(false);
        yelowPlayer.add(panel_13);

        JLabel label_14 = new JLabel("Longeur de la route:");
        label_14.setForeground(textArroundColor);
        label_14.setFont(new Font("Arial", Font.PLAIN, 12));
        label_14.setHorizontalAlignment(SwingConstants.CENTER);
        panel_13.add(label_14);

        label_15.setFont(new Font("Arial", Font.PLAIN, 12));
        label_15.setForeground(textArroundColor);
        label_15.setHorizontalAlignment(SwingConstants.CENTER);
        panel_13.add(label_15);

        JPanel greenPlayer = new JPanel();
        greenPlayer.setOpaque(false);
        greenPlayer.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 2, true), "Joueur Vert", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));
        playerPanel.add(greenPlayer);
        greenPlayer.setLayout(new BoxLayout(greenPlayer, BoxLayout.Y_AXIS));

        JPanel panel_14 = new JPanel();
        panel_14.setOpaque(false);
        greenPlayer.add(panel_14);

        JLabel label_16 = new JLabel("Point de victore:");
        label_16.setForeground(textArroundColor);
        label_16.setFont(new Font("Arial", Font.PLAIN, 12));
        label_16.setHorizontalAlignment(SwingConstants.CENTER);
        panel_14.add(label_16);

        label_17.setFont(new Font("Arial", Font.PLAIN, 12));
        label_17.setForeground(textArroundColor);
        label_17.setHorizontalAlignment(SwingConstants.CENTER);
        panel_14.add(label_17);

        JPanel panel_15 = new JPanel();
        panel_15.setOpaque(false);
        greenPlayer.add(panel_15);

        JLabel label_18 = new JLabel("Nombre colonie:");
        label_18.setForeground(textArroundColor);
        label_18.setFont(new Font("Arial", Font.PLAIN, 12));
        label_18.setHorizontalAlignment(SwingConstants.CENTER);
        panel_15.add(label_18);

        label_19.setFont(new Font("Arial", Font.PLAIN, 12));
        label_19.setForeground(textArroundColor);
        label_19.setHorizontalAlignment(SwingConstants.CENTER);
        panel_15.add(label_19);

        JPanel panel_16 = new JPanel();
        panel_16.setOpaque(false);
        greenPlayer.add(panel_16);

        JLabel label_20 = new JLabel("Nombre ville:");
        label_20.setForeground(textArroundColor);
        label_20.setFont(new Font("Arial", Font.PLAIN, 12));
        label_20.setHorizontalAlignment(SwingConstants.CENTER);
        panel_16.add(label_20);

        label_21.setFont(new Font("Arial", Font.PLAIN, 12));
        label_21.setForeground(textArroundColor);
        label_21.setHorizontalAlignment(SwingConstants.CENTER);
        panel_16.add(label_21);

        JPanel panel_17 = new JPanel();
        panel_17.setOpaque(false);
        greenPlayer.add(panel_17);

        JLabel label_22 = new JLabel("Longeur de la route:");
        label_22.setForeground(textArroundColor);
        label_22.setHorizontalAlignment(SwingConstants.CENTER);
        label_22.setFont(new Font("Arial", Font.PLAIN, 12));
        panel_17.add(label_22);

        label_23.setFont(new Font("Arial", Font.PLAIN, 12));
        label_23.setForeground(textArroundColor);
        label_23.setHorizontalAlignment(SwingConstants.CENTER);
        panel_17.add(label_23);

        if(controller.getPlayerNum() == 4) {
            JPanel bleuPlayer = new JPanel();
            bleuPlayer.setOpaque(false);
            bleuPlayer.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255), 2, true), "Joueur Bleu", TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));
            playerPanel.add(bleuPlayer);
            bleuPlayer.setLayout(new BoxLayout(bleuPlayer, BoxLayout.Y_AXIS));

            JPanel panel_1 = new JPanel();
            panel_1.setOpaque(false);
            bleuPlayer.add(panel_1);

            JLabel lblPointDeVictore = new JLabel("Point de victore:");
            lblPointDeVictore.setForeground(textArroundColor);
            lblPointDeVictore.setFont(new Font("Arial", Font.PLAIN, 12));
            panel_1.add(lblPointDeVictore);
            lblPointDeVictore.setHorizontalAlignment(SwingConstants.CENTER);

            pvLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            pvLabel.setForeground(textArroundColor);
            panel_1.add(pvLabel);
            pvLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel panel_3 = new JPanel();
            panel_3.setOpaque(false);
            bleuPlayer.add(panel_3);

            JLabel lblNombreColonie = new JLabel("Nombre colonie:");
            lblNombreColonie.setForeground(textArroundColor);
            lblNombreColonie.setFont(new Font("Arial", Font.PLAIN, 12));
            panel_3.add(lblNombreColonie);
            lblNombreColonie.setHorizontalAlignment(SwingConstants.CENTER);

            numCol.setFont(new Font("Arial", Font.PLAIN, 12));
            numCol.setForeground(textArroundColor);
            panel_3.add(numCol);
            numCol.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel panel_4 = new JPanel();
            panel_4.setOpaque(false);
            bleuPlayer.add(panel_4);

            JLabel lblNombreVille = new JLabel("Nombre ville:");
            lblNombreVille.setForeground(textArroundColor);
            lblNombreVille.setFont(new Font("Arial", Font.PLAIN, 12));
            panel_4.add(lblNombreVille);
            lblNombreVille.setHorizontalAlignment(SwingConstants.CENTER);

            numVil.setFont(new Font("Arial", Font.PLAIN, 12));
            numVil.setForeground(textArroundColor);
            panel_4.add(numVil);
            numVil.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel panel_5 = new JPanel();
            panel_5.setOpaque(false);
            bleuPlayer.add(panel_5);

            JLabel lblLongeurDeLa = new JLabel("Longeur de la route:");
            lblLongeurDeLa.setForeground(textArroundColor);
            lblLongeurDeLa.setFont(new Font("Arial", Font.PLAIN, 12));
            panel_5.add(lblLongeurDeLa);
            lblLongeurDeLa.setHorizontalAlignment(SwingConstants.CENTER);

            numLargRoad.setForeground(textArroundColor);
            numLargRoad.setFont(new Font("Arial", Font.PLAIN, 12));
            panel_5.add(numLargRoad);
            numLargRoad.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        otherPanel.add(btnPanel, BorderLayout.SOUTH);
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnLancerDe.setFont(new Font("Arial", Font.BOLD, 12));
        btnLancerDe.setForeground(textArroundColor);
        btnLancerDe.setOpaque(false);
        btnLancerDe.setFocusPainted(false);
        btnLancerDe.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLancerDe.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        btnLancerDe.setBackground(new Color(0, 0, 0, 0));
        btnLancerDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLancerDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPanel.add(btnLancerDe);

        btnFinirLeTour.setForeground(textArroundColor);
        btnFinirLeTour.setFont(new Font("Arial", Font.BOLD, 12));
        btnFinirLeTour.setOpaque(false);
        btnFinirLeTour.setFocusPainted(false);
        btnFinirLeTour.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4)));
        btnFinirLeTour.setBackground(new Color(0, 0, 0, 0));
        btnFinirLeTour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnFinirLeTour.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFinirLeTour.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPanel.add(btnFinirLeTour);

        usePort.setForeground(textArroundColor);
        usePort.setFont(new Font("Arial", Font.BOLD, 12));
        usePort.setOpaque(false);
        usePort.setFocusPainted(false);
        usePort.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        usePort.setBackground(new Color(0, 0, 0, 0));
        usePort.setHorizontalTextPosition(SwingConstants.CENTER);
        usePort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        usePort.setAlignmentX(Component.CENTER_ALIGNMENT);
        usePort.setToolTipText("Utiliser le port");
        btnPanel.add(usePort);


        btnDevelopement.setForeground(textArroundColor);
        btnDevelopement.setFont(new Font("Arial", Font.BOLD, 12));
        btnDevelopement.setOpaque(false);
        btnDevelopement.setFocusPainted(false);
        btnDevelopement.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        btnDevelopement.setBackground(new Color(0, 0, 0, 0));
        btnDevelopement.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDevelopement.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDevelopement.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDevelopement.setToolTipText("Acheter des cartes de devlopement");
        btnPanel.add(btnDevelopement);

        JPanel centerContent = new JPanel();
        centerContent.setOpaque(false);
        otherPanel.add(centerContent, BorderLayout.CENTER);
        centerContent.setLayout(new BoxLayout(centerContent, BoxLayout.Y_AXIS));

        Component verticalGlue_6 = Box.createVerticalGlue();
        centerContent.add(verticalGlue_6);

        JPanel panel_31 = new JPanel();
        panel_31.setOpaque(false);
        centerContent.add(panel_31);
        panel_31.setLayout(new BoxLayout(panel_31, BoxLayout.Y_AXIS));

        panel_33.setOpaque(false);
        panel_33.setVisible(false);
        panel_31.add(panel_33);

        JLabel label_35 = new JLabel("La plus grande route appartient a:");
        label_35.setForeground(Color.WHITE);
        label_35.setFont(new Font("Arial", Font.PLAIN, 12));
        panel_33.add(label_35);


        grandeRoute.setForeground(Color.WHITE);
        grandeRoute.setFont(new Font("Arial", Font.PLAIN, 12));
        panel_33.add(grandeRoute);


        panel_32.setOpaque(false);
        panel_32.setVisible(false);
        panel_31.add(panel_32);

        JLabel lblNewLabel_4 = new JLabel("La plus grande arm\u00E9e appartient a:");
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
        panel_32.add(lblNewLabel_4);

        grandeArme.setForeground(Color.WHITE);
        grandeArme.setFont(new Font("Arial", Font.PLAIN, 12));
        panel_32.add(grandeArme);

        Component verticalGlue_3 = Box.createVerticalGlue();
        centerContent.add(verticalGlue_3);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel_3.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Cout de construction", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));
        lblNewLabel_3.setIcon(new ImageIcon("src/main/resources/costs.png"));
        centerContent.add(lblNewLabel_3);

        Component verticalGlue_5 = Box.createVerticalGlue();
        centerContent.add(verticalGlue_5);

        JPanel panel_30 = new JPanel();
        panel_30.setOpaque(false);
        centerContent.add(panel_30);

        Component verticalGlue_4 = Box.createVerticalGlue();
        centerContent.add(verticalGlue_4);

        JPanel panel_20 = new JPanel();
        panel_20.setOpaque(false);
        panel_30.add(panel_20);

        JPanel dicePanel = new JPanel();
        panel_20.add(dicePanel);
        dicePanel.setOpaque(false);
        dicePanel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "D\u00E9", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));

        dice1.setIcon(new ImageIcon("src/main/resources/die/die0.png"));
        dicePanel.add(dice1);

        dice2.setIcon(new ImageIcon("src/main/resources/die/die0.png"));
        dicePanel.add(dice2);






        JPanel panel_19 = new JPanel();
        panel_19.setOpaque(false);
        panel_30.add(panel_19);
        panel_19.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel_21 = new JPanel();
        panel_21.setOpaque(false);
        panel_19.add(panel_21);
        panel_21.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Construction", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 13), textArroundColor));


        JPanel routePanel = new JPanel();
        routePanel.setOpaque(false);
        routePanel.setToolTipText("Construire une route");
        panel_21.add(routePanel);
        routePanel.setLayout(new BoxLayout(routePanel, BoxLayout.Y_AXIS));

        roadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roadLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        roadLabel.setHorizontalAlignment(SwingConstants.CENTER);
        routePanel.add(roadLabel);

        buildRadio.add(roadRadio);
        roadRadio.setOpaque(false);
        roadRadio.setAlignmentX(Component.CENTER_ALIGNMENT);
        roadRadio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        roadRadio.setHorizontalAlignment(SwingConstants.CENTER);
        roadRadio.setHorizontalTextPosition(SwingConstants.CENTER);
        roadRadio.setActionCommand("route");
        routePanel.add(roadRadio);

        Component horizontalStrut_4 = Box.createHorizontalStrut(20);
        panel_21.add(horizontalStrut_4);

        JPanel colPanel = new JPanel();
        colPanel.setOpaque(false);
        colPanel.setToolTipText("Construire une colonie");
        panel_21.add(colPanel);
        colPanel.setLayout(new BoxLayout(colPanel, BoxLayout.Y_AXIS));


        colLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        colLabel.setHorizontalAlignment(SwingConstants.CENTER);
        colLabel.setAlignmentX(0.5f);
        colPanel.add(colLabel);


        buildRadio.add(colRadio);
        colRadio.setOpaque(false);
        colRadio.setHorizontalTextPosition(SwingConstants.CENTER);
        colRadio.setHorizontalAlignment(SwingConstants.CENTER);
        colRadio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        colRadio.setAlignmentX(0.5f);
        colRadio.setActionCommand("col");
        colPanel.add(colRadio);

        Component horizontalStrut_5 = Box.createHorizontalStrut(20);
        panel_21.add(horizontalStrut_5);

        JPanel vilPanel = new JPanel();
        vilPanel.setOpaque(false);
        vilPanel.setToolTipText("Construire une ville");
        panel_21.add(vilPanel);
        vilPanel.setLayout(new BoxLayout(vilPanel, BoxLayout.Y_AXIS));


        vilLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        vilLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vilLabel.setAlignmentX(0.5f);
        vilPanel.add(vilLabel);

        buildRadio.add(vilRadio);
        vilRadio.setOpaque(false);
        vilRadio.setHorizontalTextPosition(SwingConstants.CENTER);
        vilRadio.setHorizontalAlignment(SwingConstants.CENTER);
        vilRadio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        vilRadio.setAlignmentX(0.5f);
        vilRadio.setActionCommand("vil");
        vilPanel.add(vilRadio);




        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setSize(new Dimension(0, 300));
        scrollPane.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Logs", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        scrollPane.setAutoscrolls(true);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        mainPanel.add(scrollPane, gbc_scrollPane);

        logsArea.setOpaque(false);
        logsArea.setSize(new Dimension(0, 300));
        logsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        logsArea.setForeground(textArroundColor);
        logsArea.setToolTipText("Ble");
        logsArea.setEditable(false);
        scrollPane.setViewportView(logsArea);
        scrollPane.getViewport().setOpaque(false);
        logsArea.setBounds(new Rectangle(0, 0, 0, 100));
        logsArea.setDragEnabled(true);



        JPanel panel_29 = new JPanel();
        panel_29.setOpaque(false);
        GridBagConstraints gbc_panel_29 = new GridBagConstraints();
        gbc_panel_29.insets = new Insets(0, 0, 5, 0);
        gbc_panel_29.fill = GridBagConstraints.BOTH;
        gbc_panel_29.gridx = 1;
        gbc_panel_29.gridy = 1;
        mainPanel.add(panel_29, gbc_panel_29);
        panel_29.setLayout(new BoxLayout(panel_29, BoxLayout.Y_AXIS));



        JPanel resPanel = new JPanel();
        resPanel.setOpaque(false);
        resPanel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Ressources", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        GridBagConstraints gbc_resPanel = new GridBagConstraints();
        gbc_resPanel.insets = new Insets(0, 0, 5, 0);
        gbc_resPanel.fill = GridBagConstraints.BOTH;
        gbc_resPanel.gridx = 1;
        gbc_resPanel.gridy = 1;
        panel_29.add(resPanel);
        resPanel.setLayout(new BoxLayout(resPanel, BoxLayout.X_AXIS));

        Component horizontalStrut = Box.createHorizontalStrut(20);
        resPanel.add(horizontalStrut);

        JPanel boisRes = new JPanel();
        boisRes.setOpaque(false);
        boisRes.setToolTipText(Resources.BOIS.toString());
        resPanel.add(boisRes);
        boisRes.setLayout(new BoxLayout(boisRes, BoxLayout.Y_AXIS));

        JLabel boisIcon = new JLabel();
        boisIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        boisIcon.setIcon(new ImageIcon("src/main/resources/woodSmall.png"));
        boisRes.add(boisIcon);

        boisNum.setForeground(textArroundColor);
        boisNum.setFont(new Font("Arial", Font.PLAIN, 13));
        boisNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        boisNum.setHorizontalAlignment(SwingConstants.CENTER);
        boisRes.add(boisNum);

        Component horizontalGlue = Box.createHorizontalGlue();
        resPanel.add(horizontalGlue);

        JPanel wheatRes = new JPanel();
        wheatRes.setOpaque(false);
        wheatRes.setToolTipText(Resources.BLE.toString());
        resPanel.add(wheatRes);
        wheatRes.setLayout(new BoxLayout(wheatRes, BoxLayout.Y_AXIS));

        JLabel wheatIcon = new JLabel();
        wheatIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        wheatIcon.setIcon(new ImageIcon("src/main/resources/wheatSmall.png"));
        wheatRes.add(wheatIcon);

        wheatNum.setForeground(textArroundColor);
        wheatNum.setFont(new Font("Arial", Font.PLAIN, 13));
        wheatNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        wheatNum.setHorizontalAlignment(SwingConstants.CENTER);
        wheatRes.add(wheatNum);

        Component horizontalGlue1 = Box.createHorizontalGlue();
        resPanel.add(horizontalGlue1);

        JPanel argileRes = new JPanel();
        argileRes.setOpaque(false);
        argileRes.setToolTipText(Resources.ARGILE.toString());
        resPanel.add(argileRes);
        argileRes.setLayout(new BoxLayout(argileRes, BoxLayout.Y_AXIS));

        JLabel argileIcon = new JLabel();
        argileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        argileIcon.setIcon(new ImageIcon("src/main/resources/brickSmall.png"));
        argileRes.add(argileIcon);

        argileNum.setForeground(textArroundColor);
        argileNum.setFont(new Font("Arial", Font.PLAIN, 13));
        argileNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        argileNum.setHorizontalAlignment(SwingConstants.CENTER);
        argileRes.add(argileNum);

        Component horizontalGlue2 = Box.createHorizontalGlue();
        resPanel.add(horizontalGlue2);

        JPanel minRes = new JPanel();
        minRes.setOpaque(false);
        minRes.setToolTipText(Resources.MINERAI.toString());
        resPanel.add(minRes);
        minRes.setLayout(new BoxLayout(minRes, BoxLayout.Y_AXIS));

        JLabel minIcon = new JLabel();
        minIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        minIcon.setIcon(new ImageIcon("src/main/resources/oreSmall.png"));
        minRes.add(minIcon);

        minNum.setForeground(textArroundColor);
        minNum.setFont(new Font("Arial", Font.PLAIN, 13));
        minNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        minNum.setHorizontalAlignment(SwingConstants.CENTER);
        minRes.add(minNum);

        Component horizontalGlue3 = Box.createHorizontalGlue();
        resPanel.add(horizontalGlue3);

        JPanel mouRes = new JPanel();
        mouRes.setOpaque(false);
        mouRes.setToolTipText(Resources.MOUTON.toString());
        resPanel.add(mouRes);
        mouRes.setLayout(new BoxLayout(mouRes, BoxLayout.Y_AXIS));

        JLabel mouIcon = new JLabel();
        mouIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        mouIcon.setIcon(new ImageIcon("src/main/resources/sheepSmall.png"));
        mouRes.add(mouIcon);

        mouNum.setForeground(textArroundColor);
        mouNum.setFont(new Font("Arial", Font.PLAIN, 13));
        mouNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        mouNum.setHorizontalAlignment(SwingConstants.CENTER);
        mouRes.add(mouNum);

        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        resPanel.add(horizontalStrut_1);

        JPanel panel_23 = new JPanel();
        panel_23.setOpaque(false);
        panel_29.add(panel_23);
        panel_23.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Cartes de devlopement", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
        panel_23.setLayout(new BoxLayout(panel_23, BoxLayout.X_AXIS));

        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        panel_23.add(horizontalStrut_2);

        JPanel panel_24 = new JPanel();
        panel_24.setOpaque(false);
        panel_24.setToolTipText("Chevalier");
        panel_23.add(panel_24);
        panel_24.setLayout(new BoxLayout(panel_24, BoxLayout.Y_AXIS));

        JLabel lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_24.add(lblNewLabel_1);
        lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/dev/icons8-knight-64.png"));

        lblNewLabel_2.setForeground(textArroundColor);
        lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
        panel_24.add(lblNewLabel_2);

        useChevalier.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_24.add(useChevalier);
        useChevalier.setOpaque(false);
        useChevalier.setFocusPainted(false);
        useChevalier.setFont(new Font("Arial", Font.BOLD, 12));
        useChevalier.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        useChevalier.setBackground(new Color(0, 0, 0, 0));
        useChevalier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        useChevalier.setForeground(textArroundColor);


        Component horizontalGlue_3 = Box.createHorizontalGlue();
        panel_23.add(horizontalGlue_3);

        JPanel panel_25 = new JPanel();
        panel_25.setOpaque(false);
        panel_25.setToolTipText("Monopole");
        panel_23.add(panel_25);
        panel_25.setLayout(new BoxLayout(panel_25, BoxLayout.Y_AXIS));

        JLabel label_27 = new JLabel();
        label_27.setIcon(new ImageIcon("src/main/resources/dev/icons8-monopoly-64.png"));
        label_27.setAlignmentX(0.5f);
        panel_25.add(label_27);

        label_28.setForeground(textArroundColor);
        label_28.setFont(new Font("Arial", Font.PLAIN, 13));
        label_28.setAlignmentX(0.5f);
        panel_25.add(label_28);

        useMonopole.setForeground(textArroundColor);
        useMonopole.setAlignmentX(0.5f);
        panel_25.add(useMonopole);
        useMonopole.setOpaque(false);
        useMonopole.setFocusPainted(false);
        useMonopole.setFont(new Font("Arial", Font.BOLD, 12));
        useMonopole.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        useMonopole.setBackground(new Color(0, 0, 0, 0));
        useMonopole.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        useMonopole.setAlignmentX(Component.CENTER_ALIGNMENT);

        Component horizontalGlue_4 = Box.createHorizontalGlue();
        panel_23.add(horizontalGlue_4);

        JPanel panel_26 = new JPanel();
        panel_26.setOpaque(false);
        panel_26.setToolTipText("Point de victoire");
        panel_23.add(panel_26);
        panel_26.setLayout(new BoxLayout(panel_26, BoxLayout.Y_AXIS));

        Component verticalGlue_2 = Box.createVerticalGlue();
        panel_26.add(verticalGlue_2);

        JLabel label_29 = new JLabel();
        label_29.setIcon(new ImageIcon("src/main/resources/dev/icons8-medal-64.png"));
        label_29.setAlignmentX(0.5f);
        panel_26.add(label_29);

        label_30.setForeground(textArroundColor);
        label_30.setFont(new Font("Arial", Font.PLAIN, 13));
        label_30.setAlignmentX(0.5f);
        panel_26.add(label_30);

        Component verticalGlue_9= Box.createVerticalGlue();
        panel_26.add(verticalGlue_9);

        Component horizontalGlue_5 = Box.createHorizontalGlue();
        panel_23.add(horizontalGlue_5);

        label_27.setForeground(textArroundColor);
        panel_27.setOpaque(false);
        panel_27.setToolTipText("Route");
        panel_23.add(panel_27);
        panel_27.setLayout(new BoxLayout(panel_27, BoxLayout.Y_AXIS));

        JLabel label_31 = new JLabel();
        label_31.setIcon(new ImageIcon("src/main/resources/dev/icons8-path-64.png"));
        label_31.setAlignmentX(0.5f);
        panel_27.add(label_31);

        label_32.setForeground(textArroundColor);
        label_32.setFont(new Font("Arial", Font.PLAIN, 13));
        label_32.setAlignmentX(0.5f);
        panel_27.add(label_32);


        useRoute.setForeground(textArroundColor);
        panel_27.add(useRoute);
        useRoute.setAlignmentX(0.5f);
        useRoute.setOpaque(false);
        useRoute.setFocusPainted(false);
        useRoute.setFont(new Font("Arial", Font.BOLD, 12));
        useRoute.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        useRoute.setBackground(new Color(0, 0, 0, 0));
        useRoute.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        useRoute.setAlignmentX(Component.CENTER_ALIGNMENT);

        Component horizontalGlue_6 = Box.createHorizontalGlue();
        panel_23.add(horizontalGlue_6);

        panel_28.setForeground(textArroundColor);
        panel_28.setOpaque(false);
        panel_28.setToolTipText("Invention");
        panel_23.add(panel_28);
        panel_28.setLayout(new BoxLayout(panel_28, BoxLayout.Y_AXIS));

        JLabel label_33 = new JLabel();
        label_33.setIcon(new ImageIcon("src/main/resources/dev/icons8-invention-64.png"));
        label_33.setAlignmentX(0.5f);
        panel_28.add(label_33);

        label_34.setForeground(textArroundColor);
        label_34.setFont(new Font("Arial", Font.PLAIN, 13));
        label_34.setAlignmentX(0.5f);
        panel_28.add(label_34);

        useInvention.setForeground(textArroundColor);
        useInvention.setAlignmentX(0.5f);
        useInvention.setOpaque(false);
        useInvention.setFocusPainted(false);
        useInvention.setFont(new Font("Arial", Font.BOLD, 12));
        useInvention.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        useInvention.setBackground(new Color(0, 0, 0, 0));
        useInvention.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        useInvention.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_28.add(useInvention);

        Component horizontalStrut_3 = Box.createHorizontalStrut(20);
        panel_23.add(horizontalStrut_3);


        updateView();
    }
}

package l2.poo3.view.gui;

import l2.poo3.controller.gui.GuiEndGameWinAdapter;
import l2.poo3.controller.gui.GuiViewAdapter;
import l2.poo3.controller.gui.UpdateBackgroundImageWorker;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

public class GuiEndGameView extends JDialog{

    private final JPanel contentPanel = new JPanel();
    private final JLabel back = new JLabel();

    private final ImageIcon background=new ImageIcon(ClassLoader.getSystemResource("background/winBackground.png"));

    public GuiEndGameView(GuiView guiView, String text) {

        setModal(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setTitle("Groupe 48 - Fin de la partie");
        setBounds(100, 100, 450, 250);
        addWindowListener(new GuiEndGameWinAdapter(this,guiView));
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
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel panel_2 = new JPanel();
        panel_2.setOpaque(false);
        contentPanel.add(panel_2);

        JLabel lblNewLabel = new JLabel("");
        panel_2.add(lblNewLabel);
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("cup.png")));

        Component verticalStrut = Box.createVerticalStrut(10);
        contentPanel.add(verticalStrut);

        JPanel panel_3 = new JPanel();
        panel_3.setOpaque(false);
        contentPanel.add(panel_3);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.BOLD, 14));
        textArea.setEditable(false);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setForeground(Color.BLUE);
        textArea.setText(text);
        panel_3.add(textArea);

        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton endButton = new JButton("Fermer le jeu");
        endButton.addActionListener(e -> {
            guiView.dispatchEvent(new WindowEvent(guiView, WindowEvent.WINDOW_CLOSING));
        });
        endButton.setActionCommand("OK");

        endButton.setOpaque(false);
        endButton.setFocusPainted(false);
        endButton.setHorizontalTextPosition(SwingConstants.CENTER);
        endButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
        endButton.setBackground(new Color(0, 0, 0, 0));
        endButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        endButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPane.add(endButton);
        getRootPane().setDefaultButton(endButton);

    }
}

package l2.poo3.view.gui;

import l2.poo3.controller.gui.UpdateBackgroundImageWorker;
import l2.poo3.model.Enum.Resources;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AskRessourcesView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup choixRes = new ButtonGroup();

	private final JLabel back = new JLabel();

	private final ImageIcon background=new ImageIcon(ClassLoader.getSystemResource("background/askRessourcesBackground.jpg"));

	private final Color textArroundColor = new Color(255, 255, 255);

	private Resources res;

	public AskRessourcesView(GuiView guiView, String title) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Groupe 48 - Choix Ressources");
		setBounds(100, 100, 500, 200);
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
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel resPanel = new JPanel();
		resPanel.setOpaque(false);
		resPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), title, TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.BLACK));
		GridBagConstraints gbc_resPanel = new GridBagConstraints();
		gbc_resPanel.insets = new Insets(0, 0, 5, 0);
		gbc_resPanel.fill = GridBagConstraints.BOTH;
		gbc_resPanel.gridx = 1;
		gbc_resPanel.gridy = 1;
		contentPanel.add(resPanel);
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
		boisIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("res/woodSmall.png")));
		boisRes.add(boisIcon);

		JRadioButton rdbtnNewRadioButton5 = new JRadioButton("");
		choixRes.add(rdbtnNewRadioButton5);
		rdbtnNewRadioButton5.setFocusPainted(false);
		rdbtnNewRadioButton5.setOpaque(false);
		rdbtnNewRadioButton5.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnNewRadioButton5.setActionCommand("wood");
		boisRes.add(rdbtnNewRadioButton5);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_5);

		JPanel wheatRes = new JPanel();
		wheatRes.setOpaque(false);
		wheatRes.setToolTipText(Resources.BLE.toString());
		resPanel.add(wheatRes);
		wheatRes.setLayout(new BoxLayout(wheatRes, BoxLayout.Y_AXIS));

		JLabel wheatIcon = new JLabel();
		wheatIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		wheatIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("res/wheatSmall.png")));
		wheatRes.add(wheatIcon);

		JRadioButton rdbtnNewRadioButton4 = new JRadioButton("");
		choixRes.add(rdbtnNewRadioButton4);
		rdbtnNewRadioButton4.setFocusPainted(false);
		rdbtnNewRadioButton4.setOpaque(false);
		rdbtnNewRadioButton4.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnNewRadioButton4.setActionCommand("wheat");
		wheatRes.add(rdbtnNewRadioButton4);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_4);

		JPanel argileRes = new JPanel();
		argileRes.setOpaque(false);
		argileRes.setToolTipText(Resources.ARGILE.toString());
		resPanel.add(argileRes);
		argileRes.setLayout(new BoxLayout(argileRes, BoxLayout.Y_AXIS));

		JLabel argileIcon = new JLabel();
		argileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		argileIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("res/brickSmall.png")));
		argileRes.add(argileIcon);

		JRadioButton rdbtnNewRadioButton3 = new JRadioButton("");
		choixRes.add(rdbtnNewRadioButton3);
		rdbtnNewRadioButton3.setFocusPainted(false);
		rdbtnNewRadioButton3.setOpaque(false);
		rdbtnNewRadioButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnNewRadioButton3.setActionCommand("brick");
		argileRes.add(rdbtnNewRadioButton3);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_3);

		JPanel minRes = new JPanel();
		minRes.setOpaque(false);
		minRes.setToolTipText(Resources.MINERAI.toString());
		resPanel.add(minRes);
		minRes.setLayout(new BoxLayout(minRes, BoxLayout.Y_AXIS));

		JLabel minIcon = new JLabel();
		minIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		minIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("res/oreSmall.png")));
		minRes.add(minIcon);

		JRadioButton rdbtnNewRadioButton2 = new JRadioButton("");
		choixRes.add(rdbtnNewRadioButton2);
		rdbtnNewRadioButton2.setFocusPainted(false);
		rdbtnNewRadioButton2.setOpaque(false);
		rdbtnNewRadioButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnNewRadioButton2.setActionCommand("ore");
		minRes.add(rdbtnNewRadioButton2);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_2);

		JPanel mouRes = new JPanel();
		mouRes.setOpaque(false);
		mouRes.setToolTipText(Resources.MOUTON.toString());
		resPanel.add(mouRes);
		mouRes.setLayout(new BoxLayout(mouRes, BoxLayout.Y_AXIS));

		JLabel mouIcon = new JLabel();
		mouIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		mouIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("res/sheepSmall.png")));
		mouRes.add(mouIcon);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		choixRes.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFocusPainted(false);
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnNewRadioButton.setActionCommand("sheep");
		mouRes.add(rdbtnNewRadioButton);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_1);

			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setOpaque(false);
				okButton.setForeground(textArroundColor);
				okButton.setFocusPainted(false);
				okButton.setHorizontalTextPosition(SwingConstants.CENTER);
				okButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2, true), new LineBorder(new Color(0, 0, 0, 0), 4, true)));
				okButton.setBackground(new Color(0, 0, 0, 0));
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				okButton.addActionListener(e -> {
					if(choixRes.getSelection() != null) {
						String choix = choixRes.getSelection().getActionCommand();

						if(choix.equalsIgnoreCase("wood")){
							res = Resources.BOIS;
						}else if(choix.equalsIgnoreCase("wheat")){
							res = Resources.BLE;
						}else if(choix.equalsIgnoreCase("brick")){
							res = Resources.ARGILE;
						}else if(choix.equalsIgnoreCase("ore")){
							res = Resources.MINERAI;
						}else if(choix.equalsIgnoreCase("sheep")){
							res = Resources.MOUTON;
						}
						if(res != null){
							dispose();
						}else{
							guiView.addInfo("Merci de choisir une ressource");
							guiView.updateView();
						}
					}
				});

	}

	public Resources getRes() {
		return res;
	}
}

package l2.poo3.view.gui;

import l2.poo3.controller.gui.UpdateBackgroundImageWorker;
import l2.poo3.model.Enum.Resources;
import l2.poo3.model.PlayerModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class DeleteReeourcesView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup choixRes = new ButtonGroup();

	private final JSpinner mouSpinner = new JSpinner();
	private final JSpinner minSpinner = new JSpinner();
	private final JSpinner argileSpinner = new JSpinner();
	private final JSpinner wheatSpinner = new JSpinner();
	private final JSpinner boisSpinner = new JSpinner();

	private final JLabel labelNum;
	private final JLabel back = new JLabel();

	private final ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("background/removeBackground.jpg"));

	private final Color textArroundColor = new Color(255, 255, 255);

	private final ArrayList<JSpinner> allSpiner = new ArrayList<>();

	int max = 0, totalAdded = 0;

	private Resources res;

	public DeleteReeourcesView(GuiView guiView, PlayerModel player) {
		this.max = player.getNbrRessources();

		setContentPane(back);
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Groupe 48 - Choix Ressources A Supprimer");
		setBounds(100, 100, 500, 250);
		setIconImage(new ImageIcon(ClassLoader.getSystemResource("catan-universe.png")).getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		UpdateBackgroundImageWorker updateBackgroundImageWorker = new UpdateBackgroundImageWorker(back, background, getWidth(), getHeight());
		updateBackgroundImageWorker.execute();

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				UpdateBackgroundImageWorker updateBackgroundImageWorker = new UpdateBackgroundImageWorker(back, background, getWidth(), getHeight());
				updateBackgroundImageWorker.execute();
			}
		});


		JPanel resPanel = new JPanel();
		resPanel.setOpaque(false);
		resPanel.setBorder(new TitledBorder(new LineBorder(textArroundColor, 2, true), "Choisissez la ressource à voler", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), textArroundColor));
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
		boisIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("woodSmall.png")));
		boisRes.add(boisIcon);
		Component verticalStrut = Box.createVerticalStrut(10);
		boisRes.add(verticalStrut);
		boisSpinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		boisSpinner.setModel(new SpinnerNumberModel(0, 0, (int) player.getResources().get(Resources.BOIS), 1));
		boisRes.add(boisSpinner);
		allSpiner.add(boisSpinner);
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		boisRes.add(verticalStrut_1);


		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_5);

		JPanel wheatRes = new JPanel();
		wheatRes.setOpaque(false);
		wheatRes.setToolTipText(Resources.BLE.toString());
		resPanel.add(wheatRes);
		wheatRes.setLayout(new BoxLayout(wheatRes, BoxLayout.Y_AXIS));

		JLabel wheatIcon = new JLabel();
		wheatIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		wheatIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("wheatSmall.png")));
		wheatRes.add(wheatIcon);
		Component verticalStrut1 = Box.createVerticalStrut(10);
		wheatRes.add(verticalStrut1);
		wheatSpinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		wheatSpinner.setModel(new SpinnerNumberModel(0, 0, (int) player.getResources().get(Resources.BLE), 1));
		wheatRes.add(wheatSpinner);
		allSpiner.add(wheatSpinner);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		wheatRes.add(verticalStrut_2);


		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_4);

		JPanel argileRes = new JPanel();
		argileRes.setOpaque(false);
		argileRes.setToolTipText(Resources.ARGILE.toString());
		resPanel.add(argileRes);
		argileRes.setLayout(new BoxLayout(argileRes, BoxLayout.Y_AXIS));

		JLabel argileIcon = new JLabel();
		argileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		argileIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("brickSmall.png")));
		argileRes.add(argileIcon);
		Component verticalStrut2 = Box.createVerticalStrut(10);
		argileRes.add(verticalStrut2);
		argileSpinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		argileSpinner.setModel(new SpinnerNumberModel(0, 0, (int) player.getResources().get(Resources.ARGILE), 1));
		argileRes.add(argileSpinner);
		allSpiner.add(argileSpinner);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		argileRes.add(verticalStrut_3);


		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_3);

		JPanel minRes = new JPanel();
		minRes.setOpaque(false);
		minRes.setToolTipText(Resources.MINERAI.toString());
		resPanel.add(minRes);
		minRes.setLayout(new BoxLayout(minRes, BoxLayout.Y_AXIS));

		JLabel minIcon = new JLabel();
		minIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		minIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("oreSmall.png")));
		minRes.add(minIcon);
		Component verticalStrut3 = Box.createVerticalStrut(10);
		minRes.add(verticalStrut3);
		minSpinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minSpinner.setModel(new SpinnerNumberModel(0, 0, (int) player.getResources().get(Resources.MINERAI), 1));
		minRes.add(minSpinner);
		allSpiner.add(minSpinner);
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		minRes.add(verticalStrut_4);


		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_2);

		JPanel mouRes = new JPanel();
		mouRes.setOpaque(false);
		mouRes.setToolTipText(Resources.MOUTON.toString());
		resPanel.add(mouRes);
		mouRes.setLayout(new BoxLayout(mouRes, BoxLayout.Y_AXIS));

		JLabel mouIcon = new JLabel();
		mouIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		mouIcon.setIcon(new ImageIcon(ClassLoader.getSystemResource("sheepSmall.png")));
		mouRes.add(mouIcon);
		Component verticalStrut4 = Box.createVerticalStrut(10);
		mouRes.add(verticalStrut4);
		mouSpinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mouSpinner.setModel(new SpinnerNumberModel(0, 0, (int) player.getResources().get(Resources.MOUTON), 1));
		mouRes.add(mouSpinner);
		allSpiner.add(mouSpinner);
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		mouRes.add(verticalStrut_5);


		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		resPanel.add(horizontalStrut_1);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Il faut rajouter encore");
		lblNewLabel.setForeground(textArroundColor);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblNewLabel);

		labelNum = new JLabel(max-7 + "");
		labelNum.setForeground(textArroundColor);
		labelNum.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(labelNum);

		JLabel lblNewLabel_2 = new JLabel("ressources");
		lblNewLabel_2.setForeground(textArroundColor);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);

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
			if ((max-7-totalAdded) == 0) {

				player.setResources(Resources.BOIS, player.getResources().get(Resources.BOIS) - Integer.parseInt(boisSpinner.getValue().toString()));
				player.setResources(Resources.BLE, player.getResources().get(Resources.BLE) - Integer.parseInt(wheatSpinner.getValue().toString()));
				player.setResources(Resources.MOUTON, player.getResources().get(Resources.MOUTON) - Integer.parseInt(mouSpinner.getValue().toString()));
				player.setResources(Resources.MINERAI, player.getResources().get(Resources.MINERAI) - Integer.parseInt(minSpinner.getValue().toString()));
				player.setResources(Resources.ARGILE, player.getResources().get(Resources.ARGILE) - Integer.parseInt(argileSpinner.getValue().toString()));
				guiView.updateView();
				player.setThiefPlay(false);
				dispose();
			}
		});
		for (JSpinner t : allSpiner) {
			t.addChangeListener(ae -> {
				updateView(player);
			});
		}
		updateView(player);
		guiView.updateView();
	}

	private void updateView(PlayerModel player) {
		totalAdded = Integer.parseInt(mouSpinner.getValue().toString()) + Integer.parseInt(minSpinner.getValue().toString()) + Integer.parseInt(argileSpinner.getValue().toString()) + Integer.parseInt(wheatSpinner.getValue().toString()) + Integer.parseInt(boisSpinner.getValue().toString());
		labelNum.setText(String.valueOf(max - 7 - totalAdded));

		for (JSpinner t : allSpiner) {
			if ((max - 7 - totalAdded) == 0) {
				if (Integer.parseInt(t.getValue().toString()) == 0) {
					t.setEnabled(false);
				} else {
					t.setModel(new SpinnerNumberModel(Integer.parseInt(t.getValue().toString()), 0, Integer.parseInt(t.getValue().toString()), 1));
					t.setOpaque(false);
					t.getEditor().setOpaque(false);
					((JSpinner.NumberEditor)t.getEditor()).getTextField().setOpaque(false);
					((JSpinner.NumberEditor)t.getEditor()).getTextField().setForeground(textArroundColor);
				}
			} else {
				t.setEnabled(true);
			}
		}

		if ((max - 7 - totalAdded) != 0) {
			argileSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(argileSpinner.getValue().toString()), 0, (int) player.getResources().get(Resources.ARGILE), 1));
			boisSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(boisSpinner.getValue().toString()), 0, (int) player.getResources().get(Resources.BOIS), 1));
			wheatSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(wheatSpinner.getValue().toString()), 0, (int) player.getResources().get(Resources.BLE), 1));
			minSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(minSpinner.getValue().toString()), 0, (int) player.getResources().get(Resources.MINERAI), 1));
			mouSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(mouSpinner.getValue().toString()), 0, (int) player.getResources().get(Resources.MOUTON), 1));

			mouSpinner.setOpaque(false);
			mouSpinner.getEditor().setOpaque(false);
			((JSpinner.NumberEditor)mouSpinner.getEditor()).getTextField().setOpaque(false);
			((JSpinner.NumberEditor)mouSpinner.getEditor()).getTextField().setForeground(textArroundColor);

			boisSpinner.setOpaque(false);
			boisSpinner.getEditor().setOpaque(false);
			((JSpinner.NumberEditor)boisSpinner.getEditor()).getTextField().setOpaque(false);
			((JSpinner.NumberEditor)boisSpinner.getEditor()).getTextField().setForeground(textArroundColor);

			minSpinner.setOpaque(false);
			minSpinner.getEditor().setOpaque(false);
			((JSpinner.NumberEditor)minSpinner.getEditor()).getTextField().setOpaque(false);
			((JSpinner.NumberEditor)minSpinner.getEditor()).getTextField().setForeground(textArroundColor);

			wheatSpinner.setOpaque(false);
			wheatSpinner.getEditor().setOpaque(false);
			((JSpinner.NumberEditor)wheatSpinner.getEditor()).getTextField().setOpaque(false);
			((JSpinner.NumberEditor)wheatSpinner.getEditor()).getTextField().setForeground(textArroundColor);

			argileSpinner.setOpaque(false);
			argileSpinner.getEditor().setOpaque(false);
			((JSpinner.NumberEditor)argileSpinner.getEditor()).getTextField().setOpaque(false);
			((JSpinner.NumberEditor)argileSpinner.getEditor()).getTextField().setForeground(textArroundColor);

		}

		if (player.getResources().get(Resources.ARGILE) < 1) {
			argileSpinner.setEnabled(false);
		}
		if (player.getResources().get(Resources.BLE) < 1) {
			wheatSpinner.setEnabled(false);
		}
		if (player.getResources().get(Resources.BOIS) < 1) {
			boisSpinner.setEnabled(false);
		}
		if (player.getResources().get(Resources.MOUTON) < 1) {
			mouSpinner.setEnabled(false);
		}
		if (player.getResources().get(Resources.MINERAI) < 1) {
			minSpinner.setEnabled(false);
		}
		repaint();
	}

	public Resources getRes() {
		return res;
	}
}

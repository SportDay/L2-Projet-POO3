package l2.poo3.controller.gui;

import l2.poo3.view.gui.GuiView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuiViewAdapter extends WindowAdapter {

    GuiController frame;

    public GuiViewAdapter(GuiController frame){
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        if(frame.isEnd()){
            frame.getGuiView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else {
            int res = JOptionPane.showConfirmDialog(frame.getGuiView(), "Vous êtes sur le point de fermer le jeu\nEtes-vous sur?", "Groupe 48 - Fermeture du jeu", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon(ClassLoader.getSystemResource("warning.png")));
            if (res == JOptionPane.YES_OPTION) {
                frame.getGuiView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }
}

package l2.poo3.controller.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuiEndGameWinAdapter extends WindowAdapter {

    JDialog frame;

    JFrame parentFrame;
    public GuiEndGameWinAdapter(JDialog frame, JFrame parentFrame){
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));
    }
}

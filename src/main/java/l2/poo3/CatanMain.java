package l2.poo3;

import l2.poo3.controller.gui.GuiController;
import l2.poo3.controller.terminal.TerminalController;
import l2.poo3.model.PlateauxModel;
import l2.poo3.view.gui.GuiSettingsView;
import l2.poo3.view.gui.GuiView;
import l2.poo3.view.terminal.TerminalView;

import javax.swing.*;


public class CatanMain {
    public static void main(String[] args) {
        PlateauxModel platModel = new PlateauxModel();
        if (args.length == 0) {
            Object[] options = {"Mode texte", "Mode graphique"};
            int res = JOptionPane.showOptionDialog(new JDialog(), "Veuillez choisir le type d'affichage", "Groupe 48 - Les Colons de Catane", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(ClassLoader.getSystemResource("icons8-question-32.png")), options, options[1]);
            if (res == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(new JDialog(), "Si vous avez lancé le programme à partir d'un terminal,\nvous avez rien à faire juste cliquer sur ok.\n" + "Si vous avez lancé le programme en double-cliquant\nmerci d'ecrire ceci dans un terminal ouvert dans un dossier contenant le programme\n \"java -jar LesColonsDeCataneGroupe48.jar text\"", "Groupe 48 - Information", JOptionPane.WARNING_MESSAGE,new ImageIcon(ClassLoader.getSystemResource("icons8-question-32.png")));
                TerminalView terView = new TerminalView();
                TerminalController terController = new TerminalController(platModel, terView);
                terController.start();
            }else if (res == JOptionPane.NO_OPTION){
                GuiView guiView = new GuiView();
                GuiController guiController = new GuiController(platModel, guiView, new GuiSettingsView());
                guiController.start();
            }
        } else if (args.length > 0) {
            String arg1 = args[0];
            if (arg1.equalsIgnoreCase("gui") || arg1.equalsIgnoreCase("graph")) {
                GuiView guiView = new GuiView();
                GuiController guiController = new GuiController(platModel, guiView, new GuiSettingsView());
                guiController.start();
            } else if (arg1.equalsIgnoreCase("text") || arg1.equalsIgnoreCase("terminal")) {
                TerminalView terView = new TerminalView();
                TerminalController terController = new TerminalController(platModel, terView);
                terController.start();
            }
        }
    }

}

package nlu.fit.leanhduc.view.component.menu;


import nlu.fit.leanhduc.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {
    private JMenuItem exitItem, aboutItem, keyItem;
    private ActionListener exitListener, aboutListener;
    private Frame frameContainer;
    MainController mainController;

    public Menu(Frame frameContainer, MainController mainController) {
        this.frameContainer = frameContainer;
        this.mainController = mainController;
        init();
    }

    private void init() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        keyItem = new JMenuItem("Tạo khóa");
        keyItem.setMnemonic(KeyEvent.VK_K);
        keyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
        fileMenu.add(keyItem);

        aboutItem = new JMenuItem("Tác giả");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        fileMenu.add(aboutItem);

        fileMenu.addSeparator();

        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_S);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(exitListener);
        fileMenu.add(exitItem);

 
        this.setActionDialog();
        this.add(fileMenu);
    }

    public void setActionDialog() {
        exitItem.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,
                    "Do you want to exit",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            switch (response) {
                case JOptionPane.YES_OPTION -> {
                    frameContainer.dispose();
                }
                case JOptionPane.NO_OPTION -> {
                    JOptionPane.showMessageDialog(null, "You selected No!");
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Dialog was closed without selection.");
                }
            }
        });

    }
}

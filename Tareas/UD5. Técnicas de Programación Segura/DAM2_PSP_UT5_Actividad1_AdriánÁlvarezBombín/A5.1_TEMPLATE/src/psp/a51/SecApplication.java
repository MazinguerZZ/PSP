package psp.a51;

import psp.a51.sec.UsersManager;
import psp.a51.ui.MainFrame;

import javax.swing.*;


public class SecApplication {

    public static void main(String[] args) {

        // Arranque de la aplicación
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
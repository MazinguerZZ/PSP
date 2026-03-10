package psp.a51.ui;

import psp.a51.model.User;
import psp.a51.sec.UsersManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class LoginFrame extends JFrame {

    private final UsersManager usersManager;
    private Consumer<User> onLoginSuccess;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(UsersManager usersManager, Consumer<User> onLoginSuccess) {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 180);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        initComponents();

        this.usersManager= usersManager;
        this.onLoginSuccess= onLoginSuccess;
    }

    private void initComponents() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);

        // Campo usuario
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Etiqueta contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);

        // Campo contraseña
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel();

        JButton loginButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        // Acción Aceptar
        loginButton.addActionListener(this::loginAction);

        // Acción Cancelar
        cancelButton.addActionListener(e -> System.exit(0));

        // Añadir paneles al frame
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loginAction(ActionEvent e) {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        // Validación de haber introducido algún dato en el username y la contraseña
        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe introducir usuario y contraseña",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            // Validación del username & password
            if (usersManager.checkUserPassword(username, password)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Login correcto",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                User user = new User(username, usersManager.getRole(username));
                onLoginSuccess.accept(user);

                dispose(); // cerrar ventana
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuario o contraseña incorrectos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Limpiar contraseña por seguridad
        java.util.Arrays.fill(password, '\0');
    }

}

package psp.a51.ui;

import psp.a51.model.Roles;
import psp.a51.model.User;
import psp.a51.sec.UsersManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnAdmin;
    private JLabel lblStatus;

    private final UsersManager usersManager;
    private User loggedUser;   // usuario autenticado

    public MainFrame() {
        try {
            usersManager= new UsersManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setTitle("Aplicación segura - PSP");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        updateUserStatus();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3, 100, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblStatus = new JLabel();
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(lblStatus, BorderLayout.NORTH);

        btnLogin = new JButton("Login");
        btnRegister = new JButton("Registro");
        btnAdmin = new JButton("Opción Administrador");

        btnAdmin.setVisible(false); // solo se hace visible tras el login de un usuario con rol admin

        btnLogin.addActionListener(e -> openLogin());
        btnRegister.addActionListener(e -> openRegister());
        btnAdmin.addActionListener(e -> openAdminWindow());

        panel.add(btnLogin);
        panel.add(btnRegister);
        panel.add(btnAdmin);

        add(panel);
    }

    private void updateUserStatus() {
        if (loggedUser == null) {
            lblStatus.setText("No hay ningún usuario logado");
            lblStatus.setForeground(Color.RED);
            btnAdmin.setVisible(false);
        } else {
            lblStatus.setText(
                    "Usuario: " + loggedUser.getUsername() +
                            " (" + loggedUser.getRole() + ")"
            );
            lblStatus.setForeground(new Color(0, 128, 0));

            if (loggedUser.getRole() == Roles.ADMIN) {
                btnAdmin.setVisible(true);
            }
        }
    }

    private void openLogin() {
        LoginFrame loginFrame = new LoginFrame(usersManager, this::onLoginSuccess);
        loginFrame.setVisible(true);
    }

    private void openRegister() {
        RegisterFrame registerFrame = new RegisterFrame(usersManager);
        registerFrame.setVisible(true);
    }

    private void onLoginSuccess(User user) {
        this.loggedUser = user;

        JOptionPane.showMessageDialog(
                this,
                "Bienvenido " + user.getUsername(),
                "Login correcto",
                JOptionPane.INFORMATION_MESSAGE
        );

        if (user.getRole() == Roles.ADMIN) {
            btnAdmin.setVisible(true);
        } else {
            btnAdmin.setVisible(false);
        }

        updateUserStatus();
    }

    private void openAdminWindow() {
        JOptionPane.showMessageDialog(
                this,
                "Esta opción solo está disponible para administradores",
                "Zona restringida",
                JOptionPane.WARNING_MESSAGE
        );
    }

}



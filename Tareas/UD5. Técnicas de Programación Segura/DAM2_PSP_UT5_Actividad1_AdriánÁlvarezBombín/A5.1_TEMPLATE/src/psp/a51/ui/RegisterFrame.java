package psp.a51.ui;

import psp.a51.model.Roles;
import psp.a51.sec.UsersManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RegisterFrame extends JFrame {

    private final UsersManager usersManager;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JRadioButton userRadio;
    private JRadioButton adminRadio;

    // Política de contraseña:
    // - Mínimo 8 caracteres
    // - Al menos una mayúscula
    // - Al menos una minúscula
    // - Al menos un número
    // - Al menos un carácter especial
    private static final Pattern PASSWORD_POLICY = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    public RegisterFrame(UsersManager usersManager) {
        setTitle("Registro de usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();

        this.usersManager= usersManager;
    }

    private void initComponents() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(18);
        panel.add(usernameField, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(18);
        panel.add(passwordField, gbc);

        // Repetir contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Repetir contraseña:"), gbc);

        gbc.gridx = 1;
        repeatPasswordField = new JPasswordField(18);
        panel.add(repeatPasswordField, gbc);

        // Rol
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Rol:"), gbc);

        gbc.gridx = 1;
        userRadio = new JRadioButton(Roles.USER.getRole(), true);
        adminRadio = new JRadioButton(Roles.ADMIN.getRole());

        ButtonGroup group = new ButtonGroup();
        group.add(userRadio);
        group.add(adminRadio);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rolePanel.add(userRadio);
        rolePanel.add(adminRadio);

        panel.add(rolePanel, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();

        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        acceptButton.addActionListener(this::registerAction);
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void registerAction(ActionEvent e) {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();
        char[] repeatPassword = repeatPasswordField.getPassword();

        try {
            // Validaciones
            if (username.isEmpty()) {
                showError("El nombre de usuario no puede estar vacío");
                return;
            }

            if (!Arrays.equals(password, repeatPassword)) {
                showError("Las contraseñas no coinciden");
                return;
            }

            if (!PASSWORD_POLICY.matcher(String.valueOf(password)).matches()) {
                showError(
                        "La contraseña no cumple la política:\n" +
                                "- Mínimo 8 caracteres\n" +
                                "- Mayúscula, minúscula, número y símbolo"
                );
                return;
            }

            Roles role = userRadio.isSelected() ? Roles.USER : Roles.ADMIN;

            // Aquí iría la lógica real:
            // - Hash + salt
            // - Almacenamiento seguro
            // - Asignación de rol
            usersManager.registerUser(username, password, role);

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            // Limpiar contraseñas de memoria
            Arrays.fill(password, '\0');
            Arrays.fill(repeatPassword, '\0');
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

}

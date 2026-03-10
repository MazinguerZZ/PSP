package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VentanaConfiguracion representa un cuadro de diálogo para configurar
 * los parámetros iniciales del cliente de chat.
 *
 * Permite al usuario ingresar su nombre de usuario, el host del servidor
 * y el puerto de conexión antes de iniciar el cliente.
 */
public class VentanaConfiguracion extends JDialog {

    /**
     * Campo de texto para ingresar el nombre de usuario.
     */
    private JTextField tfUsuario;

    /**
     * Campo de texto para ingresar la dirección del host del servidor.
     */
    private JTextField tfHost;

    /**
     * Campo de texto para ingresar el puerto de conexión.
     */
    private JTextField tfPuerto;

    /**
     * Crea un nuevo cuadro de diálogo de configuración.
     *
     * @param padre JFrame padre que bloquea este diálogo
     */
    public VentanaConfiguracion(JFrame padre) {
        super(padre, "Configuración inicial", true);

        JLabel lbUsuario = new JLabel("Usuario:");
        JLabel lbHost = new JLabel("Host:");
        JLabel lbPuerto = new JLabel("Puerto:");

        tfUsuario = new JTextField();
        tfHost = new JTextField("localhost");
        tfPuerto = new JTextField("5000");

        JButton btAceptar = new JButton("Aceptar");
        btAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setLayout(new GridLayout(4, 2, 10, 10));
        add(lbUsuario); add(tfUsuario);
        add(lbHost); add(tfHost);
        add(lbPuerto); add(tfPuerto);
        add(new JLabel()); add(btAceptar);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Devuelve el nombre de usuario ingresado.
     *
     * @return Nombre de usuario
     */
    public String getUsuario() {
        return tfUsuario.getText();
    }

    /**
     * Devuelve la dirección del host ingresada.
     *
     * @return Host del servidor
     */
    public String getHost() {
        return tfHost.getText();
    }

    /**
     * Devuelve el puerto de conexión ingresado.
     *
     * @return Puerto del servidor
     */
    public int getPuerto() {
        return Integer.parseInt(tfPuerto.getText());
    }
}

package chat;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * VentanaChat representa la interfaz gráfica del cliente de chat.
 * Permite mostrar mensajes, escribir mensajes nuevos y seleccionar destinatarios.
 *
 * Contiene un área de texto para los mensajes, un campo para escribir mensajes,
 * un JComboBox para seleccionar destinatarios y un botón para enviar.
 */
public class VentanaChat extends JFrame {

    /**
     * Área de texto donde se muestran los mensajes recibidos y enviados.
     */
    JTextArea areaMensajes;

    /**
     * Campo de texto donde el usuario escribe los mensajes a enviar.
     */
    JTextField campoMensaje;

    /**
     * ComboBox para seleccionar el destinatario del mensaje.
     * Contiene "TODOS" por defecto para mensajes a todos los clientes.
     */
    JComboBox<String> comboUsuarios;

    /**
     * Botón para enviar el mensaje escrito en el campo de texto.
     */
    JButton botonEnviar;

    /**
     * Crea una nueva ventana de chat con los componentes de interfaz.
     * 
     * @param enviar ActionListener que se ejecuta al presionar el botón Enviar
     */
    public VentanaChat(ActionListener enviar) {
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);

        comboUsuarios = new JComboBox<>();
        comboUsuarios.addItem("TODOS");

        campoMensaje = new JTextField();
        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(enviar);

        JPanel abajo = new JPanel(new BorderLayout(5, 5));
        abajo.add(comboUsuarios, BorderLayout.NORTH);
        abajo.add(campoMensaje, BorderLayout.CENTER);
        abajo.add(botonEnviar, BorderLayout.EAST);

        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);
    }

    /**
     * Muestra un mensaje en el área de mensajes.
     * 
     * @param texto Mensaje a mostrar
     */
    public void mostrarMensaje(String texto) {
        areaMensajes.append(texto + "\n");
    }

    /**
     * Devuelve el destinatario seleccionado en el JComboBox.
     * 
     * @return Nombre del destinatario seleccionado
     */
    public String getDestinatario() {
        return (String) comboUsuarios.getSelectedItem();
    }

    /**
     * Agrega un nuevo usuario al JComboBox de destinatarios.
     * 
     * @param usuario Nombre del usuario a agregar
     */
    public void addUsuario(String usuario) {
        comboUsuarios.addItem(usuario);
    }
}

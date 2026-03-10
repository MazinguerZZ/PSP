package chat;

import java.io.Serializable;

/**
 * MensajeChat representa un mensaje enviado entre clientes en el chat.
 * Contiene información sobre el remitente, el destinatario, el nombre de usuario
 * y el contenido del mensaje.
 *
 * Los mensajes con destino -1 se consideran enviados a todos los clientes.
 */
public class MensajeChat implements Serializable {

    /**
     * Identificador del cliente que envía el mensaje.
     */
    public int origen;

    /**
     * Identificador del cliente destinatario.
     * -1 indica que el mensaje va dirigido a todos los clientes.
     */
    public int destino;

    /**
     * Nombre del usuario que envía el mensaje.
     */
    public String usuario;

    /**
     * Contenido del mensaje.
     */
    public String texto;

    /**
     * Crea un nuevo objeto MensajeChat con los datos proporcionados.
     *
     * @param origen Identificador del cliente que envía el mensaje
     * @param destino Identificador del cliente destinatario (-1 = todos)
     * @param usuario Nombre del usuario que envía el mensaje
     * @param texto Contenido del mensaje
     */
    public MensajeChat(int origen, int destino, String usuario, String texto) {
        this.origen = origen;
        this.destino = destino;
        this.usuario = usuario;
        this.texto = texto;
    }
}

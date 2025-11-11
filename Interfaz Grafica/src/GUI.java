import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *Clase GUI
 *Muestra la estructuta que deberia tener una Ventana en Java con la libreria
 *Swing, contiene una etiqueta, un caja de texto y un boton, que tiene la
 *accion de mostrar el texto en la caja por una ventana de mensaje.
 */

public class GUI extends JFrame implements ActionListener {
    //Variables Absolutas para la clase.
    private JLabel texto;
    private JTextField caja;
    private JButton boton;
    /**
     * Constructor GUI
     * El constructor inicializa la aplicacion con el constructor super()
     * con el metodo configurarGUI() se configuran las caracteristicas de interfaz
     * el metodo inicializa conponentes necesarios
     *
     */
    public GUI() {
        super();
        configurarGUI();
        inicializarComponentes();

    }

    private void configurarGUI() {
        /**
         * Metodo configurarGUI()
         * Metodo que inicia el estado de la GUI
         */
        this.setTitle("Esta Es Una Ventana");                   // colocamos titulo a la ventana
        this.setSize(310, 210);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(true);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        this.setVisible(true);
    }

    private void inicializarComponentes() {
        /**
         * Metodo inicializarComponentes()
         * Metodo que genera los componentes necesarios para nuestra GUI.
         */

        texto = new JLabel();
        caja = new JTextField();
        boton = new JButton();
        // configuramos los componentes

        texto.setText("Inserte Nombre");    // colocamos un texto a la etiqueta
        texto.setBounds(50, 50, 100, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        caja.setBounds(150, 50, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        boton.setText("Mostrar Mensaje");   // colocamos un texto al boton
        boton.setBounds(50, 100, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase

        // adicionamos los componentes a la ventana

        this.add(texto);
        this.add(caja);
        this.add(boton);

    }

    @Override

    public void actionPerformed(ActionEvent e) {
        /**
         * Metodo actionPerformed()
         * Ejecutara su contenido al producirse un cambio en el action listener del boton.
         */
        String nombre = caja.getText();                                 // obtenemos el contenido de la caja de texto
        JOptionPane.showMessageDialog(this, "Hola " + nombre + ".");    // mostramos un mensaje (frame, mensaje)

    }

    public static void main(String[] args) {

        GUI usuario = new GUI();      // creamos una ventana

    }

}
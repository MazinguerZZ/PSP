package psp.procesos.a13;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal que gestiona las búsquedas de texto en ficheros
 * Permite buscar las palabras CASA, PERRO o COCHE en ficheros del 1 al 5
 * Las búsquedas se ejecutan en paralelo sin bloquear la interfaz
 *
 * @author Adrián Álvarez Bombín
 * @version 1.0
 */
public class GestorBusqueda extends BuscadorTexto implements ActionListener {
    private Map<Integer, Process> procesosActivos = new HashMap<>();

    /**
     * Constructor de la clase GestorBusqueda
     * Inicializa la interfaz y configura los listeners de los botones
     */
    public GestorBusqueda() {
        super();
        btn1.addActionListener(this);
        btn2.addActionListener(this);
    }

    /**
     * Método principal que inicia la aplicación
     * @param args Argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        new GestorBusqueda();
    }

    /**
     * Maneja los eventos de los botones de la interfaz
     * @param e Evento de acción generado por los componentes
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            String opcion = textField1.getText().toUpperCase();
            String ficheroTexto = textField2.getText();

            try {
                int fichero = Integer.parseInt(ficheroTexto);

                // Verificar si ya hay búsqueda en este fichero
                if (procesosActivos.containsKey(fichero)) {
                    JOptionPane.showMessageDialog(this, "Ya hay una búsqueda en el fichero " + fichero);
                    return;
                }

                if (opcion.equals("CASA") || opcion.equals("PERRO") || opcion.equals("COCHE")) {
                    if (fichero >= 1 && fichero <= 5) {
                        new Thread(() -> {
                            realizarBusquedaParalela(opcion, fichero);
                        }).start();
                    } else {
                        JOptionPane.showMessageDialog(this, "El fichero debe ser del 1 al 5");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Opción no válida. Use: CASA, PERRO o COCHE");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El fichero debe ser un número");
            }
        }

        if (e.getSource() == btn2) {
            // Cancelar todas las búsquedas
            for (Process proceso : procesosActivos.values()) {
                if (proceso != null) {
                    proceso.destroy();
                }
            }
            procesosActivos.clear();
            JOptionPane.showMessageDialog(this, "Todas las búsquedas canceladas");
        }
    }

    /**
     * Ejecuta la búsqueda del texto en el fichero especificado de forma paralela
     * @param opcion Texto a buscar (CASA, PERRO o COCHE)
     * @param fichero Número del fichero donde buscar (1-5)
     */
    private void realizarBusquedaParalela(String opcion, int fichero) {
        Process p = null;
        int contar = 0;

        try {
            p = new ProcessBuilder("cmd", "/c", "type", "texto" + fichero + ".txt").start();
            procesosActivos.put(fichero, p);

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.toUpperCase().contains(opcion)) {
                    contar++;
                }
            }

            final int resultadoFinal = contar;
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                        this,
                        "Valores encontrados: " + resultadoFinal + "\nOpción -> " + opcion + "\nFichero -> " + fichero,
                        "Búsqueda",
                        JOptionPane.INFORMATION_MESSAGE
                );
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            procesosActivos.remove(fichero);
        }
    }
}
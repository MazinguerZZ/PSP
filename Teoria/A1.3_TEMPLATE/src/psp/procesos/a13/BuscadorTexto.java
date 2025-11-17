package psp.procesos.a13;

import javax.swing.*;

public class BuscadorTexto extends JFrame {

    JButton btn1, btn2; // ❌ quitamos static
    JTextField textField1, textField2;
    JLabel text1, text2, text3, text4;

    public BuscadorTexto() {
        super("Buscador Texto");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        componentes();
        this.setVisible(true);
    }

    public void componentes() {
        btn1 = new JButton("Buscar");
        btn2 = new JButton("Cancelar");
        textField1 = new JTextField();
        textField2 = new JTextField();
        text1 = new JLabel("<html><h2><i>Buscar texto fichero</i></h2></html>");
        text2 = new JLabel("<html>Opciones búsqueda: CASA, PERRO, COCHE<br>Ficheros: 1 a 5</html>");
        text3 = new JLabel("Introduzca opción:");
        text4 = new JLabel("<html><strong>Introduzca fichero</strong></html>");

        text1.setBounds(160, 40, 250, 50);
        text2.setBounds(130, 90, 290, 50);
        text3.setBounds(130, 160, 150, 30);
        text4.setBounds(130, 210, 200, 30);

        textField1.setBounds(300, 160, 100, 30);
        textField2.setBounds(300, 210, 100, 30);

        btn1.setBounds(160, 300, 120, 30);
        btn2.setBounds(300, 300, 120, 30);

        this.add(text1);
        this.add(text2);
        this.add(text3);
        this.add(text4);
        this.add(textField1);
        this.add(textField2);
        this.add(btn1);
        this.add(btn2);
    }

    public void buscarTexto(String idFichero, String opcionBuscar) {
        String var1 = textField1.getText();
        String var2 = textField2.getText();
        System.out.println("Buscar: " + var1 + " en fichero: " + var2);
    }
}


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Ventana extends javax.swing.JFrame implements ActionListener {

    private JLabel valor = new JLabel("0");
    private double primerValor = 0;

    public static void main(String args[]) {
        // Definir el tipo de fuente, estilo y tamaño a todos los botones y campos
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Label.font", new Font("Arial", Font.BOLD, 30));
        new Ventana();
    }

    Ventana() {
        super("Calculadora Simple");

        estilizarCampo();

        add(Contenedor());

        setSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel Botones() {
        JPanel botones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton operaciones[] = new JButton[9];
        JButton numeros[] = new JButton[10];
        JButton punto = Boton(new JButton(), ".");
        JButton resultado = Boton(new JButton(), "=");
        int cont = 0;

        /*
         * Operadores
         * lenguaje Unicode para mostrar el valor de x elevado a la x 02B8
         * lenguaje Unicode para mostrar el valor de x al cuadrado u00B2
         * lenguaje Unicode para mostrar raiz cuadrada de x u221A
         */
        String op[] = { "C", "CE", "%", "x\u02B8", "x\u00B2", "\u221Ax", "/", "X", "+", "-" };
        ArrayList<String> operadores = new ArrayList<String>();
        for (String e : op)
            operadores.add(e);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 30;
        gbc.ipady = 30;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        for (int i = 0; i <= 6; i++) {

            switch (i) {
                case 1:
                    gbc.gridx = 2;
                    gbc.gridwidth = 1;
                    break;
                case 2:
                    break;
                case 3:
                    gbc.gridx = 0;
                    gbc.gridy++;
                    break;
            }

            operaciones[i] = Boton(operaciones[i], operadores.get(0));
            botones.add(operaciones[i], gbc);
            operadores.remove(0);
            cont = i;
            gbc.gridx++;
        }

        gbc.gridx = 0;
        gbc.gridy = 2;

        for (int i = 9; i >= 0; i--) {
            numeros[i] = Boton(numeros[i], i + "");
            botones.add(numeros[i], gbc);

            gbc.gridx++;

            if (gbc.gridx == 3) {
                operaciones[cont] = Boton(operaciones[cont], operadores.get(0));
                botones.add(operaciones[cont], gbc);

                operadores.remove(0);
                gbc.gridx = 0;
                gbc.gridy++;
                cont++;

                // Posicionar el numero 0 en el centro de los numeros
                if (gbc.gridy == 5)
                    gbc.gridx++;
            }
        }

        gbc.gridx = 0;
        botones.add(punto, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        botones.add(resultado, gbc);

        return botones;
    }

    private JPanel Contenedor() {
        JPanel contenedor = new JPanel(new BorderLayout());

        contenedor.add(Botones(), BorderLayout.CENTER);
        contenedor.add(valor, BorderLayout.NORTH);

        return contenedor;
    }

    public void actionPerformed(ActionEvent e) {
        String val = ((JButton) e.getSource()).getName();

        if (Character.isDigit(val.toCharArray()[0])) {

            if (valor.getText() != "0") {
                String concatenar = valor.getText();
                valor.setText(concatenar += val);
            } else {
                valor.setText(val);
            }

        } else {
            if (val == "CE") {

                valor.setText("0");

            } else if (val == "C") {

                valor.setText("0");
                primerValor = 0;

            } else {
                if (primerValor != 0) {

                }
                primerValor = Double.parseDouble(valor.getText());
            }
        }
    }

    // Todos los botones utilizan la misma configuracion, asi que se aplico de
    // manera global
    private JButton Boton(JButton boton, String nombre) {
        boton = new JButton(nombre);
        boton.setName(nombre);
        boton.addActionListener(this);
        return boton;
    }

    private void estilizarCampo() {
        valor.setPreferredSize(new Dimension(getSize().width, 80));
        valor.setHorizontalAlignment(JLabel.RIGHT);
        valor.setOpaque(true);
    }
}
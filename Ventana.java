
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ventana extends javax.swing.JFrame implements ActionListener {

    private JLabel valor = new JLabel("0");
    private double primerValor = 0;
    private String operador = null;
    private boolean resultado = false;

    public static void main(String args[]) {
        // Definir el tipo de fuente, estilo y tamaño a todos los botones y campos
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Label.font", new Font("Arial", Font.BOLD, 30));
        new Ventana();
    }

    Ventana() {
        super("Calculadora Simple");

        EstilizarCampo();

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
         * lenguaje Unicode para mostrar el valor de x elevado a la y 02B8
         * lenguaje Unicode para mostrar el valor de x al cuadrado u00B2
         * lenguaje Unicode para mostrar raiz cuadrada de x u221A
         */
        String op[] = { "C", "CE", "%", "x\u02B8", "x\u00B2", "\u221Ax", "/", "X", "+", "-" };
        ArrayList<String> operadores = new ArrayList<String>();
        for (String e : op)
            operadores.add(e);

        // Valores iniciales y predeterminado para acomodar los objetos en la tabla
        // tambien contiene los valores predeterminados del espaciado y tamaño de las
        // celdas
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 30;
        gbc.ipady = 30;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Iteracion para agregar los primero 6 operadores a las celdas
        for (int i = 0; i <= 6; i++) {

            switch (i) {
                case 1:
                    gbc.gridx = 2;
                    gbc.gridwidth = 1;
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

        gbc.gridx = 3;
        gbc.gridy = 2;

        // Iteracion para agregar los numero de la calcul
        for (int i = 9; i >= 0; i--) {

            // Condicion para agregar en la cuarta columna los operadores restantes
            // tambien suma otra posicion en tabla en el eje y, es decir,
            // se agrega otra columna
            if (gbc.gridx == 3 && cont < 9) {
                operaciones[cont] = Boton(operaciones[cont], operadores.get(0));
                botones.add(operaciones[cont], gbc);

                operadores.remove(0);
                gbc.gridx--;
                cont++;
            }

            numeros[i] = Boton(numeros[i], i + "");
            botones.add(numeros[i], gbc);
            gbc.gridx--;

            if (gbc.gridx < 0) {
                gbc.gridx = 3;
                gbc.gridy++;
            }

            // Posicionar el numero 0 en el centro de los numeros
            if (gbc.gridy == 5)
                gbc.gridx = 1;
        }

        gbc.gridx = 0;
        botones.add(punto, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        botones.add(resultado, gbc);

        return botones;
    }

    // Panel exclusivo para acomodor el campo de texto y los botones
    private JPanel Contenedor() {
        JPanel contenedor = new JPanel(new BorderLayout());

        contenedor.add(Botones(), BorderLayout.CENTER);
        contenedor.add(valor, BorderLayout.NORTH);

        return contenedor;
    }

    public void actionPerformed(ActionEvent e) {

        String val = ((JButton) e.getSource()).getName();

        if (Character.isDigit(val.toCharArray()[0])) {

            if (resultado) {
                valor.setText("0");
                primerValor = 0;
                resultado = false;
            }
            // Se seguiran concatenando los valores al
            // campo texto hasta que se presiones un operador0

            if (valor.getText() != "0") {
                String concatenar = valor.getText();
                valor.setText(concatenar += val);
            } else {
                valor.setText(val);
            }

        } else {

            if (val == "CE") {
                // Limpia el valor introducido
                valor.setText("0");

            } else if (val == "C") {
                // Limpia todo,valor guardado y
                // el campo texto dejandolo en 0
                valor.setText("0");
                primerValor = 0;

            } else if (val == "." && !valor.getText().contains(".")) {
                // Agregar el punto decimal cuando se presione el boton y a su vez verificar que
                // no lo tengas

                String concatenar = valor.getText();
                valor.setText(concatenar += ".");

            } else if (valor.getText().contains(".") && val == ".") {
                JOptionPane.showMessageDialog(null,
                        "Ya existe un punto decimal",
                        "Advertencia",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Se guarda el operado presionado en la variable operador
                operador = (val != "=") ? val : operador;

                // Se guardo el numero escrito en la variable primerValor
                if (primerValor == 0 ||
                        operador.compareTo("x\u00B2") == 0 ||
                        operador.compareTo("\u221Ax") == 0)
                    primerValor = Double.parseDouble(valor.getText());

                // Operadores de elevado al cuadrado y raiz cuadrada que no requieren de un
                // segundo valor
                if (operador.compareTo("x\u00B2") == 0 ||
                        operador.compareTo("\u221Ax") == 0 ||
                        operador.compareTo("%") == 0) {

                    valor.setText(new Operaciones().RealizarOperacion(operador, primerValor, 0) + "");

                } else if (val == "=") {
                    valor.setText(new Operaciones().RealizarOperacion(operador, primerValor,
                            Double.parseDouble(valor.getText())) + "");

                    resultado = true;

                    // Se guarda nuevamente el valor en caso de que el usuario quiera seguir
                    // realizando operaciones
                    primerValor = Double.parseDouble(valor.getText());
                } else {
                    valor.setText("0");
                }
            }

            valor.setText((valor.getText() == "Infinity") ? "\u221E" : valor.getText());
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

    // Estiliza el campo de texto que muestra los numeros y el resultado
    private void EstilizarCampo() {
        valor.setPreferredSize(new Dimension(getSize().width, 80));
        valor.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        valor.setHorizontalAlignment(JLabel.RIGHT);
        valor.setOpaque(true);
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class PiedraPapelTijera extends JFrame {
    private JLabel lblResultado, lblPuntaje, lblInstruccion;
    private int puntosUsuario = 0;
    private int puntosPC = 0;
    private final String[] opciones = {"Piedra", "Papel", "Tijera"};

    public PiedraPapelTijera() {
        // Configuración básica de la ventana
        setTitle("Rock Paper Scissors Deluxe");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior: Título y Puntaje
        JPanel panelNorte = new JPanel(new GridLayout(2, 1));
        panelNorte.setBackground(new Color(44, 62, 80));

        lblInstruccion = new JLabel("¡Elige tu movimiento!", SwingConstants.CENTER);
        lblInstruccion.setForeground(Color.WHITE);
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 18));

        lblPuntaje = new JLabel("Usuario: 0 | PC: 0", SwingConstants.CENTER);
        lblPuntaje.setForeground(new Color(236, 240, 241));

        panelNorte.add(lblInstruccion);
        panelNorte.add(lblPuntaje);
        panelNorte.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel Central: Botones de Juego
        JPanel panelCentro = new JPanel(new GridLayout(1, 3, 15, 0));
        panelCentro.setBorder(new EmptyBorder(20, 40, 20, 40));

        JButton btnPiedra = crearBoton("Piedra", new Color(52, 152, 219));
        JButton btnPapel = crearBoton("Papel", new Color(46, 204, 113));
        JButton btnTijera = crearBoton("Tijera", new Color(231, 76, 60));

        panelCentro.add(btnPiedra);
        panelCentro.add(btnPapel);
        panelCentro.add(btnTijera);

        // Panel Inferior: Resultado
        lblResultado = new JLabel("Esperando tu jugada...", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.ITALIC, 16));
        lblResultado.setPreferredSize(new Dimension(500, 60));

        // Agregar paneles al Frame
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(lblResultado, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addActionListener(e -> jugar(texto));
        return btn;
    }

    private void jugar(String eleccionUsuario) {
        String eleccionPC = opciones[new Random().nextInt(3)];
        String resultado;

        if (eleccionUsuario.equals(eleccionPC)) {
            resultado = "¡Empate! Ambos eligieron " + eleccionUsuario;
            lblResultado.setForeground(Color.DARK_GRAY);
        } else if ((eleccionUsuario.equals("Piedra") && eleccionPC.equals("Tijera")) ||
                (eleccionUsuario.equals("Tijera") && eleccionPC.equals("Papel")) ||
                (eleccionUsuario.equals("Papel") && eleccionPC.equals("Piedra"))) {
            resultado = "¡Ganaste! " + eleccionUsuario + " vence a " + eleccionPC;
            lblResultado.setForeground(new Color(39, 174, 96));
            puntosUsuario++;
        } else {
            resultado = "Perdiste... " + eleccionPC + " vence a " + eleccionUsuario;
            lblResultado.setForeground(new Color(192, 57, 43));
            puntosPC++;
        }

        lblResultado.setText(resultado);
        lblPuntaje.setText("Usuario: " + puntosUsuario + " | PC: " + puntosPC);
    }

    public static void main(String[] args) {
        // Usar el Look and Feel del sistema para que se vea más moderno
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> new PiedraPapelTijera().setVisible(true));
    }
}
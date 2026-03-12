//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;

class Robot {
    private String nombre;
    private int energia;
    private int energiaMaxima;
    public String rutaImagen;

    public Robot(String nombre, int energia, String rutaImagen) {
        this.nombre = nombre;
        this.energia = energia;
        this.energiaMaxima = energia;
        this.rutaImagen = rutaImagen;
    }

    public void recibirDanio(int cantidad) {
        this.energia -= cantidad;
        if (this.energia < 0) this.energia = 0;
    }

    public void reparar(int cantidad) {
        this.energia += cantidad;
        // no permitimos energía superior al máximo
        if (this.energia > energiaMaxima) this.energia = energiaMaxima;
    }

    // Getters: La única forma de leer los datos "privados"
    public int getEnergia() { return energia; }
    public String getNombre() { return nombre; }
    public int getEnergiaMaxima() { return energiaMaxima; }

    public void realizarAccionEspecial(Robot objetivo) {}
}

class RobotArtillero extends Robot {
    public RobotArtillero(String nombre) {
        super(nombre, 100, "src/resources/artillero.png");
        //imagen sacada de: https://www.google.com/goto?url=CAESgwEBO6uMpfr25ohrmD6_bbfw9rh7soN0yIywORHEBu5DCoLUEnnhMnAAw9eZamHVISqVoVt-KzJlZsbs5B-03moeOVDQi0CvoG9fRAxbXr1U_4iBuuCrXBTHLLDIIgwZRSTLawo4MfaEbSsM4Wc8psIGD7vNMUEa9fq_lspLKqqzIvD65g==
    }

    @Override
    public void realizarAccionEspecial(Robot objetivo) {
        objetivo.recibirDanio(25);
    }
}

class RobotMedico extends Robot {
    public RobotMedico(String nombre) {
        super(nombre, 120, "src/resources/medico.png");
        //imagen sacada de: https://www.pngall.com/wp-content/uploads/15/Baymax-PNG-Image-HD.png
    }

    @Override
    public void realizarAccionEspecial(Robot objetivo) {
        this.reparar(20); // Usamos el método seguro de la clase padre
    }
}

public class Main extends JFrame {
    private Robot jugador;
    private Robot enemigo = new Robot("Dron IA", 80, "src/resources/dron.png");
    //imagen sacada de: https://www.google.com/goto?url=CAESYQE7q4ylPOmY-gQxfkQryI5NMh5dt7iodMWVPgZ245IiIh_09p5pocFprA80ycKk8c0GBE_ITf9N0bENC5O8LSKX90kuJNUiGlT7vkMjfNanewp6QnnnBUMMSaWaFZztJMQ=
    private JProgressBar barraJugador, barraEnemigo;
    private JLabel lblImgJugador, lblNombreJugador;
    private JTextArea areaTutor;

    public Main() {
        setTitle("Cyber-Arena: POO Academy");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        configurarUI();
        mostrarVentanaSeleccion();
        actualizarInterfaz();
    }

    private void mostrarVentanaSeleccion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JTextArea textoHerencia = new JTextArea(
                "📚 FUNDAMENTO: HERENCIA\n\n" +
                        "Hemos creado la clase padre 'Robot'. Esta contiene lo básico: nombre y energía.\n" +
                        "Ahora, al elegir una clase, el objeto HEREDARÁ todo de Robot pero le dará un\n" +
                        "uso único a sus funciones."
        );
        textoHerencia.setEditable(false);
        textoHerencia.setBackground(new Color(245, 245, 245));

        panel.add(new JLabel("¡Bienvenido a la Arena!"), BorderLayout.NORTH);
        panel.add(new JScrollPane(textoHerencia), BorderLayout.CENTER);

        String[] opciones = {"Robot Artillero (Heredero de Daño)", "Robot Médico (Heredero de Cura)"};
        int res = JOptionPane.showOptionDialog(this, panel, "Selector de Clases (POO)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (res == 1) {
            jugador = new RobotMedico("Doc-Bot");
            explicar("HERENCIA APLICADA", "Has creado un 'RobotMedico'. Aunque usa código de 'Robot', él redefine la acción especial para curar.");
        } else {
            jugador = new RobotArtillero("Blast-Bot");
            explicar("HERENCIA APLICADA", "Has creado un 'RobotArtillero'. Hereda el sistema de vida pero su acción especial resta energía al enemigo.");
        }
    }

    private void configurarUI() {
        JPanel panelArena = new JPanel(new GridLayout(1, 2));

        JPanel pJ = new JPanel(new BorderLayout());
        lblNombreJugador = new JLabel("---", SwingConstants.CENTER);
        lblImgJugador = new JLabel("Robot", SwingConstants.CENTER);
        barraJugador = new JProgressBar(0, 120);
        barraJugador.setStringPainted(true);
        pJ.add(lblNombreJugador, BorderLayout.NORTH);
        pJ.add(lblImgJugador, BorderLayout.CENTER);
        pJ.add(barraJugador, BorderLayout.SOUTH);

        JPanel pE = new JPanel(new BorderLayout());
        barraEnemigo = new JProgressBar(0, 120);
        barraEnemigo.setValue(enemigo.getEnergia());
        barraEnemigo.setStringPainted(true);
        pE.add(new JLabel("ENEMIGO: " + enemigo.getNombre(), SwingConstants.CENTER), BorderLayout.NORTH);
        pE.add(new JLabel(new ImageIcon(enemigo.rutaImagen)), BorderLayout.CENTER);
        pE.add(barraEnemigo, BorderLayout.SOUTH);

        panelArena.add(pJ);
        panelArena.add(pE);

        JPanel panelInferior = new JPanel(new BorderLayout());
        areaTutor = new JTextArea(8, 50);
        areaTutor.setEditable(false);
        areaTutor.setLineWrap(true);
        areaTutor.setWrapStyleWord(true);
        areaTutor.setFont(new Font("SansSerif", Font.PLAIN, 14));
        areaTutor.setBackground(new Color(255, 255, 220));

        JPanel panelBotones = new JPanel();
        JButton btnAtacar = new JButton("Ataque Básico");
        JButton btnEspecial = new JButton("Acción Especial");

        btnAtacar.addActionListener(e -> ejecutarTurno("basico"));
        btnEspecial.addActionListener(e -> ejecutarTurno("especial"));

        panelBotones.add(btnAtacar);
        panelBotones.add(btnEspecial);

        panelInferior.add(new JScrollPane(areaTutor), BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelArena, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void ejecutarTurno(String tipo) {
        if (tipo.equals("basico")) {
            enemigo.recibirDanio(10);
            explicar("ENCAPSULAMIENTO", "Invocamos 'enemigo.recibirDanio(10)'. \n" +
                    "Como la energía es PRIVADA, no podemos ponerla en -50 por error. \n" +
                    "El método interno se asegura de que el mínimo sea 0. ¡Eso es protección de datos!");
        } else {
            jugador.realizarAccionEspecial(enemigo);
            explicar("POLIMORFISMO", "Ambas clases usan el mismo nombre de método: 'realizarAccionEspecial'.\n" +
                    "Pero tu objeto decidió actuar según su clase específica. ¡Una misma orden, dos resultados!");
        }

        // Turno Enemigo
        if (enemigo.getEnergia() > 0) {
            jugador.recibirDanio(12);
        } else {
            JOptionPane.showMessageDialog(this, "¡Victoria! El Dron IA ha colapsado.");
            System.exit(0);
        }

        actualizarInterfaz();
    }

    private void explicar(String concepto, String texto) {
        areaTutor.setText("🎓 " + concepto + ":\n\n" + texto);
    }

    private void actualizarInterfaz() {
        lblNombreJugador.setText(jugador.getNombre());
        lblImgJugador.setIcon(new ImageIcon(jugador.rutaImagen));
        barraJugador.setMaximum(jugador.getEnergiaMaxima());
        barraJugador.setValue(jugador.getEnergia());
        barraEnemigo.setValue(enemigo.getEnergia());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
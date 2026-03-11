//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Robot {
    protected String nombre;
    protected int energia;
    protected String rutaImagen;

    public Robot(String nombre, int energia, String rutaImagen) {
        this.nombre = nombre;
        this.energia = energia;
        this.rutaImagen = rutaImagen;
    }

    public void atacar(Robot objetivo) {
        objetivo.energia -= 10;
        if (objetivo.energia < 0) objetivo.energia = 0;
    }

    public int getEnergia() { return energia; }
    public String getNombre() { return nombre; }
    public String getRutaImagen() { return rutaImagen; }
}

class RobotArtillero extends Robot {
    public RobotArtillero(String nombre) {
        super(nombre, 100, "src/resources/artillero.png");
        //sacado de : https://pngimg.com/uploads/terminator/terminator_PNG29.png
    }

    public void habilidadEspecial(Robot objetivo) {
        objetivo.energia -= 25;
        this.energia -= 10;
        if (objetivo.energia < 0) objetivo.energia = 0;
    }
}

public class Main extends JFrame {
    private Robot jugador;
    private Robot enemigo;
    private int turno = 1;

    private JProgressBar barraJugador, barraEnemigo;
    private JLabel lblTurno, lblImgJugador, lblImgEnemigo;
    private JTextArea areaLog;

    public Main() {
        setTitle("Cyber-Arena POO");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarJuego();
        configurarUI();
    }

    private void inicializarJuego() {
        jugador = new RobotArtillero("Terminator X");
        enemigo = new Robot("Dron Alfa", 80, "src/resources/dron.png");
        //sacado de: https://png.pngtree.com/png-vector/20240730/ourmid/pngtree-isolated-attack-drone-fixed-wings-grey-png-image_13300821.png
    }

    private void configurarUI() {
        lblTurno = new JLabel("Turno: " + turno, SwingConstants.CENTER);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTurno, BorderLayout.NORTH);

        JPanel panelBatalla = new JPanel(new GridLayout(1, 2));

        JPanel pJugador = new JPanel(new BorderLayout());
        barraJugador = new JProgressBar(0, 100);
        barraJugador.setValue(jugador.getEnergia());
        barraJugador.setForeground(Color.GREEN);
        barraJugador.setStringPainted(true);

        lblImgJugador = new JLabel(new ImageIcon(jugador.getRutaImagen()));
        lblImgJugador.setHorizontalAlignment(JLabel.CENTER);

        pJugador.add(barraJugador, BorderLayout.NORTH);
        pJugador.add(lblImgJugador, BorderLayout.CENTER);
        pJugador.add(new JLabel(jugador.getNombre(), SwingConstants.CENTER), BorderLayout.SOUTH);

        JPanel pEnemigo = new JPanel(new BorderLayout());
        barraEnemigo = new JProgressBar(0, 100);
        barraEnemigo.setValue(enemigo.getEnergia());
        barraEnemigo.setForeground(Color.RED);
        barraEnemigo.setStringPainted(true);

        lblImgEnemigo = new JLabel(new ImageIcon(enemigo.getRutaImagen()));
        lblImgEnemigo.setHorizontalAlignment(JLabel.CENTER);

        pEnemigo.add(barraEnemigo, BorderLayout.NORTH);
        pEnemigo.add(lblImgEnemigo, BorderLayout.CENTER);
        pEnemigo.add(new JLabel(enemigo.getNombre(), SwingConstants.CENTER), BorderLayout.SOUTH);

        panelBatalla.add(pJugador);
        panelBatalla.add(pEnemigo);
        add(panelBatalla, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAtacar = new JButton("Atacar");
        JButton btnEspecial = new JButton("Habilidad Especial");

        areaLog = new JTextArea(5, 30);
        areaLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaLog);

        btnAtacar.addActionListener(e -> ejecutarAccion("atacar"));
        btnEspecial.addActionListener(e -> ejecutarAccion("especial"));

        panelBotones.add(btnAtacar);
        panelBotones.add(btnEspecial);
        panelInferior.add(panelBotones, BorderLayout.NORTH);
        panelInferior.add(scrollLog, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void ejecutarAccion(String tipo) {
        if (tipo.equals("atacar")) {
            jugador.atacar(enemigo);
            areaLog.append("¡Atacaste al enemigo!\n");
        } else {
            ((RobotArtillero)jugador).habilidadEspecial(enemigo);
            areaLog.append("¡Usaste Habilidad Especial!\n");
        }

        actualizarBarras();

        if (enemigo.getEnergia() > 0) {
            enemigo.atacar(jugador);
            areaLog.append("El Dron te ha contraatacado.\n");
            turno++;
            lblTurno.setText("Turno: " + turno);
            actualizarBarras();
        } else {
            JOptionPane.showMessageDialog(this, "¡Victoria! El dron ha sido destruido.");
            System.exit(0);
        }
    }

    private void actualizarBarras() {
        barraJugador.setValue(jugador.getEnergia());
        barraEnemigo.setValue(enemigo.getEnergia());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
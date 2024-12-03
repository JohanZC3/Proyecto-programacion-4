package ui;

import javax.swing.*;

import classes.backUsuario.Usuario;
import classes.backUsuario.UsuarioRepositorio;
import ui.caja.CajaFrame;
import ui.historial.HistorialFrame;
import ui.productos.InventaryFrame;
import ui.user.UserFrame;

import java.awt.*;
import java.awt.event.*;

public class PrincipalFrame extends JFrame {
    private JLabel welcomeLabel;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }

    @SuppressWarnings("unused")
    public PrincipalFrame(String email) {
        setTitle("Pantalla Principal");
        setSize(400, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setIconImage(getIconImage());

        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorEmail(email);

        // Panel para el mensaje de bienvenida
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 60);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new java.awt.Color(199, 182, 145));
        add(panel);

        welcomeLabel = new JLabel("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
        welcomeLabel.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 30));
        welcomeLabel.setForeground(new java.awt.Color(17, 59, 75));

        // Alinear el JLabel al centro
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalGlue());

        // Botón para mostrar inventario
        JButton inventarioButton = new JButton("Mostrar Inventario");
        inventarioButton.setBounds(50, 150, 300, 50);
        inventarioButton.setBackground(new Color(17, 59, 75));
        inventarioButton.setForeground(new Color(228, 202, 151));
        inventarioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inventarioButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        inventarioButton.setFocusable(false);  // Evitar enfoque inicial
        add(inventarioButton);

        //Boton para caja
        JButton CajaButton = new JButton("Caja");
        CajaButton.setBounds(50, 80, 300, 50);
        CajaButton.setBackground(new Color(17, 59, 75));
        CajaButton.setForeground(new Color(228, 202, 151));
        CajaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CajaButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        CajaButton.setFocusable(false);  // Evitar enfoque inicial
        add(CajaButton);

        // Botón para ver Historial de Acciones
        JButton HistorialButton = new JButton("Mostrar Historial");
        HistorialButton.setBounds(50, 220, 300, 50);
        HistorialButton.setBackground(new Color(17, 59, 75));
        HistorialButton.setForeground(new Color(228, 202, 151));
        HistorialButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        HistorialButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        HistorialButton.setFocusable(false);  // Evitar enfoque inicial
        add(HistorialButton);

        JButton UserButton = new JButton("Usuarios");
        UserButton.setBounds(50, 290, 300, 50);
        UserButton.setBackground(new Color(17, 59, 75));
        UserButton.setForeground(new Color(228, 202, 151));
        UserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        UserButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        UserButton.setFocusable(false);  // Evitar enfoque inicial
        if (usuario.getTipoUsuario().equals("administrador")) {
            add(UserButton);
        }

        // Botón para cerrar sesión
        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setBounds(50, 360, 300, 50);
        logoutButton.setBackground(new Color(150, 150, 150));
        logoutButton.setForeground(new Color(0, 0, 0));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        logoutButton.setFocusable(false);  // Evitar enfoque inicial
        add(logoutButton);

        int userId = usuario.getId();

        // Agregar acción para cada botón
        inventarioButton.addActionListener(e -> new InventaryFrame().ShowUpInventaryFrame(userId));
        CajaButton.addActionListener(e -> new CajaFrame().mostrarCajaFrame(userId));
        HistorialButton.addActionListener(e -> new HistorialFrame().mostrarHistorialFrame());
        UserButton.addActionListener(e -> new UserFrame().mostrarUserFrame(userId));
        logoutButton.addActionListener(e -> { new LoginFrame().setVisible(true);dispose();});

        // Configurar Enter como "clic" en el botón cuando esté enfocado
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                source.doClick();
            }
        };
        
        // Asignar la acción de Enter a los botones
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inventarioButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        inventarioButton.getActionMap().put("click", action);

        logoutButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        logoutButton.getActionMap().put("click", action);

        HistorialButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        HistorialButton.getActionMap().put("click", action);

        CajaButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        CajaButton.getActionMap().put("click", action);

        UserButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        UserButton.getActionMap().put("click", action);



        // Listener para activar el ciclo de Tab cuando se presiona por primera vez
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                inventarioButton.setFocusable(true);
                logoutButton.setFocusable(true);
                HistorialButton.setFocusable(true);
                CajaButton.setFocusable(true);
                UserButton.setFocusable(true);
                inventarioButton.requestFocusInWindow();
            }
        });
    }
}

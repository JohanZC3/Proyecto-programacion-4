package ui;

import javax.swing.*;

import classes.*;
import repositorios.UsuarioRepositorio;
import uiproducts.InventaryFrame;
import uiproviders.ProvidersFrame;

import java.awt.*;
import java.awt.event.*;

public class PrincipalFrame extends JFrame {
    private JLabel welcomeLabel;

    public PrincipalFrame(String email) {
        setTitle("Pantalla Principal");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

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
        inventarioButton.setBounds(50, 80, 300, 50);
        inventarioButton.setBackground(new Color(17, 59, 75));
        inventarioButton.setForeground(new Color(228, 202, 151));
        inventarioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inventarioButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        inventarioButton.setFocusable(false);  // Evitar enfoque inicial
        add(inventarioButton);

        // Botón para ver proveedores
        JButton proveedoresButton = new JButton("Mostrar Proveedores");
        proveedoresButton.setBounds(50, 150, 300, 50);
        proveedoresButton.setBackground(new Color(17, 59, 75));
        proveedoresButton.setForeground(new Color(228, 202, 151));
        proveedoresButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        proveedoresButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        proveedoresButton.setFocusable(false);  // Evitar enfoque inicial
        add(proveedoresButton);

        // Botón para cerrar sesión
        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setBounds(50, 220, 300, 50);
        logoutButton.setBackground(new Color(17, 59, 75));
        logoutButton.setForeground(new Color(228, 202, 151));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        logoutButton.setFocusable(false);  // Evitar enfoque inicial
        add(logoutButton);

        // Agregar acción para cada botón
        inventarioButton.addActionListener(e -> new InventaryFrame().ShowUpInventaryFrame());
        proveedoresButton.addActionListener(e -> new ProvidersFrame().mostrarProveedores());
        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

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

        proveedoresButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        proveedoresButton.getActionMap().put("click", action);

        logoutButton.getInputMap(JComponent.WHEN_FOCUSED).put(enterKey, "click");
        logoutButton.getActionMap().put("click", action);

        // Listener para activar el ciclo de Tab cuando se presiona por primera vez
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                inventarioButton.setFocusable(true);
                proveedoresButton.setFocusable(true);
                logoutButton.setFocusable(true);
            }
        });
    }
}

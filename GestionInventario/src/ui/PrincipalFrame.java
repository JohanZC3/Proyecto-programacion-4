package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.* ;

public class PrincipalFrame extends JFrame {
    private JLabel welcomeLabel;

    public PrincipalFrame(String email) {
        setTitle("Pantalla Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorEmail(email);

        welcomeLabel = new JLabel("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
        welcomeLabel.setBounds(50, 50, 300, 30);
        add(welcomeLabel);

        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setBounds(130, 200, 150, 30);
        add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
    }
}


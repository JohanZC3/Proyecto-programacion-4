package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.* ;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UsuarioServicio usuarioServicio;

    public LoginFrame() {
        usuarioServicio = new UsuarioServicio();

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel emailLabel = new JLabel("Correo:");
        emailLabel.setBounds(50, 30, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(130, 30, 120, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 70, 120, 25);
        add(passwordField);

        JButton loginButton = new JButton("Ingresar");
        loginButton.setBounds(100, 120, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (usuarioServicio.validarUsuario(email, password)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido!");
                    new PrincipalFrame(email).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
}

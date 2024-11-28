package ui;

import javax.swing.*;

import classes.servicios.UsuarioServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UsuarioServicio usuarioServicio;
    private JPanel verticalGoldPanel;
    private JLabel Welcome;
    private JLabel texto;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JButton loginButton;

    public LoginFrame() {
        usuarioServicio = new UsuarioServicio();

        setTitle("Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        verticalGoldPanel = new JPanel();
        verticalGoldPanel.setBackground(new Color(199, 182, 145));
        verticalGoldPanel.setBounds(0, 0, 80, 315);
        add(verticalGoldPanel);

        Welcome = new JLabel("Bienvenido!");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 10, 220, 40);
        add(Welcome);

        texto = new JLabel("Por favor ingrese su correo y contraseña");
        texto.setFont(new java.awt.Font("Segoe UI", 0, 16));
        texto.setBounds(105, 40, 300, 40);
        add(texto);

        emailLabel = new JLabel("Correo:");
        emailLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        emailLabel.setBounds(105, 85, 110, 30);
        add(emailLabel);

        emailField = new JTextField("Ingrese aqui su correo");
        emailField.setBounds(105, 115, 300, 30);
        emailField.setBorder(null);
        emailField.setForeground(new Color(160, 160, 160));
        emailField.setBackground(new Color(238, 238, 238));
        emailField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(emailField);

        emailField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (emailField.getText().equals("Ingrese aqui su correo")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Ingrese aqui su correo");
                    emailField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Mover foco a passwordField al presionar Enter
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocusInWindow();
                }
            }
        });

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new Color(206, 206, 206));
        jSeparator1.setBounds(105, 145, 300, 5);
        add(jSeparator1);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        passwordLabel.setBounds(105, 160, 110, 30);
        add(passwordLabel);

        passwordField = new JPasswordField("********");
        passwordField.setBounds(105, 190, 300, 30);
        passwordField.setBorder(null);
        passwordField.setForeground(new Color(160, 160, 160));
        passwordField.setBackground(new Color(238, 238, 238));
        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(passwordField);

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).equals("********")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("********");
                    passwordField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de loginButton al presionar Enter
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new Color(206, 206, 206));
        jSeparator2.setBounds(105, 220, 300, 5);
        add(jSeparator2);

        loginButton = new JButton("Ingresar");
        loginButton.setBounds(190, 240, 130, 40);
        loginButton.setBackground(new Color(17, 59, 75));
        loginButton.setForeground(new Color(228, 202, 151));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);        
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().toLowerCase();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

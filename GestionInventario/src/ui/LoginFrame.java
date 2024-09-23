package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import classes.* ;

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
        setBackground(new Color(255,255,255));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        verticalGoldPanel = new JPanel();
        verticalGoldPanel.setBackground(new Color(199, 182, 145));
        verticalGoldPanel.setBounds(0, 0, 80, 315);
        verticalGoldPanel.requestFocusInWindow();
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

        emailField = new JTextField();
        emailField.setBounds(105, 115, 300, 30);
        emailField.setBorder(null);
        emailField.setText("Ingrese aqui su correo");
        emailField.setFocusable(false);
        emailField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        emailField.setForeground(new java.awt.Color(160,160,160));
        emailField.setBackground(new java.awt.Color(238,238,238));
        add(emailField);

        emailField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new java.awt.Color(206,206,206));
        jSeparator1.setBounds(105, 145, 300, 5);
        add(jSeparator1);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        passwordLabel.setBounds(105, 160, 110, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(105, 190, 300, 30);
        passwordField.setBorder(null);
        passwordField.setText("********");
        passwordField.setFocusable(false);
        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        passwordField.setForeground(new java.awt.Color(160,160,160));
        passwordField.setBackground(new java.awt.Color(238,238,238));
        add(passwordField);

        passwordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new java.awt.Color(206,206,206));
        jSeparator2.setBounds(105, 220, 300, 5);
        add(jSeparator2);

        loginButton = new JButton("Ingresar");
        loginButton.setBounds(190, 240, 130, 40);
        loginButton.setBackground(new java.awt.Color(17, 59, 75));
        loginButton.setForeground(new java.awt.Color(228,202,151));
        loginButton.setFocusPainted(false);
        loginButton.setFocusable(true);
        loginButton.setBorder(null);
        loginButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(loginButton);

        loginButton.addActionListener((ActionListener) new ActionListener() {
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

    private void emailFieldActionPerformed(MouseEvent evt) {
        if (emailField.getText().equals("Ingrese aqui su correo")){
            emailField.setFocusable(true);
            emailField.setText("");
            emailField.setForeground(new java.awt.Color(0,0,0));
        }
        if (String.valueOf(passwordField.getPassword()).isEmpty()){
            passwordField.setText("********");
            passwordField.setForeground(new java.awt.Color(160,160,160));
        }
    }

    private void passwordFieldActionPerformed(MouseEvent evt) {
        if (String.valueOf(passwordField.getPassword()).equals("********")){
            passwordField.setFocusable(true);
            passwordField.setText("");
            passwordField.setForeground(new java.awt.Color(0,0,0));
        }
        if (emailField.getText().isEmpty()){
            emailField.setText("Ingrese aqui su correo");
            emailField.setForeground(new java.awt.Color(160,160,160));
        }
    }
}

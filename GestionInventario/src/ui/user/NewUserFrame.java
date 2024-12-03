package ui.user;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backHistorial.HistorialService;
import classes.backSerialId.SerialId;
import classes.backSerialId.SerialIdRepository;
import classes.backUsuario.Usuario;
import classes.backUsuario.UsuarioRepositorio;
import classes.backUsuario.UsuarioServicio;
import ui.LoginFrame;

public class NewUserFrame extends JFrame { 
    private JTextField idField;
    private JTextField userNameField;
    private JLabel providerAdressLabel;
    private JTextField providerAdressField;
    private JPanel verticalGoldPanel;
    private JLabel Welcome;
    private JLabel texto;
    private JLabel idLabel;
    private JLabel userNameLabel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JTextField phoneField;
    private JLabel phoneLabel;
    private JSeparator jSeparator4;
    private JButton addproviderButton;
    private JComboBox<String> userTypeCB;
    private JSeparator jSeparator5;
    private JLabel TypeLabel;
    private JSeparator jSeparator6;
    private JLabel CCLabel;
    private JTextField CCField;
    private JSeparator jSeparator7;
    private JLabel userLastnameLabel;
    private JTextField userLastnameField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }


    public void NewUserFrameFrame(int userId) {

        setTitle("Añadir Usuario");
        setSize(1030, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(getIconImage());
        setVisible(true);

        verticalGoldPanel = new JPanel();
        verticalGoldPanel.setBackground(new Color(199, 182, 145));
        verticalGoldPanel.setBounds(0, 0, 80, 450);
        add(verticalGoldPanel);

        Welcome = new JLabel("Nuevo Usuario");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 5, 400, 40);
        add(Welcome);

        texto = new JLabel("Por favor ingresa la informacion del nuevo Usuario");
        texto.setFont(new java.awt.Font("Segoe UI", 0, 16));
        texto.setBounds(105, 35, 800, 40);
        add(texto);

        idLabel = new JLabel("Id del Usuario:");
        idLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        idLabel.setBounds(105, 80, 150, 30);
        add(idLabel);

        idField = new JTextField("Ingrese aqui el Id del Usuario");
        idField.setBounds(105, 110, 150, 30);
        idField.setBorder(null);
        idField.setForeground(new Color(160, 160, 160));
        idField.setBackground(new Color(238, 238, 238));
        idField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        idField.setEnabled(false);

        // Agregar KeyListener para solo permitir números
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || idField.getText().equals("Ingrese aqui el Id del proveedor")) {
                    if (Character.isDigit(c)) {
                        idField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });

        add(idField);

        idField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (idField.getText().equals("Ingrese aqui el Id del Usuario")) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (idField.getText().isEmpty()) {
                    idField.setText("Ingrese aqui el Id del usuario");
                    idField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Mover foco a userNameField al presionar Enter
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    userNameField.requestFocusInWindow();
                }
            }
        });

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new Color(206, 206, 206));
        jSeparator1.setBounds(105, 140, 150, 5);
        add(jSeparator1);

        TypeLabel = new JLabel("Tipo de Usuario:");
        TypeLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        TypeLabel.setBounds(275, 80, 150, 30);
        add(TypeLabel);

        userTypeCB = new JComboBox<>();
        userTypeCB.setBounds(275, 110, 150, 30);
        userTypeCB.addItem("administrador");
        userTypeCB.addItem("auxiliar");
        add(userTypeCB);

        jSeparator5 = new JSeparator();
        jSeparator5.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator5.setForeground(new Color(206, 206, 206));
        jSeparator5.setBounds(275, 140, 150, 5);
        add(jSeparator5);

        userNameLabel = new JLabel("Nombre del usuario:");
        userNameLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        userNameLabel.setBounds(555, 80, 180, 30);
        add(userNameLabel);

        userNameField = new JTextField("nombre del usuario");
        userNameField.setBounds(555, 110, 180, 30);
        userNameField.setBorder(null);
        userNameField.setForeground(new Color(160, 160, 160));
        userNameField.setBackground(new Color(238, 238, 238));
        userNameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(userNameField);

        userNameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(userNameField.getText()).equals("nombre del usuario")) {
                    userNameField.setText("");
                    userNameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(userNameField.getText()).isEmpty()) {
                    userNameField.setText("nombre del usuario");
                    userNameField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        userNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    userLastnameField.requestFocusInWindow();
                }
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new Color(206, 206, 206));
        jSeparator2.setBounds(555, 140, 180, 5);
        add(jSeparator2);

        userLastnameLabel = new JLabel("Apellido del usuario:");
        userLastnameLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        userLastnameLabel.setBounds(755, 80, 180, 30);
        add(userLastnameLabel);

        userLastnameField = new JTextField("Apellido del usuario");
        userLastnameField.setBounds(755, 110, 180, 30);
        userLastnameField.setBorder(null);
        userLastnameField.setForeground(new Color(160, 160, 160));
        userLastnameField.setBackground(new Color(238, 238, 238));
        userLastnameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(userLastnameField);

        userLastnameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(userLastnameField.getText()).equals("Apellido del usuario")) {
                    userLastnameField.setText("");
                    userLastnameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(userLastnameField.getText()).isEmpty()) {
                    userLastnameField.setText("Apellido del usuario");
                    userLastnameField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        userLastnameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    providerAdressField.requestFocusInWindow();
                }
            }
        });

        jSeparator7 = new JSeparator();
        jSeparator7.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator7.setForeground(new Color(206, 206, 206));
        jSeparator7.setBounds(755, 140, 180, 5);
        add(jSeparator7);


        providerAdressLabel = new JLabel("Correo electronico:");
        providerAdressLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        providerAdressLabel.setBounds(105, 155, 400, 30);
        add(providerAdressLabel);

        providerAdressField = new JTextField("Ingrese aqui el correo electronico del usuario");
        providerAdressField.setBounds(105, 185, 400, 30);
        providerAdressField.setBorder(null);
        providerAdressField.setForeground(new Color(160, 160, 160));
        providerAdressField.setBackground(new Color(238, 238, 238));
        providerAdressField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(providerAdressField);

        providerAdressField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(providerAdressField.getText()).equals("Ingrese aqui el correo electronico del usuario")) {
                    providerAdressField.setText("");
                    providerAdressField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(providerAdressField.getText()).isEmpty()) {
                    providerAdressField.setText("Ingrese aqui el correo electronico del usuario");
                    providerAdressField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        providerAdressField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    phoneField.requestFocusInWindow();
                }
            }
        });

        jSeparator3 = new JSeparator();
        jSeparator3.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator3.setForeground(new Color(206, 206, 206));
        jSeparator3.setBounds(105, 215, 400, 5);
        add(jSeparator3);

        phoneLabel = new JLabel("Numero de telefono:");
        phoneLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        phoneLabel.setBounds(555, 155, 180, 30);
        add(phoneLabel);

        phoneField = new JTextField("numero de telefono");
        phoneField.setBounds(555, 185, 180, 30);
        phoneField.setBorder(null);
        phoneField.setForeground(new Color(160, 160, 160));
        phoneField.setBackground(new Color(238, 238, 238));
        phoneField.setFont(new java.awt.Font("Segoe UI", 0, 14));        
        add(phoneField);

        // Agregar KeyListener para solo permitir números
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || phoneField.getText().equals("numero de telefono")) {
                    if (Character.isDigit(c)) {
                        phoneField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });
        

        phoneField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(phoneField.getText()).equals("numero de telefono")) {
                    phoneField.setText("");
                    phoneField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(phoneField.getText()).isEmpty()) {
                    phoneField.setText("numero de telefono");
                    phoneField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    CCField.requestFocusInWindow();
                }
            }
        });

        jSeparator4 = new JSeparator();
        jSeparator4.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator4.setForeground(new Color(206, 206, 206));
        jSeparator4.setBounds(555, 215, 180, 5);
        add(jSeparator4);

        CCLabel = new JLabel("Numero de identificacion:");
        CCLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        CCLabel.setBounds(755, 155, 210, 30);
        add(CCLabel);

        CCField = new JTextField("numero de identificacion");
        CCField.setBounds(755, 185, 210, 30);
        CCField.setBorder(null);
        CCField.setForeground(new Color(160, 160, 160));
        CCField.setBackground(new Color(238, 238, 238));
        CCField.setFont(new java.awt.Font("Segoe UI", 0, 14));        
        add(CCField);

        // Agregar KeyListener para solo permitir números
        CCField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || CCField.getText().equals("numero de identificacion")) {
                    if (Character.isDigit(c)) {
                        CCField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });
        

        CCField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(CCField.getText()).equals("numero de identificacion")) {
                    CCField.setText("");
                    CCField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(CCField.getText()).isEmpty()) {
                    CCField.setText("numero de identificacion");
                    CCField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        CCField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocusInWindow();
                }
            }
        });

        jSeparator6 = new JSeparator();
        jSeparator6.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator6.setForeground(new Color(206, 206, 206));
        jSeparator6.setBounds(755, 215, 210, 5);
        add(jSeparator6);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        passwordLabel.setBounds(105, 230, 110, 30);
        add(passwordLabel);

        passwordField = new JPasswordField("********");
        passwordField.setBounds(105, 260, 300, 30);
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
                    addproviderButton.doClick();
                }
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new Color(206, 206, 206));
        jSeparator2.setBounds(105, 290, 300, 5);
        add(jSeparator2);


        addproviderButton = new JButton("Agregar Usuario");
        addproviderButton.setBounds(105, 310, 200, 40);
        addproviderButton.setBackground(new Color(17, 59, 75));
        addproviderButton.setForeground(new Color(228, 202, 151));
        addproviderButton.setFocusPainted(false);
        addproviderButton.setBorderPainted(false);        
        addproviderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addproviderButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(addproviderButton);

        loadProductData();

        addproviderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idint = Integer.parseInt(idField.getText());
                String userName = userNameField.getText();
                String userLastname = userLastnameField.getText();
                String providerAdress = providerAdressField.getText();
                String phoneString = phoneField.getText();
                String userType = userTypeCB.getSelectedItem().toString();
                String CC = CCField.getText();
                String password = String.valueOf(passwordField.getPassword());


                if (UsuarioServicio.validarInformacion(userName, userLastname, CC, providerAdress, phoneString, password, userType)) {
                    Usuario usuario = new Usuario(idint, userName, userLastname,"CC", CC, providerAdress,phoneString, true, userType, password);
                    UsuarioRepositorio.crearUsuario(usuario);
                    int idHistorial = HistorialService.loadHistorialId();
                    Historial historial = new Historial(idHistorial, userId,"Creacion", LocalDate.now(), idint, "Creacion de " + userName, "Usuario");
                    HistorialRepository.crearHistorial(historial);
                    HistorialService.actualizarIds();

                    SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
                    serialId.setLastidUsuario(idint);
                    SerialIdRepository.modificarSerialId(serialId);
                    JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente", "Proveedor Agregado", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadProductData() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        int lastId = serialId.getLastidUsuario();
        idField.setText(String.valueOf(lastId + 1));        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

}

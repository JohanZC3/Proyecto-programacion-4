package ui.proveedores;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import classes.*;
import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backHistorial.HistorialService;
import classes.backProveedor.Proveedor;
import classes.backProveedor.ProveedorRepositorio;
import classes.backProveedor.ProveedorServicio;
import classes.backSerialId.SerialId;
import classes.backSerialId.SerialIdRepository;
import ui.LoginFrame;

public class NewProviderFrame extends JFrame {
    private JTextField idField;
    private JTextField providerNameField;
    private JLabel providerAdressLabel;
    private JTextField providerAdressField;
    private JPanel verticalGoldPanel;
    private JLabel Welcome;
    private JLabel texto;
    private JLabel idLabel;
    private JLabel providerNameLabel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JTextField phoneField;
    private JLabel phoneLabel;
    private JSeparator jSeparator4;
    private JButton addproviderButton;


    public NewProviderFrame() {

        setTitle("Añadir Proveedor");
        setSize(1000, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        verticalGoldPanel = new JPanel();
        verticalGoldPanel.setBackground(new Color(199, 182, 145));
        verticalGoldPanel.setBounds(0, 0, 80, 400);
        add(verticalGoldPanel);

        Welcome = new JLabel("Nuevo Proveedor");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 10, 400, 40);
        add(Welcome);

        texto = new JLabel("Por favor ingresa la informacion del nuevo proveedor");
        texto.setFont(new java.awt.Font("Segoe UI", 0, 16));
        texto.setBounds(105, 40, 800, 40);
        add(texto);

        idLabel = new JLabel("Id del proveedor:");
        idLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        idLabel.setBounds(105, 85, 400, 30);
        add(idLabel);

        idField = new JTextField("Ingrese aqui el Id del proveedor");
        idField.setBounds(105, 115, 400, 30);
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
                if (idField.getText().equals("Ingrese aqui el Id del proveedor")) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (idField.getText().isEmpty()) {
                    idField.setText("Ingrese aqui el Id del proveedor");
                    idField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Mover foco a providerNameField al presionar Enter
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    providerNameField.requestFocusInWindow();
                }
            }
        });

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new Color(206, 206, 206));
        jSeparator1.setBounds(105, 145, 400, 5);
        add(jSeparator1);

        providerNameLabel = new JLabel("Nombre del Proveedor:");
        providerNameLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        providerNameLabel.setBounds(555, 85, 400, 30);
        add(providerNameLabel);

        providerNameField = new JTextField("Ingrese aqui el nombre del proveedor");
        providerNameField.setBounds(555, 115, 400, 30);
        providerNameField.setBorder(null);
        providerNameField.setForeground(new Color(160, 160, 160));
        providerNameField.setBackground(new Color(238, 238, 238));
        providerNameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(providerNameField);

        providerNameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(providerNameField.getText()).equals("Ingrese aqui el nombre del proveedor")) {
                    providerNameField.setText("");
                    providerNameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(providerNameField.getText()).isEmpty()) {
                    providerNameField.setText("Ingrese aqui el nombre del proveedor");
                    providerNameField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        providerNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    providerAdressField.requestFocusInWindow();
                }
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new Color(206, 206, 206));
        jSeparator2.setBounds(555, 145, 400, 5);
        add(jSeparator2);

        providerAdressLabel = new JLabel("Direccion del proveedor:");
        providerAdressLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        providerAdressLabel.setBounds(105, 160, 400, 30);
        add(providerAdressLabel);

        providerAdressField = new JTextField("Ingrese aqui la Direccion del proveedor");
        providerAdressField.setBounds(105, 190, 400, 30);
        providerAdressField.setBorder(null);
        providerAdressField.setForeground(new Color(160, 160, 160));
        providerAdressField.setBackground(new Color(238, 238, 238));
        providerAdressField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(providerAdressField);

        providerAdressField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(providerAdressField.getText()).equals("Ingrese aqui la Direccion del proveedor")) {
                    providerAdressField.setText("");
                    providerAdressField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(providerAdressField.getText()).isEmpty()) {
                    providerAdressField.setText("Ingrese aqui la Direccion del proveedor");
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
        jSeparator3.setBounds(105, 220, 400, 5);
        add(jSeparator3);

        phoneLabel = new JLabel("Numero del proveedor:");
        phoneLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        phoneLabel.setBounds(555, 160, 400, 30);
        add(phoneLabel);

        phoneField = new JTextField("Ingrese aqui el numero del proveedor");
        phoneField.setBounds(555, 190, 400, 30);
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
                if (!Character.isDigit(c) || phoneField.getText().equals("Ingrese aqui el numero del proveedor")) {
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
                if (String.valueOf(phoneField.getText()).equals("Ingrese aqui el numero del proveedor")) {
                    phoneField.setText("");
                    phoneField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(phoneField.getText()).isEmpty()) {
                    phoneField.setText("Ingrese aqui el numero del proveedor");
                    phoneField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addproviderButton al presionar Enter
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addproviderButton.doClick();
                }
            }
        });

        jSeparator4 = new JSeparator();
        jSeparator4.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator4.setForeground(new Color(206, 206, 206));
        jSeparator4.setBounds(555, 220, 400, 5);
        add(jSeparator4);

        addproviderButton = new JButton("Agregar proveedor");
        addproviderButton.setBounds(105, 255, 200, 40);
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
                int idint =  Integer.parseInt(idField.getText());
                String providerName =  new String(providerNameField.getText());
                String providerAdress =  new String(providerAdressField.getText());
                String phoneString = new String(phoneField.getText());

                if (ProveedorServicio.validarInformacion(idint, providerName, providerAdress, phoneString)) {
                    ProveedorRepositorio.crearProveedor(new Proveedor(idint, providerName, providerAdress, phoneString));
                    int idHistorial = HistorialService.loadHistorialId();
                    Historial historial = new Historial(idHistorial, "Creacion", LocalDate.now(), idint, "Creacion de " + providerName, "Proveedor");
                    HistorialRepository.crearHistorial(historial);
                    HistorialService.actualizarIds();

                    SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
                    serialId.setLastidProveedor(idint);
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
        int lastId = serialId.getLastidProveedor();
        idField.setText(String.valueOf(lastId + 1));        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

package ui.proveedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import classes.backProveedor.Proveedor;
import classes.backProveedor.ProveedorRepositorio;
import classes.backProveedor.ProveedorServicio;
import ui.LoginFrame;
import ui.historial.HistorialModal;

public class ProvidersUpdateFrame extends JFrame {
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
    private JButton updateProviderButton;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }

    public ProvidersUpdateFrame(int providerId) {
        setTitle("Modificar Proveedor");
        setSize(1000, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(getIconImage());

        verticalGoldPanel = new JPanel();
        verticalGoldPanel.setBackground(new Color(199, 182, 145));
        verticalGoldPanel.setBounds(0, 0, 80, 400);
        add(verticalGoldPanel);

        Welcome = new JLabel("Modificar Proveedor");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 10, 400, 40);
        add(Welcome);

        texto = new JLabel("Actualiza la información del proveedor");
        texto.setFont(new java.awt.Font("Segoe UI", 0, 16));
        texto.setBounds(105, 40, 800, 40);
        add(texto);

        idLabel = new JLabel("Id del proveedor:");
        idLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        idLabel.setBounds(105, 85, 400, 30);
        add(idLabel);

        idField = new JTextField(String.valueOf(providerId));
        idField.setBounds(105, 115, 400, 30);
        idField.setBorder(null);
        idField.setForeground(new Color(160, 160, 160));
        idField.setBackground(new Color(238, 238, 238));
        idField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        idField.setEnabled(false);  // El campo de ID no debe ser editable para modificación
        add(idField);

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new Color(206, 206, 206));
        jSeparator1.setBounds(105, 145, 400, 5);
        add(jSeparator1);

        providerNameLabel = new JLabel("Nombre del Proveedor:");
        providerNameLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        providerNameLabel.setBounds(555, 85, 400, 30);
        add(providerNameLabel);

        providerNameField = new JTextField();
        providerNameField.setBounds(555, 115, 400, 30);
        providerNameField.setBorder(null);
        providerNameField.setForeground(Color.BLACK);
        providerNameField.setBackground(new Color(238, 238, 238));
        providerNameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(providerNameField);

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

        providerAdressField = new JTextField();
        providerAdressField.setBounds(105, 190, 400, 30);
        providerAdressField.setBorder(null);
        providerAdressField.setForeground(Color.BLACK);
        providerAdressField.setBackground(new Color(238, 238, 238));
        providerAdressField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(providerAdressField);

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

        phoneLabel = new JLabel("Numero de telefono:");
        phoneLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        phoneLabel.setBounds(555, 160, 400, 30);
        add(phoneLabel);

        phoneField = new JTextField("Ingrese aqui el numero de telefono del proveedor");
        phoneField.setBounds(555, 190, 400, 30);
        phoneField.setBorder(null);
        phoneField.setForeground(Color.BLACK);
        phoneField.setBackground(new Color(238, 238, 238));
        phoneField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(phoneField);

        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateProviderButton.doClick();
                }
            }
        });

        jSeparator4 = new JSeparator();
        jSeparator4.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator4.setForeground(new Color(206, 206, 206));
        jSeparator4.setBounds(555, 220, 400, 5);
        add(jSeparator4);

        updateProviderButton = new JButton("Guardar Cambios");
        updateProviderButton.setBounds(105, 255, 200, 40);
        updateProviderButton.setBackground(new Color(17, 59, 75));
        updateProviderButton.setForeground(new Color(228, 202, 151));
        updateProviderButton.setFocusPainted(false);
        updateProviderButton.setBorderPainted(false);        
        updateProviderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateProviderButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(updateProviderButton);

        loadProviderData(providerId);  // Cargar datos del proveedor

        updateProviderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String providerName = providerNameField.getText();
                String providerAdress = providerAdressField.getText();
                String phoneString = phoneField.getText();

                if (ProveedorServicio.validarInformacion(providerId, providerName, providerAdress, phoneString)) {
                    ProveedorRepositorio.modificarProveedor(providerId, new Proveedor(providerId, providerName, providerAdress, phoneString));
                    HistorialModal historialModal = new HistorialModal(providerId, "Modificacion", "proveedor");
                    historialModal.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadProviderData(int providerId) {
        Proveedor proveedor = ProveedorRepositorio.obtenerProveedorPorId(providerId);
        if (proveedor != null) {
            providerNameField.setText(proveedor.getNombre());
            providerAdressField.setText(proveedor.getDireccion());
            phoneField.setText(proveedor.getTelefono());
        } else {
            JOptionPane.showMessageDialog(this, "Proveedor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

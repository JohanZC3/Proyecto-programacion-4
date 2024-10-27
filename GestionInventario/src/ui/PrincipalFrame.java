package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.*;

public class PrincipalFrame extends JFrame {
    private JLabel welcomeLabel;

    public PrincipalFrame(String email) {
        setTitle("Pantalla Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorEmail(email);
        
        // Mostrar mensaje de bienvenida
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

        JButton inventarioButton = new JButton("Mostrar Inventario");
        inventarioButton.setBounds(130, 150, 150, 30);
        add(inventarioButton);

        inventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });

        // Botón para ver proveedores
        JButton proveedoresButton = new JButton("Mostrar Proveedores");
        proveedoresButton.setBounds(130, 100, 150, 30);
        add(proveedoresButton);

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProveedores();
            }
        });
    }

    private void mostrarInventario() {
        JFrame inventarioFrame = new JFrame("Inventario Actual");
        inventarioFrame.setSize(600, 400);
        inventarioFrame.setLocationRelativeTo(null);
        inventarioFrame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Categoría", "Cantidad", "Precio Unitario", "Fecha de Expiración"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try {
            for (Producto producto : ProductoRepositorio.obtenerProductos()) {
                Object[] rowData = {
                    producto.getId(),
                    producto.getNombre(),
                    producto.getCategoria(),
                    producto.getCantidad(),
                    producto.getPrecioUnitario(),
                    producto.getFechaExpiracion()
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(inventarioFrame, "Error al cargar el inventario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        inventarioFrame.add(scrollPane, BorderLayout.CENTER);
        
        inventarioFrame.setVisible(true);
    }

    private void mostrarProveedores() {
        JFrame proveedoresFrame = new JFrame("Proveedores Actuales");
        proveedoresFrame.setSize(600, 400);
        proveedoresFrame.setLocationRelativeTo(null);
        proveedoresFrame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Dirección", "Teléfono"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try {
            for (Proveedor proveedor : ProveedorRepositorio.obtenerProveedores()) {
                Object[] rowData = {
                    proveedor.getId(),
                    proveedor.getNombre(),
                    proveedor.getDireccion(),
                    proveedor.getTelefono()
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(proveedoresFrame, "Error al cargar los proveedores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        proveedoresFrame.add(scrollPane, BorderLayout.CENTER);
        
        proveedoresFrame.setVisible(true);
    }
}

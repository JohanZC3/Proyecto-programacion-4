package uiproducts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import classes.*;
import repositorios.ProductoRepositorio;
import repositorios.ProveedorRepositorio;
import servicios.ProductoServicio;
import ui.*;

public class ProductUpdateFrame extends JFrame {
    private JTextField idField;
    private JTextField productNameField;
    private JLabel productCategoryLabel;
    private JTextField productCategoryField;
    private JPanel verticalGoldPanel;
    private JLabel Welcome;
    private JLabel texto;
    private JLabel idLabel;
    private JLabel productNameLabel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JTextField amoundField;
    private JLabel amoundLabel;
    private JSeparator jSeparator4;
    private JTextField priceField;
    private JLabel priceLabel;
    private JSeparator jSeparator5;
    private JLabel dateLabel;
    private JButton updateProductButton;
    private JTextField dateDayField;
    private JTextField datemonthField;
    private JTextField dateYearField;
    private JLabel providerLabel;
    private JTextField providerField;
    private JSeparator jSeparator7;
    private JSeparator jSeparator6;


    public ProductUpdateFrame(int productId) {

        setTitle("Modificar Producto");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        verticalGoldPanel = new JPanel();
        verticalGoldPanel.setBackground(new Color(199, 182, 145));
        verticalGoldPanel.setBounds(0, 0, 80, 400);
        add(verticalGoldPanel);

        Welcome = new JLabel("Modificar Producto");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 10, 400, 40);
        add(Welcome);

        texto = new JLabel("Actualiza la informacion del producto");
        texto.setFont(new java.awt.Font("Segoe UI", 0, 16));
        texto.setBounds(105, 40, 800, 40);
        add(texto);

        idLabel = new JLabel("Id del producto:");
        idLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        idLabel.setBounds(105, 85, 400, 30);
        add(idLabel);

        idField = new JTextField("Ingrese aqui el Id del producto");
        idField.setBounds(105, 115, 400, 30);
        idField.setBorder(null);
        idField.setForeground(new Color(160, 160, 160));
        idField.setBackground(new Color(238, 238, 238));
        idField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        idField.setEnabled(false); // No se puede editar el ID
        add(idField);

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new Color(206, 206, 206));
        jSeparator1.setBounds(105, 145, 400, 5);
        add(jSeparator1);

        productNameLabel = new JLabel("Nombre del Producto:");
        productNameLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        productNameLabel.setBounds(555, 85, 400, 30);
        add(productNameLabel);

        productNameField = new JTextField("Ingrese aqui el nombre del producto");
        productNameField.setBounds(555, 115, 400, 30);
        productNameField.setBorder(null);
        productNameField.setForeground(Color.BLACK);
        productNameField.setBackground(new Color(238, 238, 238));
        productNameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(productNameField);

        // Ejecutar acción de updateProductButton al presionar Enter
        productNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    productCategoryField.requestFocusInWindow();
                }
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new Color(206, 206, 206));
        jSeparator2.setBounds(555, 145, 400, 5);
        add(jSeparator2);

        productCategoryLabel = new JLabel("Categoria del Producto:");
        productCategoryLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        productCategoryLabel.setBounds(105, 160, 400, 30);
        add(productCategoryLabel);

        productCategoryField = new JTextField("Ingrese aqui la categoria del producto");
        productCategoryField.setBounds(105, 190, 400, 30);
        productCategoryField.setBorder(null);
        productCategoryField.setForeground(Color.BLACK);
        productCategoryField.setBackground(new Color(238, 238, 238));
        productCategoryField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(productCategoryField);

        // Ejecutar acción de updateProductButton al presionar Enter
        productCategoryField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    amoundField.requestFocusInWindow();
                }
            }
        });

        jSeparator3 = new JSeparator();
        jSeparator3.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator3.setForeground(new Color(206, 206, 206));
        jSeparator3.setBounds(105, 220, 400, 5);
        add(jSeparator3);

        amoundLabel = new JLabel("Cantidad del Producto:");
        amoundLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        amoundLabel.setBounds(555, 160, 400, 30);
        add(amoundLabel);

        amoundField = new JTextField("Ingrese aqui la cantidad del producto");
        amoundField.setBounds(555, 190, 400, 30);
        amoundField.setBorder(null);
        amoundField.setForeground(Color.BLACK);
        amoundField.setBackground(new Color(238, 238, 238));
        amoundField.setFont(new java.awt.Font("Segoe UI", 0, 14));        
        add(amoundField);

        // Ejecutar acción de updateProductButton al presionar Enter
        amoundField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    priceField.requestFocusInWindow();
                }
            }
        });

        jSeparator4 = new JSeparator();
        jSeparator4.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator4.setForeground(new Color(206, 206, 206));
        jSeparator4.setBounds(555, 220, 400, 5);
        add(jSeparator4);

        priceLabel = new JLabel("Precio del Producto:");
        priceLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        priceLabel.setBounds(105, 235, 400, 30);
        add(priceLabel);

        priceField = new JTextField("Ingrese aqui el precio del producto");
        priceField.setBounds(105, 265, 400, 30);
        priceField.setBorder(null);
        priceField.setForeground(Color.BLACK);
        priceField.setBackground(new Color(238, 238, 238));
        priceField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(priceField);

        // Ejecutar acción de updateProductButton al presionar Enter
        priceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dateDayField.requestFocusInWindow();
                }
            }
        });

        jSeparator5 = new JSeparator();
        jSeparator5.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator5.setForeground(new Color(206, 206, 206));
        jSeparator5.setBounds(105, 295, 400, 5);
        add(jSeparator5);

        dateLabel = new JLabel("Fecha de Expiracion:");
        dateLabel.setFont(new java.awt.Font("Arial Narrow", Font.BOLD, 20));
        dateLabel.setBounds(555, 235, 180, 30);
        add(dateLabel);

        dateYearField = new JTextField("yyyy");
        dateYearField.setBounds(635, 265, 40, 30);
        dateYearField.setBorder(null);
        dateYearField.setForeground(Color.BLACK);
        dateYearField.setBackground(new Color(238, 238, 238));
        dateYearField.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        add(dateYearField);

        dateYearField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    providerField.requestFocusInWindow();
                }
            }
        });

        jSeparator6 = new JSeparator();
        jSeparator6.setPreferredSize(new Dimension(600, 10));
        jSeparator6.setForeground(new Color(206, 206, 206));
        jSeparator6.setBounds(555, 295, 120, 5);
        add(jSeparator6);

        datemonthField = new JTextField("mm");
        datemonthField.setBounds(595, 265, 25, 30);
        datemonthField.setBorder(null);
        datemonthField.setForeground(Color.BLACK);
        datemonthField.setBackground(new Color(238, 238, 238));
        datemonthField.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        add(datemonthField);

        datemonthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dateYearField.requestFocusInWindow();
                }
            }
        });

        dateDayField = new JTextField("dd");
        dateDayField.setBounds(555, 265, 25, 30);
        dateDayField.setBorder(null);
        dateDayField.setForeground(Color.BLACK);
        dateDayField.setBackground(new Color(238, 238, 238));
        dateDayField.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        add(dateDayField);

        dateDayField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    datemonthField.requestFocusInWindow();
                }
            }
        });

        providerLabel = new JLabel("Proveedor:");
        providerLabel.setFont(new java.awt.Font("Arial Narrow", Font.BOLD, 20));
        providerLabel.setBounds(765, 235, 100, 30);
        add(providerLabel);

        providerField = new JTextField("Ingresa el Proveedor");
        providerField.setBounds(765, 265, 190, 30);
        providerField.setForeground(Color.BLACK);
        providerField.setBackground(new Color(238, 238, 238));
        providerField.setBorder(null);
        providerField.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        add(providerField);    

        // Foco y enter en JComboBox
        providerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateProductButton.doClick();
                }
            }
        });

        jSeparator7 = new JSeparator();
        jSeparator7.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator7.setForeground(new Color(206, 206, 206));
        jSeparator7.setBounds(765, 295, 190, 5);
        add(jSeparator7);


        
        updateProductButton = new JButton("Guardar Cambios");
        updateProductButton.setBounds(105, 310, 200, 40);
        updateProductButton.setBackground(new Color(17, 59, 75));
        updateProductButton.setForeground(new Color(228, 202, 151));
        updateProductButton.setFocusPainted(false);
        updateProductButton.setBorderPainted(false);        
        updateProductButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateProductButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(updateProductButton);

        loadProductData(productId);

        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idint =  Integer.parseInt(idField.getText());
                String productName =  new String(productNameField.getText());
                String productCategory =  new String(productCategoryField.getText());
                int amoundint = Integer.parseInt(amoundField.getText());
                double price = Double.parseDouble(priceField.getText());
                int idProveedor = obtenerIdProveedorSeleccionado();
                int day = Integer.parseInt(dateDayField.getText());
                int month = Integer.parseInt(datemonthField.getText());
                int year = Integer.parseInt(dateYearField.getText());
                LocalDate expirationDate = null;

                ProductoServicio productoServicio = new ProductoServicio();

                if (productoServicio.validarFecha(year, month, day)) {
                    expirationDate = LocalDate.of(year, month, day);
                    if (idProveedor != -1) {
                        if (productoServicio.validacionInformacion(idint, productName, productCategory, amoundint, price, idProveedor)) {
                            System.out.println(idint + " " + productName + " " + productCategory + " " + amoundint + " " + price + " " + expirationDate + " " + idProveedor);
                            Producto producto = new Producto(idint, productName, productCategory, amoundint, price, expirationDate, idProveedor);
                            ProductoRepositorio.modificarProveedor(productId, producto);
                            JOptionPane.showMessageDialog(null, "Producto Modificado exitosamente", "Producto Modificado", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un proveedor válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }    

    // Método para obtener el ID del proveedor seleccionado
    public int obtenerIdProveedorSeleccionado() {
        String nombreSeleccionado = providerField.getText(); 
        Proveedor proveedor = ProveedorRepositorio.obtenerProveedorPorNombre(nombreSeleccionado);
        if (proveedor != null) {
            return proveedor.getId();
        } else {
            return -1;
        }
    }

    private void loadProductData(int productId) {
        Producto producto = ProductoRepositorio.obtenerProductoPorId(productId);
        if (producto != null) {
            idField.setText(String.valueOf(producto.getId()));
            productNameField.setText(producto.getNombre());
            productCategoryField.setText(producto.getCategoria());
            amoundField.setText(String.valueOf(producto.getCantidad()));
            priceField.setText(String.valueOf(producto.getPrecioUnitario()));
            dateDayField.setText(String.valueOf(producto.getFechaExpiracion().getDayOfMonth()));
            datemonthField.setText(String.valueOf(producto.getFechaExpiracion().getMonthValue()));
            dateYearField.setText(String.valueOf(producto.getFechaExpiracion().getYear()));
            providerField.setText(String.valueOf(ProveedorRepositorio.obtenerProveedorPorId(producto.getProveedorId()).getNombre()));
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

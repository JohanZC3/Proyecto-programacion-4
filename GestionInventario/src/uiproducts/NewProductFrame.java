package uiproducts;

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
import repositorios.HistorialRepository;
import repositorios.ProductoRepositorio;
import repositorios.ProveedorRepositorio;
import servicios.HistorialService;
import servicios.ProductoServicio;
import ui.*;

public class NewProductFrame extends JFrame {
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
    private JButton addProductButton;
    private JTextField dateDayField;
    private JTextField datemonthField;
    private JTextField dateYearField;
    private JLabel providerLabel;
    private JTextField providerField;
    private JSeparator jSeparator7;
    private JSeparator jSeparator6;


    public NewProductFrame() {

        setTitle("Añadir Producto");
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

        Welcome = new JLabel("Nuevo Producto");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 10, 400, 40);
        add(Welcome);

        texto = new JLabel("Por favor ingresa la informacion del nuevo producto");
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

        // Agregar KeyListener para solo permitir números
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || idField.getText().equals("Ingrese aqui el Id del producto")) {
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
                if (idField.getText().equals("Ingrese aqui el Id del producto")) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (idField.getText().isEmpty()) {
                    idField.setText("Ingrese aqui el Id del producto");
                    idField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Mover foco a productNameField al presionar Enter
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    productNameField.requestFocusInWindow();
                }
            }
        });

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
        productNameField.setForeground(new Color(160, 160, 160));
        productNameField.setBackground(new Color(238, 238, 238));
        productNameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(productNameField);

        productNameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(productNameField.getText()).equals("Ingrese aqui el nombre del producto")) {
                    productNameField.setText("");
                    productNameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(productNameField.getText()).isEmpty()) {
                    productNameField.setText("Ingrese aqui el nombre del producto");
                    productNameField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addProductButton al presionar Enter
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
        productCategoryField.setForeground(new Color(160, 160, 160));
        productCategoryField.setBackground(new Color(238, 238, 238));
        productCategoryField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(productCategoryField);

        productCategoryField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(productCategoryField.getText()).equals("Ingrese aqui la categoria del producto")) {
                    productCategoryField.setText("");
                    productCategoryField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(productCategoryField.getText()).isEmpty()) {
                    productCategoryField.setText("Ingrese aqui la categoria del producto");
                    productCategoryField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addProductButton al presionar Enter
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
        amoundField.setForeground(new Color(160, 160, 160));
        amoundField.setBackground(new Color(238, 238, 238));
        amoundField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        
        // Agregar KeyListener para solo permitir números
        amoundField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || amoundField.getText().equals("Ingrese aqui la cantidad del producto")) {
                    if (Character.isDigit(c)) {
                        amoundField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });
        
        add(amoundField);
        

        amoundField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(amoundField.getText()).equals("Ingrese aqui la cantidad del producto")) {
                    amoundField.setText("");
                    amoundField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(amoundField.getText()).isEmpty()) {
                    amoundField.setText("Ingrese aqui la cantidad del producto");
                    amoundField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addProductButton al presionar Enter
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
        priceField.setForeground(new Color(160, 160, 160));
        priceField.setBackground(new Color(238, 238, 238));
        priceField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        
        // Agregar KeyListener para solo permitir números
        priceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
        
                // Obtener el texto actual del campo
                String currentText = priceField.getText();
        
                // Permitir solo dígitos, un solo punto decimal, y borrar el texto inicial si se ingresa un número
                if ((!Character.isDigit(c) && c != '.') || currentText.equals("Ingrese aqui el precio del producto")) {
                    if (Character.isDigit(c) || c == '.') {
                        // Borrar el texto inicial si es la primera entrada válida
                        if (currentText.equals("Ingrese aqui el precio del producto")) {
                            priceField.setText("");
                        }
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
        
                // Permitir un solo punto decimal
                if (c == '.' && currentText.contains(".")) {
                    e.consume();  // Evitar un segundo punto decimal
                }
            }
        });
        
        
        add(priceField);
        

        priceField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(priceField.getText()).equals("Ingrese aqui el precio del producto")) {
                    priceField.setText("");
                    priceField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(priceField.getText()).isEmpty()) {
                    priceField.setText("Ingrese aqui el precio del producto");
                    priceField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addProductButton al presionar Enter
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
        addFocusAndKeyListener(dateYearField, "yyyy");
        add(dateYearField);

        jSeparator6 = new JSeparator();
        jSeparator6.setPreferredSize(new Dimension(600, 10));
        jSeparator6.setForeground(new Color(206, 206, 206));
        jSeparator6.setBounds(555, 295, 120, 5);
        add(jSeparator6);

        datemonthField = new JTextField("mm");
        datemonthField.setBounds(595, 265, 25, 30);
        addFocusAndKeyListener(datemonthField, "mm");
        add(datemonthField);

        dateDayField = new JTextField("dd");
        dateDayField.setBounds(555, 265, 25, 30);
        addFocusAndKeyListener(dateDayField, "dd");
        add(dateDayField);
    }

    private void addFocusAndKeyListener(JTextField field, String placeholderText) {
        field.setBorder(null);
        field.setForeground(new Color(160, 160, 160));
        field.setBackground(new Color(238, 238, 238));
        field.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (field.getText().equals(placeholderText)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholderText);
                    field.setForeground(new Color(160, 160, 160));
                }
            }
        });

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Mueve el foco al siguiente campo de fecha
                    if (field == dateDayField) {
                        datemonthField.requestFocusInWindow();
                    } else if (field == datemonthField) {
                        dateYearField.requestFocusInWindow();
                    } else {
                        providerField.requestFocusInWindow();
                    }
                }
            }
        });

        providerLabel = new JLabel("Proveedor:");
        providerLabel.setFont(new java.awt.Font("Arial Narrow", Font.BOLD, 20));
        providerLabel.setBounds(765, 235, 100, 30);
        add(providerLabel);

        providerField = new JTextField("Ingresa el Proveedor");
        providerField.setBounds(765, 265, 190, 30);
        providerField.setForeground(new Color(160, 160, 160));
        providerField.setBackground(new Color(238, 238, 238));
        providerField.setBorder(null);
        providerField.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        add(providerField);    
        
        providerField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(providerField.getText()).equals("Ingresa el Proveedor")) {
                    providerField.setText("");
                    providerField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(providerField.getText()).isEmpty()) {
                    providerField.setText("Ingresa el Proveedor");
                    providerField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Foco y enter en JComboBox
        providerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addProductButton.doClick();
                }
            }
        });

        jSeparator7 = new JSeparator();
        jSeparator7.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator7.setForeground(new Color(206, 206, 206));
        jSeparator7.setBounds(765, 295, 190, 5);
        add(jSeparator7);


        
        addProductButton = new JButton("Agregar Producto");
        addProductButton.setBounds(105, 310, 200, 40);
        addProductButton.setBackground(new Color(17, 59, 75));
        addProductButton.setForeground(new Color(228, 202, 151));
        addProductButton.setFocusPainted(false);
        addProductButton.setBorderPainted(false);        
        addProductButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addProductButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(addProductButton);

        addProductButton.addActionListener(new ActionListener() {
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
                            ProductoRepositorio.crearProducto(producto);
                            HistorialService historialServicio = new HistorialService();
                            int idHistorial = historialServicio.obtenerMaxIdHistorial();
                            Historial historial = new Historial(idHistorial, "Creacion", LocalDate.now(), idint, "Creacion de "+productName);
                            HistorialRepository.crearHistorial(historial);
                            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente", "Producto Agregado", JOptionPane.INFORMATION_MESSAGE);
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

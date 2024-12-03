package ui.productos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import classes.backCategoria.Category;
import classes.backCategoria.CategoryRepository;
import classes.backProducto.Producto;
import classes.backProducto.ProductoRepositorio;
import classes.backProducto.ProductoServicio;
import classes.backProveedor.Proveedor;
import classes.backProveedor.ProveedorRepositorio;
import ui.*;
import ui.historial.HistorialModal;

public class ProductUpdateFrame extends JFrame {
    private JTextField idField;
    private JTextField productNameField;
    private JLabel productCategoryLabel;
    private JComboBox<String> productCategoryComboBox;
    private JPanel verticalGoldPanel;
    private JLabel Welcome;
    private JLabel texto;
    private JLabel idLabel;
    private JLabel productNameLabel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JTextField minimaField;
    private JLabel minimaLabel;
    private JTextField maximaField;
    private JLabel maximaLabel;
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
    private JComboBox<String> providerComboBox;
    private JSeparator jSeparator7;
    private JSeparator jSeparator6;
    private JTextField amoundField;
    private JSeparator jSeparator8;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }


    public ProductUpdateFrame(int productId, int userId) {

        setTitle("Modificar Producto");
        setSize(1000, 400);
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
                    productCategoryComboBox.requestFocusInWindow();
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

        productCategoryComboBox = new JComboBox<String>();
        productCategoryComboBox.setBounds(105, 190, 400, 30);
        productCategoryComboBox.setForeground(Color.BLACK);
        productCategoryComboBox.setBackground(new Color(238, 238, 238));
        productCategoryComboBox.setBorder(null);
        productCategoryComboBox.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        productCategoryComboBox.addItem("Seleccione una categoria");
        for (Category category : CategoryRepository.obtenerCategories()) {
            String item = category.getNombre();
            productCategoryComboBox.addItem(item);
            //System.out.println(item);
        }
        add(productCategoryComboBox);

        // Ejecutar acción de updateProductButton al presionar Enter
        productCategoryComboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    minimaField.requestFocusInWindow();
                }
            }
        });

        jSeparator3 = new JSeparator();
        jSeparator3.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator3.setForeground(new Color(206, 206, 206));
        jSeparator3.setBounds(105, 220, 400, 5);
        add(jSeparator3);

        minimaLabel = new JLabel("Cantidad minima:");
        minimaLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        minimaLabel.setBounds(555, 160, 190, 30);
        add(minimaLabel);

        minimaField = new JTextField("Ingrese aqui la cantidad minima del producto");
        minimaField.setBounds(555, 190, 190, 30);
        minimaField.setBorder(null);
        minimaField.setForeground(new Color(160, 160, 160));
        minimaField.setBackground(new Color(238, 238, 238));
        minimaField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        
        // Agregar KeyListener para solo permitir números
        minimaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || minimaField.getText().equals("Ingrese aqui la cantidad minima del producto")) {
                    if (Character.isDigit(c)) {
                        minimaField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });
        
        add(minimaField);

        // Ejecutar acción de addProductButton al presionar Enter
        minimaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    maximaField.requestFocusInWindow();
                }
            }
        });

        jSeparator4 = new JSeparator();
        jSeparator4.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator4.setForeground(new Color(206, 206, 206));
        jSeparator4.setBounds(555, 220, 190, 5);
        add(jSeparator4);

        maximaLabel = new JLabel("Cantidad maxima:");
        maximaLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        maximaLabel.setBounds(765, 160, 190, 30);
        add(maximaLabel);

        maximaField = new JTextField("Ingrese aqui la cantidad maxima del producto");
        maximaField.setBounds(765, 190, 190, 30);
        maximaField.setBorder(null);
        maximaField.setForeground(new Color(160, 160, 160));
        maximaField.setBackground(new Color(238, 238, 238));
        maximaField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        
        // Agregar KeyListener para solo permitir números
        maximaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitir solo dígitos y borrar el texto inicial si se ingresa un número
                if (!Character.isDigit(c) || maximaField.getText().equals("Ingrese aqui la cantidad maxima del producto")) {
                    if (Character.isDigit(c)) {
                        maximaField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });
        
        add(maximaField);

        // Ejecutar acción de addProductButton al presionar Enter
        maximaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    priceField.requestFocusInWindow();
                }
            }
        });

        jSeparator8 = new JSeparator();
        jSeparator8.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator8.setForeground(new Color(206, 206, 206));
        jSeparator8.setBounds(765, 220, 190, 5);
        add(jSeparator8);

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
                    providerComboBox.requestFocusInWindow();
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

        providerComboBox = new JComboBox<String>();
        providerComboBox.setBounds(765, 265, 190, 30);
        providerComboBox.setForeground(Color.BLACK);
        providerComboBox.setBackground(new Color(238, 238, 238));
        providerComboBox.setBorder(null);
        providerComboBox.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        for (Proveedor proveedor : ProveedorRepositorio.obtenerProveedores()) {
            String item = proveedor.getNombre();
            providerComboBox.addItem(item);
            //System.out.println(item);
        }
        add(providerComboBox);


        // Foco y enter en JComboBox
        providerComboBox.addKeyListener(new KeyAdapter() {
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

        amoundField = new JTextField();


        loadProductData(productId);

        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idint = Integer.parseInt(idField.getText());
                    String productName = productNameField.getText();
                    int productCategory = obtenerIdCategoriaSeleccionada();
                    int minimoint = Integer.parseInt(minimaField.getText());
                    int maximaint = Integer.parseInt(maximaField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    int idProveedor = obtenerIdProveedorSeleccionado();
                    int amound = Integer.parseInt(amoundField.getText());
                    
                    // Verificación de fecha
                    String dayText = dateDayField.getText();
                    String monthText = datemonthField.getText();
                    String yearText = dateYearField.getText();
                    LocalDate expirationDate = null;
        
                    if (!dayText.equals("dd") && !monthText.equals("mm") && !yearText.equals("yyyy")
                            && !dayText.isEmpty() && !monthText.isEmpty() && !yearText.isEmpty()) {
                        int day = Integer.parseInt(dayText);
                        int month = Integer.parseInt(monthText);
                        int year = Integer.parseInt(yearText);
        
                        if (new ProductoServicio().validarFecha(year, month, day)) {
                            expirationDate = LocalDate.of(year, month, day);
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
        
                    ProductoServicio productoServicio = new ProductoServicio();
                    if (idProveedor != -1) {
                        if (productoServicio.validacionInformacion(idint, productName, productCategory, minimoint, price, idProveedor)) {
                            //System.out.println(idint + " " + productName + " " + productCategory + " " + minimoint + " " + price + " " + expirationDate + " " + idProveedor);
                            Producto producto = new Producto(idint, productName, productCategory, amound, minimoint, maximaint, price, expirationDate, idProveedor);
                            ProductoRepositorio.modificarProducto(productId, producto);
                            HistorialModal historialModal = new HistorialModal(idint, "Modificacion", "productos", userId);
                            historialModal.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un proveedor válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error al convertir un valor numérico. Por favor revise los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }    

    // Método para obtener el ID del proveedor seleccionado
    public int obtenerIdProveedorSeleccionado() {
        String nombreSeleccionado = (String) providerComboBox.getSelectedItem();
        //System.out.println(nombreSeleccionado);
        Proveedor proveedor = ProveedorRepositorio.obtenerProveedorPorNombre(nombreSeleccionado);
        if (proveedor != null) {
            return proveedor.getId();
        } else {
            return -1;
        }
    }

    // Método para obtener el ID de la categoría seleccionada
    public int obtenerIdCategoriaSeleccionada() {
        String nombreSeleccionado = (String) productCategoryComboBox.getSelectedItem();
        //System.out.println(nombreSeleccionado);
        int idCategoria = CategoryRepository.obtenerIdPorNombre(nombreSeleccionado);
        return idCategoria;
    }

    private void loadProductData(int productId) {
        Producto producto = ProductoRepositorio.obtenerProductoPorId(productId);
        if (producto != null) {
            idField.setText(String.valueOf(producto.getId()));
            productNameField.setText(producto.getNombre());
            productCategoryComboBox.setSelectedItem(CategoryRepository.obtenerCategoryPorId(producto.getCategoria()).getNombre());
            minimaField.setText(String.valueOf(producto.getMinima()));
            maximaField.setText(String.valueOf(producto.getMaxima()));
            priceField.setText(String.valueOf(producto.getPrecioUnitario()));
            amoundField.setText(String.valueOf(producto.getCantidad()));
    
            // Validación para fecha de expiración
            if (producto.getFechaExpiracion() != null) {
                dateDayField.setText(String.valueOf(producto.getFechaExpiracion().getDayOfMonth()));
                datemonthField.setText(String.valueOf(producto.getFechaExpiracion().getMonthValue()));
                dateYearField.setText(String.valueOf(producto.getFechaExpiracion().getYear()));
            } else {
                // Dejar los campos en valores predeterminados si la fecha es null
                dateDayField.setText("dd");
                datemonthField.setText("mm");
                dateYearField.setText("yyyy");
            }
    
            providerComboBox.setSelectedItem(ProveedorRepositorio.obtenerProveedorPorId(producto.getProveedorId()).getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

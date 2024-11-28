package ui.uiproducts;

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
import classes.repositorios.CategoryRepository;
import classes.repositorios.HistorialRepository;
import classes.repositorios.ProductoRepositorio;
import classes.repositorios.ProveedorRepositorio;
import classes.repositorios.SerialIdRepository;
import classes.servicios.HistorialService;
import classes.servicios.ProductoServicio;
import ui.*;

public class NewProductFrame extends JFrame {
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
    private JComboBox<String> providerComboBox;
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
        idField.setEnabled(false);

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
            System.out.println(item);
        }
        add(productCategoryComboBox);

        // Ejecutar acción de addProductButton al presionar Enter
        productCategoryComboBox.addKeyListener(new KeyAdapter() {
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
        dateYearField.setBorder(null);
        dateYearField.setForeground(new Color(160, 160, 160));
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

        dateYearField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(dateYearField.getText()).equals("yyyy")) {
                    dateYearField.setText("");
                    dateYearField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(dateYearField.getText()).isEmpty()) {
                    dateYearField.setText("yyyy");
                    dateYearField.setForeground(new Color(160, 160, 160));
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
        datemonthField.setForeground(new Color(160, 160, 160));
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

        datemonthField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(datemonthField.getText()).equals("mm")) {
                    datemonthField.setText("");
                    datemonthField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(datemonthField.getText()).isEmpty()) {
                    datemonthField.setText("mm");
                    datemonthField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        dateDayField = new JTextField("dd");
        dateDayField.setBounds(555, 265, 25, 30);
        dateDayField.setBorder(null);
        dateDayField.setForeground(new Color(160, 160, 160));
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

        dateDayField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(dateDayField.getText()).equals("dd")) {
                    dateDayField.setText("");
                    dateDayField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(dateDayField.getText()).isEmpty()) {
                    dateDayField.setText("dd");
                    dateDayField.setForeground(new Color(160, 160, 160));
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
        providerComboBox.addItem("Seleccione un proveedor");
        for (Proveedor proveedor : ProveedorRepositorio.obtenerProveedores()) {
            String item = proveedor.getNombre();
            providerComboBox.addItem(item);
            System.out.println(item);
        }
        add(providerComboBox);


        // Foco y enter en JComboBox
        providerComboBox.addKeyListener(new KeyAdapter() {
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

        loadProductData();

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idint = Integer.parseInt(idField.getText());
                    String productName = productNameField.getText();
                    int productCategory = obtenerIdCategoriaSeleccionada();
                    int amoundint = Integer.parseInt(amoundField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    int idProveedor = obtenerIdProveedorSeleccionado();
                    
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
                        if (productoServicio.validacionInformacion(idint, productName, productCategory, amoundint, price, idProveedor)) {
                            Producto producto = new Producto(idint, productName, productCategory, amoundint, price, expirationDate, idProveedor);
                            ProductoRepositorio.crearProducto(producto);
        
                            int idHistorial = HistorialService.loadHistorialId();
                            Historial historial = new Historial(idHistorial, "Creacion", LocalDate.now(), idint, "Creacion de " + productName, "Productos");
                            HistorialRepository.crearHistorial(historial);
                            HistorialService.actualizarIds();

                            SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
                            serialId.setLastidProducto(idint);
                            SerialIdRepository.modificarSerialId(serialId);
        
                            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente", "Producto Agregado", JOptionPane.INFORMATION_MESSAGE);
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

    private void loadProductData() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        int lastId = serialId.getLastidProducto();
        idField.setText(String.valueOf(lastId + 1));        
    }
    

    // Método para obtener el ID del proveedor seleccionado
    public int obtenerIdProveedorSeleccionado() {
        String nombreSeleccionado = (String) providerComboBox.getSelectedItem();
        System.out.println(nombreSeleccionado);
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
        System.out.println(nombreSeleccionado);
        int idCategoria = CategoryRepository.obtenerIdPorNombre(nombreSeleccionado);
        return idCategoria;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

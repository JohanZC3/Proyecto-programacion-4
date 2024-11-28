package ui.category;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classes.backCategoria.Category;
import classes.backCategoria.CategoryRepository;
import ui.LoginFrame;
import ui.historial.HistorialModal;

public class UpdateCategoryFrame extends JFrame {

    
    private JTextField idField;
    private JTextField categoryNameField;
    private JPanel verticalGoldPanel;
    private JLabel Welcome;
    private JLabel texto;
    private JLabel idLabel;
    private JLabel categoryNameLabel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JButton addProductButton;
    private JLabel categoryDescriptionLabel;
    private JTextField categoryDescriptionField;


    public UpdateCategoryFrame(int categoryId) {

        setTitle("modificar Categoria");
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

        Welcome = new JLabel("Modificar Categoria");
        Welcome.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36));
        Welcome.setForeground(new java.awt.Color(17, 59, 75));
        Welcome.setBounds(105, 10, 400, 40);
        add(Welcome);

        texto = new JLabel("Por favor ingresa la informacion de la categoria");
        texto.setFont(new java.awt.Font("Segoe UI", 0, 16));
        texto.setBounds(105, 40, 800, 40);
        add(texto);

        idLabel = new JLabel("Id de la categoria:");
        idLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        idLabel.setBounds(105, 85, 400, 30);
        add(idLabel);

        idField = new JTextField("Ingrese aqui el Id de la categoria");
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
                if (!Character.isDigit(c) || idField.getText().equals("Ingrese aqui el Id de la categoria")) {
                    if (Character.isDigit(c)) {
                        idField.setText("");  // Borrar el texto inicial
                    } else {
                        e.consume();  // Evitar caracteres no numéricos
                    }
                }
            }
        });

        add(idField);

        // Mover foco a productNameField al presionar Enter
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    categoryNameField.requestFocusInWindow();
                }
            }
        });

        jSeparator1 = new JSeparator();
        jSeparator1.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator1.setForeground(new Color(206, 206, 206));
        jSeparator1.setBounds(105, 145, 400, 5);
        add(jSeparator1);

        categoryNameLabel = new JLabel("Nombre de la categoria:");
        categoryNameLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        categoryNameLabel.setBounds(555, 85, 400, 30);
        add(categoryNameLabel);

        categoryNameField = new JTextField("Ingrese aqui el nombre de la categoria");
        categoryNameField.setBounds(555, 115, 400, 30);
        categoryNameField.setBorder(null);
        categoryNameField.setForeground(new Color(160, 160, 160));
        categoryNameField.setBackground(new Color(238, 238, 238));
        categoryNameField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(categoryNameField);

        categoryNameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(categoryNameField.getText()).equals("Ingrese aqui el nombre de la categoria")) {
                    categoryNameField.setText("");
                    categoryNameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(categoryNameField.getText()).isEmpty()) {
                    categoryNameField.setText("Ingrese aqui el nombre de la categoria");
                    categoryNameField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addProductButton al presionar Enter
        categoryNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    categoryDescriptionField.requestFocusInWindow();
                }
            }
        });

        jSeparator2 = new JSeparator();
        jSeparator2.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator2.setForeground(new Color(206, 206, 206));
        jSeparator2.setBounds(555, 145, 400, 5);
        add(jSeparator2);

        categoryDescriptionLabel = new JLabel("Descripcion de la categoria:");
        categoryDescriptionLabel.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        categoryDescriptionLabel.setBounds(105, 160, 400, 30);
        add(categoryDescriptionLabel);

        categoryDescriptionField = new JTextField("Ingrese aqui la descripcion de la categoria");
        categoryDescriptionField.setBounds(105, 190, 400, 30);
        categoryDescriptionField.setBorder(null);
        categoryDescriptionField.setForeground(new Color(160, 160, 160));
        categoryDescriptionField.setBackground(new Color(238, 238, 238));
        categoryDescriptionField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        add(categoryDescriptionField);

        categoryDescriptionField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(categoryDescriptionField.getText()).equals("Ingrese aqui la descripcion de la categoria")) {
                    categoryDescriptionField.setText("");
                    categoryDescriptionField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(categoryDescriptionField.getText()).isEmpty()) {
                    categoryDescriptionField.setText("Ingrese aqui la descripcion de la categoria");
                    categoryDescriptionField.setForeground(new Color(160, 160, 160));
                }
            }
        });

        // Ejecutar acción de addProductButton al presionar Enter
        categoryDescriptionField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addProductButton.doClick();
                }
            }
        });

        jSeparator3 = new JSeparator();
        jSeparator3.setPreferredSize(new java.awt.Dimension(600, 10));
        jSeparator3.setForeground(new Color(206, 206, 206));
        jSeparator3.setBounds(105, 220, 400, 5);
        add(jSeparator3);

        
        addProductButton = new JButton("Agregar Categoria");
        addProductButton.setBounds(105, 240, 200, 40);
        addProductButton.setBackground(new Color(17, 59, 75));
        addProductButton.setForeground(new Color(228, 202, 151));
        addProductButton.setFocusPainted(false);
        addProductButton.setBorderPainted(false);        
        addProductButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addProductButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(addProductButton);

        loadCategoryData(categoryId);

        addProductButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String nombre = categoryNameField.getText();
                String descripcion = categoryDescriptionField.getText();
                if (id.isEmpty() || nombre.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                CategoryRepository.modificarCategory(categoryId, new Category(categoryId, nombre, descripcion));

                HistorialModal historialModal = new HistorialModal(categoryId, "Modificacion", "Categoria");
                historialModal.setVisible(true);
                dispose();
            }
        });
        
    }    

    private void loadCategoryData(int categoryId) {
        Category category = CategoryRepository.obtenerCategoryPorId(categoryId);
        if (category != null) {
            idField.setText(String.valueOf(category.getId()));
            categoryNameField.setText(category.getNombre());
            categoryDescriptionField.setText(category.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(this, "Categoria no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

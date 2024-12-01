package ui.productos;

import javax.swing.*;
import javax.swing.table.*;
import classes.backCategoria.CategoryRepository;
import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backHistorial.HistorialService;
import classes.backProducto.Producto;
import classes.backProducto.ProductoRepositorio;
import classes.backProveedor.ProveedorRepositorio;
import ui.category.CategoryFrame;
import ui.proveedores.ProvidersFrame;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class InventaryFrame extends JFrame {

    private JTextField searchField;
    private DefaultTableModel tableModel;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }

    @SuppressWarnings("unused")
    public void ShowUpInventaryFrame() {
        setTitle("Inventario Actual");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(getIconImage());

        // Panel superior con botones y campo de búsqueda
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel searchPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial Narrow", Font.PLAIN, 18));

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(17, 59, 75));
        searchButton.setForeground(new Color(228, 202, 151));
        searchButton.setFocusPainted(false);
        searchButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton homeButton = new JButton("Volver al Inicio");
        homeButton.setBackground(new Color(17, 59, 75));
        homeButton.setForeground(new Color(228, 202, 151));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton addButton = new JButton("Agregar Producto");
        addButton.setBackground(new Color(255,255,255));
        addButton.setForeground(new Color(0,0,0));
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton CategoryButton = new JButton("Categorias");
        CategoryButton.setBackground(new Color(255,255,255));
        CategoryButton.setForeground(new Color(0,0,0));
        CategoryButton.setFocusPainted(false);
        CategoryButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton proveedoresButton = new JButton("Mostrar Proveedores");
        proveedoresButton.setBounds(50, 150, 300, 50);
        proveedoresButton.setBackground(new Color(255,255,255));
        proveedoresButton.setForeground(new Color(0,0,0));
        proveedoresButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        proveedoresButton.setFont(new java.awt.Font("Arial Narrow", 1, 22));


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        topPanel.add(homeButton);
        topPanel.add(addButton);
        topPanel.add(proveedoresButton);
        topPanel.add(CategoryButton);
        mainPanel.add(topPanel);
        mainPanel.add(searchPanel);



        // Configurar la tabla
        String[] columnNames = { "ID", "Nombre", "Categoría", "Cantidad", "Precio Unitario", "Fecha de Expiración","proveedor", "Acciones" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == columnNames.length - 1;
            }
        };
        actualizarTabla(ProductoRepositorio.obtenerProductos()); // Carga inicial de productos

        JTable table = new JTable(tableModel);
        table.setRowHeight(70);

        // Fuente para el encabezado
        Font headerFont = new Font("Arial Narrow", Font.BOLD, 16);
        table.getTableHeader().setFont(headerFont);

        // Fuente para el contenido de la tabla
        Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);
        table.setFont(contentFont);
        table.getTableHeader().setBackground(new Color(228, 202, 151));
        table.getTableHeader().setForeground(new Color(17, 59, 75));

        // Configuración de la columna de acciones con botones
        TableColumn actionColumn = table.getColumnModel().getColumn(columnNames.length - 1);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelConMargen.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(scrollPane);
        add(mainPanel, BorderLayout.CENTER);


        // Listeners para botones
        homeButton.addActionListener(e -> dispose());
        proveedoresButton.addActionListener(e -> new ProvidersFrame().mostrarProveedores());
        CategoryButton.addActionListener(e -> new CategoryFrame().ShowUpCategoryFrame());

        addButton.addActionListener(e -> {
            NewProductFrame newProductFrame = new NewProductFrame();
            newProductFrame.setVisible(true);
            dispose();
        });

        // Listener de búsqueda
        searchButton.addActionListener(e -> buscarProducto());

        setVisible(true);
    }

    private void actualizarTabla(List<Producto> productos) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Producto producto : productos) {
            Object[] rowData = {
                producto.getId(),
                producto.getNombre(),
                CategoryRepository.obtenerCategoryPorId(producto.getCategoria()).getNombre(),
                producto.getCantidad(),
                producto.getPrecioUnitario(),
                producto.getFechaExpiracion(),
                ProveedorRepositorio.obtenerProveedorPorId(producto.getProveedorId()).getNombre(),
                "Acciones"
            };
            tableModel.addRow(rowData);
        }
    }

    private void buscarProducto() {
        String query = searchField.getText().trim().toLowerCase();
        List<Producto> productosFiltrados = ProductoRepositorio.obtenerProductosFiltrados(query);
        actualizarTabla(productosFiltrados);
    }

    // Renderer para los botones en la tabla
    private class ButtonRenderer extends JPanel implements TableCellRenderer {
        private final JButton editButton;
        private final JButton deleteButton;

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            editButton = new JButton("Modificar");
            editButton.setBackground(new Color(17, 59, 75));
            editButton.setForeground(new Color(228, 202, 151));
            editButton.setFocusPainted(false);

            deleteButton = new JButton("Eliminar");
            deleteButton.setBackground(new Color(17, 59, 75));
            deleteButton.setForeground(new Color(228, 202, 151));
            deleteButton.setFocusPainted(false);

            add(editButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor para los botones en la tabla
    private class ButtonEditor extends DefaultCellEditor {
        protected final JPanel panel;
        private final JButton editButton;
        private final JButton deleteButton;

        @SuppressWarnings("unused")
        public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            editButton = new JButton("Modificar");
            editButton.setBackground(new Color(17, 59, 75));
            editButton.setForeground(new Color(228, 202, 151));
            editButton.setFocusPainted(false);

            deleteButton = new JButton("Eliminar");
            deleteButton.setBackground(new Color(17, 59, 75));
            deleteButton.setForeground(new Color(228, 202, 151));
            deleteButton.setFocusPainted(false);

            panel.add(editButton);
            panel.add(deleteButton);

            editButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int id = (int) tableModel.getValueAt(row, 0);
                    ProductUpdateFrame productUpdateFrame = new ProductUpdateFrame(id);
                    productUpdateFrame.setVisible(true);
                    dispose();
                }
            });

            deleteButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "¿Estás seguro de que deseas eliminar este producto?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        int id = (int) tableModel.getValueAt(row, 0);
                        ProductoRepositorio.eliminarProducto(id);
                        int historialId = HistorialService.loadHistorialId();
                        Historial historial = new Historial(historialId, "Eliminacion", LocalDate.now(), id, "Eliminacion de producto "+tableModel.getValueAt(row, 1), "Productos");
                        HistorialRepository.crearHistorial(historial);
                        actualizarTabla(ProductoRepositorio.obtenerProductos());
                        HistorialService.actualizarIds();
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

}

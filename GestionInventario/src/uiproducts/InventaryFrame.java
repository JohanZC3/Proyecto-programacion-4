package uiproducts;

import javax.swing.*;
import javax.swing.table.*;
import classes.*;
import repositorios.HistorialRepository;
import repositorios.ProductoRepositorio;
import servicios.HistorialService;
import uihistorial.HistorialFrame;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class InventaryFrame extends JFrame {

    private JTextField searchField;
    private DefaultTableModel tableModel;

    @SuppressWarnings("unused")
    public void ShowUpInventaryFrame() {
        setTitle("Inventario Actual");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Panel superior con botones y campo de búsqueda
        JPanel topPanel = new JPanel();
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
        addButton.setBackground(new Color(17, 59, 75));
        addButton.setForeground(new Color(228, 202, 151));
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton historialButton = new JButton("Historial");
        historialButton.setBackground(new Color(17, 59, 75));
        historialButton.setForeground(new Color(228, 202, 151));
        historialButton.setFocusPainted(false);
        historialButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(homeButton);
        topPanel.add(addButton);
        topPanel.add(historialButton);
        add(topPanel, BorderLayout.NORTH);

        // Configurar la tabla
        String[] columnNames = { "ID", "Nombre", "Categoría", "Cantidad", "Precio Unitario", "Fecha de Expiración", "Acciones" };
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

        add(panelConMargen, BorderLayout.CENTER);

        // Listeners para botones
        homeButton.addActionListener(e -> dispose());

        addButton.addActionListener(e -> {
            NewProductFrame newProductFrame = new NewProductFrame();
            newProductFrame.setVisible(true);
            dispose();
        });

        // Listener de búsqueda
        searchButton.addActionListener(e -> buscarProducto());

        historialButton.addActionListener(e -> {
            HistorialFrame historialFrame = new HistorialFrame();
            historialFrame.setVisible(true);
        });

        setVisible(true);
    }

    private void actualizarTabla(List<Producto> productos) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Producto producto : productos) {
            Object[] rowData = {
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getCantidad(),
                producto.getPrecioUnitario(),
                producto.getFechaExpiracion(),
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
                        HistorialService historialServicio = new HistorialService();
                        int idHistorial = historialServicio.obtenerMaxIdHistorial();
                        Historial historial = new Historial(idHistorial, "Eliminacion", LocalDate.now(), id, "Eliminacion de "+tableModel.getValueAt(row, 1));
                        HistorialRepository.crearHistorial(historial);
                        tableModel.removeRow(row);
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

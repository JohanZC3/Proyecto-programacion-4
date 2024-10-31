package uiproducts;

import javax.swing.*;
import javax.swing.table.*;
import classes.*;
import repositorios.ProductoRepositorio;

import java.awt.*;

public class InventaryFrame extends JFrame {

    public void ShowUpInventaryFrame() {
        setTitle("Inventario Actual");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Panel superior con botones
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        topPanel.add(homeButton);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // Configurar la tabla
        String[] columnNames = { "ID", "Nombre", "Categoría", "Cantidad", "Precio Unitario", "Fecha de Expiración",
                "Acciones" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == columnNames.length - 1;
            }
        };

        try {
            for (Producto producto : ProductoRepositorio.obtenerProductos()) {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el inventario: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        // Panel para añadir el margen
        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelConMargen.add(scrollPane, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);

        homeButton.addActionListener(e -> dispose());

        addButton.addActionListener(e -> {
            NewProductFrame newProductFrame = new NewProductFrame();
            newProductFrame.setVisible(true);
            dispose();
        });

        setVisible(true);
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
                    JOptionPane.showMessageDialog(null, "Modificar producto en la fila: " + row);
                }
            });

            deleteButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "¿Estás seguro de que deseas eliminar este producto?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
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

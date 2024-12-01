package ui.proveedores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.LocalDate;


import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backHistorial.HistorialService;
import classes.backProveedor.Proveedor;
import classes.backProveedor.ProveedorRepositorio;

import java.util.List;

public class ProvidersFrame extends JFrame {

    private DefaultTableModel tableModel;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }

    @SuppressWarnings("unused")
    public void mostrarProveedores() {
        setTitle("Proveedores Actuales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(getIconImage());

        // Panel superior con botones
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton homeButton = new JButton("Volver al Inicio");
        homeButton.setBackground(new Color(17, 59, 75));
        homeButton.setForeground(new Color(228, 202, 151));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton addButton = new JButton("Agregar Proveedor");
        addButton.setBackground(new Color(255,255,255));
        addButton.setForeground(new Color(0,0,0));
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        topPanel.add(homeButton);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // Configurar la tabla con la columna de acciones
        String[] columnNames = {"ID", "Nombre", "Dirección", "Teléfono", "Acciones"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == columnNames.length - 1;
            }
        };

        actualizarTabla(ProveedorRepositorio.obtenerProveedores());

        JTable table = new JTable(tableModel);
        table.setRowHeight(70);

        // Fuente para el encabezado
        Font headerFont = new Font("Arial Narrow", Font.BOLD, 16);
        table.getTableHeader().setFont(headerFont);
        table.getTableHeader().setBackground(new Color(228, 202, 151));
        table.getTableHeader().setForeground(new Color(17, 59, 75));

        // Fuente para el contenido de la tabla
        Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);
        table.setFont(contentFont);

        // Configuración de la columna de acciones con botones
        TableColumn actionColumn = table.getColumnModel().getColumn(columnNames.length - 1);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table));

        JScrollPane scrollPane = new JScrollPane(table);

        // Panel para añadir margen alrededor de la tabla
        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelConMargen.add(scrollPane, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);

        // Acción de los botones superiores
        homeButton.addActionListener(e -> dispose());
        addButton.addActionListener(e -> {
            NewProviderFrame newProviderFrame = new NewProviderFrame();
            newProviderFrame.setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    // Renderer para los botones en la columna de acciones
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

    // Editor para los botones en la columna de acciones
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
                    ProvidersUpdateFrame providersUpdateFrame = new ProvidersUpdateFrame(id);
                    providersUpdateFrame.setVisible(true);
                    dispose();
                }
            });

            deleteButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "¿Estás seguro de que deseas eliminar este proveedor?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        int id = (int) tableModel.getValueAt(row, 0);
                        System.out.println(id);
                        ProveedorRepositorio.eliminarProveedor(id);
                        int historialId = HistorialService.loadHistorialId();
                        Historial historial = new Historial(historialId, "Eliminacion", LocalDate.now(), id, "Eliminacion de producto "+tableModel.getValueAt(row, 1), "Proveedor");
                        HistorialRepository.crearHistorial(historial);
                        actualizarTabla(ProveedorRepositorio.obtenerProveedores());
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

    private void actualizarTabla(List<Proveedor> proveedores){
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Proveedor proveedor : proveedores) {
            Object[] rowData = {
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getTelefono(),
                "Acciones"
            };
            tableModel.addRow(rowData);
        }
    }
}

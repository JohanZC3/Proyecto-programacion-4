package ui.category;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import classes.backCategoria.Category;
import classes.backCategoria.CategoryRepository;
import classes.backCategoria.CategoryService;
import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backSerialId.SerialId;
import classes.backSerialId.SerialIdRepository;
import classes.backUsuario.Usuario;
import classes.backUsuario.UsuarioRepositorio;
import ui.productos.InventaryFrame;

public class CategoryFrame extends JFrame{
    private DefaultTableModel tableModel;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }

    @SuppressWarnings("unused")
    public void ShowUpCategoryFrame(int userId) {
        setTitle("categorias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(getIconImage());

        // Panel superior con botones y campo de búsqueda
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton homeButton = new JButton("Volver al inventario");
        homeButton.setBackground(new Color(17, 59, 75));
        homeButton.setForeground(new Color(228, 202, 151));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        JButton addButton = new JButton("Agregar categoria");
        addButton.setBackground(new Color(255,255,255));
        addButton.setForeground(new Color(0,0,0));
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));

        topPanel.add(homeButton);
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // Configurar la tabla
        String[] columnNames = { "ID", "Nombre", "Descripcion", "Acciones" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == columnNames.length - 1;
            }
        };
        actualizarTabla(CategoryRepository.obtenerCategories()); // Carga inicial de productos

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
        actionColumn.setCellRenderer(new ButtonRenderer(userId));
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, userId));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelConMargen.add(scrollPane, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);

        // Listeners para botones
        homeButton.addActionListener(e ->{ new InventaryFrame().ShowUpInventaryFrame(userId);dispose();});

        addButton.addActionListener(e -> { new NewCategoryFrame().setVisible(true);dispose();});

        setVisible(true);
    }

    private void actualizarTabla(List<Category> categories) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for ( Category category : categories) {
            if (category.getId() != 0){
                Object[] rowData = {
                    category.getId(),
                    category.getNombre(),
                    category.getDescripcion(),
                    "Acciones"
                };
                tableModel.addRow(rowData);
            }
        }
    }

    // Renderer para los botones en la tabla
    private class ButtonRenderer extends JPanel implements TableCellRenderer {
        private final JButton editButton;
        private final JButton deleteButton;

        public ButtonRenderer(int userId) {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            editButton = new JButton("Modificar");
            editButton.setBackground(new Color(17, 59, 75));
            editButton.setForeground(new Color(228, 202, 151));
            editButton.setFocusPainted(false);

            deleteButton = new JButton("Eliminar");
            deleteButton.setBackground(new Color(17, 59, 75));
            deleteButton.setForeground(new Color(228, 202, 151));
            deleteButton.setFocusPainted(false);

            Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorId(userId);

            if (usuario.getTipoUsuario().equals("administrador"))
            {
                add(editButton);
                add(deleteButton);
            }
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
        public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, int userId) {
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

            Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorId(userId);

            if (usuario.getTipoUsuario().equals("administrador"))
            {
                panel.add(editButton);
                panel.add(deleteButton);
            }

            editButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int id = (int) tableModel.getValueAt(row, 0);
                    UpdateCategoryFrame updateCategoryFrame = new UpdateCategoryFrame(id,userId);
                    updateCategoryFrame.setVisible(true);
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
                        CategoryService.categoryChange(id, userId);
                        CategoryRepository.eliminarCategory(id);
                        actualizarTabla(CategoryRepository.obtenerCategories());
                        int historialId = loadHistorialId();
                        Historial historial = new Historial(historialId, userId,"Eliminacion", LocalDate.now(), id, "Eliminacion de categoria "+tableModel.getValueAt(row, 1), "Categorias");
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

    private int loadHistorialId() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        int lastId = serialId.getLastidHistorial();
        return lastId;
    }

}

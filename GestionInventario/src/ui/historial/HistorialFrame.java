package ui.historial;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;

public class HistorialFrame extends JFrame{
    private DefaultTableModel tableModel;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }

    @SuppressWarnings("unused")
    public void mostrarHistorialFrame() {
        setTitle("Inventario Actual");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(getIconImage());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton homeButton = new JButton("Volver al Inventario");
        homeButton.setBackground(new Color(17, 59, 75));
        homeButton.setForeground(new Color(228, 202, 151));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial Narrow", Font.BOLD, 22));
        
        topPanel.add(homeButton);
        add(topPanel, BorderLayout.NORTH);

        homeButton.addActionListener(e -> dispose());

        String[] columnNames = { "ID", "Accion", "fecha", "id afectado", "Tabla" ,"Razon"};
        tableModel = new DefaultTableModel(columnNames, 0);
        actualizarTabla(HistorialRepository.obtenerHistoriales());

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

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelConMargen.add(scrollPane, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);

        setVisible(true);
        
    }

        private void actualizarTabla(List<Historial> historiales) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Historial historial : historiales) {
            Object[] rowData = {
                historial.getId(),
                historial.getAccion(),
                historial.getFecha(),
                historial.getIdAfectado(),
                historial.getTabla(),
                historial.getRazon()
            };
            tableModel.addRow(rowData);
        }
    }
}

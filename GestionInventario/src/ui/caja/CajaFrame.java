package ui.caja;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import classes.backProducto.Producto;
import classes.backProducto.ProductoRepositorio;

public class CajaFrame extends JFrame{
    private JLabel lblOperacion;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/logo.png"));
        return retValue;
    }
    

    @SuppressWarnings("unused")
    public void mostrarCajaFrame(int userId) {
        setTitle("Caja");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setIconImage(getIconImage());
        setVisible(true);


        lblOperacion = new JLabel("Operación:");
        lblOperacion.setBounds(50, 50, 100, 30);
        add(lblOperacion);

        String[] operaciones = {"Venta", "Compra"};
        JComboBox<String> cbOperacion = new JComboBox<>(operaciones);
        cbOperacion.setBounds(150, 50, 200, 30);
        add(cbOperacion);

        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(50, 100, 100, 30);
        add(lblProducto);

        JComboBox<String> cbProducto = new JComboBox<>();
        cbProducto.setBounds(150, 100, 200, 30);
        cbProducto.setForeground(Color.BLACK);
        cbProducto.setBackground(new Color(238, 238, 238));
        cbProducto.setBorder(null);
        cbProducto.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cbProducto.addItem("Seleccione un producto");
        for (Producto producto : ProductoRepositorio.obtenerProductos() ) {
            String item = producto.getNombre();
            cbProducto.addItem(item);
            //System.out.println(item);
        }

        add(cbProducto);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(50, 150, 100, 30);
        add(lblCantidad);


        JSpinner spCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spCantidad.setBounds(150, 150, 200, 30);
        add(spCantidad);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(100, 200, 200, 30);
        btnAceptar.setBackground(new Color(17, 59, 75));
        btnAceptar.setForeground(new Color(228, 202, 151));
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorderPainted(false);        
        btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAceptar.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            String operacion = (String) cbOperacion.getSelectedItem();
            String producto = (String) cbProducto.getSelectedItem();
            int cantidad = (int) spCantidad.getValue();
            Producto productoSeleccionado = ProductoRepositorio.obtenerProductoPorNombre(producto);
            int productoId = productoSeleccionado.getId();
            if (operacion.equals("Venta")) {
                ProductoRepositorio.actualizarVentaCantidadProducto(productoId, cantidad, userId);
                JOptionPane.showMessageDialog(null, "Venta realizada exitosamente", "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ProductoRepositorio.actualizarCompraCantidadProducto(productoId, cantidad, userId);
                JOptionPane.showMessageDialog(null, "Compra realizada exitosamente", "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });

    }
}

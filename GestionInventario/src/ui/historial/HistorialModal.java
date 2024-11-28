package ui.historial;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import javax.swing.*;

import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backHistorial.HistorialService;
import ui.LoginFrame;

public class HistorialModal extends JFrame{
    JTextField textField;
    JPanel panel;
    JLabel label;
    JLabel label2;
    JButton button;
    JSeparator separator;

    public HistorialModal(int idCambio, String accion, String tabla) {
        setTitle("Historial de Inventario");
        setSize(500, 180);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(0, 0, 500, 50);
        panel.setBackground(new java.awt.Color(199, 182, 145));
        add(panel);

        label = new JLabel("Historial de Inventario");
        label.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 30));
        label.setForeground(new Color(17, 59, 75));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());

        label2 = new JLabel("Motivo de la modificación:");
        label2.setBounds(15, 60, 300, 30);
        label2.setFont(new java.awt.Font("Arial Narrow", 1, 20));
        add(label2);

        textField = new JTextField("Ingrese el motivo de la modificación");
        textField.setBounds(15, 95, 470, 30);
        textField.setBorder(null);
        textField.setBackground(new Color(238, 238, 238));
        textField.setForeground(new Color(160, 160, 160));
        add(textField);

        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (textField.getText().equals("Ingrese el motivo de la modificación")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Ingrese el motivo de la modificación");
                    textField.setForeground(new Color(160, 160, 160));
                }
            }
        });
        
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });

        button = new JButton("Guardar");
        button.setBounds(150, 135, 200, 30);        
        button.setBackground(new Color(17, 59, 75));
        button.setForeground(new Color(228, 202, 151));
        button.setFocusPainted(false);
        button.setBorderPainted(false);        
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new java.awt.Font("Arial Narrow", 1, 22));
        add(button);

        separator = new JSeparator();
        separator.setBounds(15, 125, 470, 5);
        separator.setForeground(new Color(17, 59, 75));
        add(separator);

        setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String motivo = new String(textField.getText());
                motivo = (!motivo.equals("Ingrese el motivo de la modificación")) ? motivo : "Sin motivo";
                // Guardar en la base de datos
                int idHistorial = HistorialService.loadHistorialId();
                System.out.println("ID Historial: " + idHistorial + ", Accion: " + accion + ", Fecha: " + LocalDate.now() + ", ID Producto: " + idCambio + ", Motivo: " + motivo);
                Historial historial = new Historial(idHistorial, accion, LocalDate.now(), idCambio, motivo, tabla);
                HistorialRepository.crearHistorial(historial);
                HistorialService.actualizarIds();
                JOptionPane.showMessageDialog(null, "Modificado exitosamente", "Modificado", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

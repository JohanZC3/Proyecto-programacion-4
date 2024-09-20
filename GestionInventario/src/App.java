import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import classes.*;
import ui.LoginFrame;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioRepositorio.crearUsuario(new Usuario(1, "Juan", "Perez", "CC", "12345", "juan@example.com", "555-1234", true));
            UsuarioRepositorio.crearUsuario(new Usuario(2, "Maria", "Lopez", "CC", "67890", "maria@example.com", "555-5678", true));

            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}


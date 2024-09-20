import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import classes.*;
import ui.LoginFrame;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}


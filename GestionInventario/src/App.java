import javax.swing.SwingUtilities;
import ui.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrincipalFrame("juan@example.com").setVisible(true);
            /*LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);*/
        });
    }
}


package MainClass;


import javax.swing.*;
import AdminView.VtnPrincipalAdmin;

public class LoginFrame {

    public static void main(String[] args) {

        seleccionarLookAndField();

        SwingUtilities.invokeLater(() -> {
            VtnPrincipalAdmin adminWindow = new VtnPrincipalAdmin();
            adminWindow.setVisible(true);
        });
    }

    private static void seleccionarLookAndField() {
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            }
        }
    }
}

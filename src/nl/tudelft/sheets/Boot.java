package nl.tudelft.sheets;

import nl.tudelft.sheets.view.Application;

import javax.swing.*;

/**
 * Created by Jasper on 10/30/2015.
 */
public class Boot {

    public static void main(final String[] args) {
        final Application application = new Application("Sheets");
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            application.setVisible(true);
        });
    }
}

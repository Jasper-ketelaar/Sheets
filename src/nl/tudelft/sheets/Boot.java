package nl.tudelft.sheets;

import nl.tudelft.sheets.view.Application;

import javax.swing.*;

/**
 * Controller class; boots the entire epplication
 *
 * Created by Jasper on 10/30/2015.
 */
public class Boot {

    /**
     * Creates a new instance of the application and sets the L&F to Nimbus L&F if intalled; if not it will set the L&F
     * to the default system L&F. After setting the L&F the application will become visible and the controller has done
     * it's job.
     *
     * @param args disposable program arguments
     */
    public static void main(final String[] args) {
        final Application application = new Application("Sheets");
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                for (final UIManager.LookAndFeelInfo feel : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(feel.getName())) {
                        UIManager.setLookAndFeel(feel.getClassName());
                        final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
                        defaults.put("Table.showGrid", true);
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            application.setVisible(true);
        });

    }
}

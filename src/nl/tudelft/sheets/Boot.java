package nl.tudelft.sheets;

import nl.tudelft.sheets.model.xml.XMLFile;
import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;
import nl.tudelft.sheets.view.Application;

import javax.swing.*;
import java.io.IOException;

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

       /* try {
            final XMLFile file = XMLFile.parse("test.xml");
            System.out.println(file.getElementCount());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidXMLFormatException e) {
            e.printStackTrace();
        }*/
    }
}

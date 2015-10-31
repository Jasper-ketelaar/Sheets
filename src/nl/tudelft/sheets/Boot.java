package nl.tudelft.sheets;

import com.sun.javafx.event.EventUtil;
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                application.setVisible(true);
            }
        });
    }
}

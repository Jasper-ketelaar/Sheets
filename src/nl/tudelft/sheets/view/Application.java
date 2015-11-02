package nl.tudelft.sheets.view;

import nl.tudelft.sheets.view.components.ContentPanel;
import nl.tudelft.sheets.view.components.listener.ResizeListener;
import nl.tudelft.sheets.view.components.menu.MenuBar;
import nl.tudelft.sheets.view.components.table.SheetsTable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class Application extends JFrame {

    public Application(final String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new ContentPanel());
        this.pack();
        this.addComponentListener(new ResizeListener(this));
        this.setJMenuBar(new MenuBar(this));

    }

    public SheetsTable getTable() {
        for (final Component comp : this.getContentPane().getComponents()) {
            if (comp instanceof JScrollPane) {
                for (final Component comp2 : ((JScrollPane) comp).getViewport().getComponents()) {
                    if (comp2 instanceof SheetsTable) {
                        return (SheetsTable) comp2;
                    }
                }
            }
        }
        return null;
    }
}

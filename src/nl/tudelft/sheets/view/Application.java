package nl.tudelft.sheets.view;

import nl.tudelft.sheets.view.components.ContentPanel;
import nl.tudelft.sheets.view.components.TabbedPane;
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
        this.setContentPane(new TabbedPane(this));
        this.pack();
        this.addComponentListener(new ResizeListener(this));
        this.setJMenuBar(new MenuBar(this));

    }

    public JTabbedPane getJTabbedPane() {
        return (JTabbedPane) this.getContentPane();
    }
}

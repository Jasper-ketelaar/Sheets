package nl.tudelft.sheets.view;

import nl.tudelft.sheets.view.components.tabs.TabbedPane;
import nl.tudelft.sheets.view.listener.ResizeListener;
import nl.tudelft.sheets.view.components.menu.MenuBar;

import javax.swing.*;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class Application extends JFrame {

    public Application(final String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final TabbedPane pane = new TabbedPane(this);
        this.setContentPane(pane);
        this.pack();
        this.addComponentListener(new ResizeListener(this));
        this.setJMenuBar(new MenuBar(this));

    }

    public JTabbedPane getJTabbedPane() {
        return (JTabbedPane) this.getContentPane();
    }
}

package nl.tudelft.sheets.view;

import nl.tudelft.sheets.view.components.tabs.TabbedPane;
import nl.tudelft.sheets.view.listener.ResizeListener;
import nl.tudelft.sheets.view.components.menu.MenuBar;

import javax.swing.*;

/**
 * Application; main view to which both the tabbed pane and the menu bar are added
 *
 * Created by jasperketelaar on 10/31/15.
 */
public class Application extends JFrame {

    /**
     * Application constrcutor, calls super with name argument and sets default close operation.
     * Initializes tabbed pane component and adds the menu bar.
     *
     * @param name the name/title of the application
     */
    public Application(final String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final TabbedPane pane = new TabbedPane(this);
        this.setContentPane(pane);
        this.pack();

        this.addComponentListener(new ResizeListener(this));

        this.setJMenuBar(new MenuBar(this));
    }

    /**
     * Gets the tabbed pane by casting the content pane instance to JTabbedPane
     * @return JTabbedPane instance
     */
    public JTabbedPane getJTabbedPane() {
        return (JTabbedPane) this.getContentPane();
    }
}

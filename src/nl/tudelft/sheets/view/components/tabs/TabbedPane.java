package nl.tudelft.sheets.view.components.tabs;


import nl.tudelft.sheets.view.Application;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * A class to create the tabs; has no unique functionality but is created anyways for future functionality yet
 * to be implemented.
 * </p>
 * <p>
 * Created by Jasper on 11/3/2015.
 */
public class TabbedPane extends JTabbedPane {


    /**
     * The parent of this JTabbedPane (which is an Application instance), currently not used but is here for future
     * usage
     */
    private final Application parent;

    /**
     * TabbedPane constructor that takes an Application instance as parameter. Also sets the position of the tabs at
     * the bottom of the application and sets the initial size of the application.
     *
     * @param parent the parent of this JTabbedPane which is an Application instance
     */
    public TabbedPane(final Application parent) {
        super(JTabbedPane.BOTTOM);
        setPreferredSize(new Dimension(500, 400));
        this.parent = parent;

    }

    /**
     * Overrides addTab from JTabbedPane to make it select the newly added component.
     *
     * @param name      the name of the new tab
     * @param component the component which this new tab should show
     */
    @Override
    public void addTab(final String name, final Component component) {
        super.addTab(name, component);
        this.setSelectedComponent(component);

    }
}

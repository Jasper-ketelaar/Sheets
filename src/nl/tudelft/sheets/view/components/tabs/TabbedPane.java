package nl.tudelft.sheets.view.components.tabs;


import nl.tudelft.sheets.view.Application;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Created by Jasper on 11/3/2015.
 */
public class TabbedPane extends JTabbedPane {

    private final Application parent;

    public TabbedPane(final Application parent) {
        super(JTabbedPane.BOTTOM);
        setPreferredSize(new Dimension(500, 400));
        this.parent = parent;

    }

    public Application getApplication() {
        return parent;
    }

    @Override
    public void addTab(final String name, final Component component) {
        super.addTab(name, component);
        this.setSelectedComponent(component);
    }

    public class RightClick extends MouseAdapter {

    }
}

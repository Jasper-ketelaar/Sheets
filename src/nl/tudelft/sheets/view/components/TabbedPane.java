package nl.tudelft.sheets.view.components;


import nl.tudelft.sheets.view.Application;
import nl.tudelft.sheets.view.components.table.SheetsTable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jasper on 11/3/2015.
 */
public class TabbedPane extends JTabbedPane {

    private final Application parent;

    public TabbedPane(final Application parent) {
        setPreferredSize(new Dimension(500, 400));
        this.parent = parent;
    }

    @Override
    public void addTab(final String name, final Component container) {
        super.addTab(name, container);
        this.setSelectedComponent(container);
        this.setPreferredSize(container.getPreferredSize());
        this.setSize(container.getPreferredSize());
        this.parent.setSize(container.getPreferredSize());
        this.repaint();


        System.out.println(container.getPreferredSize());
        System.out.println(getPreferredSize());
        System.out.println(getSize());
    }
}

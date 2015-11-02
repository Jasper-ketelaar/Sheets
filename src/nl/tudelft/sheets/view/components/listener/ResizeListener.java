package nl.tudelft.sheets.view.components.listener;

import nl.tudelft.sheets.view.components.table.SheetsTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by jasperketelaar on 11/1/15.
 */
public class ResizeListener extends ComponentAdapter {

    private final JPanel panel;
    private final JScrollPane scroll;

    public ResizeListener(final JFrame component) {
        this.panel = (JPanel) component.getContentPane();
        this.scroll = (JScrollPane) panel.getComponent(0);
    }

    @Override
    public void componentResized(final ComponentEvent evt) {
        System.out.println("evt = [" + evt.getComponent().getSize() + "]");
        final Dimension size = evt.getComponent().getSize();
        scroll.setPreferredSize(size);
    }
}

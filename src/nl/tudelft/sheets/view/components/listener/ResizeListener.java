package nl.tudelft.sheets.view.components.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by jasperketelaar on 11/1/15.
 */
public class ResizeListener extends ComponentAdapter {

    private final JTabbedPane panel;


    public ResizeListener(final JFrame component) {
        this.panel = (JTabbedPane) component.getContentPane();
    }

    @Override
    public void componentResized(final ComponentEvent evt) {

        for (final Component comp : panel.getComponents()) {
            final JScrollPane scroll = (JScrollPane) ((JPanel) comp).getComponent(0);
            final Dimension size = evt.getComponent().getSize();
            scroll.setPreferredSize(size);
            scroll.repaint();
        }
    }
}

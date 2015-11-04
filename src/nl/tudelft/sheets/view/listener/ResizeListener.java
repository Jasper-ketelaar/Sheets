package nl.tudelft.sheets.view.listener;

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
            if (!(comp instanceof JScrollPane)) continue;
            final JScrollPane scroll = (JScrollPane) comp;
            final Dimension size = evt.getComponent().getSize();
            scroll.setPreferredSize(size);
            scroll.getParent().setPreferredSize(new Dimension(size.width + 50, size.height));

        }
    }
}

package nl.tudelft.sheets.view.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * <p>
 * Listens a resize event of the main application. If this event occurs all of the components of the JTabbedPane
 * that the application has set as content panel will be resized accordingly. Extends ComponentAdapter instead of
 * implementing ComponentListener for simplicity and compactness.
 * </p>
 * <p>
 * Created by jasperketelaar on 11/1/15.
 */
public class ResizeListener extends ComponentAdapter {

    /**
     * The JTabbedPane which contains these components
     */
    private final JTabbedPane panel;


    /**
     * ResizeListener constructor, takes a JFrame as parameter gets the JTabbedPane by assuming it is the content panel
     * of the frame.
     *
     * @param component the JFrame which is supposed to have a JTabbedPane as content panel
     */
    public ResizeListener(final JFrame component) {
        this.panel = (JTabbedPane) component.getContentPane();
    }

    /**
     * <p>
     * Gets called when the application (the component this even is bound to) gets resized, will go through every
     * component the application has. If this component is an instance of a JScrollPane (Which are the only components
     * that need to be resized), this component is casted to a JScrollPane and the size of this scroll pane is then
     * set to the current sizeof the main component.
     * </p>
     *
     * @param evt the event containing information such as the source of the event which we need to get the size of
     *            the resized component.
     */
    @Override
    public void componentResized(final ComponentEvent evt) {
        for (final Component comp : panel.getComponents()) {
            if (comp instanceof JScrollPane) {
                final JScrollPane scroll = (JScrollPane) comp;
                final Dimension size = evt.getComponent().getSize();
                scroll.setPreferredSize(size);
            }
        }
    }
}

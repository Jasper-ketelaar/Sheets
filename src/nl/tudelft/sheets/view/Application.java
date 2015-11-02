package nl.tudelft.sheets.view;

import nl.tudelft.sheets.view.components.ContentPanel;
import nl.tudelft.sheets.view.components.listener.ResizeListener;

import javax.swing.*;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class Application extends JFrame {

    public Application(final String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new ContentPanel());
        this.pack();
        this.addComponentListener(new ResizeListener(this));

    }
}

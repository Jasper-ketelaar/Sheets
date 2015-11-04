package nl.tudelft.sheets.view.components.table.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Jasper on 11/4/2015.
 */
public class KeyInput extends KeyAdapter {

    @Override
    public void keyTyped(final KeyEvent evt) {
        System.out.println(evt.getKeyCode());
        System.out.println(evt.getComponent());
    }
}

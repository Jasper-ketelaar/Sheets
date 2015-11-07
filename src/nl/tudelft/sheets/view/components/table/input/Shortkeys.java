package nl.tudelft.sheets.view.components.table.input;

import nl.tudelft.sheets.model.sheet.cell.Cell;
import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.model.SheetsTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Jasper on 11/4/2015.
 */
public class Shortkeys extends KeyAdapter {

    private final SheetsTable table;

    public Shortkeys(final SheetsTable table) {
        this.table = table;
    }

    @Override
    public void keyPressed(final KeyEvent evt) {
        if (evt.isControlDown()) {
            if (evt.getKeyCode() == KeyEvent.VK_C) {
                table.copy(false);
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                table.paste();
            } else if (evt.getKeyCode() == KeyEvent.VK_X) {
                table.copy(true);

            }
        }
    }
}

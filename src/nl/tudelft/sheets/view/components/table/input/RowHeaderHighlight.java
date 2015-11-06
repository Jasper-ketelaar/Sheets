package nl.tudelft.sheets.view.components.table.input;

import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.row.RowHeaderPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Jasper on 11/6/2015.
 */
public class RowHeaderHighlight extends MouseAdapter {

    private final RowHeaderPanel rowHeaderPanel;
    private final SheetsTable sheetsTable;

    public RowHeaderHighlight(final RowHeaderPanel rowHeaderPanel, final SheetsTable sheetsTable) {
        this.rowHeaderPanel = rowHeaderPanel;
        this.sheetsTable = sheetsTable;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final Component[] comp = rowHeaderPanel.getComponents();
        for(int i = 0; i < sheetsTable.getRowCount(); i++) {
            if(sheetsTable.isRowSelected(i)) {
                comp[i].setFont(comp[i].getFont().deriveFont(Font.BOLD));
            } else {
                comp[i].setFont(comp[i].getFont().deriveFont(Font.PLAIN));
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mousePressed(e);
    }



}


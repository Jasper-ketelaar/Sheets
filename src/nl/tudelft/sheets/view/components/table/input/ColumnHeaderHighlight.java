package nl.tudelft.sheets.view.components.table.input;

import nl.tudelft.sheets.view.components.table.SheetsTable;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

/**
 * Created by Jasper on 11/6/2015.
 */
public class ColumnHeaderHighlight extends MouseAdapter {

    private final JTableHeader columnHeader;
    private final SheetsTable table;

    public ColumnHeaderHighlight(final JTableHeader columnHeader, final SheetsTable table) {
        this.columnHeader = columnHeader;
        this.table = table;
    }

    private void clearColumnHighlight() {
        final Enumeration<TableColumn> en = columnHeader.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            final TableColumn next = en.nextElement();
            if (!table.isColumnSelected(next.getModelIndex()))
                next.setHeaderValue(next.getHeaderValue().toString().replace("<html><b>", "").replace("</b></html>", ""));
        }
    }

    @Override
    public void mousePressed(final MouseEvent evt) {
        final Enumeration<TableColumn> en = columnHeader.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            final TableColumn next = en.nextElement();
            if (!table.isColumnSelected(next.getModelIndex()))
                next.setHeaderValue(next.getHeaderValue().toString().replace("<html><b>", "").replace("</b></html>", ""));
            else
                next.setHeaderValue("<html><b>" + next.getHeaderValue().toString() + "</b></html>");
        }
        columnHeader.updateUI();
    }

    @Override
    public void mouseDragged(final MouseEvent evt) {
        this.mousePressed(evt);
    }
}

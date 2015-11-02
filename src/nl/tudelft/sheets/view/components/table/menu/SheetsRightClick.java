package nl.tudelft.sheets.view.components.table.menu;

import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.SheetsTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jasperketelaar on 11/1/15.
 */
public class SheetsRightClick extends MouseAdapter {

    private final PopupMenu popup;
    private final SheetsTableModel model;
    private final SheetsTable table;

    public SheetsRightClick(final SheetsTable table) {
        this.popup = new PopupMenu();
        this.model = (SheetsTableModel) table.getModel();
        this.table = table;
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON3) {
            popup.show(event.getComponent(), event.getX(), event.getY());
        } else if (event.getButton() == MouseEvent.BUTTON1) {
            if (!table.getBounds().contains(event.getPoint())) {
                final int row = table.getSelectedRow();
                final int col = table.getSelectedColumn();
                table.clearSelection();
                if ((row & col) != -1)
                    table.getCellEditor(row, col).stopCellEditing();
            }
        }
    }

    public class PopupMenu extends JPopupMenu {

        public PopupMenu() {
            add(addRow());
            add(removeRow());
        }

        public JMenuItem addRow() {
            final JMenuItem item = new JMenuItem("Add row");
            item.addActionListener(e -> {
                model.newRow();
                table.clearSelection();
            });
            return item;
        }

        public JMenuItem removeRow() {
            final JMenuItem item = new JMenuItem("Remove row");
            item.addActionListener(e -> {
                table.clearSelection();
                int row = table.getSelectedRow();
                if (row == -1) {
                    row = model.getRowCount() - 1;
                }
                model.removeRow(row);

            });
            return item;
        }
    }
}

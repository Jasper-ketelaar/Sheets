package nl.tudelft.sheets.view.components.table.input;

import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.model.SheetsTableModel;

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
        this.model = table.getModel();
        this.table = table;
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON3) {
            if(table.getSelectedRowCount() > 0) {
                popup.setEditEnabled(true);
                ((JMenuItem)popup.getComponent(1)).setText("Remove selected row" + (table.getSelectedRowCount() > 1 ? "s" : ""));
            } else {
                popup.setEditEnabled(false);
                ((JMenuItem)popup.getComponent(1)).setText("Remove last row");
            }
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
            add(copy());
            add(paste());
            add(cut());
        }

        public void setEditEnabled(final boolean edit) {
            getComponent(2).setEnabled(edit);
            getComponent(3).setEnabled(edit);
            getComponent(4).setEnabled(edit);
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
            final JMenuItem item = new JMenuItem();
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

        public JMenuItem copy() {
            final JMenuItem copy = new JMenuItem("Copy selected cells (Ctrl + c)");
            copy.addActionListener(e -> {
                table.copy(false);
            });
            return copy;
        }

        public JMenuItem paste() {
            final JMenuItem paste = new JMenuItem("Paste into selected cells (Ctrl + v)");
            paste.addActionListener(e -> {
                table.paste();
            });
            return paste;
        }

        public JMenuItem cut() {
            final JMenuItem cut = new JMenuItem("Cut selected cells (Ctrl + x)");
            cut.addActionListener(e -> {
                table.copy(true);
            });
            return cut;
        }


    }
}

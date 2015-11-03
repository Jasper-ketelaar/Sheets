package nl.tudelft.sheets.view.components;

import nl.tudelft.sheets.view.components.menu.*;
import nl.tudelft.sheets.view.components.menu.MenuBar;
import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.SheetsTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class ContentPanel extends JPanel {

    private final SheetsTable table;

    public ContentPanel() {
        this.table = new SheetsTable();

        this.add(table.getScrollPane(), BorderLayout.CENTER);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        final Dimension pref = table.getScrollPane().getPreferredSize();
        setPreferredSize(new Dimension(pref.width, pref.height));

        System.out.println(table.getScrollPane().getViewport().getPreferredSize());
    }

    public SheetsTable getTable() {
        return table;
    }
}

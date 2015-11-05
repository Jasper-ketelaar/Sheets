package nl.tudelft.sheets.view.components.table.cell;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;


/**
 * Created by Jasper on 11/2/2015.
 */
public class CellRenderer extends DefaultTableCellRenderer {

    @Override
    public JComponent getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(Color.WHITE);

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(!isSelected) {
            setBorder(noFocusBorder);
        }

        return this;
    }
}

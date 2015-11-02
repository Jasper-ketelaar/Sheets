package nl.tudelft.sheets.view.components.table;

import nl.tudelft.sheets.view.components.table.menu.SheetsRightClick;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class SheetsTable extends JTable {

    private final JScrollPane scrollPane;

    public SheetsTable() {
        super(new SheetsTableModel());
        super.setGridColor(Color.BLACK);

        this.scrollPane = new JScrollPane(this);

        final SheetsRightClick listener = new SheetsRightClick(this);
        this.addMouseListener(listener);

        this.scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        this.scrollPane.getViewport().addMouseListener(listener);


    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    @Override
    public Component prepareRenderer(final TableCellRenderer renderer, int row, int column) {
        final Component comp = super.prepareRenderer(renderer, row, column);
        if (!isRowSelected(row))
            comp.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);

        return comp;
    }
}

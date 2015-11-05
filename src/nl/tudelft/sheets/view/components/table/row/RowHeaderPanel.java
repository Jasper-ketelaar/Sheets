package nl.tudelft.sheets.view.components.table.row;

import nl.tudelft.sheets.view.components.table.SheetsTable;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

/**
 * Created by Jasper on 11/5/2015.
 */
public class RowHeaderPanel extends JPanel implements TableModelListener {

    private final SheetsTable table;

    public RowHeaderPanel(final SheetsTable table) {
        super(null);
        table.getModel().addTableModelListener(this);
        this.table = table;
        update();

    }

    public void update() {
        this.removeAll();
        this.setPreferredSize(new Dimension(30, table.getRowCount() * table.getRowHeight()));
        for (int i = 0; i < table.getRowCount(); i++) {

            final JLabel label = new JLabel(Integer.toString(i + 1), SwingConstants.CENTER);

            label.setBorder(table.getBorder());
            label.setBounds(0, (i * table.getRowHeight()) - 1, 30, table.getRowHeight());
            final int bottom = i == table.getRowCount() - 1 ? 1 : 0;

            label.setBorder(BorderFactory.createMatteBorder(1, 1, bottom, 1, table.getGridColor()));
            this.add(label);
        }
        repaint();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
            update();
        }
    }
}

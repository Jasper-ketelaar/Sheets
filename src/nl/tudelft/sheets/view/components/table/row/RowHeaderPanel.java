package nl.tudelft.sheets.view.components.table.row;

import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.input.RowHeaderHighlight;

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
        this.table = table;
        this.table.getModel().addTableModelListener(this);
        final RowHeaderHighlight highlight = new RowHeaderHighlight(this, table);

        this.table.getScrollPane().getViewport().addMouseListener(highlight);
        this.table.getScrollPane().getViewport().addMouseMotionListener(highlight);
        this.table.addMouseListener(highlight);
        this.table.addMouseMotionListener(highlight);

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

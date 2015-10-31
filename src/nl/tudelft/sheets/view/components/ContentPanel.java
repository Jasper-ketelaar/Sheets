package nl.tudelft.sheets.view.components;

import nl.tudelft.sheets.view.components.table.SheetsTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class ContentPanel extends JPanel {

    public ContentPanel() {
        final SheetsTableModel model = new SheetsTableModel();
        final JTable table = new JTable(model);

        final JScrollPane scroller = new JScrollPane(table);
        this.add(scroller, BorderLayout.CENTER);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        System.out.println(scroller.getPreferredSize());
        setPreferredSize(scroller.getPreferredSize());

    }
}

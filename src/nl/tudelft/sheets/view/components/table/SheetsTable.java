package nl.tudelft.sheets.view.components.table;

import nl.tudelft.sheets.model.sheet.Sheet;
import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;
import nl.tudelft.sheets.view.components.table.cell.CellRenderer;
import nl.tudelft.sheets.view.components.table.input.SheetsRightClick;
import nl.tudelft.sheets.view.components.table.model.SheetsTableModel;
import nl.tudelft.sheets.view.components.table.row.RowHeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * Created by jasperketelaar on 10/31/15.
 */
public class SheetsTable extends JTable {

    private final JScrollPane scrollPane;
    private final RowHeaderPanel panel;


    public SheetsTable() {
        super(new SheetsTableModel());
        super.setGridColor(Color.LIGHT_GRAY);

        this.scrollPane = new JScrollPane(this);
        this.panel = new RowHeaderPanel(this);

        final JViewport vp = new JViewport();
        vp.setViewSize(scrollPane.getViewport().getViewSize());
        vp.setView(panel);
        this.scrollPane.setRowHeader(vp);


        final SheetsRightClick listener = new SheetsRightClick(this);
        this.addMouseListener(listener);

        this.scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        this.scrollPane.getViewport().addMouseListener(listener);
        this.setDefaultRenderer(Object.class, new CellRenderer());
        this.getTableHeader().setReorderingAllowed(false);
    }


    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void load(final String file) {
        try {
            final Sheet sheet = Sheet.parse(file);
            getModel().load(sheet);
            panel.update();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidXMLFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SheetsTableModel getModel() {
        return (SheetsTableModel) super.getModel();
    }


}

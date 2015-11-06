package nl.tudelft.sheets.view.components.table;

import nl.tudelft.sheets.model.sheet.Sheet;
import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;
import nl.tudelft.sheets.view.components.table.cell.CellRenderer;
import nl.tudelft.sheets.view.components.table.input.ColumnHeaderHighlight;
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
        setGridColor(Color.LIGHT_GRAY);
        setDefaultRenderer(Object.class, new CellRenderer());
        getTableHeader().setReorderingAllowed(false);
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(true);

        final SheetsRightClick listener = new SheetsRightClick(this);
        addMouseListener(listener);

        final ColumnHeaderHighlight  columnHeaderHighlight = new ColumnHeaderHighlight(tableHeader, this);

        this.scrollPane = new JScrollPane(this);
        this.scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        this.scrollPane.getViewport().addMouseListener(listener);

        this.scrollPane.getViewport().getView().addMouseListener(columnHeaderHighlight);
        this.scrollPane.getViewport().getView().addMouseMotionListener(columnHeaderHighlight);
        this.scrollPane.getViewport().addMouseListener(columnHeaderHighlight);

        this.panel = new RowHeaderPanel(this);
        final JViewport vp = new JViewport();
        vp.setViewSize(scrollPane.getViewport().getViewSize());
        vp.setView(panel);
        this.scrollPane.setRowHeader(vp);

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

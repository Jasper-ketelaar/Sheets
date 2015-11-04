package nl.tudelft.sheets.view.components.table;

import nl.tudelft.sheets.model.data.SheetsCell;
import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;
import nl.tudelft.sheets.model.xml.read.SheetParser;
import nl.tudelft.sheets.view.components.table.cell.CellRenderer;
import nl.tudelft.sheets.view.components.table.input.KeyInput;
import nl.tudelft.sheets.view.components.table.input.SheetsRightClick;
import nl.tudelft.sheets.view.components.table.model.SheetsTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

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
        this.setDefaultRenderer(Object.class, new CellRenderer());
        this.getScrollPane().getViewport().addKeyListener(new KeyInput());
    }


    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void load(final String file) {
        try {
            final String[] columnNames = SheetParser.getColumnNames(file);
            final ArrayList<SheetsCell[]> cells = SheetParser.parseCellsFromFile(file);
            this.getModel().load(columnNames, cells);
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

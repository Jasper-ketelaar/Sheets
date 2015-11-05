package nl.tudelft.sheets.view.components.table.model;


import nl.tudelft.sheets.model.sheet.Sheet;
import nl.tudelft.sheets.model.sheet.cell.Cell;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class SheetsTableModel extends AbstractTableModel {

    private final Sheet sheet;
    private ArrayList<String> columnNames;

    public SheetsTableModel(final int columns, final int rows) {
        this.columnNames = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            columnNames.add(Character.toString((char) (i + 65)));
        }

        this.sheet = new Sheet();
        initRows(rows);
    }

    public SheetsTableModel() {
        this(5, 5);
    }


    private void initRows(final int rows) {
        for (int i = 0; i < rows; i++) {
            newRow();
        }
    }


    /**
     * Overridden to make every cell editable
     *
     * @return true always
     */
    @Override
    public boolean isCellEditable(final int column, final int row) {
        return true;
    }

    @Override
    public int getRowCount() {
        return sheet.getRows().size();
    }

    @Override
    public String getColumnName(final int column) {
        return columnNames.get(column);
    }

    public void newRow() {
        addRow(new Object[0]);
    }

    public void addRow(final Object[] data) {
        final int row = this.sheet.getRows().size();
        final Cell[] cells = generate();
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
            if (data[i] == null)
                cells[i].setContent("");
            else
                cells[i].setContent(data[i]);
        }
        sheet.getRows().add(cells);
        this.fireTableRowsInserted(row - 1, row - 1);
    }

    private Cell[] generate() {
        final Cell[] cells = new Cell[columnNames.size()];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
        return cells;
    }

    public void removeRow(final int row) {
        sheet.getRows().remove(sheet.getRows().get(row));
        fireTableRowsDeleted(row, row);
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    public void load(final Sheet sheet) {
        this.sheet.getRows().clear();
        for (final Cell[] row : sheet.getRows()) {
            this.sheet.getRows().add(row);
        }
        fireTableStructureChanged();


    }

    @Override
    public void setValueAt(final Object value, final int row, final int column) {
        final Cell[] data = sheet.getRows().get(row);
        if (column > data.length - 1) {
            final Cell[] upgr = new Cell[data.length + 1];
            upgr[column] = new Cell();
            upgr[column].setContent(value);
            for (int i = 0; i < data.length; i++) {
                upgr[i] = data[i];
            }
            sheet.getRows().set(row, upgr);
        } else {
            data[column].setContent(value);
        }
        fireTableCellUpdated(row, column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return sheet.getValueAt(rowIndex, columnIndex);
    }
}

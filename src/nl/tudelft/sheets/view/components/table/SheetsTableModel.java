package nl.tudelft.sheets.view.components.table;


import nl.tudelft.sheets.model.data.SheetsCell;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class SheetsTableModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<SheetsCell[]> data = new ArrayList<>();

    public SheetsTableModel(final int columns, final int rows) {
        this.columnNames = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            columnNames.add(Character.toString((char) (i + 65)));
        }

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

    public void setName(final int column, final String name) {
        columnNames.set(column, name);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(final int column) {
        return columnNames.get(column);
    }

    public void newRow() {
        addRow(new Object[0]);
    }

    public void addRow(final Object[] data) {
        final int row = this.data.size();
        final SheetsCell[] cells = generate(row);
        for (int i = 0; i < data.length; i++) {
            cells[i].setContent(data[i]);
        }
        this.data.add(cells);
        this.fireTableRowsInserted(row - 1, row - 1);
    }

    private SheetsCell[] generate(final int row) {
        final SheetsCell[] cells = new SheetsCell[columnNames.size()];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new SheetsCell(row, i);
        }
        return cells;
    }

    public void removeRow(final int row) {
        this.data.remove(data.get(row));
        fireTableRowsDeleted(row, row);
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Class getColumnClass(int c) {
        try {
            return getValueAt(0, c).getClass();
        } catch (Exception e) {
            System.out.println(getValueAt(0, c));
        }
        return getValueAt(0, c).getClass();
    }


    public void load(final String[] columnNames, final ArrayList<SheetsCell[]> data) {
        this.columnNames.clear();
        this.data.clear();
        for(final String str : columnNames) {
            this.columnNames.add(str);
        }
        this.data = data;
        fireTableStructureChanged();


    }

    @Override
    public void setValueAt(final Object value, final int row, final int column) {
        final SheetsCell[] data = this.data.get(row);
        data[column].setContent(value);
        fireTableCellUpdated(row, column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex].getContent();
    }
}

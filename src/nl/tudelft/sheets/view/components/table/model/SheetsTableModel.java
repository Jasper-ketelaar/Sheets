package nl.tudelft.sheets.view.components.table.model;


import nl.tudelft.sheets.model.sheet.Sheet;
import nl.tudelft.sheets.model.sheet.cell.Cell;
import nl.tudelft.sheets.view.components.table.SheetsTable;

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

    public Sheet getSheet() {
        return this.sheet;
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
        addRow(new Object[5]);
    }

    public void addRow(final Object[] data) {
        final int row = this.sheet.getRows().size();
        final Cell[] cells = generate();
        for (int i = 0; i < data.length; i++) {
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


    public void paste(final Cell[][] data, final SheetsTable table) {
        if (data.length > 0) {
            if (table.getSelectedRowCount() > 0) {
                final int startRow = table.getSelectedRows()[0];
                final int startCol = table.getSelectedColumns()[0];
                for (int i = startRow; i < startRow + data.length; i++) {
                    for (int i2 = startCol; i2 < startCol + data[i - startRow].length; i2++) {
                        if (i >= table.getRowCount() || i2 >= table.getColumnCount())
                            continue;
                        this.setValueAt(data[i - startRow][i2 - startCol].getContent(), i, i2);
                    }
                }
            }
        }
    }

    public Cell[][] getSelectedContent(final SheetsTable table, final boolean delete) {
        final Cell[][] data = new Cell[table.getSelectedRowCount()][table.getSelectedColumnCount()];
        if (data.length == 0) {
            return data;
        } else {
            for (int i = 0; i < data.length; i++) {
                for (int i2 = 0; i2 < data[i].length; i2++) {
                    final Cell cell = table.getCellAt(i + table.getSelectedRows()[0], i2 + table.getSelectedColumns()[0]);
                    data[i][i2] = new Cell();
                    data[i][i2].setContent(cell.getContent());
                    if (delete) {
                        cell.setContent("");
                    }
                }
            }
            final int[] rowsSelected = table.getSelectedRows();
            final int[] colsSelected = table.getSelectedColumns();

            this.fireTableDataChanged();
            table.setRowSelectionInterval(rowsSelected[0], rowsSelected[rowsSelected.length - 1]);
            table.setColumnSelectionInterval(colsSelected[0], colsSelected[colsSelected.length - 1]);

            return data;
        }
    }

    public void load(final Sheet sheet) {
        this.sheet.getRows().clear();


        final int cols = columnsRequired(sheet);
        if (cols > 5) {
            for (int i = 0; i < cols - 5; i++) {
                this.columnNames.add(Character.toString((char) (i + 70)));
            }
        }

        if (cols < 5) {
            for (final Cell[] row : sheet.getRows()) {
                final Cell[] upgr = new Cell[5];
                for (int i = 0; i < 5; i++) {
                    if (i < row.length) {
                        upgr[i] = row[i];
                    } else {
                        upgr[i] = new Cell();
                    }
                }
                this.sheet.getRows().add(upgr);
            }
        } else {
            for (final Cell[] row : sheet.getRows()) {
                this.sheet.getRows().add(row);
            }
        }

        fireTableStructureChanged();
        fireTableDataChanged();


    }

    private int columnsRequired(final Sheet sheet) {
        int max = 0;
        for (final Cell[] row : sheet.getRows()) {
            if (row.length > max) {
                max = row.length;
            }
        }
        return max;
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

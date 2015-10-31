package nl.tudelft.sheets.view.components.table;


import javax.lang.model.type.PrimitiveType;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class SheetsTableModel extends AbstractTableModel {

    private final String[] columnNames = new String[5];
    private final ArrayList<Object[]> data = new ArrayList<>();

    public SheetsTableModel() {
        for(int i = 0; i < 5; i++) {
            columnNames[i] = Character.toString((char) (i + 65));
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
        columnNames[column] = name;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }


    public void addRow(final Object[] data) {
        final Object[] upDate = new Object[26];
        for(int i = 0; i < upDate.length; i++) {
            if(i > data.length - 1) {
                upDate[i] = "";
            } else {
                upDate[i] = data[i];
            }
        }
        this.data.add(upDate);
        this.fireTableRowsInserted(this.data.size() - 1, this.data.size() - 1);
    }

    public void removeRow(final int row) {
        this.data.remove(data.get(row));
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public void setValueAt(final Object value, final int row, final int column) {
        final Object[] data = this.data.get(row);
        data[column] = value;
        this.data.set(row, data);
        fireTableCellUpdated(row, column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
}

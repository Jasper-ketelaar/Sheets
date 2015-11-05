package nl.tudelft.sheets.model.sheet;

import nl.tudelft.sheets.model.sheet.cell.Cell;
import nl.tudelft.sheets.model.xml.XMLElement;
import nl.tudelft.sheets.model.xml.XMLFile;
import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jasper on 11/4/2015.
 */
public class Sheet {

    private final ArrayList<Cell[]> rows = new ArrayList<>();

    public static Sheet parse(final String file) throws IOException, InvalidXMLFormatException {
        final XMLFile xml = XMLFile.parse(file);
        final Sheet sheet = new Sheet();
        final ArrayList<XMLElement> elements = xml.getAllElements();

        final int rows = maxRows(elements);
        final Object[][] data = new Object[rows][elements.size()];

        for (int i = 0; i < elements.size(); i++) {
            final XMLElement column = elements.get(i);
            for (int i2 = 0; i2 < rows; i2++) {
                data[i2][i] = column.getChild(i2).getValue();
            }
        }

        for (int i = 0; i < rows; i++) {
            final Object[] row = data[i];
            final Cell[] cellArray = new Cell[row.length];
            for (int i2 = 0; i2 < row.length; i2++) {
                cellArray[i2] = new Cell();
                cellArray[i2].setContent(row[i2]);
            }
            sheet.rows.add(cellArray);
        }
        return sheet;
    }

    private static int maxRows(final ArrayList<XMLElement> elements) {
        int max = 0;
        for (final XMLElement element : elements) {
            if (element.getChildrenCount() > max) {
                max = element.getChildrenCount();
            }
        }
        return max;
    }


    public ArrayList<Cell[]> getRows() {
        return this.rows;
    }

    public Object getValueAt(final int row, final int column) {
        if (column > rows.get(row).length - 1) {
            return "";
        }

        return this.rows.get(row)[column].getContent();
    }
}

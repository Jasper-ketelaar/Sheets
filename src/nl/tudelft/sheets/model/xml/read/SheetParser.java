package nl.tudelft.sheets.model.xml.read;

import nl.tudelft.sheets.model.data.SheetsCell;
import nl.tudelft.sheets.model.xml.XMLElement;
import nl.tudelft.sheets.model.xml.XMLFile;
import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jasperketelaar on 11/2/15.
 */
public class SheetParser {

    public static String[] getColumnNames(final String file) throws IOException, InvalidXMLFormatException {
        final XMLFile xml = XMLFile.parse(file);
        final String[] names = new String[xml.getElementCount()];
        for (int i = 0; i < xml.getElementCount(); i++) {
            names[i] = xml.getAllElements().get(i).getAttribute();
        }
        return names;
    }

    public static ArrayList<SheetsCell[]> parseCellsFromFile(final String file) throws IOException, InvalidXMLFormatException {
        final XMLFile xml = XMLFile.parse(file);
        final ArrayList<SheetsCell[]> cells = new ArrayList<>();
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
            final SheetsCell[] cellArray = new SheetsCell[row.length];
            for (int i2 = 0; i2 < row.length; i2++) {
                cellArray[i2] = new SheetsCell(i, i2);
                cellArray[i2].setContent(row[i2]);
            }
            cells.add(cellArray);
        }
        return cells;
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


}

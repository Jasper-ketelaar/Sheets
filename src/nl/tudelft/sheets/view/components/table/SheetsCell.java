package nl.tudelft.sheets.view.components.table;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class SheetsCell {

    private final int row;
    private final int column;

    private Object content;

    public SheetsCell(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.content = "";
    }

    public void setContent(final Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }
}

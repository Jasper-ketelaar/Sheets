package nl.tudelft.sheets.model.sheet.cell;

/**
 * Represents a Cell in the spreadsheet, can have any type
 * <p>
 * TODO: Implement formulas
 * <p>
 * Created by jasperketelaar on 10/31/15.
 */
public class Cell<T> {

    /**
     * Content of this cell
     */
    private T content;

    /**
     * Returns the content of this cell, and if this cell's content is null returns an empty string.
     * TODO: Fix null values as only String values can be casted to a String therefore any types but Object/String will give an exception
     *
     * @return the content of this cell or an empty string if null
     */
    public T getContent() {
        if (content != null)
            return content;
        else
            return (T) "";
    }

    /**
     * Sets the content of this cell
     * @param content the content this cell needs to be set to
     */
    public void setContent(final T content) {
        this.content = content;
    }
}

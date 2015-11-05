package nl.tudelft.sheets.model.sheet.cell;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class Cell<T extends Object> {

    private T content = (T) "";

    public void setContent(final T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

package nl.tudelft.sheets.model.sheet.cell;

/**
 * Created by jasperketelaar on 10/31/15.
 */
public class Cell<T> {

    private T content = (T) "";

    public void setContent(final T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

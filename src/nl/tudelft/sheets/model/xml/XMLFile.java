package nl.tudelft.sheets.model.xml;

import nl.tudelft.sheets.model.xml.exc.InvalidXMLFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.function.Predicate;

/**
 * Created by Jasper on 10/30/2015.
 */
public class XMLFile {

    private final ArrayList<XMLElement> elements;
    private final String name;

    private final ArrayList<XMLElement> filtered = new ArrayList<>();

    private XMLFile(final ArrayList<XMLElement> elements, final String name) {
        this.elements = elements;
        this.name = name;
    }

    public static XMLFile parse(final String file) throws IOException, InvalidXMLFormatException {
        final Deque<String> lines = lines(file);
        final String name = lines.pop();

        if (!lines.peekLast().replace("/", "").equals(name)) {
            throw new InvalidXMLFormatException("The type tag is not found at the end of the file");
        }

        final ArrayList<XMLElement> elements = new ArrayList<>();
        lines.removeLast();

        while (!lines.isEmpty()) {
            final String first = lines.pop();
            final Deque<String> content = new ArrayDeque<>();

            while (!lines.isEmpty()) {
                final String current = lines.pop();
                if (current.replace("/", "").equals(first)) {
                    break;
                }
                content.add(current);
            }

            final XMLElement element = XMLElement.parse(first, null, content);
            elements.add(element);
        }
        return new XMLFile(elements, name.substring(name.indexOf("<") + 1, name.indexOf(">")));
    }

    private static Deque<String> lines(final String file) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final Deque<String> lines = new ArrayDeque<>();

        String current;
        while ((current = reader.readLine()) != null) {
            lines.add(current.trim());
        }

        reader.close();
        return lines;
    }

    public ArrayList<XMLElement> getAllElements() {
        return elements;
    }

    public XMLElement[] getElementsByAttribute(final String attribute) {
        filter(xmlElement -> xmlElement.getAttribute().equals(attribute));
        return filtered.toArray(new XMLElement[filtered.size()]);
    }

    public ArrayList<XMLElement> filter(final Predicate<XMLElement> predicate) {
        filtered.clear();

        for (final XMLElement element : elements) {
            if (predicate.test(element)) {
                filtered.add(element);
            }
        }

        return filtered;
    }

    public ArrayList<XMLElement> getFilterBuffer() {
        return filtered;
    }

    public String getName() {
        return name;
    }

    public int getElementCount() {
        return elements.size();
    }
}

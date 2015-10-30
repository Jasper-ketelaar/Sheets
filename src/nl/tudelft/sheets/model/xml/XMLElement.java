package nl.tudelft.sheets.model.xml;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by Jasper on 10/30/2015.
 */
public class XMLElement {

    private final String attribute;
    private final String value;
    private final ArrayList<XMLElement> children;

    private XMLElement(final String attribute, final String value, final ArrayList<XMLElement> children) {
        this.attribute = attribute;
        this.value = value;
        this.children = children;
    }

    private XMLElement(final String attribute, final String value) {
        this(attribute, value, new ArrayList<>());
    }

    public static XMLElement parse(final String first, final Deque<String> content) throws IOException {
        final String attribute = first.substring(first.indexOf("<") + 1, first.indexOf(">"));
        final StringBuilder value = new StringBuilder();
        final ArrayList<XMLElement> children = new ArrayList<>();

        while (!content.isEmpty()) {
            final String current = content.pop();
            if (current.contains("<") && !current.contains("</")) {
                final Deque<String> inner = new ArrayDeque<>();
                while (!content.isEmpty()) {
                    final String inCurrent = content.pop();
                    if (inCurrent.replace("/", "").equals(current)) {
                        break;
                    }
                    inner.add(inCurrent);
                }
                children.add(parse(current, inner));
            } else {

                if (current.replaceFirst("<", "").contains("<") && current.contains("</")) {
                    final String val = current.substring(current.indexOf(">") + 1, current.indexOf("</"));
                    final String att = current.substring(current.indexOf("<") + 1, current.indexOf(">"));
                    children.add(new XMLElement(att, val));
                } else
                    value.append(current);
            }
        }
        return new XMLElement(attribute, value.toString(), children);
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<XMLElement> getChildren() {
        return children;
    }

    public int getChildrenCount() {
        return children.size();
    }

    public boolean hasChildren() {
        return getChildrenCount() > 0;
    }

    public ArrayList<XMLElement> getChildrenByAttribute(final String attribute) {
        final ArrayList<XMLElement> elements = new ArrayList<>();

        for (final XMLElement child : getChildren()) {
            if (child.getAttribute().equals(attribute)) {
                elements.add(child);
            } else if (child.hasChildren()) {
                elements.addAll(child.getChildrenByAttribute(attribute));
            }
        }

        return elements;
    }

    public ArrayList<XMLElement> getChildrenByValue(final String value) {
        final ArrayList<XMLElement> elements = new ArrayList<>();

        for (final XMLElement child : getChildren()) {
            if (child.getValue().equals(value)) {
                elements.add(child);
            } else if (child.hasChildren()) {
                elements.addAll(child.getChildrenByAttribute(value));
            }
        }

        return elements;
    }

    @Override
    public String toString() {
        final StringBuilder rep = new StringBuilder("XMLElement[attribute=");
        rep.append(attribute);
        if (!value.equals("")) {
            rep.append(", value=");
            rep.append(value);
        }

        final int size = children.size();
        if (size > 0) {
            rep.append(", child_count=");
            rep.append(size);
        }

        rep.append("]");
        return rep.toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof XMLElement) {
            final XMLElement element = (XMLElement) other;
            return attribute.equals(element.getAttribute()) && value.equals(element.getValue()) && children.equals(element.getChildren());
        }
        return false;
    }

}

package nl.tudelft.sheets.model.xml;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by Jasper on 10/30/2015.
 */
public class XMLElement {

    private final ArrayList<XMLElement> children = new ArrayList<>();

    private final String attribute;
    private final String value;

    private XMLElement parent;

    private XMLElement(final String attribute, final String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public static XMLElement parse(final String first, final XMLElement parent, final Deque<String> content) throws IOException {
        final String attribute = first.substring(first.indexOf("<") + 1, first.indexOf(">"));
        final StringBuilder value = new StringBuilder();
        final XMLElement parsed = new XMLElement(attribute, value.toString());
        parsed.setParent(parent);

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
                parsed.addChild(parse(current, parsed, inner));
            } else {

                if (current.replaceFirst("<", "").contains("<") && current.contains("</")) {
                    final String val = current.substring(current.indexOf(">") + 1, current.indexOf("</"));
                    final String att = current.substring(current.indexOf("<") + 1, current.indexOf(">"));
                    parsed.addChild(new XMLElement(att, val));
                } else
                    value.append(current);
            }
        }
        return parsed;
    }

    public void addChild(final XMLElement child) {
        children.add(child);
    }

    public void setParent(final XMLElement parent) {
        this.parent = parent;
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

    public XMLElement getChild(final int index) {
        return getChildren().get(index);
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

    public XMLElement getParent() {
        return parent;
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

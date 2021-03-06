import java.util.ArrayList;
import java.util.Collections;

/*
Having a massive object with 10 constructor arguments is not productive. Instead, a Builder allows
for step-by-step construction.
Let's create an Html Element and allow for it to be turned into a String.
*/
class HtmlElementWithIndentation {
    public String tag, text; // Stores tag and corresponding text
    public ArrayList<HtmlElementWithIndentation> elements = new ArrayList<>();
    // This is recursive structure that stores other tags inside this one.

    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    HtmlElementWithIndentation() {
    }

    HtmlElementWithIndentation(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    // This method formats Html. Doesn't have much to do with Builder.
    private String toStringImpl(int indentLevel) {
        StringBuilder htmlBuilder = new StringBuilder();

        // Sorts out indentation
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < (indentSize * indentLevel); i++) {
            indentation.append(" ");
        }

        StringBuilder nextIndentation = new StringBuilder(indentation);
        for (int i = 0; i < indentSize; i++) {
            nextIndentation.append(" ");
        }

        // Start building html
        htmlBuilder.append(indentation + "<" + tag + ">" + newLine);

        // Add text
        if (text != null && text.length() != 0) {
            htmlBuilder.append(nextIndentation + text + newLine);
        }

        // Add the arraylist elements recursively
        for (HtmlElementWithIndentation element : elements) {
            htmlBuilder.append(element.toStringImpl(indentLevel + 1));
        }

        // End
        htmlBuilder.append(indentation + "</" + tag + ">" + newLine);
        return htmlBuilder.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

// Builder
class HtmlBuilderWithIndentation {
    String rootName;
    HtmlElementWithIndentation root = new HtmlElementWithIndentation();

    HtmlBuilderWithIndentation(String rootName) {
        this.rootName = rootName;
        root.tag = rootName;
    }

    // Enriches HtmlBuilderWithIndentation
    public void addChild(String childTag, String childText) {
        root.elements.add(new HtmlElementWithIndentation(childTag, childText));
    }

    public void clear() {
        root = new HtmlElementWithIndentation();
        root.tag = rootName;
    }

    // Returns the final string
    @Override
    public String toString() {
        return root.toString();
    }
}

class Builder_02_JavaBuilderWithIndentation {
    public static void main(String[] args) {
        HtmlBuilderWithIndentation builder = new HtmlBuilderWithIndentation("ul");
        builder.addChild("li", "Item 1");
        builder.addChild("li", "Item 2");
        builder.addChild("li", "Item 3");
        builder.addChild("li", "Item 4");

        System.out.println(builder); // builder.toString() is called automatically
    }
}
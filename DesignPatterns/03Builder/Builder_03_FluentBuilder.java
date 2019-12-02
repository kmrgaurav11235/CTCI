import java.util.ArrayList;
import java.util.Collections;

/*
Fluent interface allows you to write long chains to build objects. In last files, anytime we want to add
a element, we call builder.addChild() in separate statements. What we want is for addChild() to return 
Builder so that we can make calls like: builder.addChild().addChild() ... and so on.
This is just a small change.
*/
class HtmlElementWithIndentation1 {
    public String tag, text; // Stores tag and corresponding text
    public ArrayList<HtmlElementWithIndentation1> elements = new ArrayList<>();
    // This is recursive structure that stores other tags inside this one.

    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    HtmlElementWithIndentation1() {
    }

    HtmlElementWithIndentation1(String tag, String text) {
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
        for (HtmlElementWithIndentation1 element : elements) {
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
class FluentHtmlBuilder {
    String rootName;
    HtmlElementWithIndentation1 root = new HtmlElementWithIndentation1();

    FluentHtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.tag = rootName;
    }

    // Enriches FluentHtmlBuilder
    // Returns Builder so that we can chain calls
    public FluentHtmlBuilder addChild(String childTag, String childText) {
        root.elements.add(new HtmlElementWithIndentation1(childTag, childText));
        return this;
    }

    public void clear() {
        root = new HtmlElementWithIndentation1();
        root.tag = rootName;
    }

    // Returns the final string
    @Override
    public String toString() {
        return root.toString();
    }
}

class Builder_03_FluentBuilder {
    public static void main(String[] args) {
        FluentHtmlBuilder builder = new FluentHtmlBuilder("ul");
        builder.addChild("li", "Item 1")
            .addChild("li", "Item 2")
            .addChild("li", "Item 3")
            .addChild("li", "Item 4");

        System.out.println(builder); // builder.toString() is called automatically
    }
}
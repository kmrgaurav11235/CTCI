import java.util.ArrayList;

/*
Having a massive object with 10 constructor arguments is not productive. Instead, a Builder allows
for step-by-step construction.
Let's create an Html Element and allow for it to be turned into a String.
*/
class HtmlElement {
    public String tag, text; // Stores tag and corresponding text

    // This is recursive structure that stores other tags inside this one.
    public ArrayList<HtmlElement> elements = new ArrayList<>();

    HtmlElement() {
    }

    HtmlElement(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    private String toStringImpl() {
        StringBuilder htmlBuilder = new StringBuilder();

        // Start building html
        htmlBuilder.append("<" + tag + ">\n");

        // Add text
        if (text != null && text.length() != 0) {
            htmlBuilder.append(text + "\n");
        }

        // Add the arraylist elements recursively
        for (HtmlElement element : elements) {
            htmlBuilder.append(element.toStringImpl());
        }

        // End
        htmlBuilder.append("</" + tag + ">\n");
        return htmlBuilder.toString();
    }

    @Override
    public String toString() {
        return toStringImpl();
    }
}

// Builder
class HtmlBuilder {
    String rootName;
    HtmlElement root = new HtmlElement();

    HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.tag = rootName;
    }

    // Enriches HtmlBuilder
    public void addChild(String childTag, String childText) {
        root.elements.add(new HtmlElement(childTag, childText));
    }

    public void clear() {
        root = new HtmlElement();
        root.tag = rootName;
    }

    // Returns the final string
    @Override
    public String toString() {
        return root.toString();
    }
}

class Builder_01_JavaBuilder {
    public static void main(String[] args) {
        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "Item A");
        builder.addChild("li", "Item B");
        builder.addChild("li", "Item C");
        builder.addChild("li", "Item D");

        System.out.println(builder); // builder.toString() is called automatically
    }
}
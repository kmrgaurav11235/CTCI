import java.util.List;
import java.util.stream.Stream;


// Open-close Principle: Be open for extension but closed for modification.
// Specification Pattern: Determines if a particular criteria is satisfied.
enum Color {
    RED, GREEN, BLUE;
}
enum Size {
    SMALL, MEDUIM, LARGE, HUGE;
}
class Product {
    public String name;
    public Color color;
    public Size size;
    Product (String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter {
    // Requirement is to filter the products by color and by size. So we create two methods.
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }
    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }
    /* 
    But what if now we need to filter by by Color and size. So we have to add a new method.
    This is a violation of open-close principle. 
    */
    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
        return products.stream().filter(p -> p.size == size && p.color == color);
    }
    /*
    Also, what if there were more than two criteria, lets say: color, size, price and buildDate.
    We will end up with a lot of methods if we want to be able to filter by each intersection.
    */
}

/*
To solve these two problems: 1. Modification of existing code and 2. Too many methods, we can design 
a better architecture using the Specification pattern.
*/
interface Specification<T> {
    boolean isSatisfied (T item);
}

interface Filter<T> {
    Stream <T> filter (List<T> items, Specification<T> specification);
}
/*
The above two interfaces with Generics allow us to make things very generic. A specification can check
if a criteria is satified based on any type of input. And a filter can use any type of specification to
filter 'items'. Now lets implement both of these to satisfy our requirements.
*/
class ColorSpecification implements Specification<Product> {
    private Color color;
    ColorSpecification(Color color) {
        this.color = color;
    }
    @Override
    public boolean isSatisfied (Product item) {
        return item.color == color;
    }
}
class SizeSpecification implements Specification<Product> {
    private Size size;
    SizeSpecification(Size size) {
        this.size = size;
    }
    @Override
    public boolean isSatisfied (Product item) {
        return item.size == size;
    }
}

class BetterFilter implements Filter<Product> {
    @Override
    public Stream <Product> filter (List<Product> items, Specification<Product> specification) {
        return items.stream().filter(p -> specification.isSatisfied(p));
    }
}
/*
The upside of this architecture is that if you need new Filter and new Specifications, we can create new 
classes and implement the interfaces.
To combine two or more filters, we can use a Combinator. Combinators combines things together. In this case,
we need to combine two specifications together.
*/
class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;
    AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public boolean isSatisfied (T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class SDP_02_OpenClosePrinciple {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.MEDUIM);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        // Implementation of Old filter
        ProductFilter pf = new ProductFilter();
        System.out.println("Green Product (Old):");
        pf.filterByColor(products, Color.GREEN).forEach(p -> System.out.println("- " + p.name + " is Green."));

        // Implementation of Better filter
        Specification<Product> coloSpecification = new ColorSpecification(Color.GREEN);
        BetterFilter bf = new BetterFilter();
        System.out.println("\nGreen Product (New):");
        bf.filter(products, coloSpecification).forEach(p -> System.out.println("- " + p.name + " is Green."));

        // AndSpecification
        System.out.println("\nLarge Blue Product (New):");
        bf.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.BLUE), 
                new SizeSpecification(Size.LARGE)))
            .forEach(p -> System.out.println("- " + p.name + " is Blue and Large."));;

    }
}
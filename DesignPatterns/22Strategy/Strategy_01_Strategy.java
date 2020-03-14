import java.util.ArrayList;
import java.util.List;

/*
https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial
- Strategy design pattern is one of the behavioral design pattern. It is used when we have multiple algorithm 
    for a specific task and client decides the actual implementation to be used at runtime.
- Strategy pattern is also known as Policy Pattern. We define multiple algorithms and let client application 
    pass the algorithm to be used as a parameter.
- One of the best example of strategy pattern is Collections.sort() method that takes Comparator parameter. 
    Based on the different implementations of Comparator interfaces, the Objects are getting sorted in different 
    ways.
- For our example, we will try to implement a simple Shopping Cart where we have two payment strategies – using 
    Credit Card or using PayPal.
*/
interface PaymentStrategy {
    public void pay(double amount);
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private String cardName;
    private String expiryDate;
    private String cvv;

    CreditCardPaymentStrategy(String cardNumber, String cardName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card - " + cardNumber.substring(0, 4) + "-XXXX-XXXX-XXXX .");
    }
}

class PayPalPaymentStrategy implements PaymentStrategy {
    private String userName;
    private String password;

    PayPalPaymentStrategy(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal account - " + userName + ".");
    }
}

class Item {
    private String id;
    private String name;
    private double price;

    Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
    
    public void payBill(PaymentStrategy paymentStrategy) {
        paymentStrategy.pay(calculateTotalPrice());
    }
}

public class Strategy_01_Strategy {
    public static void main(String[] args) {
        Item dollHouse = new Item("541-toy-65", "Doll House", 599.99);
        Item ballon = new Item("672-mi-456", "Ballon", 2.0);
        Item cake = new Item("8656-cake-556", "Cake", 1679.99);
        Item knife = new Item("677-plastic-kn-78", "Plastic Knife", 3.99);

        ShoppingCart giftShopCart = new ShoppingCart();
        giftShopCart.addItem(dollHouse);
        giftShopCart.addItem(ballon);
        giftShopCart.payBill(new CreditCardPaymentStrategy("1234-5678-8901-2356", "Aule", "TA 567", "666"));

        ShoppingCart bakeryShopCart = new ShoppingCart();
        bakeryShopCart.addItem(cake);
        bakeryShopCart.addItem(knife);
        bakeryShopCart.payBill(new PayPalPaymentStrategy("yavanna.fruitgiver", "iru_gives_da_best_bounty"));
    }
}
/*
Strategy Design Pattern Important Points
- We could have used composition to create instance variable for strategies but we should avoid that as we 
    want the specific strategy to be applied for a particular task. Same is followed in Collections.sort() 
    and Arrays.sort() method that take comparator as argument.
- Strategy Pattern is very similar to State Pattern. One of the difference is that Context contains state 
    as instance variable and there can be multiple tasks whose implementation can be dependent on the state 
    whereas in strategy pattern strategy is passed as argument to the method and context object doesn’t have 
    any variable to store it.
- Strategy pattern is useful when we have multiple algorithms for specific task and we want our application 
    to be flexible to chose any of the algorithm at runtime for specific task.
*/
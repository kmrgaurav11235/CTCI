import java.util.HashMap;

/*
- Multiton pattern is a design pattern which generalizes the singleton pattern. Whereas the singleton allows 
    only one instance of a class to be created, the multiton pattern allows for the controlled creation of 
    multiple instances, which it manages through the use of a map.
- Rather than having a single instance per application (e.g. the java.lang.Runtime object), the multiton 
    pattern instead ensures a single instance per key. Also, for every instance, you can implement additional
    features like lazy loading.
*/
enum Subsystem {
    PRIMARY, SECONDARY, FALLBACK
}
class Printer {
    // Multiton: We want one printer corresponding to each subsystem. Also, we want lazy loading.
    private static HashMap<Subsystem, Printer> printers = new HashMap<>();
    private Printer() {
    }

    public static Printer getPrinter(Subsystem subsystem) {
        // Get the one instance of the subsystem.
        if (printers.containsKey(subsystem)) {
            return printers.get(subsystem);
        } else {
            Printer instance = new Printer();
            printers.put(subsystem, instance);
            return instance;
        }
    }
}
class Singleton_04_Multiton {
    public static void main(String[] args) {
        Printer primary1 = Printer.getPrinter(Subsystem.PRIMARY);
        Printer primary2 = Printer.getPrinter(Subsystem.PRIMARY);
        Printer secondary1 = Printer.getPrinter(Subsystem.SECONDARY);
        Printer secondary2 = Printer.getPrinter(Subsystem.SECONDARY);

        System.out.println(primary1.equals(primary2));
        System.out.println(secondary1.equals(secondary2));
    }
}
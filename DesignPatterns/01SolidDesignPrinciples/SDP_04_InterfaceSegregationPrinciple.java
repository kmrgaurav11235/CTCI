/*
Interface Segregation Principle: This is a recommendation on how to split interfaces into smaller interfaces.
*/
class Document {
    public String doc = "Document Content";
}

interface Machine {
    // Represents a machine for Document e.g. printer, scanner etc.
    void printDocument (Document d);
    void emailDocument (Document d);
    void scanDocument (Document d);
    // Your clients can now take this interface and implement it.
}

class MutiFunctionPrinter implements Machine{
    // Clients implementation of Machine
    @Override
    public void printDocument(Document d) {
        System.out.println("Printing document : " + d.doc);
    }

    @Override
    public void emailDocument(Document d) {
        System.out.println("Emailing document : " + d.doc);
    }

    @Override
    public void scanDocument(Document d) {
        System.out.println("Scanning document : " + d.doc);
    }
}

class OldFashionedPrinter implements Machine {
    // This client has an old fashioned printer that can only print.
    @Override
    public void printDocument(Document d) {
        System.out.println("Printing document : " + d.doc);
    }

    @Override
    public void emailDocument(Document d) {
        // It is unclear what the client should do here as OldFashionedPrinter cannot send email
        // One option is to do nothing here. But this can cause confusing to the user of this printer.
        // Another is to throw exception. But this leads to the problem of Exception specification.
        // You have to add Exception to method signature in this class as well as the interface.
        // Trouble is, the interface source code might not be controlled by you.
    }

    @Override
    public void scanDocument(Document d) {
        // It is also unclear what the client should do here as OldFashionedPrinter cannot scan
    }

}
/*
This is where we come to the Interface Segregation Principle: the idea that you should not put into your interface,
more methods than the client expects. The term to keep in mind is YAGNI:
YAGNI: You ain't gonna need it. If you don't need the methods, you should be required to implement them.
A better architecture is below.
*/

// Segregating the three interfaces
interface Printer {
    void printDocument (Document d);
}

interface Emailer {
    void emailDocument (Document d);
}

interface Scanner {
    void scanDocument (Document d);
}
// Just a Printer only implements the printer
class JustAPrinter implements Printer {
    @Override
    public void printDocument(Document d) { 
        System.out.println("Printing document : " + d.doc);
    }
}

// You can implement multiple interfaces if you need more than one functionality.
class PhotoCopier implements Printer, Scanner {
    @Override
    public void scanDocument(Document d) {
        System.out.println("Printing document : " + d.doc);
    }
    
    @Override
    public void printDocument(Document d) {
        System.out.println("Scanning document : " + d.doc);
    }
}

// Or you can make a new interface that extends several interfaces and implement it
interface MutiFunctionDevice extends Printer, Scanner {}

class MultiFunctionPrinter2 implements MutiFunctionDevice {

    @Override
    public void printDocument(Document d) {
        System.out.println("Printing document : " + d.doc);
    }

    @Override
    public void scanDocument(Document d) {
        System.out.println("Scanning document : " + d.doc);
    }

}

class SDP_04_InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        
    }
}
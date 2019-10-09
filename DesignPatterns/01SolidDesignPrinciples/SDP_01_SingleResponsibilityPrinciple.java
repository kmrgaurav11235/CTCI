import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/* 
Single Responsibility Principle states that a class should have one primary reponsibility.
    1. A class should have only one reason to change.
    2. Seperation of concerns - Different classes handling different/independent problems.
If you take on lots of responsibility, you end up with something called a 'God Object' which
is an anti-pattern (something that you shouldn't have in your code).
*/
class Journal {
    private static int journalCount = 0;
    private List<String> entry = new ArrayList<>();

    void addEntry(String text) {
        entry.add((++journalCount) + " : " + text);
    }

    void removeEntry (int index) {
        entry.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entry);
    }

    /*
    The below methods, on first glance, do appear to belong to the Journal class.
    But they violate the Single Responsibility Principle. This is because Persistence
    is a seperate responsibilty and should be kept in a seperate class 
    */
    void save(String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(toString());
        }
    }

    Journal load (String fileName) {
        // loads Journal from a file
        return new Journal();
    }
}

class Persistence {
    /* 
    A better idea is to move Persistance to a seperate class.
    This leads to each class having single responsibbility.
    */
    void save (Journal j, String fileName, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(fileName).exists()) {
            try (PrintStream out = new PrintStream(fileName)) {
                out.println(toString());
            }
        }
    }
}

class SDP_01_SingleResponsibilityPrinciple {
    public static void main(String[] args)  throws FileNotFoundException {
        Journal j = new Journal();
        j.addEntry("I cried today. I bug got in my eye.");
        j.addEntry("This Euron guy seems pretty evil.");
        System.out.println("My Journal:\n" + j);

        new Persistence().save(j, "MyJournalFile.txt", true);
    }
}
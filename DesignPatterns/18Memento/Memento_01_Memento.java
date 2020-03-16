/*
https://www.journaldev.com/1734/memento-design-pattern-java

- Memento design pattern is one of the behavioral design pattern. It is used when we want to save the 
    state of an object so that we can restore later on. Memento pattern is used to implement this in 
    such a way that the saved state data of the object is not accessible outside of the object. This 
    protects the integrity of saved state data.
- Memento design pattern is implemented with two objects – "Originator" and "Caretaker".
- Originator is the object whose state needs to be saved and restored and it uses an inner class to 
    save the state of Object. The inner class is called "Memento" and it’s private, so that it can’t 
    be accessed from other objects.
- Caretaker is the helper class that is responsible for storing and restoring the Originator’s state 
    through Memento object. Since Memento is private to Originator, Caretaker can’t access it and it’s 
    stored as an Object within the caretaker.
- One of the best real life example is the text editors where we can save it’s data anytime and use 
    undo to restore it to previous saved state.
*/

// Memento Pattern Originator Class
class FileWriterUtil {
    private String fileName;
    private StringBuilder content;

    FileWriterUtil(String fileName) {
        this.fileName = fileName;
        content = new StringBuilder();
    }

    public void write(String text) {
        content.append(text);
    }

    public Memento save() {
        return new Memento(fileName, content);
    }

    public void restoreToLastSave(Object lastSave) {
        Memento memento = (Memento) lastSave;
        this.fileName = memento.fileName;
        this.content = memento.content;
    }

    private class Memento {
        private String fileName;
        private StringBuilder content;

        Memento(String fileName, StringBuilder content) {
            this.fileName = fileName;
            this.content = new StringBuilder(content);
        }
    }

    @Override
    public String toString() {
        return content.toString();
    }
}

// Memento Pattern Caretaker Class
 class FileWriterCaretaker {
     Object content;

     public void save(FileWriterUtil fileWriterUtil) {
         content = fileWriterUtil.save();
     }

     public void undo(FileWriterUtil fileWriterUtil) {
         fileWriterUtil.restoreToLastSave(content);
     }
 }

class Memento_01_Memento {
    public static void main(String[] args) {
        FileWriterCaretaker caretaker = new FileWriterCaretaker();

        // Write two lines
        FileWriterUtil writerUtil = new FileWriterUtil("data.txt");
        writerUtil.write("Earendil was a mariner,\n");
        writerUtil.write("that tarried in Arvernien,\n");
        // And save them
        caretaker.save(writerUtil);

        System.out.println("First two lines:\n" + writerUtil);

        // Write two more lines
        writerUtil.write("he built a boat of timber felled,\n");
        writerUtil.write("in Nimbrethil to journey in.\n");

        System.out.println("Wrote two more lines:\n" + writerUtil);

        // Undo
        caretaker.undo(writerUtil);
        System.out.println("After undo:\n" + writerUtil);
    }
}
/*
- Memento pattern is simple and easy to implement. One of the thing needs to take care is that Memento 
    class should be accessible only to the Originator object. Also in client application, we should use 
    caretaker object for saving and restoring the originator state.
- Also if Originator object has properties that are not immutable, we should use deep copy or cloning to 
    avoid data integrity issue like in above example. We can use Serialization to achieve memento pattern 
    implementation that is more generic rather than Memento pattern where every object needs to have it’s 
    own Memento class implementation.
- One of the drawback is that if Originator object is very huge then Memento object size will also be huge 
    and use a lot of memory.
*/
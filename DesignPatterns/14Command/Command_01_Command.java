/*
https://www.journaldev.com/1624/command-design-pattern

- Command Pattern is one of the Behavioral Design Pattern. It is used to implement loose coupling in a 
    request-response model.
- In command pattern, the request is send to the invoker and invoker pass it to the encapsulated "Command" 
    object.
- "Command" object passes the request to the appropriate method of Receiver to perform the specific action.
- The client program create the receiver object and then attach it to the Command. Then it creates the 
    invoker object and attach the command object to perform an action.
- Now when client program executes the action, it’s processed based on the command and receiver object.

Command Design Pattern Example
- Let’s say we want to provide a File System utility with methods to open, write and close file. This file 
    system utility should support multiple operating systems such as Windows and Unix.
- To implement our File System utility, first of all we need to create the receiver classes that will actually 
    do all the work.
- We can have FileSystemReceiver interface and it’s implementation classes for different operating system 
    flavors such as Windows, Unix etc.
*/
interface FileSystemReceiver {

    public void openFile(String fileName);
    public void readFile(String fileName);
    public void closeFile(String fileName);
}

class WindowsFileSystemReceiver implements FileSystemReceiver {
    @Override
    public void openFile(String fileName) {
        System.out.println("Opening file " + fileName + " in Windows.");
    }
    @Override
    public void readFile(String fileName) {
        System.out.println("Reading file " + fileName + " in Windows.");
    }
    @Override
    public void closeFile(String fileName) {
        System.out.println("Closing file " + fileName + " in Windows.");
    }
}

class UnixFileSystemReceiver implements FileSystemReceiver {
    @Override
    public void openFile(String fileName) {
        System.out.println("Opening file " + fileName + " in Unix.");
    }
    @Override
    public void readFile(String fileName) {
        System.out.println("Reading file " + fileName + " in Unix.");
    }
    @Override
    public void closeFile(String fileName) {
        System.out.println("Closing file " + fileName + " in Unix.");
    }
}

/*
- Now that our receiver classes are ready, we can move to implement our Command classes.
- We can use interface or abstract class to create our base Command.
- We need to create implementations for all the different types of action performed by the receiver. Since 
    we have three actions we will create three Command implementations. Each Command implementation will 
    forward the request to the appropriate method of receiver.
*/
interface Command {
    public void execute();
}

class OpenCommand implements Command {
    
    private FileSystemReceiver fileSystem;
    private String fileName;

    OpenCommand(FileSystemReceiver fileSystem, String fileName) {
        this.fileSystem = fileSystem;
        this.fileName = fileName;
    }
    @Override
    public void execute() {
        fileSystem.openFile(fileName);
    }
}

class ReadCommand implements Command {
    
    private FileSystemReceiver fileSystem;
    private String fileName;

    ReadCommand(FileSystemReceiver fileSystem, String fileName) {
        this.fileSystem = fileSystem;
        this.fileName = fileName;
    }
    @Override
    public void execute() {
        fileSystem.readFile(fileName);
    }
}


class CloseCommand implements Command {
    
    private FileSystemReceiver fileSystem;
    private String fileName;

    CloseCommand(FileSystemReceiver fileSystem, String fileName) {
        this.fileSystem = fileSystem;
        this.fileName = fileName;
    }
    @Override
    public void execute() {
        fileSystem.closeFile(fileName);
    }
}
// Invoker is a simple class that encapsulates the Command and passes the request to the command object to process it.
class FileInvoker {
    private Command command;

    FileInvoker(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}

class Command_01_Command {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        FileSystemReceiver fileSystem = getFileSystem();
        FileInvoker fileInvoker;
        // Open file
        OpenCommand openCommand = new OpenCommand(fileSystem, fileName);
        fileInvoker = new FileInvoker(openCommand);
        fileInvoker.execute();
        // Read file
        ReadCommand readCommand = new ReadCommand(fileSystem, fileName);
        fileInvoker = new FileInvoker(readCommand);
        fileInvoker.execute();
        // Close file
        CloseCommand closeCommand = new CloseCommand(fileSystem, fileName);
        fileInvoker = new FileInvoker(closeCommand);
        fileInvoker.execute();
    }

    private static FileSystemReceiver getFileSystem() {
        String os = System.getProperty("os.name");
        System.out.println("OS: " + os);
        if (os.contains("Windows")) {
            return new WindowsFileSystemReceiver();
        } else {
            return new UnixFileSystemReceiver();
        }
    }
}
/*
Command Pattern Important Points:
- Command is the core of command design pattern that defines the contract for implementation.
- Receiver implementation is separate from command implementation.
- Command implementation classes chose the method to invoke on receiver object, for every method in receiver 
    there will be a command implementation. It works as a bridge between receiver and action methods.
- Invoker class just forward the request from client to the command object.
- Client is responsible to instantiate appropriate command and receiver implementation and then associate them 
    together.
- Client is also responsible for instantiating invoker object and associating command object with it and execute 
    the action method.
- Command design pattern is easily extendible, we can add new action methods in receivers and create new Command 
    implementations without changing the client code.
- The drawback with Command design pattern is that the code gets huge and confusing with high number of action 
    methods and because of so many associations.
- Runnable interface (java.lang.Runnable) and Swing Action (javax.swing.Action) uses command pattern.
*/
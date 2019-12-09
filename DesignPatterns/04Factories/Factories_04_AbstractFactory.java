/*
Lecture: https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java
https://www.youtube.com/watch?v=BPkYkyVWOaw 
Abstract Factory pattern is almost similar to Factory Pattern except for the fact that 
its more like factory of factories.
This factory class returns different subclasses based on the input provided and factory 
class uses if-else or switch statement to achieve this. In the Abstract Factory pattern, 
we get rid of if-else block and have a factory class for each sub-class. Then an Abstract 
Factory class that will return the sub-class based on the input factory class. 
*/

// Computer, PC and Server class remain the same as last lecture.

// Abstract Factory interface or abstract factory class.
interface ComputerAbstractFactory {
    public Computer createComputer();
}

class PCFactory implements ComputerAbstractFactory {
    private String hdd, ram, cpu;
    PCFactory (String hdd, String ram, String cpu) {
        this.hdd = hdd;
        this.ram = ram;
        this.cpu = cpu;
    }
    @Override
    public Computer createComputer() {
        return new PC(hdd, ram, cpu);
    }
}

class ServerFactory implements ComputerAbstractFactory {
    private String hdd, ram, cpu;
    ServerFactory (String hdd, String ram, String cpu) {
        this.hdd = hdd;
        this.ram = ram;
        this.cpu = cpu;
    }
    @Override
    public Computer createComputer() {
        return new Server(hdd, ram, cpu);
    }
}

// Consumer class that will provide the entry point for the client classes to create sub-classes.
class ComputerFactoryNew {
    // Notice that its a simple class and getComputer method is accepting ComputerAbstractFactory argument. 
    public static Computer getComputer(ComputerAbstractFactory computerAbstractFactory) {
        return computerAbstractFactory.createComputer();
    }
}

class Factories_04_AbstractFactory {
    public static void main(String[] args) {
        Computer pc = ComputerFactoryNew.getComputer(new PCFactory("500GB", "16GB", "2.4GH"));
        System.out.println("PC = " + pc);

        Computer server = ComputerFactoryNew.getComputer(new ServerFactory("1 TB", "16 GB", "2.9 GHz"));
        System.out.println("Server = " + server);
    }
}
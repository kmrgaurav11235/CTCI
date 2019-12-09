/*
Lecture: https://www.journaldev.com/1392/factory-design-pattern-in-java
https://www.youtube.com/watch?v=J1QU_R4MQQc

Factory design pattern is used when we have a super class with multiple sub-classes and based on input, we need to 
return one of the sub-class. This pattern take out the responsibility of instantiation of a class from client 
program to the factory class. 
Super class in factory design pattern can be an interface, abstract class or a normal java class. 
*/
abstract class Computer {
    public abstract String getHDD();
    public abstract String getRAM();
    public abstract String getCPU();
    @Override
    public String toString() {
        return "Computer [hdd: " + getHDD() + ", ram: " + getRAM() + ", cpu: " + getCPU() + "]";
    }
}

class PC extends Computer {
    private String hdd, ram, cpu;
    PC (String hdd, String ram, String cpu) {
        this.hdd = hdd;
        this.ram = ram;
        this.cpu = cpu;
    }
    @Override
    public String getHDD() {
        return hdd;
    }
    @Override
    public String getRAM() {
        return ram;
    }
    @Override
    public String getCPU() {
        return cpu;
    }
}

class Server extends Computer {
    private String hdd, ram, cpu;
    Server (String hdd, String ram, String cpu) {
        this.hdd = hdd;
        this.ram = ram;
        this.cpu = cpu;
    }
    @Override
    public String getHDD() {
        return hdd;
    }
    @Override
    public String getRAM() {
        return ram;
    }
    @Override
    public String getCPU() {
        return cpu;
    }
}
/*
We can keep Factory class Singleton or we can keep the method that returns the subclass as static.
Based on the input parameter, different subclass is created and returned. getComputer is the factory method.
*/
class ComputerFactory {
    public static Computer getComputer(String type, String hdd, String ram, String cpu) {
        if (type.equalsIgnoreCase("pc")) {
            return new PC(hdd, ram, cpu);
        } else if (type.equalsIgnoreCase("server")) {
            return new Server(hdd, ram, cpu);
        } else {
            return null;
        }
    }
}
// Client program
class Factories_03_FactoryWithInheritance{
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("pc", "500GB", "16GB", "2.4GH");
        System.out.println("PC = " + pc);

        Computer server = ComputerFactory.getComputer("server", "1 TB", "16 GB", "2.9 GHz");
        System.out.println("Server = " + server);
    }
}
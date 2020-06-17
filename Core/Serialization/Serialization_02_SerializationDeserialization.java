import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SerializationUtil {
    static void serialize(Person person, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(person);

        objectOutputStream.close();
        fileOutputStream.close();
    }

    static Person deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Person person = (Person) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();
        return person;
    }
}

public class Serialization_02_SerializationDeserialization {
    public static void main(String[] args) {
        Address address = new Address("Arrakis", 80002);
        Person person = new Person(1, "Duke Leto", address, 156, 1);
        System.out.println("Original Person: " + person);

        try {
            SerializationUtil.serialize(person, "Dune");

            Person.setCount(2);

            Person deserializedPerson = SerializationUtil.deserialize("Dune");
            System.out.println("Deserialized Person: " + deserializedPerson);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
class Person implements Serializable {
    private int id;
    private String name;
    private Address address;

    /*
    A variable defined with transient keyword is not serialized during serialization process.
    This variable will be initialized with default value during deserialization. (e.g: for 
    Objects it is null, for int it is 0).
    */
    private transient int heightInCm;
    
    /*
    In case of static Variables:- A variable defined with static keyword is not serialized 
    during serialization process.This variable will be loaded with current value defined in 
    the class during deserialization.
    */
    private static int count;
    private static final long serialVersionUID = 1L;

    public Person(int id, String name, Address address, int heightInCm, int count) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.heightInCm = heightInCm;
        Person.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        this.heightInCm = heightInCm;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", address=" + address 
            + ", heightInCm=" + heightInCm + ", count=" + count + "]";
    }
}
class Address implements Serializable {
    private String city;
    private long zipCode;
    private static final long serialVersionUID = 2L;

    public Address(String city, long zipCode) {
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", zipCode=" + zipCode + "]";
    }
}
import java.io.Serializable;
//import org.apache.commons.lang3.SerializationUtils;

// The class needs to implement java.io.Serializable interface
class Person implements Serializable {
    private int id;
    private String name;

    Person (int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return "Person [id: " + id + ", name: " + name + "]";
    }
}
class Prototype_03_UsingSerialization{
    public static void main(String[] args) {
        Person luthien = new Person(5, "Luthien");
        // Person beren = SerializationUtils.roundtrip();

        // beren.setName("Beren");

        System.out.println("Luthien: " + luthien);
        // System.out.println("Beren: " + beren);
        
    }
}
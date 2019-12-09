// Another way to allow cloning is to implement a copy constructor for each class
class Address {
    private String streetName, city, country;
    Address (String streetName, String city, String country) {
        this.streetName = streetName;
        this.city = city;
        this.country = country;
    }

    // Copy Constructor
    Address (Address other) {
        this(other.streetName, other.city, other.country);
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "Address [streetName: " + streetName + ", city: " 
        + city + ", country: " + country + "]";
    }
}

class Student {
    private String name;
    private Address address;

    Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Copy Constructor
    Student(Student other) {
        name = other.name;
        address = new Address(other.address);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreetName(String street) {
        this.address.setStreetName(street);
    }

    @Override
    public String toString() {
        return "Student[name: " + name + ", address: " + address + "]";
    }
}
class Prototype_02_UsingCopyConstructor {
    public static void main(String[] args) {
        Student turgon = new Student("Turgon", new Address("Rose Street", "Gondolin", "Beleriand"));
        Student tuor = new Student(turgon);
        tuor.setName("Tuor");
        tuor.setStreetName("White Street");

        System.out.println("Turgon: " + turgon);
        System.out.println("Tuor: " + tuor);
    }
}
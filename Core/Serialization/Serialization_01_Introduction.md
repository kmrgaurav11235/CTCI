# Serialization and Deserialization in Java
* Serialization is a mechanism of converting the state of an object into a byte stream. 
* Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory. 
* The Serialization-Deserialization mechanism is used to persist the object.
* The byte stream is a static stream (sequence) of bytes which can then be saved to a database or transferred over a network.
* The byte stream created is platform independent. So, the object serialized on one platform can be deserialized on a different platform.
* Constructor of object is never called when an object is deserialized.
* To make a Java object serializable we implement the `java.io.Serializable` interface. Only the objects of those classes can be serialized which are implementing `java.io.Serializable` interface.
* `Serializable` is a marker interface (has no data member and method). It is used to “mark” java classes so that objects of these classes may get certain capability. Other examples of marker interfaces are:- Cloneable and Remote.
* Serialization in java is implemented by `ObjectInputStream` and `ObjectOutputStream`. All we need, is a wrapper over them to either save it to file or send it over the network.
* Both `ObjectInputStream` and `ObjectOutputStream` are high level classes that extend `java.io.InputStream` and `java.io.OutputStream`, respectively. `ObjectOutputStream` can write primitive types and graphs of objects to an OutputStream as a stream of bytes. These streams can subsequently be read using `ObjectInputStream`.
* The ObjectOutputStream class contains writeObject() method for serializing an Object.
  * `public final void writeObject(Object obj) throws IOException`
* The ObjectInputStream class contains readObject() method for deserializing an object.
    * `public final Object readObject() throws IOException, ClassNotFoundException`

## Advantages of Serialization
1. To save/persist state of an object.
2. To travel an object across a network.

## Static and Transient members
1. Only non-static data members are saved via Serialization process.
2. Static data members and transient data members are not saved via Serialization process. So, if you don’t want to save value of a non-static data member then make it transient.

## Inheritance and Composition
* When a class implements the `java.io.Serializable` interface, all its sub-classes are serializable as well. The converse is not true.
* When an object has a reference to another object, these objects must implement the Serializable interface separately, or else a `NotSerializableException` will be thrown:
    ```
    public class Person implements Serializable {
        private int age;
        private String name;
        private Address country; // must be serializable too
    }
    ```
* If one of the fields in a serializable object consists of an array of objects, then all these objects must be serializable as well, or else a `NotSerializableException` will be thrown.

## Serial Version UID
* The Serialization runtime associates a version number (`long` type) with each Serializable class called a SerialVersionUID. 
* It is used to verify that the saved and loaded objects have the same attributes and thus are compatible on serialization. If the reciever has loaded a class for the object that has different UID than that of corresponding sender’s class, the Deserialization will result in an `InvalidClassException`. 
* A Serializable class can declare its own UID explicitly by declaring a field name.
  * It must be static, final and of type long, e.g.
    * `<ANY-ACCESS-MODIFIER> static final long serialVersionUID=42L;`
* If a Serializable class doesn’t explicitly declare a serialVersionUID, then the serialization runtime will calculate a default one for that class based on various aspects of class, as described in Java Object Serialization Specification. 
* However, it is strongly recommended that all Serializable classes explicitly declare serialVersionUID value, since its computation is highly sensitive to class details that may vary depending on compiler implementations, any change in class or using different id may affect the serialized data.
* It is also recommended to use private modifier for UID since it is not useful as inherited member.
serialver
* The serialver is a tool that comes with JDK. It is used to get serialVersionUID number for Java classes.
You can run the following command to get serialVersionUID:
    * `serialver [-classpath classpath] [-show] [classname]`
`

## Sources:
1. https://www.geeksforgeeks.org/serialization-in-java/
2. https://www.baeldung.com/java-serialization
3. https://www.journaldev.com/2452/serialization-in-java

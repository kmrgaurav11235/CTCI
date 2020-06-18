/*
- Java specifies a default way in which objects can be serialized. Java classes can 
    override this default behavior. 
- Custom serialization can be particularly useful when trying to serialize an object 
    that has some unserializable attributes. 
- This can be done by providing two methods inside the class that we want to serialize:
    private void writeObject(ObjectOutputStream out) 
        throws IOException;
    private void readObject(ObjectInputStream in) 
        throws IOException, ClassNotFoundException;
- With these methods, we can serialize those unserializable attributes into other forms 
    that can be serialized.
*/
public class Serialization_03_CustomSerialization {
}
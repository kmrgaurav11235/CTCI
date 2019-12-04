/*
https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
https://www.youtube.com/watch?v=D5NK5qMM14g

Builder pattern helps us in creating immutable classes with large set of state attributes.
1) First of all you need to create a static nested class and then copy all the arguments from 
the outer class to the Builder class. We should follow the naming convention and if the class 
name is Test then builder class should be named as TestBuilder.
2) Java Builder class should have a public constructor with all the required attributes as parameters.
3) Java Builder class should have methods to set the optional parameters and it should return the 
same Builder object after setting the optional attribute.
4) The final step is to provide a build() method in the builder class that will return the Object 
needed by client program. For this we need to have a private constructor in the Class with Builder 
class as argument.
*/

class User 
{
    /*
    In any user management module, primary entity is User. Ideally and practically as well, 
    once a user object is fully created, you will not want to change it’s state. Now, let’s assume, 
    our User object has following 5 attributes i.e. firstName, lastName, age, phone and address.
    But, what if only firstName and lastName are mandatory and rest 3 fields are optional. We will need 
    a lot of constructors.
    One way it to create more constructors, and another is to loose the immutability and introduce setter 
    methods. Here, builder pattern will help you to consume additional attributes while retaining the 
    immutability of User class.
    */

    //All final attributes
    private final String firstName; // required
    private final String lastName; // required
    private final int age; // optional
    private final String phone; // optional
    private final String address; // optional
 
    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }
 
    //All getter, and No setter to provde immutability
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
 
    @Override
    public String toString() {
        return "User: "+this.firstName+", "+this.lastName+", "+this.age+", "+this.phone+", "+this.address;
    }
 
    public static class Builder 
    {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;
 
        public Builder(String firstName, String lastName) {
            // Mandatory stuff
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        //Return the finally consructed User object
        public User build() {
            User user =  new User(this);
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {
            //Do some basic validations to check 
            //if user object does not break any assumption of system
        }
    }
}
class Builder_04_FluentBuilderForClass {
    public static void main(String[] args) {
        User user1 = new User.Builder("Lokesh", "Gupta")
            .age(30)
            .phone("1234567")
            .address("Fake address 1234")
            .build();
        System.out.println(user1);
    
        User user2 = new User.Builder("Jack", "Reacher")
            .age(40)
            .phone("5655")
            //no address
            .build();
        System.out.println(user2);
    
        User user3 = new User.Builder("Super", "Man")
            //No age
            //No phone
            //no address
            .build();
        System.out.println(user3);
        /*
        Disadvantages of Builder Pattern:
        Though Builder pattern reduce some line of code buy eliminating the need of setter methods, still 
        in double up total lines by introducing the Builder object. Furthermore, although client code is 
        more readable, the client code is also more verbose. 
        */
    }
}
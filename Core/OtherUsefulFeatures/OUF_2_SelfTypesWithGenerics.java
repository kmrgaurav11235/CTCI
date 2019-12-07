/*
https://www.sitepoint.com/self-types-with-javas-generics/
In some situations (e.g. Fluent Builder Design pattern), we need to return 'this'. This is ok on its own
but the problem arises when we need to inherit these classes. And the inherited method should return the
instance of inherited class:
Class A -> Method sample() returns A
   |
inherited by
   |
Class B -> Method sample() should return B

We want to return 'the type of this', often called 'self-type'. But there is no such thing in java.
*/
class PersonType {
    private String name;
    // And other attributes...
    PersonType(String name) {
        this.name = name;
    }
    PersonType(PersonWithoutGenericsBuilder personWithoutGenericsBuilder) {
        this.name = personWithoutGenericsBuilder.getName();
    }
    PersonType(PersonWithGenericsBuilder personWithGenericsBuilder) {
        // This method will be used in 2nd part
        this.name = personWithGenericsBuilder.getName();
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "PersonType [name: " + name + "]";
    }
}

class PersonWithoutGenericsBuilder {
    private String name;
    // And other attributes...
    PersonWithoutGenericsBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public String getName() {
        return name;
    }
    public PersonType build() {
        return new PersonType(this);
    }
} // See the use of these two in main

// Now let's create Employee by extending Person
class EmployeeType extends PersonType {
    private String position;
    // And other attributes...
    EmployeeType(String name, String position) {
        super(name);
        this.position = position;
    }
    EmployeeType(EmployeeWithoutGenericsBuilder employeeWithoutGenericsBuilder) {
        super(employeeWithoutGenericsBuilder.getName());
        this.position = employeeWithoutGenericsBuilder.getPosition();
    }
    EmployeeType(EmployeeWithGenericsBuilder employeeWithGenericsBuilder) {
        // This method will be used in 2nd part
        super(employeeWithGenericsBuilder.getName());
        this.position = employeeWithGenericsBuilder.getPosition();
    }
    public String getPosition() {
        return position;
    }
    @Override
    public String toString() {
        return "EmployeeType [name: " + getName() + ", position: " + getPosition() + "]";
    }
    
}
class EmployeeWithoutGenericsBuilder extends PersonWithoutGenericsBuilder {
    private String position;
    // And other attributes...
    EmployeeWithoutGenericsBuilder withPosition(String position) {
        this.position = position;
        return this;
    }
    public String getPosition() {
        return position;
    }
    public EmployeeType build() {
        return new EmployeeType(this);
    }
} // Now we see the problem. See the use of these two in main

/*
To deal with this, we could override withName in EmployeeWithoutGenericsBuilder. But that would mean
overriding all the methods for each Person attribute.
The problem is that the return type of withName() is explicitly fixed to the class that declares the method.
It should, instead, be the type on which the method was called instead of the one that declares it - 'self-type'.
The solution is in the next file.
*/
class OUF_2_SelfTypesWithGenerics {
    public static void main(String[] args) {
        PersonType personType = new PersonWithoutGenericsBuilder()
            .withName("Jason Bourne")
            .build();
        System.out.println("Jason: " + personType);

        // EmployeeType employeeType = // Comment as it will give Type mismatch error.
        new EmployeeWithoutGenericsBuilder() // returns an EmployeeWithoutGenericsBuilder
                .withName("John Micheal Cain") // returns a PersonWithoutGenericsBuilder
                //.withPosition("For Charlie") // withPosition() is no longer avaliable
                .build();
    }
}
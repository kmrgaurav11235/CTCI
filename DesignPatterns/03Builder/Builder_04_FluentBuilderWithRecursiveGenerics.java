/* 
https://www.sitepoint.com/self-types-with-javas-generics/
Let us create a Person class with personal info (name) and 
employee info (position)
*/
class Person {
    protected String name, position;

    @Override
    public String toString() {
        return "Person {name: " + name + "position: " + position + "}.";
    }
}
// PersonBuilder builds personal information
class PersonBuilder {
    Person person = new Person();
    PersonBuilder withName(String name) {
        person.name = name;
        return this;
    }
    Person build() {
        return person;
    }
}

// And EmployeeBuilder builds employee info
class EmployeeBuilder extends PersonBuilder {
    EmployeeBuilder withPosition(String position) {
        person.position = position;
        return this;
    }

    Person build() {
        return person;
    }
}
/* 
This looks like it should work. But it doesn't. As explained in the main method, withName() method
returns PersonBuilder for which withPosition() is not available. 
We can now use Java Recursive Generics to re-design PersonBuilder
*/
class Builder_04_FluentBuilderWithRecursiveGenerics {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();

        Person kgaurav1 = personBuilder.withName("Gaurav")
            // .withPosition("") withPosition() is not available
            .build();
        Person kgaurav2 = employeeBuilder.withName("Gaurav")
            // .withPosition("") withPosition() is still not available as withName() returns PersonBuilder not EmployeeBuilder.
            .build();
    }
}
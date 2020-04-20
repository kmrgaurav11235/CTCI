/*
https://www.sitepoint.com/self-types-with-javas-generics/

While Java doesn’t have self types, there is a way to emulate them with generics. This is limited and 
a little convoluted, though. We use Generics to pass the expected Return type of the method.

PersonType and EmployeeType classes remain same as in the last one. 
*/
class PersonWithGenericsBuilder<SELF extends PersonWithGenericsBuilder<SELF>> {
    /*
    The above line means that PersonWithGenericsBuilder with take a generic argument - SELF. This 
    SELF will be used as return-type of withName() method in order to preserve the fluent interface.
    The 'extends' part means that SELF must be a typ that expends PersonWithGenericsBuilder.
    */
    private String name;
    // And other attributes...
    public SELF withName(String name) {
        this.name = name;
        return self();
    }
    public String getName() {
        return name;
    }
    public PersonType build() {
        return new PersonType(this);
    }
    protected SELF self() {
        return (SELF)this;
    }
}
class EmployeeWithGenericsBuilder extends PersonWithGenericsBuilder<EmployeeWithGenericsBuilder> {
    private String position;
    // And other attributes...
    EmployeeWithGenericsBuilder withPosition(String position) {
        this.position = position;
        return self();
    }
    public String getPosition() {
        return position;
    }
    public EmployeeType build() {
        return new EmployeeType(this);
    }
    @Override
    protected EmployeeWithGenericsBuilder self() {
        return this;
    }
}

class Generics_08_SelfTypesWithGenericsSolution {
    public static void main(String[] args) {
        PersonType personType = new PersonWithGenericsBuilder()
            .withName("Jason Bourne")
            .build();
        System.out.println("Jason: " + personType);

        EmployeeType employeeType = 
            new EmployeeWithGenericsBuilder()
                .withName("John Micheal Cain")
                .withPosition("For Charlie")
                .build();
        System.out.println("Cain: " + employeeType);
    }
}
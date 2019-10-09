import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

/*
Not related to Dependency Injection.
Dependency Inversion Principle:
    1. High level modules should not depend on low-level modules. Both should depend on Abstractions (interfaces).
        e.g. in Asset Registry, NodeService (high-level) didn't depend on MongoNodeDaoImpl; both depended 
        on NodeDao interface.
    2. Abstractions should not depend on Details. Details should depend on Abstractions (Abstractions = Interfaces, Details = Classes).

High-level modules are those that are  closer to user-interaction, e.g. web-services.
Low-level modules are those that are closer to Data-storage, e.g. databases, cache etc.
*/

// Let's see '1.' using an application that models relationship between people.
enum RelationshipType {
    // Type of Relationships
    PARENT, CHILD, SIBLINGS;
}

class Person {
    // Represents a person
    public String name;
    public Person(String name) {
        this.name = name;
    }
}

class Relationships {
    // Represents relationship between two different 'Person'
    private List<Triplet<Person, RelationshipType, Person>> relations = new ArrayList<>(); 

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, RelationshipType.PARENT, child));
        relations.add(new Triplet<>(child, RelationshipType.CHILD, parent));
    }

    public List<Triplet<Person, RelationshipType, Person>> getRelations() {
        return relations;
    }
    // Since relations is private but it is needed in Research, we need to provide a getter.
}

class Research {
    // Represents action at high level that is useful to users.

    public void doResearch (Relationships relationships) {
        // Print all children whose parent is Aerys.
        List<Triplet<Person, RelationshipType, Person>> relations = relationships.getRelations();
        relations.stream()
            .filter(x -> x.getValue0().name.equals("Aerys") && x.getValue1() == RelationshipType.PARENT)
            .forEach(ch -> System.out.println("Aerys has a child called " + ch.getValue2().name + "."));
    }
}
/*
This set-up does work as demostrated in the first part of main() method.
The problem is that we are exposing an internal storage implementation of Relationships as a public
getter for everyone to access.
In our implementation, Relationships is a low level module as it is related to data-storage. It is 
associated with a list and allows its modification. It doesn't have any business logic of its own.
Research is a high-level module. It allows us to perform operations on low-level contsructs and get
information. In this implementation, the high-level module (Research) depends on low-level module 
(Relationships) - a violation of Dependency Inversion Principle. Instead as '2.' says, we should
depend on abstractions instead. So, let us introduce interfaces.
*/

// This is the abstraction that we develop on top of our low-level module.
interface RelationshipBrowser {
    // Provides all children of a person with provided name
    List<Person> findAllChildrenOf (String name);
}

class ImprovedRelationships implements RelationshipBrowser {
    // Represents relationship between two different 'Person'
    private List<Triplet<Person, RelationshipType, Person>> relations = new ArrayList<>(); 

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, RelationshipType.PARENT, child));
        relations.add(new Triplet<>(child, RelationshipType.CHILD, parent));
    }

    // The search does not happens in high-level module; it happens in low-level module
    // Then, if you change the implementaion, e.g. List to Set, you don't have re-write
    // code in 'Research'.
    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                    .filter(x -> x.getValue0().name.equals("Aerys") && x.getValue1() == RelationshipType.PARENT)
                    .map(x -> x.getValue2())
                    .collect(Collectors.toList());
    }
    // No need of getter here. The implemntation details are hidden from 'Research'.
}

class ImprovedResearch {
    // Represents action at high level that is useful to users.

    public void doResearch (RelationshipBrowser relationshipBrowser) {
        // Print all children whose parent is Aerys.
        List<Person> findAllChildrenOf = relationshipBrowser.findAllChildrenOf("Aerys");
        findAllChildrenOf.forEach(x -> System.out.println("Aerys has a child called " + x.name + "."));
    }
}

class SDP_05_DependencyInversionPrinciple {
    public static void main(String[] args) {
        Person parent = new Person("Aerys");
        Person child1 = new Person("Rhaegar");
        Person child2 = new Person("Daenarys");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research().doResearch(relationships);

        // Better Implementation
        System.out.println("----------Better Implementation----------");

        ImprovedRelationships improvedRelationships = new ImprovedRelationships();
        improvedRelationships.addParentAndChild(parent, child1);
        improvedRelationships.addParentAndChild(parent, child2);

        new ImprovedResearch().doResearch(improvedRelationships);
    }
}
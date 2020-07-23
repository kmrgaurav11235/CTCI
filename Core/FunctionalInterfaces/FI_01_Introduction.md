## Functional Interface
* An interface with exactly one abstract method is called Functional Interface. `@FunctionalInterface` annotation is added so that we can mark an interface as functional interface. It is not mandatory to use it, but it’s best practice to use it with functional interfaces to avoid addition of extra methods accidentally. 
* If the interface is annotated with `@FunctionalInterface` annotation and we try to have more than one abstract method, it throws compiler error.
* Note that Java's default methods are not abstract and do not count -- a functional interface may still have multiple default methods. 
* We have already discussed Functional Interfaces under Lambda Expressions. Now, we will discuss the built-in Functional Interfaces inside `java.util.function` package.

## Built-in Functional Interfaces in Java
Java contains a set of functional interfaces designed for commonly occurring use cases, so you don't have to create your own functional interfaces for every little use case. In the following sections, we will be describing some of these built-in functional interfaces in Java. 
1. Function
2. Predicate
3. Supplier
4. Consumer
5. UnaryOperator
6. BinaryOperator

## Resources
* https://www.baeldung.com/java-8-functional-interfaces
* http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html
* https://www.journaldev.com/2774/java-8-stream
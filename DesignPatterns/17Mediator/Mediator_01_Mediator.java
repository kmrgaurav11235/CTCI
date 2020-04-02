/*
https://www.journaldev.com/1730/mediator-design-pattern-java
- Mediator design pattern is one of the behavioral design pattern, so it deals with the behaviors of objects. 
    It is used to provide a centralized communication medium between different objects in a system. 
- The intent of mediator pattern is to allow loose coupling by encapsulating the way disparate sets of objects 
    interact and communicate with each other. It allows for the actions of each object set, to vary independently 
    of one another.
- Mediator design pattern is very helpful in an enterprise application where multiple objects are interacting with 
    each other. If the objects interact with each other directly, the system components are tightly-coupled with 
    each other that makes higher maintainability cost and not hard to extend. Mediator pattern focuses on providing 
    a mediator between objects for communication. It thus helps in implementing lose-coupling between objects.

- Air traffic controller is a great example of mediator pattern where the airport control room works as a mediator 
    for communication between different flights. Mediator works as a router between objects and it can have it’s 
    own logic to provide way of communication.
- The system objects that communicate each other are called Colleagues. Usually we have an interface or abstract 
    class that provides the contract for communication and then we have concrete implementation of mediators.
*/
import java.util.ArrayList;
import java.util.List;

// Mediator interface that will define the contract for concrete mediators
interface ChatMediator {
    public void addUser(User user);
    public void sendMessage(String message, User user);
}

// Colleague Interface
abstract class User {
    protected String name;
    protected ChatMediator chatMediator;

    User(String name, ChatMediator chatMediator) {
        this.name = name;
        this.chatMediator = chatMediator;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}

// Concrete Mediator
class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    ChatMediatorImpl() {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User chatUser : users) {
            if (!chatUser.equals(user)) {
                chatUser.receiveMessage(message);
            }
        }
    }
}

// Concrete Colleague
class UserImpl extends User {

    UserImpl(String name, ChatMediator chatMediator) {
        super(name, chatMediator);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("User{ name: " + name + " } sending message = " + message);
        chatMediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("User{ name: " + name + " } receiving message = " + message);
    }
}
public class Mediator_01_Mediator {
    public static void main(String[] args) {
        ChatMediator chat = new ChatMediatorImpl();

        User feanor = new UserImpl("Feanor", chat);
        User fingolfin = new UserImpl("Fingolfin", chat);
        User finarfin = new UserImpl("Finarfin", chat);
        User mandos = new UserImpl("Mandos", chat);

        chat.addUser(feanor);
        chat.addUser(fingolfin);
        chat.addUser(finarfin);

        feanor.sendMessage("None shall stand in the way of our revenge, least of all those peasant Teleri.");

        chat.addUser(mandos);
        mandos.sendMessage("As a punishment for kinslaying, the Noldor are now cursed!");

        finarfin.sendMessage("I'm out fam!");
    }
}
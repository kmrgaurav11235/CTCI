import java.util.ArrayList;
import java.util.List;

interface ChatMediator {
    public void addUser(User user);
    public void sendMessage(String message, User user);
}

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
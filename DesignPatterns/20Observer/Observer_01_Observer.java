
/*
https://www.journaldev.com/1739/observer-design-pattern-in-java
_ Observer Pattern is one of the behavioral design pattern. It is useful when you are interested in the state 
    of an object and want to get notified whenever there is any change. In observer pattern, the object that 
    watch on the state of another object are called Observer and the object that is being watched is called 
    Subject. 
-  The intent is to define a one-to-many dependency between objects so that when one object changes state, all 
    its dependents are notified and updated automatically.
- Subject contains a list of observers to notify of any change in it’s state, so it should provide methods using 
    which observers can register and unregister themselves. Subject also contain a method to notify all the 
    observers of any change and either it can send the update while notifying the observer or it can provide another 
    method to get the update.
- Observer should have a method to set the object to watch and another method that will be used by Subject to notify 
    them of any updates.
- Java Message Service (JMS) uses Observer design pattern along with Mediator pattern to allow applications to 
    subscribe and publish data to other applications.
- Model-View-Controller (MVC) frameworks also use Observer pattern where Model is the Subject and Views are observers 
    that can register to get notified of any change to the model.
*/
import java.util.ArrayList;
import java.util.List;

interface Subject {
    public void register(Observer observer);
    public void unRegister(Observer observer);
    public void notifyObservers();
    public String getMessage();
    public void postMessage(String message);
}

interface Observer {
    public void setSubject(Subject subject);
    public void update();
}

class MyTopic implements Subject {
    private final Object LOCK;
    private boolean isChanged;
    private String message;
    private List<Observer> observers;

    MyTopic() {
        LOCK = new Object();
        isChanged = false;
        observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        synchronized (LOCK) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }    
        }
    }
    @Override
    public void unRegister(Observer observer) {
        synchronized (LOCK) {
            observers.remove(observer);
        }        
    }
    @Override
    public void notifyObservers() {
        List<Observer> subscriberList;
        //Synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (LOCK) {
            if (!isChanged) {
                return;
            }
            subscriberList = new ArrayList<>(observers);
            isChanged = false;
        }

        for (Observer subscriber : subscriberList) {
            subscriber.update();
        }
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void postMessage(String message) {
        this.message = message;
        isChanged = true;
        notifyObservers();
    }
}

class MyTopicListener implements Observer{

    private String name;
    private Subject subject;
    MyTopicListener(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }
    @Override
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        String message = subject.getMessage();
        System.out.println("MyTopicListener {name: " + name + ", message: " + message + "}");
    }

}
class Observer_01_Observer {
    public static void main(String[] args) {
        MyTopic topic = new MyTopic();

        Observer obs1 = new MyTopicListener("Listener 1", topic);
        Observer obs2 = new MyTopicListener("Listener 2", topic);
        Observer obs3 = new MyTopicListener("Listener 3", topic);

        topic.register(obs1);
        topic.register(obs2);
        topic.register(obs3);

        List<String> messages = List.of("Her hair was long, her limbs were white", 
            "And fair she was and free;", 
            "And in the wind she went as light", 
            "As leaf of linden-tree.");
        
        for (String message : messages) {
            topic.postMessage(message);
        }
    }
}
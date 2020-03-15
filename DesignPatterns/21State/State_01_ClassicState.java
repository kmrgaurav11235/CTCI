/*
https://www.journaldev.com/1751/state-design-pattern-java
- State design pattern is one of the behavioral design pattern. It is used when an Object change its behavior 
    based on its internal state.
- An object transitions from one state to another (something needs to trigger the transition).
- A formalized construct that manages state and transitions is called a state machine. 

- If we have to change the behavior of an object based on its state, we can have a state variable in the Object. 
    Then, we can use if-else condition block to perform different actions based on the state.

    {code}
    if(state.equalsIgnoreCase("ON")){
        // Turn it off
    } else if(state.equalsIgnoreCase("OFF")){
        // Turn it on
    }
    {/code}
    
    However, if number of states increase then the tight coupling between implementation and the client code will be 
    very hard to maintain and extend.

- State design pattern is used to provide a systematic and loosely coupled way to achieve this through Context and 
    State implementations. State Pattern Context (here LightSwitch class) is the class that has a State reference to 
    one of the concrete implementations of the State. Context forwards the request to the state object for processing. 
*/
abstract class State {
    /*
    This class has default behavior. e.g. we are in OFF state and somebody tries to SWITCH OFF the light.
    If there is no default behavior, we can make this an interface. 
    */
    public void off(LightSwitch lightSwitch) {
        System.out.println("Light is already off.");
    }
    public void on(LightSwitch lightSwitch) {
        System.out.println("Light is already on.");
    }
}

// There should be a class for every single type of State. So, here we create OnState and OffState classes.
class OnState extends State {
    OnState() {
        System.out.println("Light is turned on.");
    }

    @Override
    public void off(LightSwitch lightSwitch) {
        System.out.println("Turning Light off now...");
        // We do the state change inside the state. Not in the LightSwitch. LightSwitch just provides the setState API.
        lightSwitch.setState(new OffState());
    }
}

class OffState extends State {
    OffState() {
        System.out.println("Light is turned off.");
    }

    @Override
    public void on(LightSwitch lightSwitch) {
        System.out.println("Turning Light on now...");
        lightSwitch.setState(new OnState());
    }
}

class LightSwitch {

    private State state;

    LightSwitch() {
        state = new OffState();
    }

    public void off() {
        /* 
        Instead of changing the state right here with:
        {code}
        state = new OffState();
        {/code}
        We do it differently. We let the current state handle the transition to the new state.
        */
        state.off(this);
    }

    public void on() {
        state.on(this);
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
}

class State_01_ClassicState {
    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
/*
- The benefits of using State pattern to implement polymorphic behavior is clearly visible. The chances of error are 
less and it’s very easy to add more states for additional behavior. Thus making our code more robust, easily maintainable 
and flexible. Also State pattern helped in avoiding if-else or switch-case conditional logic in this scenario.
*/
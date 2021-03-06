/*
https://www.baeldung.com/java-generics
https://www.journaldev.com/1663/java-generics-example-method-class-interface

- Type parameters can be bounded -- we can restrict types that can be accepted by a method.
- We can specify that a method accepts a type and all its subclasses (upper bound) or a type all its 
    superclasses (lower bound).
- To declare an upper bounded type we use the keyword extends after the type followed by the upper bound 
    that we want to use. For example:
    {code}
    public <T extends Number> List<T> fromArrayToList(T[] a) {
        ...
    }
    {/code}
- The keyword extends is used here to mean that the type T extends the upper bound in case of a class or 
    implements an upper bound in case of an interface.
- Java allows multiple bounds - but normal inheritance rules apply:
    You can use only single class, but multiple interfaces. Also, list the class first, then interfaces.
        <T extends Class & Interface1 & Interface2>
*/
import java.util.ArrayList;

class TeamWithGenericsAndBound<T extends Player> {
    String name;
    int points;
    ArrayList<T> players;

    TeamWithGenericsAndBound(String name) {
        this.name = name;
        points = 0;
        players = new ArrayList<>();
    }

    void addPlayer(T player) {
        if (players.contains(player)) {
            System.out.println(player.name + " is already in team " + this.name + ".");
        } else {
            players.add(player);
            System.out.println(player.name + " added to team " + this.name + ".");
        }
    }

    @Override
    public String toString() {
        return "TeamWithGenericsAndBound: " + name + " {Points : " + points + "}\n";
    }
}

class Generics_02_Bound{
    public static void main(String[] args) {
        CricketPlayer sachin = new CricketPlayer("Sachin");

        TeamWithGenericsAndBound<CricketPlayer> boundCricketTeam = new TeamWithGenericsAndBound<>("Bound Cricket Team");
        boundCricketTeam.addPlayer(sachin);
        System.out.println(boundCricketTeam);

        // Below line will give compile time errors now.
        // TeamWithGenericsAndBound<String> invalidStringTeam1 = new TeamWithGenericsAndBound<>("Invalid Team");
    }
}
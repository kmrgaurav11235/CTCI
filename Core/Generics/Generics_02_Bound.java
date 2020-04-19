/*
https://www.baeldung.com/java-generics
https://www.journaldev.com/1663/java-generics-example-method-class-interface
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
}/*
Notes:
1. T can extend a class or interface.
2. Interfaces can have generics too (with or without bound).
3. Java allows multiple bounds - but normal inheritance rules apply:
    You can use only single class, but multiple interfaces. Also, list the class first, then interfaces.
        <T extends Class & Interface1 & Interface2>
4. We can also pass multiple Type parameters in Generic classes.
    class Test<T, U> { 
        T obj1;
        U obj2;
    }
*/

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
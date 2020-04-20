/*
https://www.baeldung.com/java-generics
https://www.journaldev.com/1663/java-generics-example-method-class-interface

- Generics was added in Java 5 to provide compile-time type checking and removing risk of ClassCastException 
    that was common while working with collection classes. The whole collection framework was re-written to 
    use generics for type-safety.
- Below code compiles fine but throws ClassCastException at runtime because we are trying to cast Object in 
    the list to String whereas one of the element is of type Integer.
    {code}
    List list = new ArrayList();
    list.add("abc");
    list.add(new Integer(5)); //OK

    for(Object obj : list){
        //type casting leading to ClassCastException at runtime
        String str = (String) obj; 
    }
    {/code}
- Coding with Generics:
    {code}
    List<String> list1 = new ArrayList<String>(); // java 7 ? List<String> list1 = new ArrayList<>(); 
    list1.add("abc");
    //list1.add(new Integer(5)); //compiler error

    for(String str : list1){
        //no type casting needed, avoids ClassCastException
    }
    {/code}
- Notice that at the time of list creation, we have specified that the type of elements in the list will be 
    String. So if we try to add any other type of object in the list, the program will throw compile-time 
    error. Also notice that in for loop, we don’t need typecasting of the element in the list, hence removing 
    the ClassCastException at runtime.
- We can also pass multiple Type parameters in Generic classes.
    {code}
    class Test<T, U> { 
        T obj1;
        U obj2;
    }
    {/code}
}

*/
import java.util.ArrayList;

// We have to create 3 types of players: Football, Cricket and Hockey. Then we have to allow them to form a team.
abstract class Player {
    String name;
    Player(String name) {
        this.name = name;
    }
}

// We can now derive this into 3 types of players
class FootballPlayer extends Player {
    FootballPlayer(String name) {
        super(name);
    }
}

class CricketPlayer extends Player {
    CricketPlayer(String name) {
        super(name);
    }
}

class HockeyPlayer extends Player {
    HockeyPlayer(String name) {
        super(name);
    }
}

class Team {
    String name;
    int points;
    ArrayList<Player> players;

    Team(String name) {
        this.name = name;
        points = 0;
        players = new ArrayList<>();
    }

    void addPlayer(Player player) {
        if (players.contains(player)) {
            System.out.println(player.name + " is already in team " + this.name + ".");
        } else {
            players.add(player);
            System.out.println(player.name + " added to team " + this.name + ".");
        }
    }

    @Override
    public String toString() {
        return "Team: " + name + " {Points : " + points + "}\n";
    }
}

/*
- This team has a simple issue. It allows us to form a team using any type of Player object, e.g. a FootballPlayer,
    CricketPlayer and HockeyPlayer can all be a part of single team. This is demonstrated in the main method. To deal with
    this, let us create a better team using Generics.
- We can now define our classes with generics type. A generic type is a class or interface that is parameterized over types. 
    We use angle brackets (<>) to specify the type parameter. 
- References to generic type GenericsType<T> should be parameterized. When we don’t provide the type, the type becomes 
    Object. But, we should always try to avoid this because we will have to use type casting while working on raw type 
    that can produce runtime errors.
*/

class TeamWithGenerics<T> {
    String name;
    int points;
    ArrayList<T> players;

    TeamWithGenerics(String name) {
        this.name = name;
        points = 0;
        players = new ArrayList<>();
    }

    void addPlayer(T player) {
        if (players.contains(player)) {
            /* 
            Since player is now a generic type, we have to cast it before we can access its name.
            Also, this allows any type of team to be created, e.g. TeamWithGenerics<String> cricketTeam
            which is invalid as addPlayer() method will throw error.
            This is not a good code; we will solve this problem next.
            */
            System.out.println(((Player) player).name + " is already in team " + this.name + ".");
        } else {
            players.add(player);
            System.out.println(((Player) player).name + " added to team " + this.name + ".");
        }
    }

    @Override
    public String toString() {
        return "TeamWithGenerics: " + name + " {Points : " + points + "}\n";
    }
}

class Generics_01_Generics{
    public static void main(String[] args) {
        System.out.println("**********  Simple Teams  **********");
        FootballPlayer bhutia = new FootballPlayer("Bhutia");
        CricketPlayer sachin = new CricketPlayer("Sachin");
        HockeyPlayer dhanraj = new HockeyPlayer("Dhanraj");

        Team weirdTeam = new Team("Weird Team");
        weirdTeam.addPlayer(bhutia);
        weirdTeam.addPlayer(sachin);
        weirdTeam.addPlayer(dhanraj);
        System.out.println(weirdTeam);
        // Any type of player can be added in this team. This is clearly a design flaw.

        System.out.println("**********  Teams with Generics  **********");
        TeamWithGenerics<CricketPlayer> cricketTeam = new TeamWithGenerics<>("Cricket Team");
        cricketTeam.addPlayer(sachin);
        // Below lines will give compile time errors now.
        //cricketTeam.addPlayer(bhutia);
        //cricketTeam.addPlayer(dhanraj);
        System.out.println(cricketTeam);

        // We should not allow the creation of this team as it will give cast error when addPlayer() is called
        TeamWithGenerics<String> invalidStringTeam = new TeamWithGenerics<>("Invalid Team");
        // Below line will throw java.lang.ClassCastException
        invalidStringTeam.addPlayer("test"); 
    }
}
/*
- Java Generic Type Naming convention helps us understanding code easily and having a naming convention is 
    one of the best practices of Java programming language. So, Generics also comes with its own naming 
    conventions. 
- Usually, type parameter names are single, uppercase letters to make it easily distinguishable from java 
    variables. The most commonly used type parameter names are:
    * E – Element (used extensively by the Java Collections Framework, for example ArrayList, Set etc.)
    * K – Key (Used in Map)
    * N – Number
    * T – Type
    * V – Value (Used in Map)
    * S,U,V etc. – 2nd, 3rd, 4th types

*/
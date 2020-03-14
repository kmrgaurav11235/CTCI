/*
https://www.baeldung.com/java-generics
https://www.journaldev.com/1663/java-generics-example-method-class-interface
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
This team has a simple issue. It allows us to form a team using any type of Player object, e.g. a FootballPlayer,
CricketPlayer and HockeyPlayer can all be a part of single team. This is demonstrated in the main method. To deal with
this, let us create a better team using Generics.
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

// Finally let's allow team comparisons using the Comparable interface
class ComparableTeamWithGenericsAndBound<T extends Player> implements Comparable<ComparableTeamWithGenericsAndBound<T>>{
    /* 
    Note the syntax. Usual syntax of Comparable: Comparable<Class to compare>
    If we keep just Comparable<ComparableTeamWithGenericsAndBound>, we will be able to compare points
    different types of teams, e.g. Cricket and Hockey teams.
    */
    String name;
    int points;
    ArrayList<T> players;

    ComparableTeamWithGenericsAndBound(String name) {
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
    public int compareTo(ComparableTeamWithGenericsAndBound<T> team) {
        if (points > team.points) {
            return 1;
        } else if (points == team.points) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "ComparableTeamWithGenericsAndBound: " + name + " {Points : " + points + "}\n";
    }
}
class Generics_1_Generics{
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
        // invalidStringTeam.addPlayer("test"); 

        System.out.println("**********  Team with Generics and Bound  **********");
        TeamWithGenericsAndBound<CricketPlayer> boundCricketTeam = new TeamWithGenericsAndBound<>("Bound Cricket Team");
        boundCricketTeam.addPlayer(sachin);
        System.out.println(boundCricketTeam);

        // Below line will give compile time errors now.
        // TeamWithGenericsAndBound<String> invalidStringTeam1 = new TeamWithGenericsAndBound<>("Invalid Team");

        System.out.println("**********  Comparable Team with Generics and Bound  **********");
        ComparableTeamWithGenericsAndBound<CricketPlayer> comparableCricketTeam0 = 
                    new ComparableTeamWithGenericsAndBound<>("First Comparable Cricket Team");
        comparableCricketTeam0.addPlayer(sachin);
        comparableCricketTeam0.points = 10;
        ComparableTeamWithGenericsAndBound<CricketPlayer> comparableCricketTeam1 = 
                    new ComparableTeamWithGenericsAndBound<>("Second Comparable Cricket Team");
        comparableCricketTeam1.addPlayer(sachin);
        comparableCricketTeam1.points = 7;
        System.out.println(comparableCricketTeam0);
        System.out.println(comparableCricketTeam1);
        System.out.println("Comparing First team to second: " + comparableCricketTeam0.compareTo(comparableCricketTeam1));

        ComparableTeamWithGenericsAndBound<HockeyPlayer> comparableHockeyTeam0 = 
                    new ComparableTeamWithGenericsAndBound<>("First Comparable Hockey Team");
        comparableHockeyTeam0.addPlayer(dhanraj);
        comparableHockeyTeam0.points = 5;
        ComparableTeamWithGenericsAndBound<HockeyPlayer> comparableHockeyTeam1 = 
                    new ComparableTeamWithGenericsAndBound<>("Second Comparable Hockey Team");
        comparableHockeyTeam1.addPlayer(dhanraj);
        comparableHockeyTeam1.points = 9;
        System.out.println(comparableHockeyTeam0);
        System.out.println(comparableHockeyTeam1);
        System.out.println("Comparing First team to second: " + comparableHockeyTeam0.compareTo(comparableHockeyTeam1));
        System.out.println("Comparing First team to first: " + comparableHockeyTeam0.compareTo(comparableHockeyTeam0));

        // Below line will give compile time errors.
        // comparableHockeyTeam1.compareTo(comparableCricketTeam0);

    }
}
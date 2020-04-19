/*
https://www.baeldung.com/java-generics
https://www.journaldev.com/1663/java-generics-example-method-class-interface
*/
import java.util.ArrayList;

// Finally let's allow team comparisons using the Comparable interface
class ComparableTeam<T extends Player> implements Comparable<ComparableTeam<T>>{
    /* 
    Note the syntax. Usual syntax of Comparable: Comparable<Class to compare>
    If we keep just Comparable<ComparableTeamWithGenericsAndBound>, we will be able to compare points
    different types of teams, e.g. Cricket and Hockey teams.
    */
    String name;
    int points;
    ArrayList<T> players;

    ComparableTeam(String name) {
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
    public int compareTo(ComparableTeam<T> team) {
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
class Generics_03_Comparable{
    public static void main(String[] args) {
        CricketPlayer sachin = new CricketPlayer("Sachin");
        HockeyPlayer dhanraj = new HockeyPlayer("Dhanraj");

        ComparableTeam<CricketPlayer> comparableCricketTeam0 = 
                    new ComparableTeam<>("First Comparable Cricket Team");
        comparableCricketTeam0.addPlayer(sachin);
        comparableCricketTeam0.points = 10;
        ComparableTeam<CricketPlayer> comparableCricketTeam1 = 
                    new ComparableTeam<>("Second Comparable Cricket Team");
        comparableCricketTeam1.addPlayer(sachin);
        comparableCricketTeam1.points = 7;
        System.out.println(comparableCricketTeam0);
        System.out.println(comparableCricketTeam1);
        System.out.println("Comparing First team to second: " + comparableCricketTeam0.compareTo(comparableCricketTeam1));

        ComparableTeam<HockeyPlayer> comparableHockeyTeam0 = 
                    new ComparableTeam<>("First Comparable Hockey Team");
        comparableHockeyTeam0.addPlayer(dhanraj);
        comparableHockeyTeam0.points = 5;
        ComparableTeam<HockeyPlayer> comparableHockeyTeam1 = 
                    new ComparableTeam<>("Second Comparable Hockey Team");
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
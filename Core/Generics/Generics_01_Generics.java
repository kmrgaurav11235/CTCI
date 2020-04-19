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
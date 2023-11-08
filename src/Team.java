import java.util.ArrayList;

public class Team {
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player goalkeeper;
    private final int teamRating;
    private final String name;
    public Team(String name){
        this.name = name;
        int ratingSum = 0;
        for(int i = 0; i < 10; i++) {
            Player p = new Player();
            players.add(p);
            ratingSum += p.getRating();
        }
        Player gk = new Player();
        goalkeeper = gk;
        ratingSum += gk.getRating();
        teamRating = ratingSum;
    }

    @Override
    public String toString() {
        return name + " (" + teamRating + ") ";
    }

    public int getTeamRating() {
        return teamRating;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }
}

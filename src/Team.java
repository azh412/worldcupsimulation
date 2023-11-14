import java.util.ArrayList;
import java.util.Objects;

public class Team {
    private ArrayList<Player> players = new ArrayList<Player>();
    private final Player goalkeeper;
    private final int teamRating;
    private final String name;
    private final String abrv;
    public Team(String name, String abrv){
      //  name = abrv = "";
        this.name = name;
        this.abrv = abrv;
        int ratingSum = 0;
        for(int i = 0; i < 10; i++) {
            Player p = new Player(this);
            players.add(p);
            ratingSum += p.getRating();
        }
        Player gk = new Player(this,true);
        goalkeeper = gk;
        ratingSum += gk.getRating()*2;
        teamRating = ratingSum;
    }

    @Override
    public String toString() {
        return name + " (" + abrv + ") " + "(" + teamRating + ") ";
    }

    public int getTeamRating() {
        return teamRating;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getGoalie() {
        return goalkeeper;
    }

    public String getName() {
        return name;
    }

    public String getAbrv() {
        return abrv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Team otherTeam = (Team) obj; // Cast the object to Team
        return name.equals(otherTeam.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

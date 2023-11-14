import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tournament {
    public static void main(String[] args){
        for(int i = 0; i < 70; i++){System.out.print("*");}System.out.println();
        System.out.println("                    .__       .___                      \n" +
                "__  _  _____________|  |    __| _/   ____  __ ________  \n" +
                "\\ \\/ \\/ /  _ \\_  __ \\  |   / __ |  _/ ___\\|  |  \\____ \\ \n" +
                " \\     (  <_> )  | \\/  |__/ /_/ |  \\  \\___|  |  /  |_> >\n" +
                "  \\/\\_/ \\____/|__|  |____/\\____ |   \\___  >____/|   __/ \n" +
                "                               \\/       \\/      |__|    ");

        for(int i = 0; i < 70; i++){System.out.print("*");}System.out.println();
        System.out.println("World Cup Simulator");
        System.out.println("Created by Azhaan Salam");
        for(int i = 0; i < 70; i++){System.out.print("*");}System.out.println();
        ArrayList<Team> teams = new ArrayList<Team>();
        // https://stackoverflow.com/questions/13185727/reading-a-txt-file-using-scanner-class-in-java
        File countries = new File("src/countries.txt");
        try {
            Scanner fscanner = new Scanner(countries);
            while (fscanner.hasNextLine()) {
                String line = fscanner.nextLine();
                String[] split_line = line.split("\t");
                teams.add(new Team(split_line[0], split_line[1]));
            }
            fscanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Team> qualified_teams = new ArrayList<Team>();
        for(int i = 0; i < 16; i++){
            Team random_team = teams.get((int) (Math.random() * teams.size()));
            while(qualified_teams.contains(random_team)) {
                random_team = teams.get((int) (Math.random() * teams.size()));
            }
            qualified_teams.add(random_team);
        }
        qualified_teams.sort(new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                int o1r = o2.getTeamRating();
                int o2r = o1.getTeamRating();
                return Integer.compare(o1r, o2r);
            }
        });
        System.out.println("Choose one of the tournament favorites to use for the World Cup.\nTournament Favorites: \n");
        ArrayList<Team> favorites = new ArrayList<Team>();
        for(int i = 0; i < 5; i++){
            System.out.println("[" + (i+1) + "]: " + qualified_teams.get(i).getName() + " (" + qualified_teams.get(i).getAbrv() + ")");
            favorites.add(qualified_teams.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your pick! [1-5]: ");
        int uteamchoice = Integer.parseInt(scanner.nextLine());
        uteamchoice--;
        Team choice = favorites.get(uteamchoice);
        qualified_teams.remove(uteamchoice);
        System.out.println();

        ArrayList<Game> matchups = new ArrayList<Game>();
        Team choice_matchup = qualified_teams.get((int) (Math.random() * qualified_teams.size()));
        while(choice_matchup.equals(choice)){
            choice_matchup = qualified_teams.get((int) (Math.random() * qualified_teams.size()));
        }
        qualified_teams.remove(choice_matchup);
        Game pgame = new Game(choice,choice_matchup);
        matchups.add(pgame);
        pgame = null;
        for(int i = 0; i < qualified_teams.size(); i+=2) {
            pgame = null;
            Team a = qualified_teams.get(i);
            Team b = qualified_teams.get(i + 1);
            pgame = new Game(a, b);
            matchups.add(pgame);
        }
        for(Game matchup: matchups) {
            if(matchup.getHome() == choice){
                pgame = matchup;
            }
        }
        System.out.println("You chose " + choice.getName() + " with a rating of " + choice.getTeamRating());
        System.out.println("Team Roster:\n");
        for(Player player: choice.getPlayers()){
            System.out.println(player);
        }
        System.out.println("GK: " + choice.getGoalie()+"\n");
        System.out.println(choice + "is playing " + choice_matchup.toString());
        System.out.print("\nSimulate or play game? (s, p): ");
        String playsimchoice = scanner.nextLine();
        if(playsimchoice.equals("s")){
            pgame.simulateScript();
        }
        else{
            pgame.readScript();
        }

    }
}
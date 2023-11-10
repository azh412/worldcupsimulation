import java.util.ArrayList;

public class Game {
    private static Team home;
    private static Team away;
    private static String script;
    private static int homePoints;
    private static int awayPoints;
    private static Team coinToss;
    public Game(Team home, Team away){
        this.home = home;
        this.away = away;
        generateScript();
    }
    public static void generateScript(){
        int ratingsSum = home.getTeamRating() + away.getTeamRating();
        Team possession = null;
        int zone = 0;
        String[] goalAdj = {"wonderful ", "beautiful ", "incredible ", "jaw-dropping "};
        String[] goalType = {"top-corner ", "bottom-corner "};
        String[] goalSpeed = {"rocket! ", "power shot! ", "curved ball! ", "attempted cross! ", "knuckle-ball! ", "tap-in! "};
        for(int i = 0; i <= 90; i++){
            if(i == 0){
                if((int)((Math.random()) * 2) == 0){
                    coinToss = home;
                    possession = home;
                    zone = 2;
                }
                else{
                    coinToss = away;
                    possession = away;
                    zone = 3;
                }
                script += "(0') Whistle blown, " + coinToss.getName() + " kicks off.\n";
                continue;
            }
            if(i == 45){
                script += "(45') Halftime.\n";
                possession = null;
                continue;
            }
            if(i == 46) {
                Team starting = home;
                if(coinToss == home){
                    starting = away;
                    zone = 3;
                }
                else{
                    zone = 2;
                }
                script += "(46') " + starting.getName() + " starts off the second half.\n";
                possession = starting;
                continue;
            }
            int newEvent = (int)(Math.random() * ratingsSum);
            boolean up = true;
            if(newEvent <= home.getTeamRating()){
                zone++;
            }
            else{
                zone--;
                up = false;
            }
            if(zone == 1){
                if(possession == away) {
                    if ((int) (Math.random() * 4) == 3) {
                        awayPoints++;
                        script += "(" + String.valueOf(i) + "') GOAL!!! " + away.getPlayers().get((int) (Math.random() * away.getPlayers().size())) + "scores a " + goalAdj[(int) (Math.random() * goalAdj.length)] + goalType[(int) (Math.random() * goalType.length)] + goalSpeed[(int) (Math.random() * goalSpeed.length)] + "\n";
                        zone = 2;
                        script += "(" + String.valueOf(i + 1) + "') " + away.getName() + " starts off with the ball.\n";
                        possession = home;
                        i++;
                        continue;
                    }
                }
                else{
                    script += "(" + String.valueOf(i) + "') " + away.getPlayers().get((int) (Math.random() * away.getPlayers().size())) + "passes it back to " + away.getGoalie() + " who kicks the ball up.";
                    zone += 2;
                }
            }
            else if(zone == 5){
                if(possession == home) {
                    if ((int) (Math.random() * 4) == 3) { //todo: choose random player and make probability player based
                        homePoints++;
                        script += "(" + String.valueOf(i) + "') GOAL!!! " + home.getPlayers().get((int) (Math.random() * home.getPlayers().size())) + "scores a " + goalAdj[(int) (Math.random() * goalAdj.length)] + goalType[(int) (Math.random() * goalType.length)] + goalSpeed[(int) (Math.random() * goalSpeed.length)] + "\n";
                        zone = 3;
                        script += "(" + String.valueOf(i + 1) + "') " + home.getName() + " starts off with the ball.";
                        possession = away;
                        i++;
                        continue;
                    }
                }
                else{
                    script += "(" + String.valueOf(i) + "') " + home.getPlayers().get((int) (Math.random() * home.getPlayers().size())) + "passes it back to " + home.getGoalie() + " who kicks the ball up.";
                    zone += 2;
                }
            }
            else {
                if(up){
                    if(possession == away){
                        if((int)((Math.random()) * 2) == 0){
                            int x;
                        }
                    }
                }
            }
        }
        script += "(90') Game over.\n\n" + String.valueOf(home) + String.valueOf(homePoints) + "-" + String.valueOf(awayPoints) + " " + String.valueOf(away);
    }

    @Override
    public String toString() {
        return script;
    }
}

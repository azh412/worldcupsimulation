import java.util.ArrayList;

public class Game {
    private Team home;
    private Team away;
    private String script = "";
    private int homePoints;
    private int awayPoints;
    private Team coinToss;
    private ArrayList<String> keyEvents = new ArrayList<String>();
    public ArrayList<Game> TournamentGames = new ArrayList<Game>();

    public Game(Team homex, Team awayx) {
        home = away = null;
        home = homex;
        away = awayx;
      //  AddGameToList(this);
        generateScript();
    }
    public void generateScript() {
        int ratingsSum = home.getTeamRating() + away.getTeamRating();
        Team possession = null;
        int zone = 0;
        String[] goalAdj = {"wonderful ", "beautiful ", "incredible ", "jaw-dropping "};
        String[] goalType = {"top-corner ", "bottom-corner "};
        String[] goalSpeed = {"rocket! ", "power shot! ", "curved ball! ", "attempted cross! ", "knuckle-ball! ", "tap-in! "};
        for (int i = 0; i <= 90; i++) {
            if (i == 0) {
                if ((int) ((Math.random()) * 2) == 0) {
                    coinToss = home;
                    possession = home;
                    zone = 2;
                } else {
                    coinToss = away;
                    possession = away;
                    zone = 3;
                }
                script += "(0') Whistle blown, " + coinToss.getName() + " kicks off.\n";
                keyEvents.add("(0') Whistle blown, " + coinToss.getName() + " kicks off.\n");
                continue;
            }
            if (i == 45) {
                script += "(45') Halftime.\n";
                keyEvents.add("(45') Halftime.\n");
                possession = null;
                continue;
            }
            if (i == 46) {
                Team starting = home;
                if (coinToss == home) {
                    starting = away;
                    zone = 3;
                } else {
                    zone = 2;
                }
                script += "(46') " + starting.getName() + " starts off the second half.\n";
                possession = starting;
                continue;
            }
            int newEvent = (int) (Math.random() * ratingsSum);
            boolean up = true;
            if (newEvent <= home.getTeamRating()) {
                zone++;
            } else {
                zone--;
                up = false;
            }
            if (zone == 1) {
                if (possession == away) {
                    Player shooter = away.getPlayers().get((int) (Math.random() * away.getPlayers().size()));
                    if ((int) (Math.random() * 56) >= shooter.getRating()) {
                        awayPoints++;
                        script += "(" + String.valueOf(i) + "') GOAL!!! " + shooter + "scores a " + goalAdj[(int) (Math.random() * goalAdj.length)] + goalType[(int) (Math.random() * goalType.length)] + goalSpeed[(int) (Math.random() * goalSpeed.length)] + "\n";
                        zone = 2;
                        keyEvents.add("(" + String.valueOf(i) + "') GOAL!!! " + shooter + "scores a " + goalAdj[(int) (Math.random() * goalAdj.length)] + goalType[(int) (Math.random() * goalType.length)] + goalSpeed[(int) (Math.random() * goalSpeed.length)] + "\n");
                        script += "(" + String.valueOf(i + 1) + "') " + away.getName() + " starts off with the ball.\n";
                        possession = home;
                        i++;
                        continue;
                    } else {
                        String[] missedLines = {"gets his shot rejected by ", "'s miss wows the crowd. Ball is kicked up by ", "ALMOST MAKES IT! INCREDIBLE SAVE BY ", "shoots for the stars. Ball kicked up by ", "'s shot picked up by ", "'s dribble stopped by "};
                        possession = home;
                        zone += 2;
                        script += "(" + String.valueOf(i) + "') " + shooter + missedLines[(int) (Math.random() * missedLines.length)] + home.getGoalie() + "\n";
                        keyEvents.add("(" + String.valueOf(i) + "') " + shooter + missedLines[(int) (Math.random() * missedLines.length)] + home.getGoalie() + "\n");
                    }
                } else {
                    script += "(" + String.valueOf(i) + "') " + home.getPlayers().get((int) (Math.random() * home.getPlayers().size())) + "passes it back to " + home.getGoalie() + "who kicks the ball up.\n";
                    zone += 2;
                }
            } else if (zone == 5) {
                if (possession == home) {
                    Player shooter = home.getPlayers().get((int) (Math.random() * home.getPlayers().size()));
                    if ((int) (Math.random() * 55.5) >= shooter.getRating()) {
                        homePoints++;
                        script += "(" + String.valueOf(i) + "') GOAL!!! " + shooter + "scores a " + goalAdj[(int) (Math.random() * goalAdj.length)] + goalType[(int) (Math.random() * goalType.length)] + goalSpeed[(int) (Math.random() * goalSpeed.length)] + "\n";
                        keyEvents.add("(" + String.valueOf(i) + "') GOAL!!! " + shooter + "scores a " + goalAdj[(int) (Math.random() * goalAdj.length)] + goalType[(int) (Math.random() * goalType.length)] + goalSpeed[(int) (Math.random() * goalSpeed.length)] + "\n");
                        zone = 3;
                        script += "(" + String.valueOf(i + 1) + "') " + home.getName() + " starts off with the ball.\n";
                        possession = away;
                        i++;
                        continue;
                    } else {
                        String[] missedLines = {"gets his shot rejected by ", "'s miss wows the crowd. Ball is kicked up by ", "ALMOST MAKES IT! INCREDIBLE SAVE BY ", "shoots for the stars. Ball kicked up by ", "'s shot picked up by ", "'s dribble stopped by "};
                        possession = away;
                        zone += 2;
                        script += "(" + String.valueOf(i) + "') " + shooter + missedLines[(int) (Math.random() * missedLines.length)] + away.getGoalie() + "\n";
                        keyEvents.add("(" + String.valueOf(i) + "') " + shooter + missedLines[(int) (Math.random() * missedLines.length)] + away.getGoalie() + "\n");
                    }
                } else {
                    script += "(" + String.valueOf(i) + "') " + away.getPlayers().get((int) (Math.random() * away.getPlayers().size())) + "passes it back to " + away.getGoalie() + "who kicks the ball up.\n";
                    zone += 2;
                }
            } else {
                if (up) {
                    if (possession == away) {
                        //todo: bad team, bad chances, but 75% chance of retaining ball, if not retained then switch possession with a message with tackle or interception
                        script += "(" + String.valueOf(i) + "') " + away.getPlayers().get((int) (Math.random() * away.getPlayers().size())) + "passes it back to " + away.getPlayers().get((int) (Math.random() * away.getPlayers().size())) + "\n";
                    }
                    //todo: pushing up
                } else {
                    if (possession == home) {
                        script += "(" + String.valueOf(i) + "') " + home.getPlayers().get((int) (Math.random() * home.getPlayers().size())) + "passes it back to " + home.getPlayers().get((int) (Math.random() * home.getPlayers().size())) + "\n";
                    }
                }
            }
        }
        script += "(90') Game over.\n\n" + String.valueOf(home) + String.valueOf(homePoints) + "-" + String.valueOf(awayPoints) + " " + String.valueOf(away);
        keyEvents.add("(90') Game over.\n\n" + String.valueOf(home) + String.valueOf(homePoints) + "-" + String.valueOf(awayPoints) + " " + String.valueOf(away));
    }

    public void readScript() {
        try {
            for (String line : script.split("\n")) {
                System.out.println(line);
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void simulateScript() {
        for (String line : keyEvents) {
            System.out.print(line);
        }
    }
    public Team getHome(){
        return home;
    }

    public Team getAway() {
        return away;
    }

    public Team getWinner() {
        if (homePoints > awayPoints) {
            return home;
        }
        return away;
    }

    @Override
    public String toString() {
        return home.toString() + "plays " + away.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Game otherGame = (Game) obj; // Cast the object to Team
        if(this.home.toString().equals(otherGame.toString())){
            return true;
        }
        return false;
    }
}
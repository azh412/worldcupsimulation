import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Tournament {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Team> teams = new ArrayList<Team>();
        new Game(new Team("USA"), new Team("Canada")).readScript();
    }
}
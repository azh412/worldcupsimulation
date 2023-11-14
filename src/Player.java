public class Player {
    private final int rating;
    private final String name;
    private final boolean isGK;
    private final Team team;
    public Player(Team team){
        this.team = team;
        String vowels = "aeiou";
        String consonants = "bcdfghjklmnprst";
        String n = "";
        this.rating = (int)(Math.random() * (37));
        for(int i = 0; i < 2; i++){
            int a = (int)(Math.random() * consonants.length() - 1);
            int b = (int)(Math.random() * vowels.length() - 1);
            n += consonants.substring(a, a+1);
            n += vowels.substring(b, b+1);
        }
        int a = (int)(Math.random() * consonants.length() - 1);
        n += consonants.substring(a, a+1);
        this.name = n.substring(0,1).toUpperCase() + n.substring(1);
        isGK = false;
    }
    public Player(Team team, boolean isGK){
        this.team = team;
        String vowels = "aeiou";
        String consonants = "bcdfghjklmnprst";
        String n = "";
        this.rating = (int)(Math.random() * (37));
        for(int i = 0; i < 2; i++){
            int a = (int)(Math.random() * consonants.length() - 1);
            int b = (int)(Math.random() * vowels.length() - 1);
            n += consonants.substring(a, a+1);
            n += vowels.substring(b, b+1);
        }
        int a = (int)(Math.random() * consonants.length() - 1);
        n += consonants.substring(a, a+1);
        this.name = n.substring(0,1).toUpperCase() + n.substring(1);
        this.isGK = isGK;
    }
    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if(!isGK) {
            return name + " (" + team.getAbrv() + ") " + "(" + String.valueOf(rating) + ") ";
        }
        else {
            return name + " (" + team.getAbrv() + ") " + "(" + String.valueOf(rating) + ") (GK) ";
        }
    }
    public boolean equals(Player obj) {
        return name.equals(obj.name);
    }
}

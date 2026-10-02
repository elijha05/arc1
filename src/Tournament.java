import java.util.ArrayList;


public class Tournament {
    private ArrayList<Team> Teams;
    private ArrayList<Team> currentBracket = new ArrayList<>();

    public Tournament(ArrayList<Team> teams){
        this.Teams = teams;
    }

    public ArrayList<Integer> CreateBracket() {
        int size = Teams.size();

        int nextPower = 1;
        while (nextPower < size) {
            nextPower *= 2;
        }

        ArrayList<Integer> seeds = new ArrayList<>();
        seeds.add(1);

        while (seeds.size() < nextPower) {
            int nextMax = seeds.size() * 2 + 1;
            ArrayList<Integer> newOrder = new ArrayList<>();

            for (int seed : seeds) {
                newOrder.add(seed);
                newOrder.add(nextMax - seed);
            }

            seeds = newOrder;
        }

        League playoff = new League("playoff");
        playoff.AddTeams(this.Teams);
        ArrayList<Team> sorted = playoff.SortRankings();

        // Make room for all bracket slots
        currentBracket.clear();

        for (int i = 0; i < nextPower; i++) {
            currentBracket.add(null);
        }

        for (int i = 0; i < seeds.size(); i++) {
            int seed = seeds.get(i);

            if (seed <= sorted.size()) {
                currentBracket.set(i, sorted.get(seed - 1));
            }
            // Otherwise this is a bye
        }

        return seeds;
    }

    public String toString(){
        StringBuilder con = new StringBuilder();
        for(Team team: currentBracket){
            if(currentBracket.indexOf(team) % 2 == 0){
                con.append("\n");
            } else {
                con.append(" vs ");
            }
            if (team == null){
                con.append("BYE");
            } else {
                con.append(" ").append(team.GetName());
            }

        }
        return "Current bracket: " + con;
    }

}

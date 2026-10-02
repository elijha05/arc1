import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class League {
    private String name;
    ArrayList<Team> league = new ArrayList<>();

    public League(String name){
        this.name = name;
    }

    public void AddTeam(Team team){
        league.add(team);
    }

    public void AddTeams(ArrayList<Team> teams){
        league.addAll(teams);
    }

    public int GetSize(){
        return league.size();
    }

    public String GetName(){
        return this.name;
    }

    public ArrayList<Team> SortRankings(){
        ArrayList<Team> sortedTeams = new ArrayList<>(league);
        sortedTeams.sort(Comparator.comparingDouble(Team::GetWinningPercentage).reversed());
        return sortedTeams;
    }

    public Game CreateRandomGame(){
        Random rnd = new Random();

        int team1Index = rnd.nextInt(league.size());
        int team2Index = rnd.nextInt(league.size() - 1);

        if (team2Index >= team1Index) {
            team2Index++;
        }

        Team team1 = league.get(team1Index);
        Team team2 = league.get(team2Index);

        return new Game(team1, team2);
    };

    public Team FindTeam(String team){
        Team target = null;

        for(Team v: league){
            if (Objects.equals(v.GetName(), team)){
                target = v;
            }
        }

        return target;
    };

    public ArrayList<Team> GetWinningTeams(){
        ArrayList<Team> winningTeams =  new ArrayList<>();

        for(Team v: league){
            if(v.GetWinningPercentage() > 0.5){
                winningTeams.add(winningTeams.size()+1, v);
            }
        }

        return winningTeams;
    }

    public ArrayList<Team> GetTeams(){
        return league;
    }

    public int GetAverageRunsScored(){
        int total = 0;

        for(Team v: league){
            total += v.GetRunsScored();
        }

        return  total/league.size();
    }

    public int GetRunsAllowed(){
        int total = 0;

        for(Team v: league){
            total += v.GetRunsAllowed();
        }

        return  total/league.size();
    }

}

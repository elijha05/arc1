public class Team {
    private String name;
    private String location;
    private int wins;
    private int losses;
    private int offensiveRating;
    private int pitchingRating;
    private int runsScored;
    private int runsAllowed;


    public Team(String name, String location, int wins, int losses, int offensiveRating, int pitchingRating, int runsScored, int runsAllowed){
        this.name = name;
        this.location = location;
        this.wins = wins;
        this.losses = losses;
        this.offensiveRating = offensiveRating;
        this.pitchingRating = pitchingRating;
    }

    public void IncrementWin() {
        this.wins += 1;
    };
    public void IncrementLoss() {
        this.losses += 1;
    };

    public int CalculateOffenseRating(int runsScored, int runsScoredAverage) {

       int oldRating = this.offensiveRating;
       int avg = (runsScored/runsScoredAverage);
       int newRating = ((avg*50)+oldRating)/2;

       this.offensiveRating = newRating;

       return newRating;
    };

    public int CalculatePitchingRating(int runsAllowed, int runsAllowedAverage) {

        int oldRating = this.pitchingRating;
        int avg = (runsAllowed/runsAllowedAverage);
        int newRating = ((avg*50)+oldRating)/2;

        this.pitchingRating = newRating;

        return newRating;
    }

    public int GetOverall(){
        return (offensiveRating+pitchingRating)/2;
    }

    public float GetWinningPercentage(){
        return (float) wins /(wins+losses);
    }

    public int GetWins() {
        return this.wins;
    };

    public int GetLosses() {
        return this.losses;
    }

    public int GetRunsScored(){return  this.runsScored;}

    public int GetRunsAllowed(){return  this.runsAllowed;}

    public  String GetName(){return name;};

    public String toString(){
        return location + " " + name + "| Record: " + wins + "-" + losses + "| Offense Rating: " + offensiveRating + "| Pitching Rating: " + pitchingRating;
    }
}

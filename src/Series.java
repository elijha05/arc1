public class Series {
    private Team team1;
    private Team team2;
    private int maxGames;
    private int gamesPlayed;

    private int team1Wins;
    private int team2Wins;

    public Series(Team team1, Team team2, int maxGames){
        this.team1 = team1;
        this.team2 = team2;
        this.maxGames = maxGames;
    }

    public int GetWinningNumber(){
        return (maxGames/2)+1;
    }

    public void SimulateGame(){
        if (gamesPlayed < maxGames && team1Wins < GetWinningNumber() || team2Wins < GetWinningNumber()){
            Game newGame = new Game(team1, team2);
            Team winner = newGame.CalculateWinner();

            if (winner==team1){
                team1Wins += 1;
            } else {
                team2Wins += 1;
            }
        }
    }

    public void SimulateSeries(){
        while(team1Wins < GetWinningNumber() && team2Wins < GetWinningNumber()){
            SimulateGame();
        }
    }


    public String  GetLeaderString(){
        if(team1Wins>team2Wins){
            return team1.GetName()+" is leading the series ";
        } else if (team1Wins<team2Wins) {
            return team2.GetName()+" is leading the series ";
        }
        return "Series is tied ";
    }

    public String toString(){
        return team1.GetName() + " vs " + team2.GetName()+"\n"+GetLeaderString()+team1Wins+"-"+team2Wins+"\n"+"Best of "+maxGames;
    }

}

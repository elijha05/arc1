import java.util.Random;

public class Game {
    private Team team1;
    private Team team2;
    int team1Overall;
    int team2Overall;
    float team1WP;
    float team2WP;

    private Team winner;
    private int team1Score;
    private int team2Score;
    
    public Game(Team team1, Team team2){
       this.team1 = team1;
       this.team2 = team2;

       this.team1Overall = team1.GetOverall();
       this.team2Overall = team2.GetOverall();
   //    this.team1WP = team1.GetWinningPercentage()*100;
     //  this.team2WP = team2.GetWinningPercentage()*100;
    }

    public float CalculateOdds(Team team){
       return team.GetOverall();
    };

    public Team CalculateWinner(){

        float totalOdds = team1Overall + team2Overall;
        float team1Odds = CalculateOdds(team1);

       Random random = new Random();
       int roll = random.nextInt((int) totalOdds);

       if (roll < (int)team1Odds) {
            winner = team1;

       } else {
           winner = team2;
       };

       team1Score = random.nextInt(15);
       if (team1Score == 0){
           team1Score = 1;
       }
       team2Score = random.nextInt(team1Score);

       if (team2Score == team1Score) {
           team2Score -= 1;
       }

       return winner;
    }

    public String PrintResults(){
        if(winner!=null){
           return winner.GetName()+" Wins! Score:"+team1Score+"-"+team2Score;
        }
        return "Game has not started";
    };

    public String toString(){
        float total = CalculateOdds(team1)+CalculateOdds(team2);
        return "Game Information: " + team1.toString() + "| Win Odds: " + CalculateOdds(team1)/total + "\n" + team2.toString() + "| Win Odds: " + CalculateOdds(team2)/total;
    }

}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
private ArrayList<League> currentLeagues = new ArrayList<>();
void main() {
// added sorting
    PRESET();

   Scanner scanner = new Scanner(System.in);
   boolean running = true;

   while (running){
       System.out.println("Welcome to Baseball Sim! Pick an Option:");
       System.out.println("1). Add Team");
       System.out.println("2). Create Game");
       System.out.println("3). Create League");
       System.out.println("4). Create Bracket");
       int choice = scanner.nextInt();
       scanner.nextLine();
       if(choice==1){
           CreateTeamPrompt(scanner);
       } else if (choice==2) {
           CreateGamePrompt(scanner);
       } else if (choice==3){
           CreateLeaguePrompt(scanner);
       } else if (choice==4){
           CreateBracketPrompt(scanner);
       }
   };



}

private void CreateTeamPrompt(Scanner scanner){
    System.out.println("Type Your Team Name");
    String teamName = scanner.nextLine();
    System.out.println("Type Your Team Location");
    String teamLocation = scanner.nextLine();
    System.out.println("Offensive Rating: ");
    int oRating = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Pitching Rating: ");
    int pRating = scanner.nextInt();
    scanner.nextLine();


    Team newTeam = new Team(teamName, teamLocation, 0, 0, oRating, pRating, 0,0);

    League selectedLeague = SelectLeaguePrompt(scanner);
    selectedLeague.AddTeam(newTeam);

    System.out.println("Added " + teamName + " to " + selectedLeague.GetName());
}

private League SelectLeaguePrompt(Scanner scanner){
    System.out.println("Select A League: ");
    StringBuilder leagueList = new StringBuilder();
    for(int i = 0; i < currentLeagues.size(); i++){
        System.out.println(
                (i + 1) + "). " + currentLeagues.get(i).GetName()
        );
    }
    boolean run = true;

    while (run==true){

        System.out.println(leagueList + "0). Create New League");
        int choice = scanner.nextInt();


        if(choice <= -1 || choice > 9){
            System.out.println("ERROR: Invalid Input");
        } else {
            run = false;
            if (choice == 0) {
                return CreateLeaguePrompt(scanner);
            }

            return currentLeagues.get(choice-1);
        }
    }
    return null;
}

private League CreateLeaguePrompt(Scanner scanner) {
    if(currentLeagues.size() < 9){
        System.out.println("Type Your League Name");
        String leagueName = scanner.nextLine();

        League newLeague = new League(leagueName);
        currentLeagues.add(newLeague);
        System.out.println("League Created: "+leagueName);
        return newLeague;
    }

    return null;
}

private void CreateGamePrompt(Scanner scanner) {
    League selectedLeague = SelectLeaguePrompt(scanner);

    System.out.println("Selected League: '"+ selectedLeague.GetName() + "' Pick an Option");
    System.out.println("1). Random Game ");
    System.out.println("2). Custom Game");
    int choice = scanner.nextInt();
    scanner.nextLine();
    if (selectedLeague.GetSize() >=2){
        if(choice==1){
            Game game = selectedLeague.CreateRandomGame();
            System.out.println(game.toString());
            System.out.println("Simulate Game?");
            System.out.println("1). Yes");
            System.out.println("2). No");

            int choice2 = scanner.nextInt();
            scanner.nextLine();
            if(choice2==1){
                game.CalculateWinner();
                System.out.println(game.PrintResults());
                System.out.println("Press Enter to Return to Menu");
                scanner.nextLine();
            }
        } else if (choice==2){
            Team team1;
            Team team2;
            boolean run = true;

            while (run) {
                System.out.println("Type Team Name 1");
                String team1Name = scanner.nextLine();


                team1 = selectedLeague.FindTeam(team1Name);

                if (team1 == null) {
                    System.out.println("Error: Could not find team");
                    continue;
                }

                System.out.println("Type Team Name 2");
                String team2Name = scanner.nextLine();

                team2 = selectedLeague.FindTeam(team2Name);

                if (team2 == null) {
                    System.out.println("Error: Could not find team");
                    continue;
                }

                if (team1 == team2) {
                    System.out.println("Error: You cannot select the same team twice.");
                    continue;
                }

                Game newGame = new Game(team1, team2);

                System.out.println(newGame);

                System.out.println("Simulate Game?");
                System.out.println("1). Yes");
                System.out.println("2). No");

                int choice2 = scanner.nextInt();
                scanner.nextLine();

                if (choice2 == 1) {
                    newGame.CalculateWinner();
                    System.out.println(newGame.PrintResults());

                    System.out.println("Press Enter to Return to Menu");
                    scanner.nextLine();
                }

                run = false;
            }
        }
    } else {
        System.out.println("Error: Need At Least 2 Teams In Your League");
    }

}

private void CreateBracketPrompt(Scanner scanner){
    League selectedLeague = SelectLeaguePrompt(scanner);
    assert selectedLeague != null;

    if (selectedLeague.GetSize() >=2){
        Tournament tournament = new Tournament(selectedLeague.GetTeams());
        tournament.CreateBracket();
        System.out.println(tournament.toString());
    }
}

private void PRESET(){
    Team LAA = new Team("Angels", "Los Angeles", 62, 100, 74, 70,80,80);
    Team TEX = new Team("Rangers", "Texas", 80, 82, 80, 78,80,80);
    Team HOU = new Team("Astros", "Houston", 81, 81, 83, 72,80,80);
    Team SEA = new Team("Mariners", "Seattle", 76, 86, 82, 83,80,80);
    Team LV = new Team("Athletics", "Las Vegas", 64, 98, 74, 74,80,80);
    Team KC = new Team("Royals", "Kansas City", 69, 93, 79, 73,80,80);
    Team CWS = new Team("White Sox", "Chicago", 84, 78, 79, 72,80,80);
    Team CLE = new Team("Guardians", "Cleveland", 85, 77, 82, 79,80,80);
    Team MIN = new Team("Twins", "Minnesota", 77, 85, 78, 74,80,80);
    Team DET = new Team("Tigers", "Detroit", 76, 86, 78, 74,80,80);
    Team NYY = new Team("Yankees", "New York", 93, 68, 82, 83,80,80);
    Team BOS = new Team("Red Sox", "Boston", 87, 75, 82, 78,80,80);
    Team TOR = new Team("Blue Jays", "Toronto", 79, 83, 82, 79,80,80);
    Team BAL = new Team("Orioles", "Baltimore", 79, 82, 79, 76,80,80);
    Team TB = new Team("Rays", "Tampa Bay", 98, 64, 82, 78,80,80);
    Team LAD = new Team("Dodgers", "Los Angeles", 100, 62, 85, 90,80,80);
    Team SD = new Team("Padres", "San Diego", 91, 71, 82, 80,80,80);
    Team ARZ = new Team("Diamondbacks", "Arizona", 86, 76, 79, 77,80,80);
    Team SF = new Team("Giants", "San Francisco", 65, 97, 78, 74,80,80);
    Team COL = new Team("Rockies", "Colorado", 58, 104, 76, 68,80,80);
    Team MIL = new Team("Brewers", "Milwaukee", 103, 59, 83, 79,80,80);
    Team CHC = new Team("Cubs", "Chicago", 89, 73, 83, 79,80,80);
    Team CIN = new Team("Reds", "Cincinnati", 75, 87, 80, 78,80,80);
    Team STL = new Team("Cardinals", "St. Louis", 77, 85, 79, 74,80,80);
    Team PIT = new Team("Pirates", "Pittsburgh", 82, 80, 82, 81,80,80);
    Team NYM = new Team("Mets", "New York", 74, 88, 79, 74,80,80);
    Team ATL = new Team("Braves", "Atlanta", 94, 68, 85, 79,80,80);
    Team PHI = new Team("Phillies", "Philadelphia", 88, 74, 86, 82,80,80);
    Team WSH = new Team("Nationals", "Washington D.C", 77, 85, 76, 71,80,80);
    Team MIA = new Team("Marlins", "Miami", 80, 82, 80, 77,80,80);


    League MLB = new League("PRESET MLB 2026");
    MLB.AddTeams(new ArrayList<>(List.of(LAA, HOU, TEX, SEA, LV, KC, CWS, MIN, DET, CLE, NYY, BOS, TOR, BAL, TB, LAD, SD, ARZ, COL, SF, CHC, MIL, CIN, PIT, STL, MIA, PHI, ATL, NYM, WSH)));
    currentLeagues.add(MLB);
}
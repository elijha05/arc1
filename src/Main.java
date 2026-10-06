//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
private ArrayList<League> currentLeagues = new ArrayList<>();
void main() {
// hello
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
    Team TEX = new Team("Rangers", "Texas", 24, 29, 80, 78,80,80);
    Team HOU = new Team("Astros", "Houston", 29, 24, 83, 72,80,80);
    Team SEA = new Team("Mariners", "Seattle", 34, 19, 82, 83,80,80);
    Team LV = new Team("Athletics", "Las Vegas", 30, 23, 74, 74,80,80);
    Team KC = new Team("Royals", "Kansas City", 27, 26, 79, 73,80,80);
    Team CWS = new Team("White Sox", "Chicago", 29, 24, 79, 72,80,80);
    Team CLE = new Team("Guardians", "Cleveland", 35, 18, 82, 79,80,80);
    Team MIN = new Team("Twins", "Minnesota", 35, 18, 78, 74,80,80);
    Team DET = new Team("Tigers", "Detroit", 35, 18, 78, 74,80,80);
    Team NYY = new Team("Yankees", "New York", 35, 18, 82, 83,80,80);
    Team BOS = new Team("Red Sox", "Boston", 35, 18, 82, 78,80,80);
    Team TOR = new Team("Blue Jays", "Toronto", 35, 18, 82, 79,80,80);
    Team BAL = new Team("Orioles", "Baltimore", 35, 18, 79, 76,80,80);
    Team TB = new Team("Rays", "Tampa Bay", 35, 18, 82, 78,80,80);
    Team LAD = new Team("Dodgers", "Los Angeles", 33, 20, 85, 90,80,80);
    Team SD = new Team("Padres", "San Diego", 24, 29, 82, 80,80,80);
    Team ARZ = new Team("Diamondbacks", "Arizona", 29, 24, 79, 77,80,80);
    Team SF = new Team("Giants", "San Francisco", 34, 19, 78, 74,80,80);
    Team COL = new Team("Rockies", "Colorado", 30, 23, 76, 68,80,80);
    Team MIL = new Team("Brewers", "Milwaukee", 27, 26, 83, 79,80,80);
    Team CHC = new Team("Cubs", "Chicago", 29, 24, 83, 79,80,80);
    Team CIN = new Team("Reds", "Cincinnati", 35, 18, 80, 78,80,80);
    Team STL = new Team("Cardinals", "St. Louis", 35, 18, 79, 74,80,80);
    Team PIT = new Team("Pirates", "Pittsburgh", 35, 18, 82, 81,80,80);
    Team NYM = new Team("Mets", "New York", 35, 18, 79, 74,80,80);
    Team ATL = new Team("Braves", "Atlanta", 35, 18, 85, 79,80,80);
    Team PHI = new Team("Phillies", "Philadelphia", 35, 18, 86, 82,80,80);
    Team WSH = new Team("Nationals", "Washington D.C", 35, 18, 76, 71,80,80);
    Team MIA = new Team("Marlins", "Miami", 35, 18, 80, 77,80,80);


    League MLB = new League("PRESET MLB 2026");
    MLB.AddTeams(new ArrayList<>(List.of(LAA, HOU, TEX, SEA, LV, KC, CWS, MIN, DET, CLE, NYY, BOS, TOR, BAL, TB, LAD, SD, ARZ, COL, SF, CHC, MIL, CIN, PIT, STL, MIA, PHI, ATL, NYM, WSH)));
    currentLeagues.add(MLB);
}
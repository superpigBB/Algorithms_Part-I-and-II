import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class BaseballElimination{
	private int number_team;
	private String teams[];
	private int wins[];
	private int losses[];
	private int remains[];
	private int game[][];
	private int otherTeamGames;
	private  HashMap<String, Integer> HM;
	
	public BaseballElimination(String filename) throws IOException{
		FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String myreadline;
        
        myreadline = br.readLine();
        number_team=Integer.parseInt(myreadline);
        teams=new String[number_team];
        wins=new int[number_team];
        losses=new int[number_team];
        remains=new int[number_team];
        game=new int[number_team][number_team];
        HM = new HashMap<String, Integer>(); 
        
        int v=0;        
        while (br.ready()) {
            myreadline = br.readLine();
            String a[] = myreadline.split("\\s+"); 
            int b=0;  //for handle multi-file format.
            
            if(myreadline.charAt(0)==' ') b=1;  
            teams[v]=a[0+b];
            int wins_count=Integer.parseInt(a[1+b]);
            wins[v]=wins_count;
            int losses_count=Integer.parseInt(a[2+b]);
            losses[v]=losses_count;
            int remain_count=Integer.parseInt(a[3+b]);
            remains[v]=remain_count;
            
            for (int u = 0; u < number_team; u++)
                game[v][u] = Integer.parseInt(a[u+4+b]);
            
            HM.put(teams[v], v);
            v++;
        }
        
        br.close();
        fr.close();
		
	}
	
	public int numberOfTeams(){
		
		return number_team;
	}
	
	public Iterable<String> teams(){
		ArrayList<String> a=new ArrayList<String>();
		
		for(int i=0;i<number_team;i++)
		    a.add(teams[i]);
		
		return a;
	}
	
	public int wins(String team){
		if (!HM.containsKey(team))
            throw new IllegalArgumentException();
    	
		return wins[HM.get(team)];
	}
	
    public int losses(String team){
    	if (!HM.containsKey(team))
            throw new IllegalArgumentException();
		
		return losses[HM.get(team)];
	}
	
    public int remaining(String team){
    	if (!HM.containsKey(team))
            throw new IllegalArgumentException();
    	
		return remains[HM.get(team)];
	}
	
    public int against(String team1, String team2){
    	if (!HM.containsKey(team1)||!HM.containsKey(team2))
            throw new IllegalArgumentException();
    	
		
 		return game[HM.get(team1)][HM.get(team2)];
 	}
    
    
    public boolean isEliminated(String team){
    	if (!HM.containsKey(team))
            throw new IllegalArgumentException();
    	
    	//trivial elimination
    	int team_index=wins(team)+remaining(team);
    	for(String t:teams()){
    		if(team_index<wins(t))
    			return true;
    	}
    	
    	
        //non trivial -----maxflow problem 
    	/*
    	int s = number_team + number_team*number_team;
        int t = number_team + number_team*number_team + 1;
    	
    	FlowNetwork flowNetwork = new FlowNetwork(number_team*number_team + number_team  + 2);

    	*/
    	
    	FordFulkerson fordFulkerson = generateFordFulkersonForTeam(HM.get(team));
        //System.out.println("************************"+fordFulkerson.value());
        //System.out.println("========================"+otherTeamGames);
        return fordFulkerson.value() < otherTeamGames;
    	
    	//return false;
    	
    }
    
    
    
    private FordFulkerson generateFordFulkersonForTeam(int currentTeamIndex) {
        int VerticeCount = number_team * number_team;
        FlowNetwork flowNetwork = new FlowNetwork(VerticeCount + number_team + 2);

        int s = number_team + VerticeCount;
        int t = number_team + VerticeCount + 1;
        //System.out.println("*******"+s);
        //System.out.println("*******"+t);
        otherTeamGames = 0;

        for (int firstTeamIndex = 0; firstTeamIndex < number_team - 1; firstTeamIndex++) {
            for (int secondTeamIndex = firstTeamIndex + 1; secondTeamIndex < number_team; secondTeamIndex++) {
                if (firstTeamIndex != currentTeamIndex || secondTeamIndex != currentTeamIndex){
                otherTeamGames += game[firstTeamIndex][secondTeamIndex];
                //System.out.println("kkkkkkkkkkkkkkkkkkkkkk " +firstTeamIndex +" kkkkkkkkkkkkkkkkkkkkkk " + secondTeamIndex +" kkkkkkkkkkkkkkkkkkkkkk " + game[firstTeamIndex][secondTeamIndex]);
                flowNetwork.addEdge(new FlowEdge(s, findIndexforTwoTeam(firstTeamIndex,secondTeamIndex), game[firstTeamIndex][secondTeamIndex]));
                flowNetwork.addEdge(new FlowEdge(findIndexforTwoTeam(firstTeamIndex,secondTeamIndex), getTeamVerticeIndex(firstTeamIndex), Double.POSITIVE_INFINITY));
                flowNetwork.addEdge(new FlowEdge(findIndexforTwoTeam(firstTeamIndex,secondTeamIndex), getTeamVerticeIndex(secondTeamIndex), Double.POSITIVE_INFINITY));
                }
            }
        }

        for (int teamIndex = 0; teamIndex < number_team; teamIndex++) {
            if (teamIndex != currentTeamIndex)
            flowNetwork.addEdge(new FlowEdge(getTeamVerticeIndex(teamIndex), t, Math.max(wins[currentTeamIndex] + remains[currentTeamIndex] - wins[teamIndex], 0)));
        }

        return new FordFulkerson(flowNetwork, s, t);
    }

    private int getTeamVerticeIndex(int teamIndex) {
        return number_team * number_team + teamIndex;
    }
    
    private int findIndexforTwoTeam(int firstTeamIndex, int secondTeamIndex) {
      if (firstTeamIndex > secondTeamIndex)
          return number_team * secondTeamIndex + firstTeamIndex;
      return number_team * firstTeamIndex + secondTeamIndex;
    }
    
    public Iterable<String> certificateOfElimination(String team){
    	if (!HM.containsKey(team))
            throw new IllegalArgumentException();
    	 Integer currentTeamIndex = (Integer) HM.get(team);

         Queue<String> result = new Queue<String>();

         int team_index=wins(team)+remaining(team);
     	   for(String t:teams()){
     		 if(team_index<wins(t)){
     			 int maxPossibleWin = wins[currentTeamIndex] + remains[currentTeamIndex];
                for (int teamIndex = 0; teamIndex < number_team; teamIndex++) {
                 if (wins[teamIndex] > maxPossibleWin && teamIndex != currentTeamIndex)
                     result.enqueue(teams[teamIndex]);
                 }
             return result;
     	    }
     	   }
         

         FordFulkerson fordFulkerson = generateFordFulkersonForTeam(currentTeamIndex);

         if (fordFulkerson.value() == otherTeamGames)
             return null;

         for (int teamIndex = 0; teamIndex < number_team; teamIndex++) {
             if (fordFulkerson.inCut(getTeamVerticeIndex(teamIndex))) {
                 result.enqueue(teams[teamIndex]);
             }
         }

         return result;
    }
    
	public static void main(String[] args) throws IOException { 
		 BaseballElimination division = new BaseballElimination(args[0]);
		    for (String team : division.teams()) {
		        if (division.isEliminated(team)) {
		        	StdOut.println(team +" is eliminated");
		            StdOut.print(team + " is eliminated by the subset R = { ");
		            for (String t : division.certificateOfElimination(team))
		                StdOut.print(t + " ");
		            StdOut.println("}");
		        }
		        else {
		            StdOut.println(team + " is not eliminated");
		        }
		    }

	}

}

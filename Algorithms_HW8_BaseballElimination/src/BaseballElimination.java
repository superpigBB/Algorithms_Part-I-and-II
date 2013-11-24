import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class BaseballElimination{
	private int number_team;
	private String team[];
	private int wins[];
	private int losses[];
	private int remains[];
	private int game[][];
	private  HashMap<String, Integer> HM;
	
	public BaseballElimination(String filename) throws IOException{
		FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String myreadline;
        
        
        myreadline = br.readLine();
        System.out.println(myreadline);
        number_team=Integer.parseInt(myreadline);
        team=new String[number_team];
        wins=new int[number_team];
        losses=new int[number_team];
        remains=new int[number_team];
        game=new int[number_team][number_team];
        HM = new HashMap<String, Integer>(); 
        
        int v=0;        
        while (br.ready()) {
            myreadline = br.readLine();
            String a[] = myreadline.split(" "); 
            team[v]=a[0];
            int wins_count=Integer.parseInt(a[1]);
            wins[v]=wins_count;
            int losses_count=Integer.parseInt(a[2]);
            losses[v]=losses_count;
            int remain_count=Integer.parseInt(a[3]);
            remains[v]=remain_count;
            
            for (int u = 0; u < number_team; u++)
                game[v][u] = Integer.parseInt(a[u+4]);
            
            
            HM.put(team[v], v);
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
		    a.add(team[i]);
		
		return a;
	}
	
	public int wins(String team){
		
		return wins[HM.get(team)];
	}
	
    public int losses(String team){
		
		return losses[HM.get(team)];
	}
	
    public int remaining(String team){
		
		return remains[HM.get(team)];
	}
	
    public int against(String team1, String team2){
		
 		return game[HM.get(team1)][HM.get(team2)];
 	}
    
    
    public boolean isEliminated(String team){
    	
    	return true;
    }
    
    public Iterable<String> certificateOfElimination(String team){
    	
    	return null;
    }
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BaseballElimination b=new  BaseballElimination("teams4.txt");
		 
		 System.out.println("number of team is :" + b.numberOfTeams());
		 System.out.println("all team is :" +b.teams());
		 System.out.println(" Philadelphia again NY is :"+b.against("Philadelphia", "Montreal"));
		 System.out.println(" Philadelphia wins is :"+ b.wins("Philadelphia"));
		 System.out.println(" Philadelphia loses is :" + b.losses("Philadelphia"));
		 System.out.println(" Philadelphia remain is :" + b.remaining("Philadelphia"));

	}

}

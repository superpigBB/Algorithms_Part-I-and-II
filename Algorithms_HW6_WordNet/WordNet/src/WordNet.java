import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//import java.util.Scanner;
//import java.util.Set;

public class WordNet {

	private Digraph g;
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) throws IOException{
	//	File s= new File("synnet");
	//	File h= new File("hypernyms");
		FileReader fr = new FileReader("synsets.txt");
        BufferedReader br = new BufferedReader(fr);
        String myreadline=null;
        HashMap<Integer , String> HM = new HashMap<Integer , String>();  
        int v=0; //vertices number
        while (br.ready()) {
            myreadline = br.readLine();
            String a[] = myreadline.split(","); 
            int a0=Integer.parseInt(a[0]);
            HM.put(a0, a[1]);
            v++;
        }
        
        br.close();
        fr.close();
		
        g=new Digraph(v);
        
        fr = new FileReader("hypernyms.txt");
        BufferedReader br1 = new BufferedReader(fr);
        while (br1.ready()) {
            myreadline = br1.readLine();
            String a[] = myreadline.split(","); 
            int i=a.length;
            int a_num[]=new int[i];
            
            for(int k=0;k<a.length;k++){
            a_num[k]=Integer.parseInt(a[k]);                   
            }
            //System.out.print(i);
            for(int j=1;j<a.length;j++){
            g.addEdge(a_num[0], a_num[j]);
           
            }
          
       
        
        }
        
        br1.close();
        fr.close(); 
 
        System.out.println(g.V());
        System.out.println(g.E());
        
        //test node number
        //System.out.print(v);
        //test HM
        /*
        for(Object key : HM.keySet()){
            String v = (String) HM.get(key);
            System.out.println("key="+key +" value="+v);
        }
        */
	}

	// the set of nouns (no duplicates), returned as an Iterable
	public Iterable<String> nouns(){
		return null;
		
	}
	
	// is the word a WordNet noun?
	public boolean isNoun(String word){
		return false;
		
	}
	
	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB){
		return 0;
		
	}
	
	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB){
		return null;
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WordNet wn=new WordNet("synsets.txt","h.txt");
		
		
		
         //g=new Digraph(50);
         //System.out.println(g.E());
         //g.addEdge(2, 3);
         //g.addEdge(3, 2);
         //System.out.println(g.E());
	}

}

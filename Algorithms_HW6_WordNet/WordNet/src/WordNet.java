import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//import java.util.Scanner;
//import java.util.Set;

public class WordNet {

	private Digraph g;
	private SAP s;
	private  HashMap<String, Integer> HM;
	private LinkedList<String> N;
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) throws IOException{
		FileReader fr = new FileReader(synsets);
        BufferedReader br = new BufferedReader(fr);
        String myreadline=null;
        HM = new HashMap<String, Integer>(); 
        N=new LinkedList<String>();
        int v=0; //vertices number
       
        while (br.ready()) {
            myreadline = br.readLine();
            String a[] = myreadline.split(","); 
            int a0=Integer.parseInt(a[0]);
            
            for (String noun : a[1].split(" ")) {                
            	N.add(noun);                       
                HM.put(noun, a0);
            }
            v++;
        }
        
        br.close();
        fr.close();
		
        g=new Digraph(v);
        
        fr = new FileReader(hypernyms);
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
            for(int j=0;j<a.length;j++){
            g.addEdge(a_num[0], a_num[j]);
           
            }
          
        
        }
        
        br1.close();
        fr.close(); 
        
        s=new SAP(this.g);
        
      ///test  information				
        //for(Integer a :g.adj(58)){
        //	System.out.println(a);
        //	System.out.println(HM.containsValue(a));
       // }
 
        
        //System.out.println(g.V());
        //System.out.println(g.E());
       // System.out.println(a);
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
		
		return N;
		
	}
	
	// is the word a WordNet noun?
	public boolean isNoun(String word){
		
		return N.contains(word);
		
	}
	
	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB){
		
		if (!isNoun(nounA) || !isNoun(nounB))      
			throw new IllegalArgumentException();
		
		int a=HM.get(nounA);
		int b=HM.get(nounB);
		
		// distance between two node;
		
		int dis=s.length(a, b);
		return dis;
		
	}
	
	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB){
		
		if (!isNoun(nounA) || !isNoun(nounB))            
			throw new IllegalArgumentException();
		
		int a=HM.get(nounA);
		int b=HM.get(nounB);
		int ancestor=s.ancestor(a, b);
		
		//convert int to nouns
		
		return null;
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		 //WordNet wn=new WordNet("synsets.txt","hypernyms.txt");
		 
         //g=new Digraph(50);
         //System.out.println(g.E());
         //g.addEdge(2, 3);
         //g.addEdge(3, 2);
         //System.out.println(g.E());
	}

}

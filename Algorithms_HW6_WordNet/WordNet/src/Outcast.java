import java.io.IOException;

public class Outcast {
    private WordNet wn;
	// constructor takes a WordNet object
	public Outcast(WordNet wordnet){
		this.wn=wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns){
			
		int dist;
		int dismax=-1;
		int index=-1;
		for(int i=0;i<nouns.length;i++){
			dist=0;
			for(int j=0;j<nouns.length;j++){
				if(i!=j){
					dist =dist + wn.distance(nouns[i], nouns[j]);
					
				}
			}
			
			if(dist>dismax){
				dismax=dist;
				index=i;
			}
		}
		
		if(index==-1)
			return null;
		else
			return nouns[index];
	}

	// for unit testing of this class (such as the one below)
	//public static void main(String[] args)
	//Assume that argument array to the outcast() method contains only valid wordnet nouns (and that it contains at least two such nouns).
	//The following test client takes from the command line the name of a synset file, the name of a hypernym file, 
	//followed by the names of outcast files, and prints out an outcast in each file:

	public static void main(String[] args) throws IOException {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        @SuppressWarnings("deprecation")
			String[] nouns = In.readStrings(args[t]);
	        
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}

}

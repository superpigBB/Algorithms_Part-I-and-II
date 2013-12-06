import java.util.HashMap;


public class BoggleSolver
{
	private TrieST<Integer> st;
	private HashMap<Integer, Character> hm;
	private Graph g;
	private DepthFirstPaths dfp;
	private Stack<Integer> path;
	private String str;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){
    	st = new TrieST<Integer>();
    	for(int i=0;i<dictionary.length;i++){
    		st.put(dictionary[i], i);
    	}
    	//for(int i=0;i<dictionary.length;i++)
        //{
        //	StdOut.println("Score = " + st.get(dictionary[i]));
        //}
        //for (String key : st.keys()) {
        //  StdOut.println(key + " " + st.get(key));
        //}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	int rows=board.rows();
    	int columns=board.cols();
    	Queue<String> words=new Queue<String>();
    	g=new Graph(rows*columns);
    	hm=new HashMap<Integer, Character> ();
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++){
    			char dice=board.getLetter(i, j);
    			int value=i*rows+j;
    			//StdOut.println(value+" "+dice);
    			
    	      	hm.put(value, dice);
    		}
    	
    	//connect the edges
     	//insides
    
    	
        //sides
    	
        for(int b=0;b<columns-1;b++){
        	
    		g.addEdge(b, b+1 );
            g.addEdge((rows-1)*rows+b, (rows-1)*rows+b+1 );
        }
      
    	for(int a=0;a<rows-1;a++){
    		g.addEdge(a*rows, (a+1)*rows);
    	    g.addEdge(a*rows+columns-1, (a+1)*rows+columns-1);
    	}
    		
    	//
    	for(int b=1;b<columns-1;b++){
    		g.addEdge(0*rows+b, 1*rows+b);
    		g.addEdge(0*rows+b, 1*rows+b-1);
    		g.addEdge(0*rows+b, 1*rows+b+1);
    		
    		g.addEdge((rows-1)*rows+b, (rows-2)*rows+b);
    		g.addEdge((rows-1)*rows+b, (rows-2)*rows+b-1);
    		g.addEdge((rows-1)*rows+b, (rows-2)*rows+b+1);
    		
    	}
    	
    	for(int a=1;a<rows-1;a++){
    		g.addEdge(a*rows, (a-1)*rows+1);
    		g.addEdge(a*rows, a*rows+1);
    		g.addEdge(a*rows, (a+1)*rows+1);
    		
    		g.addEdge(a*rows+columns-1, (a-1)*rows+columns-2);
    		g.addEdge(a*rows+columns-1, (a)*rows+columns-2);
    		g.addEdge(a*rows+columns-1, (a+1)*rows+columns-2);
    		
    	}
    	
    	for(int i=1;i<rows-1;i++)
    		for(int j=1;j<columns-1;j++){
    			g.addEdge(i*rows+j, (i-1)*rows+j-1 );
    			g.addEdge(i*rows+j, (i-1)*rows+j);
    			g.addEdge(i*rows+j, (i-1)*rows+j+1 );
    			g.addEdge(i*rows+j, (i)*rows+j-1);
    			g.addEdge(i*rows+j, (i)*rows+j+1);
    			g.addEdge(i*rows+j, (i+1)*rows+j-1);
    			g.addEdge(i*rows+j, (i+1)*rows+j-1);
    			g.addEdge(i*rows+j, (i+1)*rows+j-1);
    		}
    	
    	StdOut.println(g.toString());
    	
       for(int i=0;i<g.V();i++){
          dfp=new DepthFirstPaths(g,i);
          //StdOut.println("kkkkkkkkkkkkkkkkkkkk");
          for(int j=0;j<g.V();j++){
        	  if(j==i) continue;
              if(dfp.hasPathTo(j)==true){
            	  path = new Stack<Integer>();
            	  path=(Stack<Integer>) dfp.pathTo(j);
            	  str=new String();
            	  while(path.isEmpty()==false){
            		  int v=path.pop();
            		  char ch=hm.get(v);
            		  
            		  str=str+ch;
            		  //StdOut.println(hm.containsKey(v));
                  }
            	  //StdOut.println(str);
            	  words.enqueue(str);
              }
          }
       }
    	return words;
    }
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
    	if(st.contains(word)){
    		if(word.length()>0&& word.length()<=2){
    			return 0;
    		}
    		else if(word.length()>=3&& word.length()<=4){
    			return 1;
    		}
    		else if(word.length()==5){
    			return 2;
    		}
    		else if(word.length()==6){
    			return 3;
    		}
    		else if(word.length()==7){
    			return 5;
    		}
    		else{
    			return 11;
    		}
    	}
    	return 0;
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        
       
        solver.getAllValidWords(board);
        //solver.printHash();
       for (String word : solver.getAllValidWords(board))
        	//StdOut.println(word);
       
       
       
       
        /*
       int score = 0;
       for (String word : solver.getAllValidWords(board))
        {
           StdOut.println(word);
           score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
         
        */
        return;
    }
    
    
}
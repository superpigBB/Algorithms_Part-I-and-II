
import java.util.HashMap;

public class BoggleSolver
{
	private my_TST<Integer> st;
	private HashMap<Integer, Character> hm;
	
	private HashMap<String,Integer> hm_prefix=new HashMap<String,Integer>() ;
	private Graph g;
	private Stack<Integer> path  = new Stack<Integer>();   
    private SET<Integer> onPath  = new SET<Integer>(); 
    private int max_dict_len=0;
	
	private Queue<Stack<Integer>> reque= new Queue<Stack<Integer>>();

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){
    	st = new my_TST<Integer>();
    	for(int i=0;i<dictionary.length;i++){
    		if(dictionary[i].length()>max_dict_len) 
    			max_dict_len=dictionary[i].length();
    		//for(int j=0;j<dictionary[i].length();j++){
    			//StdOut.println(dictionary[i].substring(0, j));
    			//hm_prefix.put(dictionary[i].substring(0, j), i*j);
    		//}
    		
    		st.put(dictionary[i], i);
    	}
    	//StdOut.println(max_dict_len);
    }
    
    private void findAllPath(Graph g, Integer s, Integer d){
    	if(path.size()<=max_dict_len){ 
    	   // add node v to current path from s
    	   path.push(s);
    	   onPath.add(s);
    	   
    	   // found path from s to t - currently prints in reverse order because of stack
    	   if (s.equals(d)){
    		  if(path.size()>2 &&path.size()<=max_dict_len){
    		  Stack<Integer> reverse=new Stack<Integer>();
    	      for(Integer temp:path)
    			 reverse.push(temp);
    	     
    	       reque.enqueue(reverse);
    	       }
    	    }
    	    // consider all neighbors that would continue path with repeating a node
    	    else {
    	        for (Integer w : g.adj(s)) {
    	            if (!onPath.contains(w)){ 
    	            	
    	            	Stack<Integer> reverse=new Stack<Integer>();
    	      		    //optimization
    	    			  for(Integer temp:path)
    	    			    reverse.push(temp);
    	    			    
    	    			  if(path.size()<=max_dict_len){
    	    			  String str=new String();
    	    			  
    	                  for(Integer temp: reverse){
    	                	
    	                	char c=hm.get(temp);
    	                	if(c=='Q') str=str+c+'U';
    	          		    else str=str+c;
    	                   }
    	                //System.out.println(str);
    	            	
    	            	  /*
    	                  if(path.size()<=max_dict_len){
    	            	   StringBuilder str=new StringBuilder();
    	            	   
    	            	   for(Integer temp:path){
    	            		  
    	            		   char c=hm.get(temp);
    	            		   //StdOut.println(c);
    	            	       if(c=='Q') str.insert(0, c);
   	          		           else str.insert(0, c);
    	            	      
    	            	      
    	            	   }
    	            	 StdOut.println(str);
    	            	*/
    	            	
    	                  //TODO: prefix
    	                  //Queue<String> q=(Queue<String>) st.prefixMatch(str);
    	                  
    	                  //if(!q.isEmpty())  {
    	                  //if(st.keysWithPrefix(str)==true){
    	                  if(st.prefixMatch(str)==true){
    	                    //if(hm_prefix.containsKey(str)){
    	                        findAllPath(g, w, d);
    	                  }
    	               }else {
    	            	   break;
    	               }
    	            }
    	        }
    	    }
    	    // done exploring from v, so remove from path
    	    path.pop();
    	    onPath.delete(s);
    	}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	int rows=board.rows();
    	int columns=board.cols();
    	if(rows==0||columns==0) return null;
    	SET<String> words=new SET<String>();
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
    	for(int b=0;b<columns-1;b++){
    		g.addEdge((rows-1)*rows+b, (rows-1)*rows+b+1);
    	}
    	
    	for(int a=0;a<rows-1;a++){
    		g.addEdge(a*rows+columns-1, (a+1)*rows+columns-1);
    	}
    	
    	for(int i=0;i<rows-1;i++)
    		for(int j=0;j<columns-1;j++){
    			g.addEdge(i*rows+j, (i)*rows+j+1);
    			g.addEdge(i*rows+j, (i+1)*rows+j);
    			g.addEdge(i*rows+j, (i+1)*rows+j+1);
    			g.addEdge((i+1)*rows+j, (i)*rows+j+1);
    		}
    	/*
    	for(int i=1;i<rows;i++)
    		for(int j=0;j<columns-1;j++){
    			g.addEdge(i*rows+j, (i-1)*rows+j+1);
    		}*/
    	//StdOut.println(g.toString());
    	
       for(int i=0;i<g.V();i++){
          for(int j=0;j<g.V();j++){
        	  if(i==j) continue;
        	  
        	
        	  findAllPath(g,i,j);
        	  
        	  while(reque.isEmpty()==false){
                String str=new String();
        	    for( Integer t:reque.dequeue()){
        		  char c=hm.get(t);
        		  if(c=='Q') str=str+c+'U';
        		  else str=str+c;
        	    }
        	    
        	    if(st.contains(str)&&str.length()>2){
        	    if(!words.contains(str))
        	       words.add(str);
        	    }
        	  }
          }
          //StdOut.println();
       }
    	return words;
    }
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
    	//if(st.contains(word)){
    		//StdOut.println(word);
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
    	//}
    	//return 0;
    }
    
    public static void main(String[] args)
    {   
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
     
        
        //BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        //for(int i=0;i<2500;i++){
        BoggleBoard board = new BoggleBoard(args[1]);
        
        //BoggleBoard board = new BoggleBoard();
        //BoggleBoard board = new BoggleBoard(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        //solver.getAllValidWords(board);
        //solver.printHash();
        
        //for (String word : solver.getAllValidWords(board))
        	//StdOut.println(word);

       
       for (String word : solver.getAllValidWords(board))
        {
    	   //System.out.println("**********************");
           StdOut.println(word);
           //System.out.println("**********************");
           score += solver.scoreOf(word);
        }
        
        
       // }
        StdOut.println("Score = " + score);
        return;
    }
    
    
}

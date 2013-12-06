
public class BoggleSolver
{
	private TrieST<Integer> st;
	
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
    	
    	
    	
    	
    	return null;
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
        
       
        /*
        //int score = 0;
       for (String word : solver.getAllValidWords(board))
        {
         //   StdOut.println(word);
         //   score += solver.scoreOf(word);
        }
        //StdOut.println("Score = " + score);
         * 
         */
        return;
    }
    
    
}

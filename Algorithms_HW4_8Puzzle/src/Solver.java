import java.util.ArrayList;

public class Solver {
	private MinPQ<Board> pq=null;
	private int move_count;
	private ArrayList<Board> result = new ArrayList<Board>();
    public Solver(Board initial){
    	// find a solution to the initial board (using the A* algorithm)
    	  Board min = initial; 
    	  pq=new MinPQ<Board>();
    	  System.out.println(min);

    	  do{
    	  for (Board neighbor : min.neighbors()){            
    		  if (!neighbor.equals(min)){                
    			  pq.insert(neighbor);   
    			  
    		    }        
    		  }        
    	  min = pq.delMin();
    	  if(min.hamming()==0) break;
    	  result.add(min);
    	  }while(true);
    }
    public boolean isSolvable(){
    	// is the initial board solvable?//TODO
    	return true;
    }
    public int moves(){
    	//number of moves to solve initial board; -1 if no solution
    	if(this.isSolvable()==false){
    		return -1;
    	}else
    		return move_count;
    }
    public Iterable<Board> solution() {
    	// sequence of boards in a shortest solution; null if no solution
    	return result;
    	
    }
    public static void main(String[] args) {
    	// solve a slider puzzle (given below)
    	In in = new In(args[0]);
        int N = in.readInt();
        
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
               
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
     }

    }
}

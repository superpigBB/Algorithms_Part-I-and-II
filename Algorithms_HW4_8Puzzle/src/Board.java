import java.util.ArrayList;

public class Board {
   
    private int[][] block=null;
    
	public Board(final int[][] blocks) {
		// construct a board from an N-by-N array of blocks
		// (where blocks[i][j] = block in row i, column j)
		  this.block = new int[blocks.length][blocks.length];
		  
		  for(int i=0; i<blocks.length; i++)
	    		for(int j=0;j<blocks.length;j++)
	    			block[i][j]=blocks[i][j];
	       
	}
    
    public int dimension()  {
    	// board dimension N
    	return block.length;
    }
    
    public int hamming() {
    	// number of blocks out of place
    	int hamming=0;
    	for(int i=0; i<block.length; i++)
    		for(int j=0;j<block.length;j++){
    			if (block[i][j] == 0)  continue;               
    		    if(block[i][j]!=(i*block.length)+j+1) hamming++;
    		}
    	return hamming;
    }
    
    public int manhattan()     {
    	// sum of Manhattan distances between blocks and goal  
    	int manhattan = 0;       
    	for (int i = 0; i < dimension(); i++) {            
    		for (int j = 0; j < dimension(); j++) {                
    			if (block[i][j] == 0) {                    
    				continue;               
    				}                
    			
    			if(block[i][j]!=(i*block.length)+j+1){
    			  int row=(block[i][j]-1)/dimension();
    			  int col=(block[i][j]-1)%dimension();
    			  
    			  manhattan += Math.abs(row - i) + Math.abs(col - j );
    			  
    			}
    		}        
    	}        
    	return manhattan;
    }
    
    public boolean isGoal()  {
    	// is this board the goal board?
    	if(this.hamming()==0) return true;
    	
    	return false;
    }
    
    public Board twin() {
    	int temp;
    	  Board swap=null;
    	// a board obtained by exchanging two adjacent blocks in the same row  
    	for (int i = 0; i < dimension(); i++) {            
    		for (int j = 0; j < dimension(); j++) {                
    			if (block[i][j] == 0) {
    				if(i==0){
    					if(j!=0){
                        swap=new Board(block);
    					
    				    temp=swap.block[i+1][j];
    				    swap.block[i+1][j]=swap.block[i+1][j-1];
    				    swap.block[i+1][j-1]=temp;
    				    return swap;
    				    }else{
    				    	swap=new Board(block);
        					
        				    temp=swap.block[i+1][j];
        				    swap.block[i+1][j]=swap.block[i+1][j+1];
        				    swap.block[i+1][j+1]=temp;
        				    return swap;
    				    }
    					
    					
    					
    				}else{
    					if(j!=0){
                            swap=new Board(block);
        					
        				    temp=swap.block[i-1][j];
        				    swap.block[i-1][j]=swap.block[i-1][j-1];
        				    swap.block[i-1][j-1]=temp;
        				    return swap;
        				    }else{
        				    	swap=new Board(block);
            					
            				    temp=swap.block[i-1][j];
            				    swap.block[i-1][j]=swap.block[i-1][j+1];
            				    swap.block[i-1][j-1]=temp;
            				    return swap;
        				    }
    				}
    					
    			}
    			
    		}
    	}
		return swap;

    }
    
    public boolean equals(Object y)  {
    	// does this board equal y?
    	if(y==null) return false;
    	
    	Board temp=(Board) y;
    	for (int i = 0; i < this.block.length; i++) {
            for (int j = 0; j < this.block.length; j++) {
                if (this.block[i][j] != temp.block[i][j]) {
                    return false;
                }
            }
        }
        return true;	
    
    }
    
    public Iterable<Board> neighbors()  {
    	// all neighboring boards
    	int temp;
    	ArrayList<Board> neighbors = new ArrayList<Board>();
    	for (int i = 0; i < dimension(); i++) {            
    		for (int j = 0; j < dimension(); j++) {                
    			if (block[i][j] == 0) {
    				if(j!=0) {
    			        Board leftswap=new Board(block);
    				    temp=leftswap.block[i][j];
    				    leftswap.block[i][j]=leftswap.block[i][j-1];
    				    leftswap.block[i][j-1]=temp;
    				    neighbors.add(leftswap);
    				}
    				if(j!=dimension()-1) {
    	    			
    					 Board rightswap=new Board(block);
    					 //System.out.println("b");
     				    temp=rightswap.block[i][j];
     				    rightswap.block[i][j]=rightswap.block[i][j+1];
     				    rightswap.block[i][j+1]=temp;
     				    neighbors.add(rightswap);
    				}
    				if(i!=0) {
    					 Board upswap=new Board(block);
    					 //System.out.println("c");
    					 temp=upswap.block[i][j];
      				    upswap.block[i][j]=upswap.block[i-1][j];
      				    upswap.block[i-1][j]=temp;
      				    neighbors.add(upswap);
    				}
    				if(i!=dimension()-1) {
    					 Board downswap=new Board(block);
    					 //System.out.println("d");
    					 temp=downswap.block[i][j];
      				    downswap.block[i][j]=downswap.block[i+1][j];
      				    downswap.block[i+1][j]=temp;
      				    neighbors.add(downswap);
    				}
    			}
    		}
    	}
		return neighbors;
    }
    
    public String toString() {
    	// string representation of the board (in the output format specified below)
    	StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", block[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    	
    }

	
	
}

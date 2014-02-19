public class Percolation {
	private WeightedQuickUnionUF WQUF=null;
	private boolean[] OpenArray=null;
	private int rowlength;
    
	public Percolation(int N){     //create N by N grid, with all sites blocked
		WQUF=new WeightedQuickUnionUF(N*N+2);
		OpenArray=new boolean[N*N+2];
		rowlength=N;
	   
		for(int i=0; i<N*N; i++)
	    	OpenArray[i]=false;
		
		OpenArray[N*N]=true;
		OpenArray[N*N+1]=true;
   }
	
	public void open(int i, int j){
		if (i < 1 || i > rowlength || j < 1 || j > rowlength) 
		{     
			throw new java.lang.IndexOutOfBoundsException();        
		} 
		
		if(isOpen(i,j)) return;
		
		int RowIndex=i-1; //-1 make the site count from 1 to N
		int ColumnIndex=j-1;
		
		OpenArray[RowIndex*rowlength+ColumnIndex]=true;
		
		 //virtual top cell
		
		  if(i==1){
	    	  if(!WQUF.connected(RowIndex*rowlength+ColumnIndex, rowlength*rowlength))
		      WQUF.union(RowIndex*rowlength+ColumnIndex, rowlength*rowlength);
		  }
		
		   //top row
		  if(i!=1 && isOpen(i-1,j)){
			  if(!WQUF.connected(((RowIndex-1)*rowlength+ColumnIndex),RowIndex*rowlength+ColumnIndex))
			  WQUF.union(((RowIndex-1)*rowlength+ColumnIndex),RowIndex*rowlength+ColumnIndex); 
	      }
		   
		  if (i==rowlength){
	          //virtual bottom cell
	    	  if(!WQUF.connected(RowIndex*rowlength+ColumnIndex, rowlength*rowlength+1))
	    	  WQUF.union(RowIndex*rowlength+ColumnIndex, rowlength*rowlength+1);
	      }
		  
	       // bottom row
	       if(i!=rowlength && isOpen(i+1,j)){       
	    	   if(!WQUF.connected(((RowIndex+1)*rowlength+ColumnIndex),RowIndex*rowlength+ColumnIndex)) 
	    	   WQUF.union(((RowIndex+1)*rowlength+ColumnIndex),RowIndex*rowlength+ColumnIndex); 
	       }
	       
	       //left border
	       if(j!=1 && isOpen(i,j-1)){
	    	   if(!WQUF.connected(((RowIndex)*rowlength+ColumnIndex-1),RowIndex*rowlength+ColumnIndex)) 
	    	   WQUF.union(((RowIndex)*rowlength+ColumnIndex-1),RowIndex*rowlength+ColumnIndex); 
	       }
	       
	       //right border
	        if(j!=rowlength && isOpen(i,j+1)){
	           if(!WQUF.connected(((RowIndex)*rowlength+ColumnIndex+1),RowIndex*rowlength+ColumnIndex))
	           WQUF.union(((RowIndex)*rowlength+ColumnIndex+1),RowIndex*rowlength+ColumnIndex); 
	       }
	
	}
	
	public boolean isOpen(int i, int j){
		if (i < 1 || i > rowlength || j < 1 || j > rowlength) 
		{     
			throw new java.lang.IndexOutOfBoundsException();        
		} 
		
		int RowIndex=i-1;
		int ColumnIndex=j-1;
		
		return OpenArray[RowIndex*rowlength+ColumnIndex];
	}

	public boolean isFull(int i, int j){
		if (i < 1 || i > rowlength || j < 1 || j > rowlength) 
		{     
			throw new java.lang.IndexOutOfBoundsException();        
		} 
		
		int RowIndex=i-1;
		int ColumnIndex=j-1;
		
		return WQUF.connected(rowlength*rowlength,RowIndex*rowlength+ColumnIndex);
	}
	
	public boolean percolates(){
		
		return WQUF.connected(rowlength*rowlength, rowlength*rowlength+1);
	}
}

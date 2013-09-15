public class Percolation {
	private WeightedQuickUnionUF WQUF=null;
	private boolean[][] OpenArray=null;
	private int rowlength;
    
	public Percolation(int N){     //create N by N grid, with all sites blocked
		WQUF=new WeightedQuickUnionUF(N*N);
		OpenArray=new boolean[N][N];
		rowlength=N;
	   
		for(int i=0; i<N; i++)
	    	for(int j=0; j<N; j++)
	    		OpenArray[i][j]=false;
   }
	
	public void open(int i, int j){
		if (i < 1 || i > rowlength || j < 1 || j > rowlength) 
		{     
			throw new java.lang.IndexOutOfBoundsException();        
		} 
		
		int RowIndex=i-1; //-1 make the site count from 1 to N
		int ColumnIndex=j-1;
		
		
		OpenArray[RowIndex][ColumnIndex]=true;
		
		if((RowIndex>0)&&(OpenArray[RowIndex-1][ColumnIndex]==true)){ // top is open
			WQUF.union(rowlength*(RowIndex)+ColumnIndex, rowlength*(RowIndex-1)+ColumnIndex);
		}	
		
        if((ColumnIndex>0)&&(OpenArray[RowIndex][ColumnIndex-1]==true)){ // left is open
    	   WQUF.union(rowlength*(RowIndex)+ColumnIndex, rowlength*(RowIndex)+ColumnIndex-1);
		}
        
        if((ColumnIndex<rowlength-1)&&(OpenArray[RowIndex][ColumnIndex+1]==true)){ // right is open
    	   WQUF.union(rowlength*(RowIndex)+ColumnIndex, rowlength*(RowIndex)+ColumnIndex+1);
        }
        
        if((RowIndex<rowlength-1)&&(OpenArray[RowIndex+1][ColumnIndex]==true)){ // down is open
    	   WQUF.union(rowlength*(RowIndex)+ ColumnIndex, rowlength*(RowIndex+1)+ColumnIndex);
        }
	
       return;	
	}
	
	public boolean isOpen(int i, int j){
		
		if (i < 1 || i > rowlength || j < 1 || j > rowlength) 
		{     
			throw new java.lang.IndexOutOfBoundsException();        
		} 
		
		int RowIndex=i-1;
		int ColumnIndex=j-1;
		
		return OpenArray[RowIndex][ColumnIndex];
	}

	public boolean isFull(int i, int j){
		
		if (i < 1 || i > rowlength || j < 1 || j > rowlength) 
		{     
			throw new java.lang.IndexOutOfBoundsException();        
		} 
		
		int RowIndex=i-1;
		int ColumnIndex=j-1;
		
		for(int n=0; n<rowlength;n++){
			if(isOpen(i, j)&&WQUF.connected((RowIndex)*rowlength+ColumnIndex, n)==true)
				return true;
		}
		return false;
	}
	
	public boolean percolates(){
		
		for(int i=0; i<rowlength; i++)
			for(int j=0; j<rowlength; j++){
			   if(isOpen(1, i + 1) && isOpen(rowlength, j + 1)&&WQUF.connected(rowlength*(rowlength-1)+j, i)==true)
				   return true;				
			}
				
		return false;
	}
}

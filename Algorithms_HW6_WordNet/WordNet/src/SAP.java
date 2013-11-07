
public class SAP {
    private Digraph g;
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G){
    	this.g=G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
	     if (!(0 <= v && v < g.V() && 0 <= w && w < g.V()))  
    		throw new IndexOutOfBoundsException();
    	
    	BreadthFirstDirectedPaths bfsv=new BreadthFirstDirectedPaths(g, v);
    	BreadthFirstDirectedPaths bfsw=new BreadthFirstDirectedPaths(g, w);
    	
    	int temp=Integer.MAX_VALUE;
    	int ancestor=-1;
        int len=-1;
        
        //if w and v connected;
        if (bfsv.hasPathTo(w)){
    	   len=bfsv.distTo(w);
    	   return len;
        }
        
        if (bfsw.hasPathTo(v)){
    	   len=bfsw.distTo(v);
    	   return len;
        }
    	
        for(int a=0; a<g.V();a++){
        	if(bfsv.hasPathTo(a)&&bfsw.hasPathTo(a)){
        		if(temp>bfsv.distTo(a)+bfsw.distTo(a))
        			ancestor=a;
        			temp=bfsv.distTo(a)+bfsw.distTo(a);
        	}
        	
        }
    
       if(ancestor!=-1)
    	   len= temp;
       else 
    	   len=-1;
       
       return len;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w){
    	if (!(0 <= v && v < g.V() && 0 <= w && w < g.V()))            
   		 throw new IndexOutOfBoundsException();
    	
    	BreadthFirstDirectedPaths bfsv=new BreadthFirstDirectedPaths(g, v);
    	BreadthFirstDirectedPaths bfsw=new BreadthFirstDirectedPaths(g, w);
    	
    	int temp=Integer.MAX_VALUE;
    	int ancestor=-1;
        
        
        //if w and v connected;
        if (bfsv.hasPathTo(w)){
    	   return w;
        }
        
        if (bfsw.hasPathTo(v)){
    	   return v;
        }
    	
        for(int a=0; a<g.V();a++){
        	if(bfsv.hasPathTo(a)&&bfsw.hasPathTo(a)){
        		if(temp>bfsv.distTo(a)+bfsw.distTo(a))
        			ancestor=a;
        			temp=bfsv.distTo(a)+bfsw.distTo(a);
        	}
        	
        }
    
       if(ancestor==-1)
    	   return -1;
       else 
    	   return ancestor;

    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w){
    	int min = Integer.MAX_VALUE;        
    
    	for (Integer iw : w) {            
    		for (Integer iv : v) {                
    			int len = length(iv, iw);                
    			if (len < min)                    
    				min = len;            
    			}       
    		}   
    	
    	return min;
    	
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
     	int ancestor = Integer.MAX_VALUE;        
        
    	
    	return -1;
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args){
    	In in =new In(args[0]);
    	Digraph G=new Digraph(in);
    	SAP sap=new SAP(G);
    	
    	while(!StdIn.isEmpty()){
    		
    		int v=StdIn.readInt();
    		int w=StdIn.readInt();
    		int length =sap.length(v, w);
    		int ancestor=sap.ancestor(v, w);
    		StdOut.printf("length=%d,  ancestor =%d\n", length, ancestor);
    		
    	}
    }
    

}

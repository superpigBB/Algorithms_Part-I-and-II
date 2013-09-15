
public class Subset {

	public static void main(String[] args){
		int K=Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<String>();       
         while (StdIn.hasNextLine() && !StdIn.isEmpty()) {                       
            queue.enqueue(StdIn.readString()); 
        	//queue.enqueue("AA"); 
        	//queue.enqueue("BB"); 
        	//queue.enqueue("CC"); 
        	//queue.enqueue("DD"); 
        	//queue.enqueue("EE"); 
        	//queue.enqueue("FF"); 
        	//queue.enqueue("GG"); 
        	//queue.enqueue("HH"); 
        	//queue.enqueue("aa"); 
        	//queue.enqueue("df"); 
        	//queue.enqueue("hi"); 
        	 //System.out.println("jjj");
         }        
       // System.out.println("kkk");
         for (int i = 0; i < K; i++) {           
        	 StdOut.println(queue.iterator().next());        
        	 }
        	 
         }
	}


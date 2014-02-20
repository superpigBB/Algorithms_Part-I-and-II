
public class Subset {    
	public static void main(String[] args) {        
		int k = Integer.parseInt(args[0]);        //Deque<String> queue = new Deque<String>();        
		RandomizedQueue<String> queue = new RandomizedQueue<String>();        
		while (StdIn.hasNextLine() && !StdIn.isEmpty()) {            //
			queue.enqueue(StdIn.readString());            
			//queue.enqueue("aa");
			//queue.enqueue("ss");
			//queue.enqueue("df");
			//queue.enqueue("cc");
			//queue.enqueue("ww");
			//System.out.println(queue.sample());
			
	    }        
		for (int i = 0; i < k; i++) {            
			StdOut.println(queue.iterator().next());        
		}
	}
}

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private class node{
		Item item;
		node pre;
		node next;
	}
	
	private int nodeCounter=0;
	private node head=null;
	private node tail=null;
	
	public Deque(){      // construct an empty dequeue
	   head=null;
	   tail=null;
	 
	   nodeCounter=0;
	}
    
	public boolean isEmpty()  {         // is the deque empty?
		if(nodeCounter==0) return true;
     
		return false;
	}
	   
	public int size() {              // return the number of items on the deque
	    return  nodeCounter;
	}
	   
	public void addFirst(Item item){  // insert the item at the front
		if(item==null) {           
			throw new NullPointerException("item is NULL");       
		}	
	   node n=new node();
	   n.item=item;
	   n.pre=null;
	   n.next=null;
	   if(nodeCounter==0) {
		   head=n;
		   tail=n;
	   }
	   else{
		n.next=head;
		n.pre=null;
		head.pre=n;
		head=n;
	   }
	   
	   nodeCounter++;
	 }
	   
	 public void addLast(Item item){   // insert the item at the end  
		 if(item==null) {           
				throw new NullPointerException("item is NULL");       
		}	
	    node n=new node();
	    n.item=item;
	    n.pre=null;
	    n.next=null;
		
	   if(nodeCounter==0) {
		   head=n;
		   tail=n;
	   }
	   else{
		   n.pre=tail;
		   n.next=null;
		   tail.next=n;
		   tail=n;
	    }
		
	    nodeCounter++;
	    
	 }
	   
	 public Item removeFirst(){  // delete and return the item at the front
		 if(nodeCounter==0){          
			 throw new NoSuchElementException("Deque is empty");       
		   }
		 Item out=head.item;
	     if(nodeCounter==1){
	    	 head=null;
	    	 tail=null;
	    	 nodeCounter=0;
	     }
	     else{
	        head=head.next;
		    head.pre=null;
		    nodeCounter--;
	     }
		 
		 
		 return out;
	 }
	   
	 public Item removeLast(){   // delete and return the item at the end
		 if(nodeCounter==0){          
			 throw new NoSuchElementException("Deque is empty");       
		   }
		 Item out=tail.item;
		 if(nodeCounter==1){
	    	 head=null;
	    	 tail=null;
	    	 nodeCounter=0;
	     }
		 else{
		     tail=tail.pre;
		     tail.next=null;
		     nodeCounter--;
		 }
		 
	     return out;
     }
	   
	 public Iterator<Item> iterator(){ // return an iterator over items in order from front to end
		 return new DequeIterator();   
	 }
	 
	 private class DequeIterator implements Iterator<Item> {      
		 private node current=head;                              
		 public boolean hasNext()  {            
			 return current != null;                        
		 }    
		 
		 public void remove() { 
			 throw new UnsupportedOperationException();        
		 }
		 
		 public Item next() {           
			 if (!hasNext()) throw new NoSuchElementException();           
			 Item item=removeFirst();                        
			 return item;        
	     }   
	 }
}


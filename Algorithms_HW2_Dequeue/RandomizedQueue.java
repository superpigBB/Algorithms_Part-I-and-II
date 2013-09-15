import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {
	   private class node{
		Item item;
		node pre;
		node next;
	   }
	   private Random rand = new Random();
	   private int nodeCounter=0;
	   private node head=null;
	   private node tail=null;
	
	   public RandomizedQueue() {          // construct an empty randomized queue
		   head=null;
		   tail=null;
		 
		   nodeCounter=0;
	   }
	   
	   public boolean isEmpty()  {         // is the queue empty?
		   if(nodeCounter==0) return true;
		     
			return false;
	   }
	   
	   public int size()   {               // return the number of items on the queue
		   return nodeCounter;
	   }
	   
	   public void enqueue(Item item)   {  // add the item
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
	   
	   public Item dequeue() {     // delete and return a random item
		   node output=null;
		   if(nodeCounter==0){          
				 throw new NoSuchElementException("Deque is empty");       
		      }
		   
		   if(nodeCounter==1)
			   { nodeCounter=0;
			     output=head;
			     head=null;
			     tail=null;
			   return output.item;
			   }
		   
		   int s= rand.nextInt(nodeCounter);
		   
		   if(s==0) {
			   output=head;
			   head=head.next;
			   output.next=null;
			   head.pre=null;
			   nodeCounter--;
			   return output.item;
		   }
		   
		   if(s==nodeCounter-1){
			   output=tail;
			   tail=tail.pre;
			   output.pre=null;
			   tail.next=null;
			   nodeCounter--;
			   return output.item;
		   }
		   
		   node current=head;
		   for(int i =0;i<s;i++)
			   current=current.next;
		   
		   node preone=current.pre;
		   node nextone=current.next;
		   //output=current;
		   preone.next=nextone;
		   nextone.pre=preone;
		   
		   current.next=null;
		   current.pre=null;
		   nodeCounter--;
		   return current.item;  
	   }
	   
	   public Item sample()      {         // return (but do not delete) a random item
		     if(nodeCounter==0){          
				 throw new NoSuchElementException("Deque is empty");       
		      }
		   
		   int s= rand.nextInt(nodeCounter);
		   
		   node current=head;
		   for(int i =0;i<s;i++)
			   current=current.next;
		   
		   return current.item;   
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
				 Item item=dequeue();  
				 //current=head;
				 return item;        
		     }   
		 }
		
	   
	   public Iterator<Item> iterator()   {// return an independent iterator over items in random order
		   return new DequeIterator();
	   }
	   
}


import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {    
	private List<Item> list;    
	private Random rand = new Random();
	
	public RandomizedQueue() {          
		list = new LinkedList<Item>();
	}    
	
	
	public boolean isEmpty() {       
		return list.isEmpty();   
		}   
	
	  
	public int size() {        
		return list.size();   
		}    
	
	
	public void enqueue(Item item) {       
		if (item == null) {           
			throw new java.lang.NullPointerException();       
			}        
		list.add(item);    
		}    
	

	public Item dequeue() {        
		if (isEmpty()) {           
			throw new java.util.NoSuchElementException();        
			}        
		int index=rand.nextInt(size());
		return (Item) list.remove(index);    
		}    
	
	 
	public Item sample() {        
		if (isEmpty()) {            
			throw new java.util.NoSuchElementException();       
			}        
		int index=rand.nextInt(size());
		return (Item) list.get(index);   
		}    
	
	private class RandomizedQueueIterator<T> implements Iterator<T> {        
		public boolean hasNext() {            
			return size() > 0;       
			}       
		public T next() {            
			if (isEmpty()) {                
				throw new java.util.NoSuchElementException();            
				}            
			return (T) dequeue();        
			}        
		public void remove() {            
			throw new java.lang.UnsupportedOperationException();       
			}    
		}    
	
	public Iterator<Item> iterator() {        
		return new RandomizedQueueIterator<Item>();    
		}
	
}

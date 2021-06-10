import java.util.Iterator;


public class Deque<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	private int n=0;
	//using a doubly linked list to implement dequeue
	private class Node{
		Item item;
		Node previous;
		Node next;
	}
	//default constructor
	public Deque(){
		first=null;
		last=first;
	}
	public boolean isEmpty(){
		return first==null;
	}
	public int size(){
		
		return n;
	}
	//adding node at the beginning of the queue
	public void addFirst(Item item){
		if(item!=null){
			
		Node oldfirst=first;
		first=new Node();
		first.item=item;
		first.next=oldfirst;
		first.previous=null;
		if(oldfirst==null)
		last=first;
		else
		oldfirst.previous=first;
		n++;
	}
	else
	throw new IllegalArgumentException("invalid item argument");
	}
	//adding node at the end of the queue
	public void addLast(Item item){
		if(item!=null){
			
		Node oldlast=last;
		last=new Node();
		last.item=item;
		last.next=null;
		last.previous=oldlast;
		if(oldlast==null)
		first=last;
		else
		oldlast.next=last;
		n++;
	}
	else
	throw new IllegalArgumentException("invalid item argument");
	}
	//removing the first node
	public Item removeFirst(){
		if(!isEmpty()){
		Item item=first.item;
		Node re=first;
		first=first.next;
		re.next=null;
		if(first==null)
		last=first;
		else
		first.previous=null;
		n--;
		return item;
	}
	else
	throw new java.util.NoSuchElementException("No elements in the dequeue");
	}
	//removing the last node
	public Item removeLast(){
		if(!isEmpty()){
		Item item=last.item;
		Node re=last;
		last=last.previous;
		re.previous=null;
		if(last==null)
		first=last;
		else
		last.next=null;
		n--;
		return item;
	}
	else
	throw new java.util.NoSuchElementException("No elements in the dequeue");
	}
	//iterator for iterating through the queue
	public Iterator<Item> iterator(){
		return new ListIterator();}
	private class ListIterator implements Iterator<Item>{
		private Node current=first;
		public boolean hasNext(){
			return current!=null;
		}
		public Item next(){
			if(current!=null){
				
			Item item=current.item;
			current=current.next;
			return item;
		}
		else
		throw new java.util.NoSuchElementException("No more items in iteration");
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}	
	}
	//unit testing
	public static void main(String[] args){
		Deque<Integer> d=new Deque<Integer>();
		d.addFirst(6);
		d.addLast(8);
		d.addFirst(9);
		d.addLast(15);
		System.out.println(d.removeLast());
		d.addFirst(67);
		d.addLast(90);
		System.out.println(d.removeFirst());
		Iterator<Integer> i=d.iterator();
		while(i.hasNext())
		{
			int s=i.next();
			System.out.println(s);
		}	
		i.remove();
	}
}		
			
		
		
		
		

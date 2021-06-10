import java.util.Iterator;
import java.util.Random;   

public class RandomizedQueue<Item> implements Iterable<Item>{
	private Node first;
	private Node last;
	private int n;
	
	private class Node{
		Item item;
		Node previous;
		Node next;
	}
	private Item removeFirst(){
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
	private Item removeLast(){
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
	
	private Item removeAt(int i){
		Node current=find(i);
		Node pr=current.previous;
		Node ne=current.next;
		pr.next=ne;
		ne.previous=pr;
		Item item=current.item;
		current.next=null;
		current.previous=null;
		n--;
		return item;
	}
	//finding the ith entry in the queue
	private Node find(int i){
		int j=0;
		Node current=first;
		if(current==null)
		return null;
		while(j!=i){
			current=current.next;
			j++;
		}
		return current;
	}
	//swapping array elements
	private void swap(int arr[],int i,int j){
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	//to produce a random permutation of 0 to n-1 numbers
	private int[] shuffleArray(int arr[],int size){
		if(size>1){
		Random rand=new Random();
		for(int i=0;i<size-1;i++)
		swap(arr,i,rand.nextInt(size-i-1)+i+1);
		
	}
	return arr;
	
	}
	//array of 0 to n-1 numbers
	private int[] fillArray(){
		int arr[]=new int[n];
		for(int i=0;i<n;i++)
		arr[i]=i;
		return arr;
	}		
				
			
			
			
		
		

	
	//default constructor
	public RandomizedQueue(){
		first=null;
		last=first;
		n=0;
	}
	public boolean isEmpty(){
		return n==0;
	}
	public int size(){
		return n;
	}
	//Adding from the end
	public void enqueue(Item item){
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
	//Deleting a node randomly
	public Item dequeue(){
		Item item;
		Random random = new Random(); 
		int re=random.nextInt(n);
		if(re==0)
		item=removeFirst();
		else if(re==n-1)
		item=removeLast();
		else
		item=removeAt(re);
		return item;
	}
	//Returning a node randomly
	public Item sample(){
		Random random = new Random(); 
		int re=random.nextInt(n);
		Node current=find(re);
		Item item=current.item;
		return item;
	}
	public Iterator<Item> iterator(){
		return new ListIterator();}
	private class ListIterator implements Iterator<Item>{
		//Random random = new Random(); 
		
		int arr1[]=fillArray();
		int arr2[]=shuffleArray(arr1,n);
		int re=0;
		//Iterating the list based on the random number arrangment in the array
		private Node current=find(arr2[re]);
		public boolean hasNext(){
			return re<n;
		}
		public Item next(){
			if(re<n){
			Item item=current.item;
			re++;
			if(re<n)
			current=find(arr2[re]);
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
		RandomizedQueue<Double> r=new RandomizedQueue<Double>();
		r.enqueue(45.9);
		r.enqueue(56.7);
		r.enqueue(46.8);
		r.enqueue(778.9);
		r.enqueue(65.4);
		System.out.println(r.dequeue());
		System.out.println(r.sample());
		System.out.println(r.size());
		Iterator<Double> i=r.iterator();
		
		while(i.hasNext())
		{
			double s=i.next();
			System.out.println(s);
		}
		
	}
}			
		
			
		
			
		
	

		
			

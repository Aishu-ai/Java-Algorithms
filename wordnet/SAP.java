import java.util.*;
import java.io.*;

//class to implement Shortest Ancestral path

public class SAP{
	private boolean[] marked; //to mark the vertices
	private int[]edgeTo; //include the edges
	private int[] index; //labels for sets
	private Digraph G;
	private int common_dist=-1;
	private int common_ancestor=-1;
	private void bfs_var(int v,int w){ //given two vertices
		for(int i=0;i<G.vertices();i++){ //resetting the data structures
			marked[i]=false;
			edgeTo[i]=-1;
		}
		Queue<Integer>q=new LinkedList<Integer>();
		q.add(v); //adding to the queue
		q.add(w);
		marked[v]=true;
		marked[w]=true;
		while(q.size()>0){
			int t=q.poll();
			for(int s:G.store.get(t)){//exploring its neighbours
				if(!marked[s]){ //if the vertex is unmarked
					q.add(s);
					marked[s]=true;
					edgeTo[s]=t;
				}
				else{
					//the first marked vertex we reach is the ancestor.
					int count=0;
					common_ancestor=s;
					//length calculation
					while(s!=v&&s!=w){ //length of common ancestor(s) from source/sink nodes
						count++;
						s=edgeTo[s];
					}
					while(t!=v&&t!=w){
						count++;
						t=edgeTo[t];
						
					}		
					common_dist=count+1; //1 for the edge t->s(not included as s is already marked)
					return;
					}	
				}
		}
	}	
	private void bfs_var2(Iterable<Integer> v, Iterable<Integer> w){ //given two sets of vertices
		for(int i=0;i<G.vertices();i++){
			marked[i]=false;
			edgeTo[i]=-1;
			
		}
		Queue<Integer>q=new LinkedList<Integer>();
		for(int i:v){
			if(i<0||i>G.vertices()-1) //if any vertex is out of the bounds
			throw new IllegalArgumentException();
			q.add(i); //adding all vertices to queue
			marked[i]=true;
			index[i]=0; //label 0 for set v
		}	
		
		for(int i:w){
			if(i<0||i>G.vertices()-1)
			throw new IllegalArgumentException();
			q.add(i);
			marked[i]=true;
			index[i]=1; //label 1 for set w
		}
		while(q.size()>0){
			int t=q.poll();
			for(int s:G.store.get(t)){
				if(!marked[s]){
					q.add(s);
					marked[s]=true;
					edgeTo[s]=t;
					index[s]=index[t];
				}
				else{
					//if s is not in the same set as t but is reachable from t and is the first one to do so
					if(index[s]!=index[t]){
					int count=0;
					common_ancestor=s;
					while(edgeTo[s]!=-1){
						count++;
						s=edgeTo[s];
					}
					while(edgeTo[t]!=-1){
						count++;
						t=edgeTo[t];
					}		
					common_dist=count;
					return;
				}
					
				}	
				
			}
		}
	}		
	public SAP(Digraph G){ //constructor
		if(G==null)
		throw new IllegalArgumentException();
		this.G=G;
		marked=new boolean[G.vertices()];
		edgeTo=new int[G.vertices()];
		index=new int[G.vertices()];
		
		for(int i=0;i<G.vertices();i++){
			marked[i]=false;
			edgeTo[i]=-1;
			index[i]=-1;
			
		}
	}
	
	//sap length
	public int length(int v,int w){
		if(v<0||w<0||v>G.vertices()-1||w>G.vertices()-1)
		throw new IllegalArgumentException();
		bfs_var(v,w);
		return common_dist;
	}
	//shortest length ancestor
	public int ancestor(int v, int w){
		if(v<0||w<0||v>G.vertices()-1||w>G.vertices()-1)
		throw new IllegalArgumentException();
		bfs_var(v,w);
		return common_ancestor;
	}
	//length for two sets
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		if(v==null||w==null)
		throw new IllegalArgumentException();
		bfs_var2(v,w);
		return common_dist;
	}
	//ancestor for two sets
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		if(v==null||w==null)
		throw new IllegalArgumentException();
		bfs_var2(v,w);
		return common_ancestor;
	}
	//unit testing
	public static void main(String[] args){
		Digraph D=new Digraph("hypernyms.txt");
		SAP solve=new SAP(D);
		System.out.println(solve.length(3,24532));
		System.out.println(solve.ancestor(3,24532));
	}				
		
	
		
}	
		
	

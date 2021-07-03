import java.util.*;
import java.io.*;

//Class for directed graph

public class Digraph{
	private int V; //Vertices
	public final List<Set<Integer>> store; //adjacent list representation of graph
	public Digraph(int V){
		this.V=V;
		store=new ArrayList<Set<Integer>>();
		for(int i=0;i<V;i++)
		store.add(new TreeSet<Integer>()); //Empty graph
	}
	public Digraph(String filename){ //Graph from file input
		store=new ArrayList<Set<Integer>>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {  //example file given
				String[] values = line.split(",");
				Set<Integer> temp=new TreeSet<Integer>();
				if(values.length>1){
					for(int i=1;i<values.length;i++)
					temp.add(Integer.valueOf(values[i]));
				}
				store.add(temp);
			}
			V=store.size();
		}
		catch(IOException ioex) {
        ioex.printStackTrace();
	}
	}		
		
	public void addEdge(int v,int w){ //add an edge
		store.get(v).add(w);
	}
	public int vertices(){
		return V;
	}
	//unit testing
	public static void main(String[] args){
		Digraph text=new Digraph("hypernyms.txt");
	}
	
}		

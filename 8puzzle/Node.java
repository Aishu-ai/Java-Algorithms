import java.util.Comparator;

//Nodes to make a game tree where each node contains a board, the move at which it was formed and its manhattan distance from the goal
public class Node{
	private Board b; //Board
	private int moves;
	private int comp; //Manhattan distance
	
	public Node(Board b,int moves){
		this.b=b;
		this.moves=moves;
		comp=b.manhattan();
	}
	public int getP(){
		return moves+comp;
	}	
	public void setm(int m){
		moves=m;
	}
	public int getMan(){
		return comp;
	}
	public Board getb(){
		return b;
	}			
	
}	

//Compare function for the priority queue
//Using manhattan as the priority function
class NodeComparator implements Comparator<Node>{
	public int compare(Node n1,Node n2){
		if(n1.getP()>n2.getP())
		return +1;
		else if(n1.getP()<n2.getP())
		return -1;
		else
		return 0;
	}
}		
		
	
	

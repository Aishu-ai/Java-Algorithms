import java.io.*;
import java.util.*;

public class Solver{
	private int M=0; //number of moves
	private ArrayList<Board> steps; //List to store steps
	private class SolIterator implements Iterator<Board>{ //Iterator for the list
		
		Iterator it=steps.iterator();
		public boolean hasNext(){
			return it.hasNext();
		}
		public Board next(){
			return (Board)it.next();
		}
	}		
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board Initial){
		if(Initial==null)
		throw new IllegalArgumentException("Null Argument");
		PriorityQueue<Node> pq=new PriorityQueue<Node>(1,new NodeComparator());
		steps=new ArrayList<Board>();
		Node n=new Node(Initial,0);
		pq.add(n);
		int move=0;
		Board temp=Initial;
		while(!pq.isEmpty()&&!pq.peek().getb().isGoal()){//if the queue is not null and we have not yet reached the goal
			
			move++;
			temp=pq.peek().getb();
			
			Iterator<Board> it=temp.neighbours();
			n=pq.poll(); //Removing the node with least priority
			while(it.hasNext()){
				Board t=it.next();
				if((!steps.contains(t)&&!pq.contains(t))&&(t.manhattan()<temp.manhattan())){
					Node u=new Node(t,move);
					pq.add(u); //adding its neighbours if they already do not exist and if they are closer to the goal 
				}	
			}
			steps.add(n.getb()); //adding the least priority boards to the list
		}
		
		if(!pq.isEmpty()&&pq.peek().getb().isGoal()){ //If goal is reached
			steps.add(pq.poll().getb());
			M=move;
		}
		else{ //else unsolvable
			M=-1;
			steps.clear();
		}	
		
	}
	// is the initial board solvable? 
	public boolean isSolvable(){
		return M>=0;
	}	
	// min number of moves to solve initial board; -1 if unsolvable
	public int moves(){
		return M;
	}
	// sequence of boards in a shortest solution; null if unsolvable
	public Iterator<Board> solution(){
		if(!isSolvable())
		return null;
		else
		return new SolIterator();}	
	// test client
	public static void main(String[] args){
		try{
			// create initial board from file
			Scanner scanner = new Scanner(new File(args[0]));
			int n=scanner.nextInt();
			int[][]tiles=new int[n][n];
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++)
				tiles[i][j] = scanner.nextInt();
			}
			Board initial=new Board(tiles);
			// solve the puzzle
			Solver solver=new Solver(initial);
			// print solution to standard output
			if (!solver.isSolvable())
			System.out.println("Unsolvable puzzle");
			else {
				System.out.println("Minimum number of moves = " + solver.moves());
				Iterator<Board> i=solver.solution();
				while(i.hasNext()){
					Board temp=i.next();
					System.out.println(temp.toString());
				}	
			}
		}
		catch(IOException ioex) {
        ioex.printStackTrace();
	}
    
  }	
			
				
    
		
		
	
}			
				
			
		

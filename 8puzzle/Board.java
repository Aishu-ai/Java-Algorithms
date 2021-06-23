import java.lang.Math;
import java.lang.Object;
import java.util.Iterator;

public class Board{
	private int N; //dimension of board
	private int[][]grid;
	private int emptyr; //row and column of the blank square
	private int emptycol;
	private void findempty(){ //finding the position of blank square
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(grid[i][j]==0){
						emptyr=i;
						emptycol=j;
						break;
					}
				}
			}
		}
	private int numOfNeigh(){ //number of neighbours of the given board
		if((emptyr==0||emptyr==N-1)&&(emptycol==0||emptycol==N-1)) //for corner tiles
		return 2;
		else if((emptyr==0||emptyr==N-1)||(emptycol==0||emptycol==N-1)) //for tiles on the last and first row/columns
		return 3;
		else
		return 4;
	}
	//swapping two tiles of a board
	private void swap(int[][]arr,int row1,int col1,int row2,int col2){
		int temp=arr[row1][col1];
		arr[row1][col1]=arr[row2][col2];
		arr[row2][col2]=temp;
	}
	//Iterator to give neighbours	
	private class BoardIterator implements Iterator<Board>{
		private int n=numOfNeigh();
		private int str;
		private int stc;
		public boolean hasNext(){
			return n>0;} //number of neighbours remaining
		public Board next(){
			int[][] neigh=new int[N][N];
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++)
				neigh[i][j]=grid[i][j];
			}
			//Anticlockwise swappping of neighbouring tiles with the blank square
			if(n==4){
				str=emptyr-1;
				stc=emptycol;
			}
			else if(n==3){
				if((emptycol==0||emptycol==N-1)||emptyr==N-1){
					str=emptyr-1;
					stc=emptycol;
				}
				else
				{
					str=emptyr;
					stc=emptycol-1;
				}
			}
			else if(n==2){
				if(emptyr==N-1&&(emptycol==0||emptycol==N-1)){
					str=emptyr-1;
					stc=emptycol;
				}
				else if((emptycol==N-1&&emptyr!=N-1)||(emptyr==N-1&&(emptycol!=0&&emptycol!=N-1))){
					str=emptyr;
					stc=emptycol-1;
				}
				else{
					str=emptyr+1;
					stc=emptycol;
				}
			}
			else{
				if(emptyr==N-1 &&emptycol==N-1){
					str=emptyr;
					stc=emptycol-1;
				}
				else if(emptycol==N-1&&emptyr!=N-1){
					str=emptyr+1;
					stc=emptycol;
				}
				else{
					str=emptyr;
					stc=emptycol+1;
				}
			}											
			swap(neigh,emptyr,emptycol,str,stc);
			Board board=new Board(neigh);
			n--;
			return board;
		}
	}		
	// create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)	
	public Board(int[][] tiles){
		N=tiles.length;
		grid=new int[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++)
			grid[i][j]=tiles[i][j];
		}
		findempty();	
	}
	// string representation of this board
	public String toString(){
		String str;
		str=String.valueOf(N)+"\n";
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++)
			str+=String.valueOf(grid[i][j])+" ";
			str+="\n";
		}
		return str;	
	}
	// board dimension N
	public int dimension(){
		return N;
	}
	// number of tiles out of place
	public int hamming(){
		int wrong=0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(grid[i][j]!=i*N+j+1&&i*N+j+1!=N*N)
				wrong++;
			}
		}
		if(grid[N-1][N-1]!=0)
		wrong++;
		return wrong;
	}
	// sum of Manhattan distances between tiles and goal
	public int manhattan(){
		int dist=0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(grid[i][j]!=0){
					if (grid[i][j]%N==0)
					dist+=Math.abs(grid[i][j]/N-1-i)+Math.abs(N-1-j);
					else
					dist+=Math.abs(grid[i][j]/N-i)+Math.abs(grid[i][j]%N-1-j);
				}
				else
				dist+=Math.abs(i-N+1)+Math.abs(j-N+1);
			}
		}		
				return dist;
	}
	// is this board the goal board?	
	public boolean isGoal(){
		return (hamming()==0);
	}
	// does this board equal y?
	public boolean equals(Object y){
		if (y == this) 
        return true;
        if (!(y instanceof Board)) 
        return false;
        Board comp=(Board)y;
        if(this.dimension()!=comp.dimension())
        return false;
        for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(grid[i][j]!=comp.grid[i][j])
				return false;
			}
		}
		return true;
	}
	// all neighboring boards
	public Iterator<Board> neighbours(){
		return new BoardIterator();}
	//Unit testing	
	public static void main(String[] args){
		int[][] input=new int[3][3];
		input[1][1]=0;
		input[0][0]=7;
		input[0][1]=4;
		input[0][2]=5;
		input[1][0]=6;
		input[1][2]=8;
		input[2][0]=1;
		input[2][1]=3;
		input[2][2]=2;
		
		Board b=new Board(input);
		System.out.println(b.toString());
		System.out.println(b.manhattan());
		System.out.println(b.hamming());
		System.out.println(b.equals(b));
		Iterator<Board> i=	b.neighbours();
		while(i.hasNext()){
			Board temp=i.next();
			System.out.println(temp.toString());
			System.out.println(b.equals(temp));
		}	
	}		
}							
				
			
		
						
				
			
		
	

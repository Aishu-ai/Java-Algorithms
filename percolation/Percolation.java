import java.util.*;

public class Percolation extends QuickUnionUF{
	//Array to track status either closed 'c' or open 'd' of the sites
	private char[]status;
	//Size of the grid i.e n
	private int size;
	
	//Parametrized constructor
	public Percolation(int n){
		super(n*n+2);//Creating an array of size n*n+2
		//+2 as we will connect the 0th node to all the nodes in the first row and n+1th node to all the nodes in the last row
		//so the problem effectively becomes value of connected(0,n+1).if its true it percolates
		if(n<=0)
		throw new IllegalArgumentException(" not valid");
		else{
		size=n;
		int num=n*n+2;
		status=new char[size*size];
			//Initailaizing status to closed
		for(int i=0;i<num-2;i++){
			status[i]='c';
		}
		for(int i=0;i<size;i++){
			union(0,i+1);
			union(num-1,num-2-i);
		}
	}
	}
	//Opening a site
	public void open(int row, int col){
		if(row<1||row>size)
		throw new IllegalArgumentException(" invalid row index");
		else if(col<1||col>size)
		throw new IllegalArgumentException(" invalid column index");
		else{
			
		int index=(row-1)*size+(col-1);
		status[index]='o';
		//If any of its adjacent sites are open join them...	
		if(index>=1 && status[index-1]=='o')
		union(index,index+1);
		if(index<size*size-1 && status[index+1]=='o')
		union(index+1,index+2);
		if(index>=size && status[index-size]=='o')
		union(index-size+1,index+1);
		if(index<size*size-1-size && status[index+size]=='o')
		union(index+size+1,index+1);
	}
	}
	public boolean isOpen(int row,int col){
		if(row<1||row>size)
		throw new IllegalArgumentException(" invalid row index");
		else if(col<1||col>size)
		throw new IllegalArgumentException(" invalid column index");
		else{
		
		int index=(row-1)*size+(col-1);
		return(status[index]=='o');
	}
	}
	//A site is full if its connected to the top row i.e the first row or to the 0th node
	public boolean isFull(int row, int col){
		if(row<1||row>size)
		throw new IllegalArgumentException(" invalid row index");
		else if(col<1||col>size)
		throw new IllegalArgumentException(" invalid column index");
		else{
			int index=(row-1)*size+(col-1);
			return(connected(0,index+1));
		}
	}
	public int numberOfOpensites(){
		int num=0;
		for(int i=0;i<size*size;i++){
			if(status[i]=='o'){
				//System.out.print(i+1+" ");
			num++;
		}
		}
		return num;
	}
	public boolean percolates(){
		return(connected(0,size*size+1));
	}
	
	
	
	
	}
//Using the weighted Quick union method.Modelling the n*n boxes as n*n objects and considering them to be connected when there are adjacent full boxes.
//We implement a forest i.e a set of trees where if id[i]=i, then i is the root of the tree	
	
			
class QuickUnionUF{
	private int[]id;//where id[i] is the parent of i
	private int[]sz;//sz[i] gives no. of objects in tree rooted at i
	
	//parametrized constructor
	public QuickUnionUF(int N){
		id=new int[N];
		sz=new int[N];
		for(int i=0;i<N;i++){
			id[i]=i;
			sz[i]=0;
	}
}
	//To find root(using path compression..keeping the tree almost flat)
	private int root(int i){
		while(i!=id[i]){
			id[i]=id[id[i]];
			i=id[i];
		}
		return i;
	}
	//if p and q belong to the same tree
	public boolean connected(int p,int q){
		return(root(p)==root(q));
	}
	//combining trees( weighted implementation..parent node dependd upon the size of th trees being joined)
	public void union(int p,int q){
		int i=root(p);
		int j=root(q);
		if(i==j)
		return;
		if(sz[i]<sz[j]){
			id[i]=j;
			sz[j]+=sz[i];
		}
		else{
			id[j]=i;
			sz[i]+=sz[j];
		}
	}
}
		

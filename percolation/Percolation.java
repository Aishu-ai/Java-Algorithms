import java.util.*;

public class Percolation extends QuickUnionUF{
	private char[]status;
	private int size;
	
	
	public Percolation(int n){
		super(n*n+2);
		//System.out.println("Hi");
		if(n<=0)
		throw new IllegalArgumentException(" not valid");
		else{
		size=n;
		int num=n*n+2;
		status=new char[size*size];
		for(int i=0;i<num-2;i++){
			status[i]='c';
		}
		for(int i=0;i<size;i++){
			union(0,i+1);
			union(num-1,num-2-i);
		}
	}
	}
	public void open(int row, int col){
		if(row<1||row>size)
		throw new IllegalArgumentException(" invalid row index");
		else if(col<1||col>size)
		throw new IllegalArgumentException(" invalid column index");
		else{
			
		int index=(row-1)*size+(col-1);
		status[index]='o';
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
			
		
		
		
	
	
	



class QuickUnionUF{
	private int[]id;
	private int[]sz;
	
	
	public QuickUnionUF(int N){
		id=new int[N];
		sz=new int[N];
		for(int i=0;i<N;i++){
			id[i]=i;
			sz[i]=0;
	}
}
	private int root(int i){
		while(i!=id[i]){
			id[i]=id[id[i]];
			i=id[i];
		}
		return i;
	}
	public boolean connected(int p,int q){
		return(root(p)==root(q));
	}
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
		

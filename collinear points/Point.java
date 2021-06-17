import java.util.*;
import java.io.*;

public class Point implements Comparable<Point>{
	private final int x;
	private final int y;
	
	
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}		
	public int compareTo(Point that){//Comparing points(Natural)
		if(this.y<that.y)
		return -1;
		else if(this.y==that.y&&this.x<that.x)
		return -1;
		else if(this.y>that.y)
		return 1;
		else if(this.y==that.y&&this.x>that.x)
		return 1;
		else return 0;
	}
	public double slopeTo(Point that){//Slope calculation
		if(this.x==that.x&&this.y==that.y)
		return Double.NEGATIVE_INFINITY;
		else if(this.y==that.y)
		return 0;
		else if(this.x==that.x)
		return Double.POSITIVE_INFINITY;
		else
		return ((that.y-this.y)*1.0/(that.x-this.x));
	}
	public Comparator<Point> slopeOrder(){ //Comparing based on slopes
		return (o1, o2) -> Double.compare(slopeTo(o1), slopeTo(o2));
	}
		
    
	
}			
		
				
	

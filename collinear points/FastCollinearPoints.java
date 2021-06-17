import java.io.*;
import java.util.*;
import java.lang.*;

public class FastCollinearPoints{
	
	private int N=0;  //Number of points
	private Vector<LineSegment> v = new Vector<>();  //To store line segments
	
	public FastCollinearPoints(Point[] points){
		if(points==null) //if argument is null
		throw new IllegalArgumentException("Argument is null");
		N=points.length;
		Point temp[]=new Point[N];
		Arrays.sort(points); //natural sorting of the points
		for(int i=0;i<N;i++){
			if(points[i]==null)//if any of the points are null
			throw new IllegalArgumentException("Argument is null");
			Point p=points[i];
			System.arraycopy(points,0,temp,0,N);//copying the points
			Arrays.sort(temp,p.slopeOrder());// sorting them based on the slope they make with points[i]
			int start=0;
			int k=0;
			boolean result=false;
			boolean prev=false;
			for(int j=2;j<N-1;j++){
				//Duplicate points
				if(p.compareTo(temp[j-1])==0)
				throw new IllegalArgumentException("Repeated arguments");
				if(temp[j].compareTo(temp[j-1])==0||temp[j].compareTo(temp[j+1])==0)
				throw new IllegalArgumentException("Repeated arguments");
				if(temp[j-1].compareTo(temp[j+1])==0)
				throw new IllegalArgumentException("Repeated arguments");
				
				if(p.slopeTo(temp[j-1])==p.slopeTo(temp[j])&&p.slopeTo(temp[j])==p.slopeTo(temp[j+1])){//if 4 points are collinear
					result=true;
					prev=true;
					k++;
					if(k==1)
					start=j-1;//
				}	
				else{
					 result=false;
					 k=0;
				 }
				if(result==false&&prev==true){
					if(p.compareTo(temp[start])==-1){//if the linesegment is being included for the first time
						LineSegment u=new LineSegment(p,temp[j]);
						v.add(u); //adding the line segment
						prev=false;
					}	
						
				}
			}
			if(p.compareTo(temp[N-2])==0) //Duplicate points
			throw new IllegalArgumentException("Repeated arguments");
			if(p.compareTo(temp[N-1])==0)
			throw new IllegalArgumentException("Repeated arguments");
		}
		temp=null;//deleting the temporary array
	}
	public int numberOfSegments(){
		return v.size();
	}		
	
	public LineSegment[] segments(){
		Object ob[]=v.toArray();
		LineSegment ls[]=Arrays.copyOf(ob,ob.length,LineSegment[].class);
		return ls;
	}
	public static void main(String[] args){
		
		try{
		Scanner scanner = new Scanner(new File(args[0]));
		int N=scanner.nextInt();
		Point arr[]=new Point[N];
		for(int i=0;i<N;i++)
		arr[i]=new Point(scanner.nextInt(),scanner.nextInt());
		FastCollinearPoints b=new FastCollinearPoints(arr);
		//System.out.println(b.numberOfSegments());
		LineSegment ls[]=b.segments();
		for(LineSegment r:ls)
		r.printls();
	}
	catch(IOException ioex) {
        ioex.printStackTrace();
    
  }
	}
}			
				
		

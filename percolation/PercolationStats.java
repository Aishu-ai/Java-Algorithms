import java.lang.Math;
import java.util.*;

public class PercolationStats extends Percolation{
	private double[] res;//to store p* from each trial
	private int T;//no. of trials
	
	public PercolationStats(int n,int trials){
		super(1);//initialzing the parent constructor
		if(n<=0||trials<=0)
		throw new IllegalArgumentException(" not valid");
		else{
		res=new double[trials];
		T=trials;
		
		for(int i=0;i<trials;i++){
			res[i]=result(n);
			//Storing the results
		}
	}
	}
	//Opens the sites randomly until the grid percolates and returns the fraction of open sites
	private double result(int n){
		Percolation per=new Percolation(n);
		Random rand=new Random(System.currentTimeMillis());
		while(per.percolates()==false){
			int row=rand.nextInt(n)+1;
			int col=rand.nextInt(n)+1;
			per.open(row,col);
		}
		double frac=(double)per.numberOfOpensites()/(n*n);
		return frac;
	
			
		}
		//Mean of all fractions obtained which gives us a good estimation of p* (the threshold)
	public double mean(){
		double m=0;
		for(double x:res)
		m+=x;
		m/=T;
		return m;
	}
	public double stddev(){
		double d=0;
		double m=0;
		for(double x:res)
		m=(this.mean()-x)*(this.mean()-x)+m;
		m=m/(T-1);
		m=Math.sqrt(m);
		return m;
	}
	// low endpoint of 95% confidence interval
	public double confidenceLo(){
		double lo=this.mean()-1.96*this.stddev()/Math.sqrt(T);
		return lo;
	}
	// high endpoint of 95% confidence interval
	public double confidenceHi(){
		double lo=this.mean()+1.96*this.stddev()/Math.sqrt(T);
		return lo;
	}
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter n:");
		int n=scan.nextInt();
		System.out.println("Enter the number of trials:");
		int trials=scan.nextInt();
		PercolationStats pe=new PercolationStats(n,trials);
		System.out.print("mean                    = ");
		System.out.println(pe.mean());
		System.out.print("stddev                  = ");
		System.out.println(pe.stddev());
		System.out.print("95% confidence interval = ");
		System.out.println("["+pe.confidenceLo()+", "+pe.confidenceHi()+"]");
	}
}
		
		
			

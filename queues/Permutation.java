import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Permutation{
	public static void main(String[] args){
		try{
		int n=Integer.parseInt(args[0]);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		
		RandomizedQueue<String> r=new RandomizedQueue<//tring content = Files.readString(filepath);
		String use[]=s.split(" ");//splitting the string at blank spaces
		for(String st:use)
		r.enqueue(st);
		int j=r.size();
		//exiting if k is greater than size of queue	
		if(n>j){
			System.out.println("Only "+j+" elements are present.");
			System.exit(1);
		}	
		for(int i=0;i<n;i++)
		System.out.println(r.dequeue());//printing k random elements
	}
	catch (IOException ioex) {
        ioex.printStackTrace();
    
  }
}
}

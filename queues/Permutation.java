import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Permutation{
	public static void main(String[] args){
		try{
		int n=Integer.parseInt(args[0]);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		//Path filepath=Paths.get(s);
		RandomizedQueue<String> r=new RandomizedQueue<String>();
		//tring content = Files.readString(filepath);
		String use[]=s.split(" ");
		for(String st:use)
		r.enqueue(st);
		int j=r.size();
		if(n>j){
			System.out.println("Only "+j+" elements are present.");
			System.exit(1);
		}	
		for(int i=0;i<n;i++)
		System.out.println(r.dequeue());
	}
	catch (IOException ioex) {
        ioex.printStackTrace();
    
  }
}
}

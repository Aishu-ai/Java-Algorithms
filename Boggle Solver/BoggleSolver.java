import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class BoggleSolver{
	private final TST dictionary; //dictionary
	private Set<String>store; //to store possible words
	private boolean visited[]; // status of all dies
	private static void shuffleArray(String[] ar){
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			// Simple swap
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
  /* Reading the dictionary from the file*/
	public static BoggleSolver readFromFile(String dictionaryName)throws IOException{
		String[] result;
		Stream<String> stream = Files.lines(Paths.get(dictionaryName));
		result = stream.toArray(String[]::new);
		shuffleArray(result);
		return new BoggleSolver(result);
        
    }
      /*Reading dictionary from an array of strings*/ 
    public BoggleSolver(String[] dictionarySet){
		 dictionary=new TST();
		 store=new HashSet<String>(); //hashset
		 for(String s:dictionarySet)
		 dictionary.put(s); //inserting words into dictionary
	}   
	 /*Set containing all valid words*/
    public Iterable<String> getAllValidWords(BoggleBoard board){
		store.clear();
		visited= new boolean[board.rows()*board.cols()];//status array
		for(int i=0;i<board.rows();i++){
			for(int j=0;j<board.cols();j++)
			dictionary.searchWords(board,store,visited,i,j);
		}
	    return store;
	}
	/*Score secured*/
	public int scoreSecured(){
		int score=0;
		for(String s:store){
			if(s.length()<=4) //1 point for length<=4
			score+=1;
			else if(s.length()==5) //2 points for length=5
			score+=2;
			else if(s.length()==6) //3 points for length=6
			score+=3;
			else if(s.length()==7) //5 points for length=7
			score+=5;
			else
			score+=11; //11 points for length>=8
		}
		return score;
	}
	//Client code
	public static void main(String[] args){
		char[][] a =  {
           { 'A', 'T', 'E', 'E' },
           { 'A', 'P', 'Y', 'O' },
           { 'T', 'I', 'N', 'U' },
           { 'E', 'D', 'S', 'E' }
        };
        
        try{
        BoggleSolver solver=BoggleSolver.readFromFile("dictionary-algs4.txt"); //dictionary
        long startTime = System.currentTimeMillis(); //start time
        for(int i=0;i<1000;i++){
			BoggleBoard board = new BoggleBoard(); //1000 random boards
			System.out.print(i+1+": ");
			System.out.println(board); //printing board
			for(String s:solver.getAllValidWords(board))
			System.out.println(s); //printing possible words
			System.out.println(solver.scoreSecured()); //score secured
			System.out.println();
		}	
       long endTime = System.currentTimeMillis();
			System.out.println("Time to solve 1000 random boards(in seconds): "+(endTime - startTime)/1000.0);
       }
       /* Took an average of 1 second to solve 1000 random boards*/
       catch(IOException ioex){
		   ioex.printStackTrace();
		  } 
	} 
}       
			
			
		
	

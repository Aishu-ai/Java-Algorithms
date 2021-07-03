import java.util.*;
import java.io.*;

//class for constructing WordNet
public class WordNet{
	
	private Vector<Set<String>>store; //to store synsets
	private Vector<String>nouns_store; //to store all nouns
	private final Digraph D; //Digraph for hypernyms
	private int V; //no. of synsets
	public WordNet(String synsets,String hypernyms){
		store=new Vector<Set<String>>();
		nouns_store=new Vector<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(synsets))) {
			String line;
			int count=0;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String nouns[]=values[1].split(" ");//example file given
				Set<String>temp=new HashSet<String>(); //set for the nouns in the sysnset
				for(String str:nouns){
					temp.add(str);
					nouns_store.add(str);//add the nouns
				}
				store.add(temp);	
				count++;
			}
			V=count;
		}	
		catch(IOException ioex) {
        ioex.printStackTrace();
	}
		D=new Digraph(hypernyms);
	}
	public Iterable<String>nouns(){
		return nouns_store;
	}
	public boolean isNoun(String word){
		return nouns_store.contains(word);
	}
	public int distance(String nounA, String nounB){
		if(isNoun(nounA)&&isNoun(nounB)){
			Stack<Integer>v=new Stack<Integer>();
			Stack<Integer>w=new Stack<Integer>();
			for(int i=0;i<V;i++){
				if(store.get(i).contains(nounA))//storing the sysnsets where nounA appears
				v.add(i);
				if(store.get(i).contains(nounB))
				w.add(i);
			}
			SAP sap=new SAP(D);
			return sap.length(v,w);//sap length for set of synsets where nounA appears and nounB appears.
				
		}
		else
		throw new IllegalArgumentException();
	}
	//sap synset for nounA and nounB
	public String sap(String nounA, String nounB){
		if(isNoun(nounA)&&isNoun(nounB)){
			Stack<Integer>v=new Stack<Integer>();
			Stack<Integer>w=new Stack<Integer>();
			for(int i=0;i<V;i++){
				if(store.get(i).contains(nounA))
				v.add(i);
				if(store.get(i).contains(nounB))
				w.add(i);
			}
			SAP sap=new SAP(D);
			int t=sap.ancestor(v,w);
			StringBuilder str=new StringBuilder();
			for(String str1:store.get(t)){
				str.append(str1);
				str.append(" ");
			}
			return str.toString();	
				
		}
		else
		throw new IllegalArgumentException();
		
	}
	//unit testing
	public static void main(String[] args){
		WordNet net=new WordNet("synsets.txt","hypernyms.txt");
		System.out.println(net.distance("Antedon","Anthus"));
		System.out.println(net.sap("Abadan","Aberdare"));
	}			
}	
				
		
		
			
				
					
		

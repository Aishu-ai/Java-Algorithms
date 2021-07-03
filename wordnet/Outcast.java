
public class Outcast{
	private WordNet W;
	public Outcast(WordNet wordnet){
		W=wordnet;
	}
	public String outcast(String[] nouns){//to find outcast among the given set of nouns
		//Assuming all nouns are a part of WordNet nouns
		int max=0;
		String out="";
		for(int i=0;i<nouns.length;i++){
			String noun=nouns[i];
			int sum=0;
			for(int j=0;j<nouns.length;j++)	
			sum+=W.distance(noun,nouns[j]);
			if(sum>max){
				max=sum;
				out=noun;
			}
		}
		return out;//return the noun whose sum of sap lengths from other nouns is the maximum
	}
	
	//unit testing
	public static void main(String[] args){
		String[] strAr2 = {"Cyclosporeae","call_waiting","caliphate","Haldea","Aaron","lead_glass","Aachen"}; 			
		WordNet W=new WordNet("synsets.txt","hypernyms.txt");
		Outcast O=new Outcast(W);
		System.out.println(O.outcast(strAr2));
	}
}	

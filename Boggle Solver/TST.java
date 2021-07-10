import java.util.Set;

/*Ternary Search Tries for storing the dictionary*/
public class TST{
	private Node root;
	private class Node{
		private boolean isEnd; //specifies end of the word
		private char c;
		private Node left,mid,right;
	}
	public void put(String key){ //inserting word into trie
		root=put(root,key,true,0);
		
	}
	private Node put(Node x,String key,boolean val,int d){
		char c=key.charAt(d);
		if(x==null){ //if char is not in the trie
			x=new Node();
			x.c=c;
		}
		if(c<x.c) //if char is less than the current char
		x.left=put(x.left,key,val,d);
		else if(c>x.c)
		x.right=put(x.right,key,val,d);
		else if(d<key.length()-1) //its not the end of the word
		x.mid=put(x.mid,key,val,d+1);
		else
		x.isEnd=val; //its end of the word
		return x;
	}
	//search words in Boggle Board using TST
	public void searchWords(BoggleBoard b,Set<String>store,boolean visited[],int i,int j){
		String str=new String();
		search(b,root,str,store,visited,i,j);
	}	
	private void search(BoggleBoard b,Node x,String str,Set<String> store,boolean visited[],int i,int j){
		if(i<0||i>=b.rows()||j<0||j>=b.cols()||visited[i*b.cols()+j]==true||x==null) //return if indices are invalid/already visited/if node is null
		return;
		//if char is found
		if(x.c==b.getLetter(i,j)){
			visited[i*b.cols()+j]=true;
			StringBuilder temp=new StringBuilder();
			temp.append(Character.toString(x.c));
			if(x.c=='Q'){ //If char is Q, directly move to node containing U
				
				Node ptr=x.mid;
				while(ptr.c!='U'&&ptr!=null){
					if(ptr.c<'U')
					ptr=ptr.right;
					else
					ptr=ptr.left;
				}
				if(ptr!=null){
					x=ptr;
					temp.append("U");
				}
				
			}	
			
			if(x.isEnd&&(str+temp.toString()).length()>=3){ //if string length>=3
				if(!store.contains(str+temp.toString())) //no duplicate words
				store.add(str+temp.toString());
			}	
		//visiting its neighbours	
		search(b,x.mid,str+temp.toString(),store,visited,i-1,j);
		search(b,x.mid,str+temp.toString(),store,visited,i+1,j);
		search(b,x.mid,str+temp.toString(),store,visited,i,j-1);
		search(b,x.mid,str+temp.toString(),store,visited,i,j+1);
		search(b,x.mid,str+temp.toString(),store,visited,i-1,j-1);
		search(b,x.mid,str+temp.toString(),store,visited,i-1,j+1);
		search(b,x.mid,str+temp.toString(),store,visited,i+1,j-1);
		search(b,x.mid,str+temp.toString(),store,visited,i+1,j+1);
		visited[i*b.cols()+j]=false; //unmarking die after exhausting all its posibilities
		}
		//search for char in the trie
		else if(b.getLetter(i,j)<x.c) 
		search(b,x.left,str,store,visited,i,j);			
		else
		search(b,x.right,str,store,visited,i,j);
		
		
	}	
}	
		

<h1>WORDNET</h1>
<p>WordNet is a semantic lexicon for the English language that computational linguists and cognitive scientists use extensively. For example, WordNet was a key 
component in IBM’s Jeopardy-playing Watson computer system. WordNet groups words into sets of synonyms called synsets. For example, { AND circuit, AND gate } is a 
synset that represent a logical gate that fires only when all of its inputs fire. WordNet also describes semantic relationships between synsets. One such 
relationship is the is-a relationship, which connects a hyponym (more specific synset) to a hypernym (more general synset). For example, the synset 
  { gate, logic gate } is a hypernym of { AND circuit, AND gate } because an AND gate is a kind of logic gate. </p>
  <h3>The Wordnet digraph</h3>
 <p> In this implementation, a Wordnet digraph has been built: each vertex v is an integer that represents a synset, and each directed edge v→w represents that w is a 
 hypernym of v. The WordNet digraph is a rooted DAG: it is acyclic and has one vertex—the root—that is an ancestor of every other vertex. However, it is not 
  necessarily a tree because a synset can have more than one hypernym. A small subgraph of the WordNet digraph appears below.</p></br>
 <img src="https://coursera.cs.princeton.edu/algs4/assignments/wordnet/wordnet-event.png">
 <h5>The WordNet input file formats.</h5>
 <p>The files are in comma-separated values (CSV) format: each line contains a sequence of fields, separated by commas. </p>
 <ul>
  <li><i>List Of Synsets</i>: The file synsets.txt contains all noun synsets in WordNet, one per line. Line i of the file (counting from 0) contains the information for synset i. The first field is the synset id, which is always the integer i; the second field is the synonym set (or synset); and the third field is its dictionary definition (or gloss), which is not relevant to this assignment.</br>
 <img src="https://coursera.cs.princeton.edu/algs4/assignments/wordnet/wordnet-synsets.png">
 <p>For example, line 36 means that the synset { AND_circuit, AND_gate } has an id number of 36 and its gloss is a circuit in a computer that fires only when all of its inputs fire. The individual nouns that constitute a synset are separated by spaces. If a noun contains more than one word, the underscore character connects the words (and not the space character). </p>
  <li><i>List Of Hypernyms</i>: The file hypernyms.txt contains the hypernym relationships. Line i of the file (counting from 0) contains the hypernyms of synset i. The first field is the synset id, which is always the integer i; subsequent fields are the id numbers of the synset’s hypernyms. </br>
  <img src="https://coursera.cs.princeton.edu/algs4/assignments/wordnet/wordnet-hypernyms.png">
  <p>For example, line 36 means that synset 36 (AND_circuit AND_Gate) has 42338 (gate logic_gate) as its only hypernym. Line 34 means that synset 34 (AIDS acquired_immune_deficiency_syndrome) has two hypernyms: 47569 (immunodeficiency) and 48084 (infectious_disease). </p>
  <h5>Shortest ancestral path</h5>
  <p>An ancestral path between two vertices v and w in a digraph is a directed path from v to a common ancestor x, together with a directed path from w to the same ancestor x. A shortest ancestral path is an ancestral path of minimum total length. We refer to the common ancestor in a shortest ancestral path as a shortest common ancestor. Note also that an ancestral path is a path, but not a directed path. </p></br>
  <p>The notion of shortest common ancestor has been generalized to to subsets of vertices. A shortest ancestral path of two subsets of vertices A and B is a shortest ancestral path over all pairs of vertices v and w, with v in A and w in B. The figure below shows an example in which, for two subsets, red and blue, we have computed several (but not all) ancestral paths, including the shortest one.</p>
  <img src="https://coursera.cs.princeton.edu/algs4/assignments/wordnet/wordnet-sca-set.png">
  <h5>Measuring the semantic relatedness of two nouns</h5>
  <p>Semantic relatedness refers to the degree to which two concepts are related. For example, you consider George W. Bush and John F. Kennedy (two U.S. presidents) to be more closely related than George W. Bush and chimpanzee (two primates). It might not be clear whether George W. Bush and Eric Arthur Blair are more related than two arbitrary people. However, both George W. Bush and Eric Arthur Blair (a.k.a. George Orwell) are famous communicators and, therefore, closely related. </p>
  <p>The semantic relatedness of two WordNet nouns x and y has been defined as follows: </p>
  <ul>
  <li>A = set of synsets in which x appears </li>
  <li> B = set of synsets in which y appears</li>
  <li>distance(x, y) = length of shortest ancestral path of subsets A and B</li>
  <li> sca(x, y) = a shortest common ancestor of subsets A and B </li>
  </ul>
  <h5>Outcast detection.</h5>
  <p>Given a list of WordNet nouns x1, x2, ..., xn, which noun is the least related to the others? To identify an outcast, we compute the sum of the distances between each noun and every other one: </br>
  di   =   distance(xi, x1)   +   distance(xi, x2)   +   ...   +   distance(xi, xn) </br>
  and return a noun xt for which dt is maximum.</p>


  
   
 

<h3>WORDNET</h3>
<p>WordNet is a semantic lexicon for the English language that computational linguists and cognitive scientists use extensively. For example, WordNet was a key 
component in IBM’s Jeopardy-playing Watson computer system. WordNet groups words into sets of synonyms called synsets. For example, { AND circuit, AND gate } is a 
synset that represent a logical gate that fires only when all of its inputs fire. WordNet also describes semantic relationships between synsets. One such 
relationship is the is-a relationship, which connects a hyponym (more specific synset) to a hypernym (more general synset). For example, the synset 
  { gate, logic gate } is a hypernym of { AND circuit, AND gate } because an AND gate is a kind of logic gate. </p>
  <h5>The Wordnet digraph</h5>
 <p> In this project, a Wordnet digraph has been built:each vertex v is an integer that represents a synset, and each directed edge v→w represents that w is a 
 hypernym of v. The WordNet digraph is a rooted DAG: it is acyclic and has one vertex—the root—that is an ancestor of every other vertex. However, it is not 
  necessarily a tree because a synset can have more than one hypernym. A small subgraph of the WordNet digraph appears below.</p></br>
 <img src="https://coursera.cs.princeton.edu/algs4/assignments/wordnet/wordnet-event.png">

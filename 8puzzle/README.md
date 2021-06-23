<h1>Slider Puzzle</h1>
<h3>The Problem</h3>
<p>The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so
that they are in row-major order, using as few moves as possible. You are permitted to slide tiles either horizontally or vertically into the blank square. The 
 following image link shows a sequence of moves from an initial board (left) to the goal board (right).(<a href="https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/4moves.png">Image Link</a>)</p></br>
 <h3>Solution:A* Search</h3>
 <p>A solution to the 8-puzzle problem that illustrates a general artificial intelligence methodology is known as the <b>A* search algorithm.</b>We define a search 
  node of the game to be a board, the number of moves made to reach the board, and the previous search node. First, insert the initial search node 
 (the initial board, 0 moves, and a null previous search node) into a priority queue. Then, delete from the priority queue the search node with the minimum priority
 , and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node). Repeat this procedure until
  the search node dequeued corresponds to the goal board. </p></br>
 <p> The efficacy of this approach hinges on the choice of priority function for a search node. We consider two priority functions: <p>
  <ul>
   <li><b>The Hamming priority function</b> is the Hamming distance of a board plus the number of moves made so far to get to the search node. Intuitively, a search node
      with a small number of tiles in the wrong position is close to the goal, and we prefer a search node if has been reached using a small number of moves. </li>
   <li><b>The Manhattan priority function</b> is the Manhattan distance of a board plus the number of moves made so far to get to the search node. </li>
  </ul>
  <p>To solve the puzzle from a given search node on the priority queue, the total number of moves we need to make (including those already made) is at least its
  priority, using either the Hamming or Manhattan priority function. Why? Consequently, when the goal board is dequeued, we have discovered not only a sequence 
  of moves from the initial board to the goal board, but one that makes the fewest moves. </p></br>
  <h4>Game tree</h3>
  <p>One way to view the computation is as a game tree, where each search node is a node in the game tree and the children of a node correspond to its neighboring 
  search nodes. The root of the game tree is the initial search node; the internal nodes have already been processed; the leaf nodes are maintained in a priority 
  queue; at each step, the A* algorithm removes the node with the smallest priority from the priority queue and processes it (by adding its children to both the 
  game tree and the priority queue). </p></br>
  <p>For example, the following image link illustrates the game tree after each of the first three steps of running the A* search algorithm on a 3-by-3 puzzle using 
  the Manhattan priority function. <a href="https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/game-tree.png">Image Link</a></p>
  <h4>Two Optimizations</h4>
  <ul>
  <li><i>The critical optimization.</i> A* search has one annoying feature: search nodes corresponding to the same board are enqueued on the priority queue many
  times (e.g., the bottom-left search node in the game-tree diagram above). To reduce unnecessary exploration of useless search nodes, when considering the 
  neighbors of a search node, don’t enqueue a neighbor if its board is the same as the board of the previous search node in the game tree.</li>
  <li><i>Caching the Hamming and Manhattan priorities.</i> To avoid recomputing the Manhattan priority of a search node from scratch each time during various 
  priority queue operations, pre-compute its value when you construct the search node; save it in an instance variable; and return the saved value as needed. 
  This caching technique is broadly applicable: consider using it in any situation where you are recomputing the same quantity many times and for which computing
    that quantity is a bottleneck operation. </li>
  </ul>
  <h4>Detecting unsolvable boards</h4>
  <p>Not all initial boards can lead to the goal board by a sequence of moves. Such examples are given in two of the text files. The output obtained on the console
  is given in "output.txt"</p>
  




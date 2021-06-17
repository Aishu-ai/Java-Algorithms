<h3>Collinear Points</h3>
<p>Computer vision involves analyzing patterns in visual images and reconstructing the real-world objects that produced them. The process is often broken up 
into two phases: feature detection and pattern recognition. Feature detection involves selecting important features of the image; pattern recognition involves 
discovering patterns in the features. We will investigate a particularly clean pattern recognition problem involving points and line segments. This kind of pattern
recognition arises in many other applications such as statistical data analysis.</p></br>
<h5>The Problem</h5>
<p>Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.</p>
<h5>Solution</h5>
<p>Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.</p>
<ul>
  <li>Think of p as the origin. </li>
  <li>For each other point q, determine the slope it makes with p.</li>
  <li>Sort the points according to the slopes they makes with p. </li>
  <li>Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear. </li>
</ul>
<p>Applying this method for each of the n points in turn yields an efficient algorithm to the problem. The algorithm solves the problem because points that have 
  equal slopes with respect to p are collinear, and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.</p></br>
<p> The program gives maximal line segments containing 4 (or more) points exactly once. For example, if 5 points appear on a line segment in the order p→q→r→s→t,
  then only p→t is included not sub-segments p→s or q→t.</p></br>
  <h6>Performance</h6>
  <p>The order of growth of the running time of the program is n^2log n in the worst case and it uses space proportional to n plus the number of line segments 
  returned. FastCollinearPoints works properly even if the input has 5 or more collinear points.</p> 

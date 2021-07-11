<h1 align="center">PERCOLATION (USES WQUPC) -- Weighted QuickUnion Path Compression algorithm</h1>

<p>Given a composite systems comprised of randomly distributed insulating and metallic materials: we would like to calculate thefraction of the materials that need to be metallic so that the composite system is an electrical conductor. Similarly, for a given porous landscape with water on the surface (or oil below), we woould like to know under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface).An abstract process known as percolation is used to model such situations.

<h3 align="center">THE MODEL</h3>

<p>We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row.
In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. </br>
(For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top
to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a 
system that percolates lets water fill open sites, flowing from top to bottom.)<p> 

<img src="https://coursera.cs.princeton.edu/algs4/assignments/percolation/percolates-yes.png">
<img src="https://coursera.cs.princeton.edu/algs4/assignments/percolation/percolates-no.png"</br>

THE PROBLEM
*************************************
In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p
(and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1,the system percolates. The plots (in the links given below) show the site vacancy probability p versus the percolation probability for 20-by-20 random grid and 
100-by-100 random grid.</br>
https://coursera.cs.princeton.edu/algs4/assignments/percolation/percolation-threshold20.png  (for 20-by-20 random grid)</br>
https://coursera.cs.princeton.edu/algs4/assignments/percolation/percolation-threshold100.png  (for 100-ny-100 random grid)</br>
When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates, and when p > p*,
a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. We estimate the threshold using the following simulation.
************************************
MONTE CARLO SIMULATION
***********************************
To estimate the percolation threshold, consider the following computational experiment:</br>
a.Initialize all sites to be blocked.<br/>

b.Repeat the following until the system percolates</br>

  1.Choose a site uniformly at random among all blocked sites.</br>

  2.Open the site. </br>

c.The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold. </br>




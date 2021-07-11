<h1 align="center">SEAM CARVING</h1>
<p>Seam-carving is a content-aware image resizing technique where the image is reduced in size by one pixel of height (or width) at a time. A vertical seam in an 
image is a path of pixels connected from the top to the bottom with one pixel in each row; a horizontal seam is a path of pixels connected from the left to the right
with one pixel in each column.</p>
<p>Finding and removing a seam involves three parts:</p>
<ul>
  <li><h3>Notation</h3>
    <p>In image processing, pixel (x, y) refers to the pixel in column x and row y, with pixel (0, 0) at the upper left corner and pixel (W − 1, H − 1) at the bottom
      right corner.(opposite of the standard mathematical notation used in linear algebra where (i, j) refers to row i and column j and with Cartesian coordinates
      where (0, 0) is at the lower left corner.) The color of a pixel is represented in RGB space, using three integers between 0 and 255. </p></li>
  <li><h3>EnergyCalculation</h3>
    <p>The dual-gradient energy function is implemented for this project. The dual-gradient energy function of the input image used:</p>

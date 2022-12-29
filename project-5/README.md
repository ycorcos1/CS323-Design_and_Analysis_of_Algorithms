(C++) Quadtree: Implement Quadtree representation of a given image. To verify the correctness of the Quadtree your program produces, your program will create a new image based on the Quadtree your program produced. If your program works, then the new image should be identical to the input image.

You will be given two images (img1 and img2) to test your program.
Run your program twice, one on each test image.
For each image, print the image on the top of a blank page, then draw the quadtree of the image on the bottom of the page.

Input: A text file contains a binary image with header information. The first text-line is the header information: numRows numCols minVal maxVal follows by rows and columns of pixels, 0’s and 1’s. An example below is a binary image has four (4) rows and five (5) columns, minVal is 0 and maxVal is 1:

Outputs:
  - outFile1: The pre-order and post-order traversals of the quadtree (with captions)
  - outFile2: All the debugging outputs
  - outFile2: To print imgAry and newImgAry

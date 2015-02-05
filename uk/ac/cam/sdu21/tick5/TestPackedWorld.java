package uk.ac.cam.sdu21.tick5;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;


public class TestPackedWorld implements World {

 private int generation;
 private int width;
 private int height;
 private long cells;

 public TestPackedWorld() {
   width = 8;
   height = 8;
   generation=0;
   cells = 0;
   // set cells initially to 0 
 }

 protected TestPackedWorld(TestPackedWorld prev) {
   generation = prev.generation + 1;
   cells = 0;
   width = height = 8;

 }

 static private boolean sane(int col, int row) {
   return(col>=0&&col<8&&row>=0&&row<8);
 }

 public boolean getCell(int col, int row) { 
   if (sane(col,row))    
     return PackedLong.get(cells, 8*row + col);
   return false;
 }

 public void setCell(int col, int row, boolean alive) { 
    if (sane(col,row))
      cells = PackedLong.set(cells, 8*row+col,alive);

 }

 public int getWidth()  { 
   return width;
 }

 public int getHeight()  { 
   return height;
 }

 public int getGeneration()  { 
   return generation;
 }



 public int getPopulation()  { return 0; }




 public void print(Writer w)  { 
   PrintWriter pw = new PrintWriter(w);
   // See the Java documentation for PrintWriter
   //
   // use pw.print("something") to write to the writer
   // use pw.println("something") to write a newline
   //
   // you must always call pw.flush() at the end of this method
   // to force the PrintWriter to write to the terminal (if you
   // do not, then data may be buffered inside PrintWriter).
   for (int row = 0; row < height; row++) { 
      for (int col = 0; col <width; col++) {
            pw.print(getCell(col, row) ? "#" : "_"); 
      }
      pw.println(); 
   } 
   pw.flush();
}


 public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }

 private int countNeighbours(int col, int row) {
   final int[] r = new int[]{-1,-1,-1,0,1,1,1,0};
   final int[] c = new int[]{-1,0,1,1,1,0,-1,-1};
   int nr=0;
   for (int i=0; i<8; i++)
      if (getCell(col+r[i],row+c[i]))
         nr++;
   return nr;


}



 private boolean computeCell(int col,int row) {

    // liveCell is true if the cell at position (col,row) in world is live
    boolean liveCell = getCell(col, row);

    // neighbours is the number of live neighbours to cell (col,row)
    int neighbours = countNeighbours(col, row);

    // we will return this value at the end of the method to indicate whether 
    // cell (col,row) should be live in the next generation
    boolean nextCell = false;

    //A live cell with less than two neighbours dies (underpopulation)
    if (liveCell && neighbours < 2) {
        nextCell = false;
    }
 
    //A live cell with two or three neighbours lives (a balanced population)
    if (liveCell && (neighbours == 2 || neighbours == 3))
      nextCell = true;

    //A live cell with with more than three neighbours dies (overcrowding)
    if (liveCell && neighbours > 3)
      nextCell = false;

    //A dead cell with exactly three live neighbours comes alive
    if (!liveCell && neighbours == 3)
      nextCell = true;

    return nextCell;
}



 private TestPackedWorld nextGeneration() {
   //Construct a new TestArrayWorld object to hold the next generation:
   TestPackedWorld next = new TestPackedWorld(this);
   //Using for loops with "setCell" and "computeCell" to populate "world"


   for(int i=0; i<height; i++)
      for (int j=0; j<width; j++)
         if (computeCell(j,i))
            next.setCell(j,i,true);

   return next;
 }

 public World nextGeneration(int log2StepSize)  { 

   TestPackedWorld world = this;
   //repeat the statement in curly brackets 2^log2StepSize times
   for (int i=0; i<(1<<log2StepSize); i++) {
   
    world = world.nextGeneration();
   }

   return world;
 }
   
    
}
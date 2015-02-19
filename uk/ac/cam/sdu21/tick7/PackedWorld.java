package uk.ac.cam.sdu21.tick7;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;


public class PackedWorld extends WorldImpl {


 private long cells;

 public PackedWorld() {
   super(8,8);
   cells = 0;
   // set cells initially to 0 
 }

 protected PackedWorld(PackedWorld prev) {
   super(prev);
   cells = 0;
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



 protected PackedWorld nextGeneration() {
   //Construct a new TestArrayWorld object to hold the next generation:
   PackedWorld next = new PackedWorld(this);
   //Using for loops with "setCell" and "computeCell" to populate "world"


   for(int i=0; i<height; i++)
      for (int j=0; j<width; j++)
         if (computeCell(j,i))
            next.setCell(j,i,true);

   return next;
 }


    
}
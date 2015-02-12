package uk.ac.cam.sdu21.tick6;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;


public class ArrayWorld extends WorldImpl {


 private boolean[][] cells;

 public ArrayWorld(int w, int h) {
   super(w,h);
   cells = new boolean[height][width];
   // set cells to reference a new rectangular two-dimensional 
   //       boolean array of size height by width  
 }

 protected ArrayWorld(ArrayWorld prev) {
   super(prev);
   cells = new boolean[height][width];
   //set cells to reference a new rectangular two-dimensional 
   //       boolean array of size height by width  
 }

 public boolean getCell(int col, int row) { 
    if (row < 0 || row > cells.length - 1) return false;
    if (col < 0 || col > cells[row].length - 1) return false;

    return cells[row][col];
 }

 public void setCell(int col, int row, boolean alive) { 
   if (row>=0 && row<cells.length && col>=0 && col<cells[row].length)
         cells[row][col]=alive;
 }


 protected ArrayWorld nextGeneration() {
   //Construct a new TestArrayWorld object to hold the next generation:
   ArrayWorld next = new ArrayWorld(this);
   //Using for loops with "setCell" and "computeCell" to populate "world"


   for(int i=0; i<height; i++)
      for (int j=0; j<width; j++)
         if (computeCell(j,i))
            next.setCell(j,i,true);

   return next;
 }

    
}
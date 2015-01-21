package uk.ac.cam.sdu21.tick3;

public class Pattern {

 private String name;
 private String author;
 private int width;
 private int height;
 private int startCol;
 private int startRow;
 private String cells;

 public String getName() {
  return name;
 }

 public String getAuthor(){
	return author;
 }

 public int getWidth() {
	return width;
 }

 public int getHeight(){
	return height;
 }
  

 public int getStartCol(){
	return startCol;
 }

 public int getStartRow(){
	return startRow;
 }

 public String getCells(){
	return cells;
 }

 public Pattern(String format) {

 	String[] parsed_string = format.split(":");
 	name = parsed_string[0];
 	author = parsed_string[1];
 	width = Integer.parseInt(parsed_string[2]);
 	height = Integer.parseInt(parsed_string[3]);
 	startCol = Integer.parseInt(parsed_string[4]);
 	startRow = Integer.parseInt(parsed_string[5]);
 	cells = parsed_string[6];
 }

 public void initialise(boolean[][] world) {
 	String[] cell_rows = cells.split(" "); 
 	int i,j;
 	for(i=0; i<height; i++)
		for ( j=0; j<width; j++) 
			world[i][j]=false;
	 	
	for(i=startRow; i<startRow + cell_rows.length; i++ ){
	 	char[] line = cell_rows[i-startRow].toCharArray();
	 	for(j=startCol; j<startCol + line.length; j++)
	 		if(line[j-startCol]=='1')
	 			world[i][j]=true;
	 }
 }
} 
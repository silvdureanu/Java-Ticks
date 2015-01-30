package uk.ac.cam.sdu21.tick4;

public class Pattern {

 private String name;
 private String author;
 private int width;
 private int height;
 private int startCol;
 private int startRow;
 private String cells;
 private String data;

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

 public String getData(){
 	return data;
 }

 public Pattern(String format) throws PatternFormatException {
 	try{
 	int i,j;
 	data=format;
 	if(format.length() == 0)
 		throw new PatternFormatException();

 	String[] parsed_string = format.split(":");

 	if (parsed_string.length != 7)
 		throw new PatternFormatException();

 	name = parsed_string[0];
 	author = parsed_string[1];
 	width = Integer.parseInt(parsed_string[2]);

 	if(width<1)
 		throw new PatternFormatException();

 	height = Integer.parseInt(parsed_string[3]);

 	if(height<1)
 		throw new PatternFormatException();

 	startCol = Integer.parseInt(parsed_string[4]);
 	startRow = Integer.parseInt(parsed_string[5]);
 	cells = parsed_string[6];


	String[] cell_rows = cells.split(" ");

	int nr=cell_rows[0].length();
	for(i=0; i<cell_rows.length; i++) {
		char[] line = cell_rows[i].toCharArray();

	 	/*if(line.length != nr)
	 		throw new PatternFormatException();*/

	 	for(j=0; j<line.length; j++)
	 		if(!(line[j]=='0' || line[j]=='1'))
	 			throw new PatternFormatException();
	 }

	}
	catch(NumberFormatException d) { throw new PatternFormatException();}
 }

 public void initialise(boolean[][] world) throws PatternFormatException{

 	String[] cell_rows = cells.split(" "); 

 	int nr = cell_rows[0].length();

 	int i,j;
 	for(i=0; i<height; i++)
		for ( j=0; j<width; j++) 
			world[i][j]=false; 
	 	
	for(i=startRow; i<startRow + cell_rows.length; i++ ){
	 	char[] line = cell_rows[i-startRow].toCharArray();

	 	if(line.length != nr)
	 		throw new PatternFormatException();

	 	for(j=startCol; j<startCol + line.length; j++) {
	 		if(!(line[j-startCol]=='0' || line[j-startCol]=='1'))
	 			throw new PatternFormatException();

	 		if(line[j-startCol]=='1')
	 			world[i][j]=true;
	 	}
	 
	}

 }
} 

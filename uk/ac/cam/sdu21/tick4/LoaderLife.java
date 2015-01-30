package uk.ac.cam.sdu21.tick4; 
import java.util.List;
import java.util.LinkedList;

public class LoaderLife {



	public static boolean getCell(boolean[][] world, int col, int row) {
	 if (row < 0 || row > world.length - 1) return false;
	 if (col < 0 || col > world[row].length - 1) return false;

	 return world[row][col];
	}

	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if (row>=0 && row<world.length && col>=0 && col<world[row].length)
			world[row][col]=value;		
	}

	public static void print(boolean[][] world) { 
 		System.out.println("-"); 
 		for (int row = 0; row < world.length; row++) { 
  			for (int col = 0; col < world[row].length; col++) {
   				System.out.print(getCell(world, col, row) ? "#" : "_"); 
  			}
  			System.out.println(); 
 		} 
	}


	public static int countNeighbours(boolean[][] world, int col, int row) {
		final int[] r = new int[]{-1,-1,-1,0,1,1,1,0};
		final int[] c = new int[]{-1,0,1,1,1,0,-1,-1};
		int nr=0;
		for (int i=0; i<8; i++)
			if (getCell(world,col+r[i],row+c[i]))
				nr++;
		return nr;

	
	}


	
	public static boolean computeCell(boolean[][] world,int col,int row) {

		 // liveCell is true if the cell at position (col,row) in world is live
		 boolean liveCell = getCell(world, col, row);
	
		 // neighbours is the number of live neighbours to cell (col,row)
		 int neighbours = countNeighbours(world, col, row);

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

	public static boolean[][] nextGeneration(boolean[][] world) {
		boolean next[][] = new boolean[world.length][world[0].length];
		for(int i=0; i<world.length; i++)
			for (int j=0; j<world[i].length; j++)
				if (computeCell(world,j,i))
					setCell(next,j,i,true);
		return next;
	}




	public static void play(boolean[][] world) throws Exception {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}


	public static void main(String[] args) throws Exception, PatternFormatException, NumberFormatException {
	try{
	 if(args.length==0)
	 	throw new PatternFormatException();


 	List<Pattern> patterns;
 	if(args[0].startsWith("http://")) 
 		patterns=PatternLoader.loadFromURL(args[0]);
 	else 
 		patterns=PatternLoader.loadFromDisk(args[0]);
 	int l=0;
 	if(args.length == 1) {
 		for(Pattern p: patterns) {
 			System.out.println(l + ") "+p.getData());
 			l++;
 		}
 	}
 	else if(args.length ==2) {
 		int index = Integer.parseInt(args[1]);
 		if (index<0 || index>= patterns.size())
 			throw new NumberFormatException();
 		Pattern p = patterns.get(index);

	 	boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
		p.initialise(world);
		play(world);

 	}

	else throw new PatternFormatException();	 





	}
	catch(PatternFormatException e) {
		System.out.println("Bad input");
	}

	catch(NumberFormatException e) {
		System.out.println("Bad number");
	}
	}
}

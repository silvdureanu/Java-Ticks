package uk.ac.cam.sdu21.tick2; 

public class TinyLife {


	static private boolean sane(int col, int row) {
		return(col>=0&&col<8&&row>=0&&row<8);
	}

	public static boolean getCell(long world, int col, int row) {
		if (sane(col,row))		
			return PackedLong.get(world, 8*row + col);
		return false;

	}

	public static long setCell(long world, int col, int row, boolean value) {
		if (sane(col,row))
			return PackedLong.set(world, 8*row+col,value);
		return world;
		
	}

	public static void print(long world) { 
 		System.out.println("-"); 
 		for (int row = 0; row < 8; row++) { 
  			for (int col = 0; col < 8; col++) {
   				System.out.print(getCell(world, col, row) ? "#" : "_"); 
  			}
  			System.out.println(); 
 		} 
	}


	public static int countNeighbours(long world, int col, int row) {
		final int[] r = new int[]{-1,-1,-1,0,1,1,1,0};
		final int[] c = new int[]{-1,0,1,1,1,0,-1,-1};
		int nr=0;
		for (int i=0; i<8; i++)
			if (getCell(world,col+r[i],row+c[i]))
				nr++;
		return nr;

	
	}


	
	public static boolean computeCell(long world,int col,int row) {

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

	public static long nextGeneration(long world) {
		long next=0L;
		for(int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if (computeCell(world,i,j))
					next=setCell(next,i,j,true);
		return next;
	}




	public static void play(long world) throws Exception {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}


	public static void main(String[] args) throws Exception {
		play(Long.decode(args[0]));
	}

}

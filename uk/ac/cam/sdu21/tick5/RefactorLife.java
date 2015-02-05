package uk.ac.cam.sdu21.tick5; 
import java.util.List;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;

public class RefactorLife {


	public static void play(World world) throws Exception {
		int userResponse = 0;
		while (userResponse != 'q') {
			Writer w = new OutputStreamWriter(System.out);
			world.print(w);
			userResponse = System.in.read();
			world = world.nextGeneration(0);
		}
	}


	public static void main(String[] args) throws Exception, PatternFormatException, NumberFormatException {
	try{
	 if(args.length==0)
	 	throw new PatternFormatException();


 	List<Pattern> patterns;

 	if(args.length == 1) {

	 	if(args[0].startsWith("http://")) 
	 		patterns=PatternLoader.loadFromURL(args[0]);
	 	else 
	 		patterns=PatternLoader.loadFromDisk(args[0]);
	 	int l=0;

 		for(Pattern p: patterns) {
 			System.out.println(l + ") "+p.getData());
 			l++;
 		}
 	}
 	else if (args.length <4){

 		String worldType = args.length == 3 ? args[0] : "--array"; 

 		int pattern_index,index;

 		if (args.length == 2) {
 			pattern_index = 0;
 			index = Integer.parseInt(args[1]);
 		}
 		else {
 			pattern_index = 1;
 			index = Integer.parseInt(args[2]);
 		}


	 	if(args[pattern_index].startsWith("http://")) 
	 		patterns=PatternLoader.loadFromURL(args[pattern_index]);
	 	else 
	 		patterns=PatternLoader.loadFromDisk(args[pattern_index]);


 		if (index<0 || index>= patterns.size())
 			throw new NumberFormatException();

 		Pattern p = patterns.get(index); 


		World world = null;
		if (worldType.equals("--array")) {
		 world = new ArrayWorld(p.getWidth(),p.getHeight());
		} else if (worldType.equals("--long")) {
		 world = new PackedWorld(p.getWidth(),p.getHeight()); 
		} else {
		  System.out.println("Invalid world type");
		  return; 
		}
			 	
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

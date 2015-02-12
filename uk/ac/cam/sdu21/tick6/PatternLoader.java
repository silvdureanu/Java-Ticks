package uk.ac.cam.sdu21.tick6;

import java.net.*;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;


public class PatternLoader {

	private static void add_pattern(List<Pattern> rl, String data)  {
		try{
			Pattern q = new Pattern(data);
			rl.add(q);
		}

		catch(PatternFormatException e) {
			//System.out.println("Bad input in file");
		}
		catch(NumberFormatException e) {
			//System.out.println("Bad number in file");
		}


	}
	public static List<Pattern> load(Reader r) throws IOException{
	try {
		List<Pattern> resultList = new LinkedList <Pattern>();
		BufferedReader buf = new BufferedReader(r);
		String data = buf.readLine();

		while(data!=null) {
			add_pattern(resultList,data);
			data=buf.readLine();
		}

		return resultList;

	}

	catch(IOException e) {
		System.out.println("Bad File");
		return new LinkedList <Pattern>();
	}


	}

	public static List<Pattern> loadFromURL(String url) throws IOException, PatternFormatException  {

	 URL destination = new URL(url);
	 URLConnection conn = destination.openConnection();
	 return load(new InputStreamReader(conn.getInputStream())); }


		 

	

	public static List<Pattern> loadFromDisk(String filename) throws IOException, PatternFormatException  {

	 return load(new FileReader(filename));
	}

 

	
} 
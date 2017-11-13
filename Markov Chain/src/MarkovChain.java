/* MarkovChain.java
 * Author: Jared Wolfe
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class MarkovChain {
	
	// visit http://www.textfiles.com/etext/ for free, legal books in .txt format
	// ENTER .TXT FILE DESTINATION BELOW
	final static String FILENAME = "//home//jared//Downloads//twocities";

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		try {
			Random r = new Random();
			String lines = readFile(FILENAME); // lines is the file in one string
			Map<String, ArrayList<String>> m = new HashMap<String, ArrayList<String>>();
			String[] splitStr = lines.trim().split("\\s+"); //splitStr is an Array of all lines in the file
			
			try{
			for(int i = 0; i < splitStr.length; i++)
			{
				StringBuilder sb = new StringBuilder();
				sb.append(splitStr[i] + " " + splitStr[i + 1]); // sb contains two words
				if(m.containsKey(sb.toString())) // if this two-word key exists, update the array value to contain
												 // the word following the two words in sb
				{
					ArrayList<String> update = m.get(sb.toString());
					update.add(splitStr[i+2]);
					m.put(sb.toString(), update);
				}
				else // if not, create the key in the map and add the word in a new array value
				{
					ArrayList<String> newList = new ArrayList<String>();
					newList.add(splitStr[i+2]);
					m.put(sb.toString(), newList);
				}
			}
			}catch(Exception e)
			{
				// nothing
			}
			
			int k = r.nextInt(10000); // may need to adjust for smaller text files
			String phrase = splitStr[k] + " " + splitStr[k + 1]; //randomly select pair of 2 of the first thousand words
			System.out.print(phrase + " "); // print these words
			String input = "x"; //ignore
			while(input != "") {
				for (int j = 0; j < 10; j++) // j is the words per line to print, can be adjusted
				{
					ArrayList<String> newWords = m.get(phrase); // get the array for the two words
					String nextWord = newWords.get(r.nextInt(newWords.size())); // get a random word from the array value
					System.out.print(nextWord + " "); // print the word
					String[] f = phrase.trim().split(" ");
					f[0] = f[1];
					f[1] = nextWord;
					phrase = f[0] + " " + f[1]; // adjusts the phrase to now contain the second original word and the new word
				}
				System.out.println("");
				input = s.nextLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// reads in the file
	private static String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
}

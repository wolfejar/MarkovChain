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
	final static String FILENAME = "";
	
	public static void main(String[] args){
		
		try {
			Random r = new Random();
			List<String> lines = Files.readAllLines(Paths.get(FILENAME));
			ArrayList<String> allWords = new ArrayList<String>();
			Map<String, ArrayList<String>> m = new HashMap<String, ArrayList<String>>();
			for(String s: lines)
			{
				String[] splitStr = s.trim().split("\\s+");
				for(String s2: splitStr)
				{
					if(!(allWords.contains(s2)))
					{
						allWords.add(s2);
					}
				}
			}
			for(String s: allWords)
			{
				m.put(s, new ArrayList<String>());
			}
			for(int i = 0; i < lines.size(); i ++)
			{
				String[] splitStr = lines.get(i).trim().split("\\s+");
				for(int j = 0; j < splitStr.length; j ++)
				{
					ArrayList<String> update = m.get(splitStr[j]);
					try
					{
						update.add(splitStr[j + 1]);
						m.put(splitStr[j], update);
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						try{
						update.add(lines.get(i + 1).substring(0, lines.get(i + 1).indexOf(' ')));
						m.put(splitStr[j], update);
						}
						catch(IndexOutOfBoundsException p)
						{
							//nothing
						}
					}
				}
			}
			String word = allWords.get(r.nextInt(allWords.size()));
			for(int i = 0; i < 5; i ++) // i is the number of lines to print, can be adjusted
			{
				for(int j = 0; j < 20; j ++) // j is the words per line to print, can be adjusted
				{
					ArrayList<String> newWords = m.get(word);
					String word2 = newWords.get(r.nextInt(newWords.size()));
					System.out.print(word + " ");
					word = word2;
				}
				System.out.println("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

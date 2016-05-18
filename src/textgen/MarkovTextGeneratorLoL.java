package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if (sourceText.equals("")){
			starter = "";
		}else{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[^ ]+");
		Matcher m = tokSplitter.matcher(sourceText);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		this.starter = tokens.get(0);
		ListNode prev = new ListNode(this.starter);
		this.wordList.add(prev);
		int mm = tokens.size();
		for (int i=0;i<mm;i++){
			int j = i+1;
			if (i==mm-1){
				j = i-mm+1;
			}
			String prevword = tokens.get(i);
			String current = tokens.get(j);
			int nn = this.wordList.size();
			boolean shit = false;
			int kkk = 0;
			for (int h=0;h<nn;h++){
				if (this.wordList.get(h).getWord().equals(prevword)){
					shit = true;
					kkk = h;
				}
			}
			if (shit){
				this.wordList.get(kkk).addNextWord(current);
			} else{
				ListNode haha = new ListNode(prevword);
				haha.addNextWord(current);
				this.wordList.add(haha);
				
			}
			
		}
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    if (starter.equals("")||numWords == 0){
	    	return "";
	    }else{
		String uyy = starter;
	    ArrayList<String> output = new ArrayList<String>();
	    output.add(uyy+" ");
	    int i = 1;
	    String w = new String();
	    while (i<numWords){
	    	for (int h=0;h<this.wordList.size();h++){
	    		if (this.wordList.get(h).getWord().equals(uyy)){
	    			w = this.wordList.get(h).getRandomNextWord(rnGenerator);
	    			output.add(w+" ");  			
	    		}
	    	}
	    	uyy = w;
	    	i++;    	
	    }   
	    String ccc = new String();
	    for (String y:output){
	    	ccc+=y;
	    }
		return ccc;
	    }
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		if (sourceText.equals("")){
			starter = "";
		}else{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[^ ]+");
		Matcher m = tokSplitter.matcher(sourceText);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		this.starter = tokens.get(0);
		this.wordList.clear();
		ListNode prev = new ListNode(this.starter);
		this.wordList.add(prev);
		int mm = tokens.size();
		for (int i=0;i<mm;i++){
			int j = i+1;
			if (i==mm-1){
				j = i-mm+1;
			}
			String prevword = tokens.get(i);
			String current = tokens.get(j);
			int nn = this.wordList.size();
			boolean shit = false;
			int kkk = 0;
			for (int h=0;h<nn;h++){
				if (this.wordList.get(h).getWord().equals(prevword)){
					shit = true;
					kkk = h;
				}
			}
			if (shit){
				this.wordList.get(kkk).addNextWord(current);
			} else{
				ListNode haha = new ListNode(prevword);
				haha.addNextWord(current);
				this.wordList.add(haha);
				
			}
			
		}
		}
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(0));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int bbc = this.nextWords.size()-1;
		int abc = generator.nextInt(bbc+1)+0;
		String jjj = this.nextWords.get(abc);
	    return jjj;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}



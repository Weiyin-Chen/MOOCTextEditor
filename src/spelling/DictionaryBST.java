package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
    public DictionaryBST(){
    	dict = new TreeSet<String>();
    }
	
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String lword = word.toLowerCase();
    	dict.add(lword);
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	int kk = dict.size();
        return kk;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	String ss = s.toLowerCase(); 
    	if (dict.contains(ss)){
    		return true;
    	}else{
    		return false;
    	}
    }

}

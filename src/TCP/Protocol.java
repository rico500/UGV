package TCP;

public class Protocol {
	
	String baseString;
	String processedString;
	boolean isProcessed;
	
	public Protocol(String baseString, boolean isProcessed){
			this.baseString = baseString;
			this.isProcessed = isProcessed;
	}
	
		
	/**
	 * public method process(String, boolean)
	 * Takes string and transforms it to be sent or read.
	 * If the String is not processed, the elements of the string must be seperated by delimiters ("|").
	 * If the String is already processed, the method returns a backwards 
	 * processed string replacing the delimiters by spaces.
	 * @return processed String
	 * */
	
	public String process(){
		
		if(!isProcessed){
			processedString = replaceSpace(" "+removeSpace(baseString)+" ");
			
			return processedString;
			
		}else{
			processedString = baseString.replace("|", " ");
			
			return processedString;
		}
		
	}
	
	public String getBaseString(){
		
		return baseString;
	}
	
	public String getProcessedString(){
		
		return processedString;
	}
	
	
	  /**
     * Static Method replaceSpace(String)
     * Remplace les spaces par '|'
     * @param s - la chaine a trater
     * @return the new String
     */

    private static String replaceSpace(String s) {
	
	return s.replace(' ', '|');
	
    }
	
	/**
     * Static Method removeSpace(String)
     * Enleve les espaces en trop de la chaine
     * @param s - la chaine a traiter
     * @return the new String
     */

    private static String removeSpace(String s) {
	
	String res = "";
	int i = 0;
	
	while(i<s.length()) {
	    
	    if ( s.charAt(i) != ' ' || ( s.charAt(i) == ' ' && s.charAt(i-1) != ' ' ) ) {
		res += s.charAt(i);
	    }
	    i++;
	}

	return res;
    }
}

package com.nfcfriend.matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class TokensMatcherUtil {
	
	//private List<String> tokens;	
	private Map<String, Pattern> patterns = new HashMap<String, Pattern>();
	
	public TokensMatcherUtil(List<String> tokens){
		//List<String> tokens = Collections.unmodifiableList(tokens);
		for(String token : tokens){
			Pattern pattern = Pattern.compile(".*(" + token.toLowerCase() + ").*", Pattern.CASE_INSENSITIVE);
			patterns.put(token, pattern);
		}
	}
	
	public List<String> getAssociatedTokens(String text){
		List<String> out = new ArrayList<String>();
		for(String token : patterns.keySet()){
			if(patterns.get(token).matcher(text).matches()) out.add(token); 
		}
		return out;
	}

}

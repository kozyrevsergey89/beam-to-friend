package com.nfcfriend.matcher;

import java.util.*;
import java.util.regex.Pattern;

public final class TokensMatcherUtil {

	private Map<String, Pattern> patterns = new HashMap<String, Pattern>();

    public TokensMatcherUtil(){
        this(Arrays.asList("Apple", "Kyivstar", "Samsung", "GE", "Amazon", "NTT Group", "Toyota", "Wells Fargo", "Bank of America", "McDonald's", "Shell", "Intel", "BMW", "Tesco", "Mersedes-Benz", "Mitsubishi", "Chase", "Ford", "Pepsi", "Oracle", "Nestle", "UPS", "Nike", "Adidas", "Walt Disney", "Nokia", "DOU", "Hackaton", "хакатон"));
    }

	public TokensMatcherUtil(List<String> tokens){

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

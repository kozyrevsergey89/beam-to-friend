package com.nfcfriend.matcher;

import com.nfcfriend.matcher.model.FacebookTextable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextMatcher implements Matcher<FacebookTextable>{
	
	private TokensMatcherUtil matcherUtil;

	public void setMatcherUtil(TokensMatcherUtil matcherUtil) {
		this.matcherUtil = matcherUtil;
	}

	/**
	 * Finds all records from others matched with mine
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FacebookTextable> findMatches(
			List<FacebookTextable> mine,
			List<FacebookTextable> others){		
		
		List<FacebookTextable> out = new ArrayList<FacebookTextable>();
						
		Set<String> tokens = new HashSet<String>();
		for(FacebookTextable item : mine){
			tokens.addAll(matcherUtil.getAssociatedTokens(item.getText()));
		}
		
		//Multimap<String, String> storage = ArrayListMultimap.create(); 
		for(FacebookTextable item : others){
			List<String> othersTokens = matcherUtil.getAssociatedTokens(item.getText());
			List<String> tokensClone = new ArrayList(tokens);
			tokensClone.retainAll(othersTokens);
            if(tokensClone.size() > 0)
			    out.add(item);
		}

		return Collections.unmodifiableList(out);		
		
	}

}

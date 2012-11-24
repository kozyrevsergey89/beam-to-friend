package com.nfcfriend.matcher;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.nfcfriend.matcher.model.FacebookTextable;
import com.nfcfriend.matcher.model.MatchedResult;

import java.util.ArrayList;
import java.util.List;

public class TextMatcher implements Matcher<FacebookTextable>{
	
	private TokensMatcherUtil matcherUtil;

	public void setMatcherUtil(TokensMatcherUtil matcherUtil) {
		this.matcherUtil = matcherUtil;
	}

	/**
	 * Finds all records from others matched with mine
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MatchedResult<FacebookTextable> findMatches(
			List<FacebookTextable> mine,
			List<FacebookTextable> yours){

        MatchedResult<FacebookTextable> out = new MatchedResult<FacebookTextable>();

		Multimap<String, FacebookTextable> tokens = ArrayListMultimap.create();
		for(FacebookTextable item : mine){
            for(String token : matcherUtil.getAssociatedTokens(item.getText())){
			    tokens.put(token, item);
            }
		}

		for(FacebookTextable item : yours){
			List<String> othersTokens = matcherUtil.getAssociatedTokens(item.getText());
			List<String> tokensClone = new ArrayList(tokens.keySet());
			tokensClone.retainAll(othersTokens);
            if(tokensClone.size() > 0){
			    out.getMine().addAll(tokens.get(null));
            }
		}

		return out;
		
	}

}

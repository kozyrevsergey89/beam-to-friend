package com.nfcfriend.matcher;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.nfcfriend.matcher.model.FacebookStory;
import com.nfcfriend.jsonhandler.entity.MatchedResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TextMatcher implements Matcher<Map<String, MatchedResult<FacebookStory>>, FacebookStory>{
	
	private TokensMatcherUtil matcherUtil;

	public void setMatcherUtil(TokensMatcherUtil matcherUtil) {
		this.matcherUtil = matcherUtil;
	}

	/**
	 * Finds all records from yours matched with mine
     * returns
     * map: token -> result (list-mine, list-yours) with comments
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, MatchedResult<FacebookStory>> findMatches(
			List<FacebookStory> mine,
			List<FacebookStory> yours){

        if(matcherUtil == null)
            throw new RuntimeException("TextMatcher is not prepared, configure it using setMatcherUtil method");

        ConcurrentMap<String, MatchedResult<FacebookStory>> out =
                                        new ConcurrentHashMap<String, MatchedResult<FacebookStory>>();

		Multimap<String, FacebookStory> tokens = ArrayListMultimap.create();
		for(FacebookStory item : mine){
            for(String token : matcherUtil.getAssociatedTokens(item.getStory())){
			    tokens.put(token, item);
            }
		}

		for(FacebookStory item : yours){
			List<String> othersTokens = matcherUtil.getAssociatedTokens(item.getStory());
			List<String> tokensClone = new ArrayList(tokens.keySet());
			tokensClone.retainAll(othersTokens);
            if(tokensClone.size() > 0){
                for(String both : tokensClone){
                    if(out.containsKey(both)){
			            out.get(both).getMine().addAll(tokens.get(both));
                        out.get(both).getYours().add(item);
                    }
                }
            }
		}

		return out;
		
	}

}

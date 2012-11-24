package com.nfcfriend.matcher;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.nfcfriend.matcher.model.FacebookTextable;
import com.nfcfriend.jsonhandler.entity.MatchedResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TextMatcher implements Matcher<Map<String, MatchedResult<FacebookTextable>>, FacebookTextable>{
	
	private TokensMatcherUtil matcherUtil;

	public void setMatcherUtil(TokensMatcherUtil matcherUtil) {
		this.matcherUtil = matcherUtil;
	}

	/**
	 * Finds all records from others matched with mine
     * returns
     * map: token -> result (list-mine, list-yours) with comments
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, MatchedResult<FacebookTextable>> findMatches(
			List<FacebookTextable> mine,
			List<FacebookTextable> yours){

        ConcurrentMap<String, MatchedResult<FacebookTextable>> out =
                                        new ConcurrentHashMap<String, MatchedResult<FacebookTextable>>();

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
                for(String both : tokensClone){
                    if(out.containsKey(both)){
			            out.get(both).getMine().addAll(tokens.get(both));
                        out.get(both).getOthers().add(item);
                    }
                }
            }
		}

		return out;
		
	}

}

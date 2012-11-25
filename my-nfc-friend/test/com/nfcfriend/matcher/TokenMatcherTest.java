package com.nfcfriend.matcher;

import java.util.Arrays;
import java.util.Map;

import com.nfcfriend.jsonhandler.entity.MatchedResult;
import com.nfcfriend.jsonhandler.entity.FacebookStory;

public class TokenMatcherTest {
	
	public static void main(String [] args){
		TextMatcher tm = new TextMatcher();
		tm.setMatcherUtil(new TokensMatcherUtil(Arrays.asList("Apple")));

        Map<String, MatchedResult<FacebookStory>> matches = tm.findMatches(
                Arrays.asList(textable("Some buulshit comment regarging apple crp"), textable("I like apples")),
				Arrays.asList(textable("apples are good"), textable("non app about")));

        assert matches.get("Apple").getMine().size() == 2;
        assert matches.get("Apple").getYours().size() == 1;

	}
	
	private static FacebookStory textable (final String t){
		return new FacebookStory(){
			
			@Override
			public String getStory(){
				return t;
			}

        };
	}

}
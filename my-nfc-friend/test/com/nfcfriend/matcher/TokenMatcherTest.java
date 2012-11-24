package com.nfcfriend.matcher;

import java.util.Arrays;

import com.nfcfriend.matcher.model.FacebookTextable;

public class TokenMatcherTest {
	
	public static void main(String [] args){
		TextMatcher tm = new TextMatcher();
		tm.setMatcherUtil(new TokensMatcherUtil(Arrays.asList("Apple")));
		
		Object o = tm.findMatches(
                Arrays.asList(textable("Some buulshit comment regarging apple crp"), textable("I like apples")),
				Arrays.asList(textable("apples are good"), textable("non app about")));
		
	}
	
	private static FacebookTextable textable (final String t){
		return new FacebookTextable(){
			
			@Override
			public String getText(){
				return t;
			}

        };
	}

}
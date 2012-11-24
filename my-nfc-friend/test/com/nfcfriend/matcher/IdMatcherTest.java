package com.nfcfriend.matcher;

import com.nfcfriend.matcher.model.FacebookIdentifiable;
import com.nfcfriend.matcher.model.FacebookTextable;
import com.nfcfriend.matcher.model.MatchedResult;

import java.util.Arrays;

public class IdMatcherTest {
	
	public static void main(String [] args){
		IdMatcher im = new IdMatcher();

		MatchedResult<FacebookIdentifiable> ident = im.findMatches(
                Arrays.asList(identifiable("12345"), identifiable("222222"), identifiable("11111")),
				Arrays.asList(identifiable("11111"), identifiable("12345"), identifiable("12345")));

        assert ident.getMine().size() == 2;
        assert ident.getOthers().size() == 2;
		
	}
	
	private static FacebookIdentifiable identifiable (final String id){
		return new FacebookIdentifiable(){
			
			@Override
			public String getId(){
				return id;
			}

        };
	}

}
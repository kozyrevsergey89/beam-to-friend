package com.nfcfriend.matcher;

import com.nfcfriend.jsonhandler.entity.FacebookIdentifiable;

import java.util.Arrays;
import java.util.List;

public class IdMatcherTest {
	
	public static void main(String [] args){
		IdMatcher im = new IdMatcher();

		List<FacebookIdentifiable> ident = im.findMatches(
                Arrays.asList(identifiable("12345"), identifiable("2222"), identifiable("11111")),
				Arrays.asList(identifiable("11111"), identifiable("12345"), identifiable("12345")));

        assert ident.size() == 2;
        assert ident.size() == 2;
		
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
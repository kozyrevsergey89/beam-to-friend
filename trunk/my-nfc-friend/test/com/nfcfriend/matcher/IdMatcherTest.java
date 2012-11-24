package com.nfcfriend.matcher;

import com.nfcfriend.jsonhandler.entity.FacebookIdentifiable;

import java.util.Arrays;
import java.util.List;

public class IdMatcherTest {
	
	public static void main(String [] args){
		IdMatcher im = new IdMatcher();

		List<FacebookIdentifiable> ident = im.findMatches(
                Arrays.asList(identifiable(12345L), identifiable(2222L), identifiable(11111L)),
				Arrays.asList(identifiable(11111L), identifiable(12345L), identifiable(12345L)));

        assert ident.size() == 2;
        assert ident.size() == 2;
		
	}
	
	private static FacebookIdentifiable identifiable (final Long id){
		return new FacebookIdentifiable(){
			
			@Override
			public Long getId(){
				return id;
			}

        };
	}

}
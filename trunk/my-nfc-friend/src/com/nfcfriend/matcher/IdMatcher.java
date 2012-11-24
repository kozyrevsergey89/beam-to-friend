package com.nfcfriend.matcher;

import java.util.*;

import com.nfcfriend.matcher.model.FacebookIdentifiable;
import com.nfcfriend.matcher.model.MatchedResult;

public class IdMatcher implements Matcher<FacebookIdentifiable> {
	
	public MatchedResult<FacebookIdentifiable> findMatches(
			List<FacebookIdentifiable> mine,
			List<FacebookIdentifiable> yours){

        MatchedResult<FacebookIdentifiable> out = new MatchedResult<FacebookIdentifiable>();

        Map<String, FacebookIdentifiable> othersMap = new HashMap<String, FacebookIdentifiable>(yours.size());
        for(FacebookIdentifiable id : yours){
            othersMap.put(id.getId(), id);
        }

        for(FacebookIdentifiable f : mine){
            if(othersMap.containsKey(f.getId())){
                out.getMine().add(f);
                out.getOthers().add(othersMap.get(f.getId()));
            }
        }
		return out;
		
	}
}

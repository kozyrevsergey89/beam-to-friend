package com.nfcfriend.matcher;

import java.util.*;

import com.nfcfriend.matcher.model.FacebookIdentifiable;

public class IdMatcher implements Matcher<FacebookIdentifiable> {
	
	public List<FacebookIdentifiable> findMatches(
			List<FacebookIdentifiable> mine,
			List<FacebookIdentifiable> others){

        List<FacebookIdentifiable> out = new ArrayList<FacebookIdentifiable>();
        Map<String, FacebookIdentifiable> othersMap = new HashMap<String, FacebookIdentifiable>(others.size());

        for(FacebookIdentifiable id : others){
            othersMap.put(id.getId(), id);
        }

        for(FacebookIdentifiable f : mine){
            if(othersMap.containsKey(f.getId())){
                out.add(othersMap.get(f.getId()));
            }
        }
		return Collections.unmodifiableList(out);
		
	}
}

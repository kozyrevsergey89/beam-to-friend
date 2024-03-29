package com.nfcfriend.matcher;

import java.util.*;

import com.nfcfriend.jsonhandler.entity.FacebookIdentifiable;

public class IdMatcher implements Matcher<List<FacebookIdentifiable>, FacebookIdentifiable> {

    @Override
	public List<FacebookIdentifiable> findMatches(
			List<FacebookIdentifiable> mine,
			List<FacebookIdentifiable> yours){

        List<FacebookIdentifiable> out = new ArrayList<FacebookIdentifiable>(mine.size());

        Map<String, FacebookIdentifiable> othersMap = new HashMap<String, FacebookIdentifiable>(yours.size());
        for(FacebookIdentifiable id : yours){
            othersMap.put(id.getId(), id);
        }

        for(FacebookIdentifiable f : mine){
            if(othersMap.containsKey(f.getId())){
                out.add(f);
            }
        }
		return Collections.unmodifiableList(out);
	}


}

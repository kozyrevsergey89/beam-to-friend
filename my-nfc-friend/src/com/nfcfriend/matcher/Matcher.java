package com.nfcfriend.matcher;


import com.nfcfriend.jsonhandler.entity.FacebookIdentifiable;

import java.util.List;


public interface Matcher <R, T> {
	
	public R findMatches(
			List<T> mine,
			List<T> yours);

}

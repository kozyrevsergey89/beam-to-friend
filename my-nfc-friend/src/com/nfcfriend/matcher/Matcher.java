package com.nfcfriend.matcher;

import com.nfcfriend.matcher.model.MatchedResult;

import java.util.List;


public interface Matcher <R, T> {
	
	public R findMatches(
			List<T> mine,
			List<T> yours);

}

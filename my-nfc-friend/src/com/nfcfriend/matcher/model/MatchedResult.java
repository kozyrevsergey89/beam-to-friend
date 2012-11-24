package com.nfcfriend.matcher.model;

import java.util.ArrayList;
import java.util.List;

public class MatchedResult <T> {

    public List<T> mine = new ArrayList<T>();
    public List<T> others = new ArrayList<T>();

    public MatchedResult() {
    }

    public List<T> getMine() {
        return mine;
    }

    public List<T> getOthers() {
        return others;
    }

}

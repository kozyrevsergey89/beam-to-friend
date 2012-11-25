package com.nfcfriend.jsonhandler.entity;

import java.util.ArrayList;
import java.util.List;

public class MatchedResult <T> {

    public List<T> mine = new ArrayList<T>();
    public List<T> yours = new ArrayList<T>();

    public MatchedResult() {
    }

    public List<T> getMine() {
        return mine;
    }

    public List<T> getYours() {
        return yours;
    }

    @Override
    public String toString() {
        return "MatchedResult{" +
                "mine=" + mine +
                ", yours=" + yours +
                '}';
    }
}

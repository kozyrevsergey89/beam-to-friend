package com.nfcfriend.matcher;

import com.nfcfriend.jsonhandler.entity.MatchedResult;
import com.nfcfriend.jsonhandler.entity.Photo;

import java.util.List;

/**
 * @author Maxim Galushka
 * @since DouHack 2012
 */
public class PhotoMatcher implements Matcher<MatchedResult<Photo>, Photo> {

    /**
     * This returns 2 lists inside matched object
     *
     * Matcher(mine, yours)
     * mine - list of my photos where I marked you
     * yours - list of your photos where you marked me
     *
     * @param mine all mine photos
     * @param yours all your photos
     * @return mathed list - see above
     */
    @Override
    public MatchedResult<Photo> findMatches(List<Photo> mine, List<Photo> yours) {

        MatchedResult<Photo> out = new MatchedResult<Photo>();
        if(mine == null || mine.size() == 0) return out;
        if(yours == null || yours.size() == 0) return out;

        String myId = mine.get(0).getAuthorId();
        String yourId = yours.get(0).getAuthorId();

        if(myId == null || "".equals(myId.trim()) || yourId == null || "".equals(yourId.trim())) return out;

        fillOut(out.getMine(), mine, yourId);
        fillOut(out.getYours(), yours, myId);

        return out;
    }

    private void fillOut(List<Photo> out, List<Photo> in, String id){
        for(Photo minePhoto : in){
            for(String tag : minePhoto.getTagz()){
                if(tag.equals(id)){
                    out.add(minePhoto);
                }
            }
        }
    }
}

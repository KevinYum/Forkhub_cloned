package com.github.mobile.ui.comment;

/**
 * Created by Sindy on 2/13/17.
 */

public class CommentBuilder {
    public RawCommentFragment rcf(String comments){
        RawCommentFragment rawcomm = new RawCommentFragment();
        rawcomm.setText(comments);
        return rawcomm;
    }
    public RenderedCommentFragment rencf(){
        return new RenderedCommentFragment();
    }
}

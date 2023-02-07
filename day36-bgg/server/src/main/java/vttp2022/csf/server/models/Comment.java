package vttp2022.csf.server.models;

import org.bson.Document;

public class Comment {
    private String commentId;
    private String user;
    private int rating;
    private String text;

    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public static Comment create(Document doc){
        Comment comment = new Comment();
        comment.setCommentId(doc.getString("c_id"));
        comment.setUser(doc.getString("user"));
        comment.setRating(doc.getInteger("rating", 0));
        comment.setText(doc.getString("text"));
        return comment;
    }
}

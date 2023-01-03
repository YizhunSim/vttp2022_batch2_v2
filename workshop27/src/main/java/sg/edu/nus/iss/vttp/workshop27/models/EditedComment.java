package sg.edu.nus.iss.vttp.workshop27.models;

import java.time.LocalDateTime;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class EditedComment {
    private Integer rating;
    private String comment;
    private LocalDateTime posted;

    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getPosted() {
        return posted;
    }
    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
                .add("rating", this.getRating())
                .add("comment", this.getComment())
                .add("posted", this.getPosted() != null ? this.getPosted().toString() : "")
                .build();
    }
    
}

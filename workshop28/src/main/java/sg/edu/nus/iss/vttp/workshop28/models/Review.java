package sg.edu.nus.iss.vttp.workshop28.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review extends EditedComment{
    private String _id;
    private String user;
    private Integer gameId;
    private String boardGame;
    private Boolean isEdited;
    private List<EditedComment> edited;

    public Review(){

    }

    public Review(String user, Integer rating, String comment, Integer gameId, String boardGame){
        this.user = user;
        super.setRating(rating);
        super.setComment(comment);
        this.gameId = gameId;
        this.boardGame = boardGame;
        super.setPosted(LocalDateTime.now());
        this.isEdited = false;
        this.edited = new LinkedList<>();
    }
    
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getGameId() {
        return gameId;
    }
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    public String getBoardGame() {
        return boardGame;
    }
    public void setBoardGame(String boardGame) {
        this.boardGame = boardGame;
    }
    public List<EditedComment> getEdited() {
        return edited;
    }
    public void setEdited(List<EditedComment> edited) {
        this.edited = edited;
    }
    public Boolean getIsEdited() {
        return isEdited;
    }

    public void setIsEdited(Boolean isEdited) {
        this.isEdited = isEdited;
    }

    public static Review create(Document d){
        System.out.println("Review Model: " + d);
        Review r = new Review();
        r.set_id(d.getObjectId("_id").toString());
        r.setUser(d.getString("user"));
        r.setRating(d.getInteger("rating"));
        r.setComment(d.getString("comment"));
        r.setGameId(d.getInteger("gameId"));
        LocalDateTime lPosted =  Instant.ofEpochMilli(d.getDate("posted").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        r.setPosted(lPosted);
        r.setBoardGame(d.getString("boardGame"));

        return r;
    }

    public JsonObject toJSON(){
        List<JsonObject> editedArray = this.getEdited().stream().map(c -> c.toJSON()).toList();
        return Json.createObjectBuilder()
                .add("_id", this.get_id())
                .add("user", this.getUser())
                .add("rating", this.getRating())
                .add("comment", this.getComment())
                .add("gameId", this.getGameId())
                .add("posted", this.getPosted().toString())
                .add("boardGame", this.getBoardGame())
                .add("idEdited", editedArray.size() > 0 ? true : false)
                .add("edited", editedArray.toString())
                .build();
    }

    public JsonObject toJSONR(){
        return Json.createObjectBuilder()
                .add("_id", this.getGameId())
                .add("name", this.getBoardGame())
                .add("rating", this.getRating())
                .add("user", this.getUser())
                .add("comment", this.getComment())
                .add("review_id", this.get_id().toString())
                .build();
    }
    
}

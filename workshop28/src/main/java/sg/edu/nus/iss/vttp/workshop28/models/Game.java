package sg.edu.nus.iss.vttp.workshop28.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Game {
    // private String _id;
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;
    private String timestamp;
    private List<String> reviews = new LinkedList<>();

    // public String get_id() {
    // return _id;
    // }

    // public void set_id(String _id) {
    // this._id = _id;
    // }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsersRated() {
        return users_rated;
    }

    public void setUsersRated(Integer usersRated) {
        this.users_rated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Game [gid= "
                .concat("" + this.gid)
                .concat(", name=")
                .concat(this.name)
                .concat(", year=")
                .concat("" + this.year)
                .concat(", ranking=")
                .concat("" + this.ranking)
                .concat(", usersRated=")
                .concat("" + this.users_rated)
                .concat(", url=")
                .concat(this.url)
                .concat(", image=")
                .concat(this.image)

                .concat("]");
    }

    public static Game create(Document d) {
        System.out.println("Game - create: " + d);
        Game g = new Game();
        List<Object> reviewsIdArrList = (List<Object>) d.get("reviews");
        System.out.println("Game - Create on List: " + reviewsIdArrList);
        
        List<String> newReviewsId = new LinkedList<>();
        for (Object a : reviewsIdArrList) {
            ObjectId oa = (ObjectId) a;
            newReviewsId.add("/review/" + oa.toString());
        }

        g.setGid(d.getInteger("gid"));
        g.setName(d.getString("name"));
        g.setYear(d.getInteger("year"));
        g.setRanking(d.getInteger("ranking"));
        g.setUsersRated(d.getInteger("users_rated"));
        g.setUrl(d.getString("url"));
        g.setImage(d.getString("image"));
        g.setTimestamp(d.getDate("timestamp").toString());
        g.setReviews(newReviewsId);
        return g;
    }

    public JsonObject toJSON() {
        JsonArray reviewsJ = null;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String review : getReviews()) {
            arrayBuilder.add(review);
        }

        reviewsJ = arrayBuilder.build();
        return Json.createObjectBuilder()
                .add("gid", getGid())
                .add("name", getName())
                .add("year", getYear() != null ? "" + getYear() : "")
                .add("ranking", getRanking() != null ? "" + getRanking() : "")
                .add("users_rated", getUsersRated() != null ? "" + getUsersRated() : "")
                .add("url", getUrl() != null ? "" + getUrl() : "")
                .add("image", getImage() != null ? "" + getImage() : "")
                .add("timestamp", getTimestamp() != null ? "" + getTimestamp() : "")
                .add("reviews", reviewsJ.toString())
                .build();
    }

}

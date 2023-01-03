package sg.edu.nus.iss.vttp.workshop22.models;

import java.io.ByteArrayInputStream;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class RSVP {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private DateTime confirmationDate;
    private String comments;
    private Integer totalCount;

    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public DateTime getConfirmationDate() {
        return confirmationDate;
    }
    public void setConfirmationDate(DateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    // Deserialize
    public static RSVP create(String jsonStr) throws Exception {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(jsonStr.getBytes()));
        return create(reader.readObject());
    }

    // Serialize
    public static RSVP create(JsonObject readObject){
        final RSVP rsvp = new RSVP();
        rsvp.setName(readObject.getString("name"));
        rsvp.setEmail(readObject.getString("email"));
        rsvp.setPhone(readObject.getString("phone"));
        rsvp.setConfirmationDate(new DateTime(Instant.parse(readObject.getString("confirmation_date"))));
        rsvp.setComments(readObject.getString("comments"));
        return rsvp;
    }

    // Serialize
    public static RSVP create(SqlRowSet sqlObject){
        final RSVP rsvp = new RSVP();
        rsvp.setId(sqlObject.getInt("id"));
        rsvp.setName(sqlObject.getString("name"));
        rsvp.setEmail(sqlObject.getString("email"));
        rsvp.setPhone(sqlObject.getString("phone"));
        rsvp.setConfirmationDate(new DateTime(
            DateTimeFormat.forPattern("dd/MM/yyyy")
                    .parseDateTime(sqlObject.getString("confirmation_date"))));
        rsvp.setComments(sqlObject.getString("comments"));
        return rsvp;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("name", getName())
                .add("email", getEmail())
                .add("phone", getPhone())
                .add("confirmation_date", getConfirmationDate() != null ? getConfirmationDate().toString() : "")
                .add("comments", getComments() != null ? getComments().toString() : "")
                .build();
    }
}

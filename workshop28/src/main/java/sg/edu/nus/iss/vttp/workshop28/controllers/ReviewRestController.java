package sg.edu.nus.iss.vttp.workshop28.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.vttp.workshop28.models.EditedComment;
import sg.edu.nus.iss.vttp.workshop28.models.Review;
import sg.edu.nus.iss.vttp.workshop28.services.ReviewService;

@RestController
@RequestMapping(path="/api/review")
public class ReviewRestController {
    @Autowired
    private ReviewService reviewService;

    @PutMapping(path="{_id}")
    public ResponseEntity<String> updateEdited(@PathVariable String _id, @RequestBody EditedComment editedComment){
        JsonObject result = null;
        Review upsertReview = reviewService.upsertEdited(_id, editedComment);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("review", upsertReview.toJSON());
        result = builder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path="{_id}")
    public ResponseEntity<String> retrieveReviewById(@PathVariable String _id){
        JsonObject result = null;
        Review review = reviewService.retrieveReviewById(_id);
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();

        objBuilder.add("review",review.toJSON());
        result = objBuilder.build();

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
    }

}

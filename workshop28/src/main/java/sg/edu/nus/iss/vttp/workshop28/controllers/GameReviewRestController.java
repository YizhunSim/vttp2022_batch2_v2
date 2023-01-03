package sg.edu.nus.iss.vttp.workshop28.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.vttp.workshop28.models.Game;
import sg.edu.nus.iss.vttp.workshop28.models.Review;
import sg.edu.nus.iss.vttp.workshop28.services.ReviewService;

@RestController
@RequestMapping(path = "/game")
public class GameReviewRestController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "{gameId}/reviews")
    public ResponseEntity<String> getReviewHistory(@PathVariable String gameId){
        JsonObject result = null;
        Optional<Game> gameResult = reviewService.aggregateReviews(gameId);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("review", gameResult.get().toJSON());
        result = builder.build();
        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
    }

    @GetMapping(path = "/{sortDirection}")
    public ResponseEntity<String> getReviewByUser(@PathVariable String sortDirection, @RequestParam(name = "user") String user){
        JsonObject result = null;
        Optional<List<Review>> reviewResults = reviewService.retreiveReviewsByUserRating(user, sortDirection);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("rating", sortDirection);
        builder.add("games", reviewResults.get().stream().map(r -> r.toJSONR()).toList().toString());
        result = builder.build();
        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
    }



}

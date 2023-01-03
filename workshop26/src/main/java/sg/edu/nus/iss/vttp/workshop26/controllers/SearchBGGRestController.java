package sg.edu.nus.iss.vttp.workshop26.controllers;

import java.util.List;

import org.joda.time.DateTime;
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
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.vttp.workshop26.models.Comment;
import sg.edu.nus.iss.vttp.workshop26.models.Game;
import sg.edu.nus.iss.vttp.workshop26.models.Games;
import sg.edu.nus.iss.vttp.workshop26.services.SearchBggService;

@RestController
@RequestMapping(path = "/api/bgg")
public class SearchBGGRestController {
    @Autowired
    private SearchBggService searchBGGService;

    @GetMapping(path = "/game")
    public ResponseEntity<String> getAllGames(@RequestParam Integer limit, @RequestParam Integer offset) {
        List<Game> gamesResult = searchBGGService.searchAllGames(limit, offset);
        JsonObject result = null;
        JsonObjectBuilder builder = Json.createObjectBuilder();

        Games gs = new Games();
        gs.setGames(gamesResult);
        gs.setLimit(limit);
        gs.setOffset(offset);
        gs.setTotal(gamesResult.size());
        gs.setTimestamp(DateTime.now());
        builder.add("bgg", gs.toJson());

        result = builder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path = "/games/rank")
    public ResponseEntity<String> getGameByRanking() {
        JsonArray result = null;
        List<Game> results = searchBGGService.getGamesByRank();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        // results.stream().map(g -> arrBuilder.add(g.toJson()));
        // result = arrBuilder.build();
    
        for (Game g : results)
            arrBuilder.add(g.toJson());

        result = arrBuilder.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path = "/games/{gameId}")
    public ResponseEntity<String> getGameDetailsById(@PathVariable Integer gameId) {
        JsonObject result = null;
        Game gameDetails = searchBGGService.getGameDetailsById(gameId);
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();

        objBuilder.add("game", gameDetails.toJson());
        result = objBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping(path="/comment")
    public ResponseEntity<String> searchComment(@RequestParam String q, @RequestParam Integer limit, @RequestParam Integer offset){
        JsonArray results = null;
        List<Comment> commentResults = searchBGGService.searchCommentByKeyword(q, limit, offset);
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (Comment c : commentResults){
            builder.add(c.toJson());
        }
        
        results = builder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(results.toString());
    }
}

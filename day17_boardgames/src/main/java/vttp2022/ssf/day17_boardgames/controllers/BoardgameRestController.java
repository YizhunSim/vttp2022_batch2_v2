package vttp2022.ssf.day17_boardgames.controllers;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp2022.ssf.day17_boardgames.models.Boardgame;
import vttp2022.ssf.day17_boardgames.services.BoardgameService;

@RestController
@RequestMapping("/boardgame")
public class BoardgameRestController {

  @Autowired
  private BoardgameService bgService;

  @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getBoardGame(@PathVariable("id") String id, Model model){
    String payload = bgService.getSingleBoardGame(id);
    if (payload == null){
      JsonObject errResponse = Json.createObjectBuilder()
                      .add("error", "id %s not found".formatted(id)).build();
      payload = errResponse.toString();
      // Return 400
      return ResponseEntity
            //.status(HttpStatus.BAD_REQUEST)
            .badRequest() // 400
            .body(payload);
    } else{
    // Convert Payload to JsonObject
    // Convert the String to a Reader
    Reader strReader = new StringReader(payload);
    // Create a JsonReader from reader
    JsonReader jsonReader = Json.createReader(strReader);
    JsonObject boardgameResult = jsonReader.readObject();

    System.out.println("boardgameResult: " + boardgameResult);
    Boardgame boardgame = new Boardgame();
    boardgame = boardgame.create(boardgameResult);
    System.out.println("getBoardGame: " + boardgame);

    // Create the response payload
    JsonObject response = boardgame.toJson(boardgame);
    return ResponseEntity.ok(response.toString());
    }
  }
}

package sg.edu.nus.iss.vttp.workshop22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp.workshop22.models.RSVP;
import sg.edu.nus.iss.vttp.workshop22.services.RSVPService;

@RestController
@RequestMapping(path = "/api")
public class RSVPRestController {
    @Autowired
    private RSVPService rsvpService;
    
    @GetMapping(path="/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRSVP(@RequestParam(required=false) String q) throws Exception{
        List<RSVP> rsvps = rsvpService.getAllRSVP(q);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (RSVP r : rsvps){
            arrBuilder.add(r.toJSON());
        }
        JsonArray result = arrBuilder.build();
        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
    }

    @GetMapping(path = "/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchRSVPByName(@RequestParam String q) throws Exception {

        List<RSVP> allRsvp = rsvpService.searchRSVPByName("%" + q + "%");

        if (allRsvp.size()  <= 0) {
            return ResponseEntity
                    .status(404)
                    .body(Json.createObjectBuilder()
                    .add("error", "No RSVP records found for " + q) .build().toString());
        }

        // Build the result
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (RSVP r : allRsvp)
            arrBuilder.add(r.toJSON());
        JsonArray result = arrBuilder.build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());

    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> countRSVP(){
        JsonObject resp;
        Integer totalCntRsvps = rsvpService.getTotalRsvp();

        resp = Json.createObjectBuilder()
                .add("total_count", totalCntRsvps)
                .build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(resp.toString());
    }

   
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertRSVP(@RequestBody String json) throws Exception{
        RSVP rsvpResult = null;
        RSVP rsvp =  RSVP.create(json);
        rsvpResult = rsvpService.insertRSVP(rsvp);

        JsonObject respObj = Json.createObjectBuilder()
                            .add("rsvpId", rsvpResult.getId())
                            .build();

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(respObj.toString());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRSVP(@RequestBody String json) throws Exception{
        RSVP rsvp =  RSVP.create(json);
        boolean updateFlag = rsvpService.updateRSVP(rsvp);

        JsonObject respObj = Json.createObjectBuilder()
                            .add("updated", updateFlag)
                            .build();

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(respObj.toString());
    }


}

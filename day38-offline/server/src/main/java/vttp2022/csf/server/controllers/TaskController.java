package vttp2022.csf.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.csf.server.models.Task;
import vttp2022.csf.server.services.TodoService;

import java.io.StringReader;
import java.util.List;

@Controller
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
	@Autowired
	private TodoService todoSvc;

    @PostMapping(path="/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postTask(@RequestBody String payload) {

        System.out.println(">>> payload: " + payload);
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray jsonArr = reader.readArray();

        List<Task> tasks = jsonArr.stream()
				.map(v -> v.asJsonObject())
				.map(v -> Task.create(v))
				// method reference
				//.map(Task::create)
				.toList();

		todoSvc.addTask(tasks);

		JsonObject response = Json.createObjectBuilder()
		.add("insertCount", tasks.size())
		.build();

        // return ResponseEntity.ok(newContact.toJson().toString());
         return ResponseEntity.ok(response.toString());
    }


}

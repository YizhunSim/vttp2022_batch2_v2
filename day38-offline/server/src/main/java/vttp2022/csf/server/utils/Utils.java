package vttp2022.csf.server.utils;

import java.io.StringReader;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.csf.server.models.Task;

public class Utils {
    public static JsonArray toJsonArray(String str) {
		JsonReader reader = Json.createReader(new StringReader(str));
		return reader.readArray();
	}

	// public static Task toTask(JsonArray json) {
	// 	List<Task> tasks = json.asJsonArray()
	// 		.stream()
			
	// 		.map(v -> v.asJsonObject())
	// 		.map(Utils::toTaskItem)
	// 		.
			
	// 	// Order order = new Order();
	// 	// order.setName(json.getString("name"));
	// 	// order.setLineItems(lineItems);
	// 	// return order;
	// }

	public static Task toTaskItem(JsonObject json) {
		Task task = new Task();
		task.setId(json.getString("id"));
		task.setTask(json.getString("task"));
		task.setPriority(json.getInt("priority"));
		task.setDueDate(json.getString("dueDate"));
		return task;
	}

	// public static Order toOrder(JsonObject json) {
	// 	List<LineItem> lineItems = json.getJsonArray("lineItems")
	// 		.stream()
	// 		.map(v -> v.asJsonObject())
	// 		.map(Utils::toLineItem)
	// 		.toList();
	// 	Order order = new Order();
	// 	order.setName(json.getString("name"));
	// 	order.setLineItems(lineItems);
	// 	return order;
	// }

	// public static LineItem toLineItem(JsonObject json) {
	// 	LineItem lineItem = new LineItem();
	// 	lineItem.setItem(json.getString("item"));
	// 	lineItem.setQuantity(json.getInt("quantity"));
	// 	return lineItem;
	// }
}

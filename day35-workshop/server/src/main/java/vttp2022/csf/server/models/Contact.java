package vttp2022.csf.server.models;

import org.springframework.util.MultiValueMap;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Contact {
    public String name;
    public Integer phone;
    public String email;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPhone() {
        return phone;
    }
    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public JsonObject toJson() {
		JsonObjectBuilder objBuilder = Json.createObjectBuilder()
				.add("name", name)
				.add("phone", phone)
                .add("email", email);

		return objBuilder.build();
	}

    public static Contact create(MultiValueMap<String, String> form) {
		Contact contact = new Contact();
		contact.setName(form.getFirst("name"));
		contact.setEmail(form.getFirst("email"));
		contact.setPhone(Integer.valueOf(form.getFirst("phone")));
		return contact;
	}
    
}

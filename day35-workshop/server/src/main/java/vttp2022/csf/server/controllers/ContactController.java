package vttp2022.csf.server.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp2022.csf.server.models.Contact;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
// @CrossOrigin(origins="*") // Add CORS header to the response
public class ContactController {
    
    @PostMapping(value = "/contact", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> contactForm(@RequestBody MultiValueMap<String, String> form){
        System.out.println(">>>> contact form: "+ form );

        Contact newContact = Contact.create(form);
        
        return ResponseEntity.ok(newContact.toJson().toString());
    }

}

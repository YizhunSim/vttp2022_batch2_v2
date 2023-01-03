package sg.edu.nus.iss.vttp.workshop28.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.vttp.workshop28.models.Review;
import sg.edu.nus.iss.vttp.workshop28.services.ReviewService;

@Controller
@RequestMapping(path="/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public String postReview(@RequestBody MultiValueMap<String, String> form, Model model){

        String username = form.getFirst("user");
        String rating = form.getFirst("rating");
        String comment = form.getFirst("comment");
        String gameId = form.getFirst("gameId");
        String boardGameName = form.getFirst("name");

        Review r = new Review(username, Integer.parseInt(rating), comment, Integer.parseInt(gameId), boardGameName);

        Review insertedReview = reviewService.insertReview(r);
        model.addAttribute("review", insertedReview);
        return "review_result";
    }

}

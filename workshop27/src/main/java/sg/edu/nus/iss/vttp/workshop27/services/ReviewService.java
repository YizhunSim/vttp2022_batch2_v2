package sg.edu.nus.iss.vttp.workshop27.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp.workshop27.models.EditedComment;
import sg.edu.nus.iss.vttp.workshop27.models.Review;
import sg.edu.nus.iss.vttp.workshop27.repositories.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review insertReview(Review r){
        return reviewRepository.insertReview(r);
    }

    public Review upsertEdited(String _id, EditedComment editedComment){
        return reviewRepository.upsertEdited(_id, editedComment);
    }

    public Review retrieveReviewById(String rid){
        return reviewRepository.getReviewById(rid);
    }
}

package sg.edu.nus.iss.vttp.workshop28.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp.workshop28.models.EditedComment;
import sg.edu.nus.iss.vttp.workshop28.models.Game;
import sg.edu.nus.iss.vttp.workshop28.models.Review;
import sg.edu.nus.iss.vttp.workshop28.repositories.ReviewRepository;

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

    public Optional<Game> aggregateReviews(String gameId) {
        return reviewRepository.aggregateReviews(gameId);
    }

    public Optional<List<Review>> retreiveReviewsByUserRating(String user, String sortDirection) {
        return reviewRepository.retreiveReviewsByUserRating(user, sortDirection);
    }
}

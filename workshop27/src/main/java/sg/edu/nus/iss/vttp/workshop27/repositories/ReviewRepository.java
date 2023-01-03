package sg.edu.nus.iss.vttp.workshop27.repositories;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp.workshop27.models.EditedComment;
import sg.edu.nus.iss.vttp.workshop27.models.Review;

@Repository
public class ReviewRepository {
    private static final String REVIEWS_COL = "reviews";

    @Autowired 
    private MongoTemplate mongoTemplate;

    public Review insertReview(Review r){
        return mongoTemplate.insert(r, "reviews");
    }

    public Review upsertEdited(String _id, EditedComment editedComment){
        ObjectId objectId = new ObjectId(_id);
        Review existingReview = mongoTemplate.findById(objectId, Review.class, REVIEWS_COL);

        // Existing
        if (existingReview != null){
            EditedComment ec = new EditedComment();
            ec.setComment(editedComment.getComment());
            ec.setRating(editedComment.getRating());
            ec.setPosted(LocalDateTime.now());

            if (existingReview.getEdited() != null){
                existingReview.getEdited().add(ec);
            }else{
                List<EditedComment> editedArrayComment = new LinkedList<>();
                editedArrayComment.add(editedComment);
                existingReview.setEdited(editedArrayComment);
            }
            mongoTemplate.save(existingReview, "reviews");
        }
        return existingReview;
    }

    public Review getReviewById(String rid){
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(rid));
        
        return mongoTemplate.findOne(q, Review.class, REVIEWS_COL);
    }

}

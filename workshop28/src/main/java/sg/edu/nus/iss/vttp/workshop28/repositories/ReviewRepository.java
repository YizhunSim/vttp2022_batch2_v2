package sg.edu.nus.iss.vttp.workshop28.repositories;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation.AddFieldsOperationBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp.workshop28.models.EditedComment;
import sg.edu.nus.iss.vttp.workshop28.models.Game;
import sg.edu.nus.iss.vttp.workshop28.models.Review;

@Repository
public class ReviewRepository {
    private static final String REVIEWS_COL = "reviews";

    private static final String HIGHEST = "HIGHEST";

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review insertReview(Review r) {
        return mongoTemplate.insert(r, REVIEWS_COL);
    }

    public Review upsertEdited(String _id, EditedComment editedComment) {
        ObjectId objectId = new ObjectId(_id);
        Review existingReview = mongoTemplate.findById(objectId, Review.class, REVIEWS_COL);

        // Existing
        if (existingReview != null) {
            EditedComment ec = new EditedComment();
            ec.setComment(editedComment.getComment());
            ec.setRating(editedComment.getRating());
            ec.setPosted(LocalDateTime.now());

            if (existingReview.getEdited() != null) {
                existingReview.getEdited().add(ec);
            } else {
                List<EditedComment> editedArrayComment = new LinkedList<>();
                editedArrayComment.add(editedComment);
                existingReview.setEdited(editedArrayComment);
            }
            mongoTemplate.save(existingReview, REVIEWS_COL);
        }
        return existingReview;
    }

    public Review getReviewById(String rid) {
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(rid));

        return mongoTemplate.findOne(q, Review.class, REVIEWS_COL);
    }

    // db.game.aggregate([ { $match: { gid: 13 } },
    // { $lookup: { from: "reviews", localField: "gid", foreignField: "gameId", as:
    // "reviewsDocs" } },
    // { $project: { _id: 1, gid: 1, name: 1, year: 1, ranking: 1, users_rated: 1,
    // url: 1, image: 1, reviews: "$reviewsDocs._id", timestamp: "$$NOW" } }]);

    public Optional<Game> aggregateReviews(String gameId) {
        MatchOperation matchGameId = Aggregation.match(Criteria.where("gid").is(Integer.parseInt(gameId)));

        LookupOperation linkReviewsGame = Aggregation.lookup(REVIEWS_COL, "gameId", "gid", "reviewsDocs");

        ProjectionOperation selectFields = Aggregation
                .project("_id", "gid", "name", "year", "ranking", "users_rated",
                        "url",
                        "image")
                .and("reviewsDocs._id").as("reviews");

        AddFieldsOperationBuilder adFieldOpsBld = Aggregation.addFields()
                .addFieldWithValue("timestamp", LocalDateTime.now());
        AddFieldsOperation newFieldOps = adFieldOpsBld.build();

        Aggregation pipeline = Aggregation.newAggregation(matchGameId, linkReviewsGame, selectFields, newFieldOps);

        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "game", Document.class);
        System.out.println("result: ReviewRepository - " + results);
        if (!results.iterator().hasNext()) {
            System.out.println("I am empty");
            return Optional.empty();
        }
        Document doc = results.iterator().next();
        Game g = Game.create(doc);

        return Optional.of(g);

    }

    /*
     * db.reviews.aggregate([
     * {
     * $match: { user: 'ysim' }
     * },
     * {
     * $sort: {rating : 1}
     * },
     * {
     * $project: { gameId: 1, boardGame: 1, rating: 1, user: 1, comment: 1, _id: 1}
     * }
     * 
     * 
     * ]);
     */

    public Optional<List<Review>> retreiveReviewsByUserRating(String user, String sortDirection) {
        System.out.println("user - " + user + " sortDirection - " + sortDirection);
        MatchOperation matchUser = Aggregation.match(Criteria.where("user").is(user));

        System.out.println(sortDirection.toUpperCase());
        Direction sortingDirection = sortDirection.toUpperCase().equals(HIGHEST) ? Direction.DESC : Direction.ASC;

        System.out.println("Direction - SortTT: " + sortingDirection);
        // $sort
        SortOperation sortByRating = Aggregation.sort(sortingDirection, "rating");

        ProjectionOperation selectFields = Aggregation
        .project("gameId", "boardGame", "rating", "user", "comment", "_id", "posted");

        Aggregation pipeline = Aggregation.newAggregation(matchUser, sortByRating, selectFields);

        // Query the collection
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "reviews", Document.class);
        List<Review> reviews = new LinkedList<>();

        if (!results.iterator().hasNext()){
            System.out.println("Empty Results");
            return Optional.empty();
        }
        for (Document doc : results){
            Review r = Review.create(doc);
            reviews.add(r);
        }

        System.out.println("ReviewRepository - results from db: " + Optional.of(reviews).get());
        return Optional.of(reviews);
    }

}

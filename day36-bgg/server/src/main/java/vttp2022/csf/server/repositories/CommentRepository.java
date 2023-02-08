package vttp2022.csf.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.csf.server.models.Comment;

@Repository
public class CommentRepository {
    @Autowired
    private MongoTemplate template;

    public List<Comment> findCommentByGid(int gameId){
        Criteria criteria = Criteria.where("gid").is(gameId);
        Query query = Query.query(criteria);
        return template.find(query, Document.class, "comment")
        .stream()
        .map(v -> {
            return Comment.create(v);
        })
        .toList();
    }
    
}

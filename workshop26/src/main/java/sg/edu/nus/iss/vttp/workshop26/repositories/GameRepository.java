package sg.edu.nus.iss.vttp.workshop26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp.workshop26.models.Game;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> searchAllGames(Integer limit, Integer offset){
        final Pageable pageableRequest = PageRequest.of(offset, limit);
        Query query = new Query();
        query.with(pageableRequest);

        return mongoTemplate.find(query, Document.class, "game")
                .stream()
                .map(d -> Game.create(d))
                .toList();
    }

    public List<Game> getGamesByRank(){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "ranking"));

        return mongoTemplate.find(query, Document.class, "game")
                .stream()
                .map(d -> Game.create(d))
                .toList();
    }

    public Game getGameDetailsById(Integer gid){
        Query q = new Query();
        q.addCriteria(Criteria.where("gid").is(gid));
        
        return mongoTemplate.findOne(q, Game.class, "game");
    }
    
}

package vttp2022.csf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.server.models.Comment;
import vttp2022.csf.server.models.Game;
import vttp2022.csf.server.repositories.CommentRepository;
import vttp2022.csf.server.repositories.GameRepository;

@Service
public class BggService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentRepository commentRepo;

    public List<Comment> getComments(int gameId){
        return commentRepo.findCommentByGid(gameId);
    }

    public List<Game> getGames(int limit, int skip){
        return gameRepository.getGames(limit, skip);
    }

    
}

package sg.edu.nus.iss.vttp.workshop26.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp.workshop26.models.Comment;
import sg.edu.nus.iss.vttp.workshop26.models.Game;
import sg.edu.nus.iss.vttp.workshop26.repositories.CommentRepository;
import sg.edu.nus.iss.vttp.workshop26.repositories.GameRepository;

@Service
public class SearchBggService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Game> searchAllGames(Integer limit, Integer offset){
        return gameRepository.searchAllGames(limit, offset);
    }

    public List<Game> getGamesByRank(){
        return gameRepository.getGamesByRank();
    }

    public Game getGameDetailsById(Integer gid){
        return gameRepository.getGameDetailsById(gid);
    }

    public List<Comment> searchCommentByKeyword(String s, Integer limit, Integer offset){
        List<String> includes = new LinkedList<>();
        List<String> excludes = new LinkedList<>();
        for (String w : s.split(" ")) {
            // System.out.println(w);
            if (w.startsWith("-")) {
                String[] exW = w.split("-");
                // System.out.println("ex " + exW[1]);
                excludes.add(exW[1]);
            } else {
                // System.out.println("in " + w);
                includes.add(w);
            }
        }
        // excludes.get(0).substring(2);
        // includes.get(0).substring(2);
        System.out.println("excludes: " + excludes);
        System.out.println("includes: " + includes);
        return commentRepository.searchCommentByText(includes, excludes, limit, offset);
    }
}

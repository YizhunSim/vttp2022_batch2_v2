package vttp2022.csf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.server.models.Game;
import vttp2022.csf.server.repositories.GameRepository;


@Service
public class BGGService {

    @Autowired
    private GameRepository gameRepo;

    public List<Game> findGameByName(String name) {
        return gameRepo.findGamesByName("%%%s%%".formatted(name));
    }
    
}
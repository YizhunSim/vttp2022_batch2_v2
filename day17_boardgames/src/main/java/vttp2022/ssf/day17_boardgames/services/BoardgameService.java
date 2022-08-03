package vttp2022.ssf.day17_boardgames.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.ssf.day17_boardgames.repositories.BoardgameRepositories;

@Service
public class BoardgameService {

  @Autowired
  private BoardgameRepositories boardGameRepo;


  public String getSingleBoardGame(String boardgameId){
    Optional<String> opt = boardGameRepo.get(boardgameId);
    String payload;
    if (opt.isEmpty()){
      return null;
    } else{
      payload = opt.get();
      System.out.println("PAYLOADDDD: " + payload);
      return payload;
    }
  }
}

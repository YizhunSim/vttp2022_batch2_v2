package vttp2022.ssf.day17_boardgames.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BoardgameRepositories {

  @Autowired
  @Qualifier("redislab")
  private RedisTemplate<String, String> redisTemplate;

  // public void save(String boardgame, String payload){
  //   ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
  //   valueOp.set(boardgame.toLowerCase(), payload);
  // }

  public Optional<String> get(String id){
      ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
      String value = valueOp.get(id);
      if (value == null){
        return Optional.empty();
      } else{
        return Optional.of(value);
      }
  }
}
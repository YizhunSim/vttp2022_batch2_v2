package vttp2022.paf.day29.repositories;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.day29.AppConfig;
import vttp2022.paf.day29.models.SuperHero;

@Repository
public class MarvelCache {
    @Autowired
    @Qualifier(AppConfig.CACHE_MARVEL)
    private RedisTemplate<String, String> redisTemplate;


    public void cache(String key, List<SuperHero> values){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        values.stream()
        .forEach(c -> {
            arrBuilder.add(c.toJson());
        });
        ops.set(key, arrBuilder.build().toString());
    }

    public void cacheBySuperHeroId(String key, SuperHero value){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value.toJson().toString());
        System.out.println("MarvelCache: - cache Id: " + key + "- SUCCESS!");
    }

    public Optional<List<SuperHero>> get(String name){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(name);
        if (value == null){
            return Optional.empty();
        }

        JsonReader reader = Json.createReader(new StringReader(value));
        JsonArray results = reader.readArray();

        List<SuperHero> heros = results.stream()
                                .map(v -> (JsonObject) v)
                                .map(v -> SuperHero.saveToCache(v))
                                .toList();

        return Optional.of(heros);
    }

    public Optional<SuperHero> getSuperheroById(String id){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String payload = ops.get(id);

        if (payload == null){
            return Optional.empty();
        }

        System.out.println(">>>> payload: "+ payload);
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject results = reader.readObject();

        SuperHero superhero = SuperHero.saveToCache(results);

        return Optional.of(superhero);
    }

    
}

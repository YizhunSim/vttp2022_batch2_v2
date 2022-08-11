package vttp2022.ssf.crypto_practice.repositories;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.crypto_practice.models.CryptoCurrency;
import vttp2022.ssf.crypto_practice.models.WatchList;

@Repository
public class CryptoWatchListRepositories {
    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public void saveUser(String user){
      // ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
      ListOperations<String, String> listOps = redisTemplate.opsForList();
      listOps.leftPush("users", user);
      // valueOp.set("users", user);
    }

    public boolean isUserFound(String user) {
      System.out.println("Entered isUserFound");
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        long listOfUsers = listOps.size("users");
        System.out.println("Number of users: " + listOfUsers);

        for (long i = 0; i < listOfUsers; i++) {
          String userRetrieved = listOps.index("users", i);
          System.out.println("User: " + userRetrieved);
          if (userRetrieved.equals(user)){
            System.out.println("isUserFound: " + true);
            return true;
          }
      }
        System.out.println("isUserFound: " + false);
        return false;
    }
    // public String getUsers() {
    //     ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
    //     return valueOps.get("users");
    // }

    public Optional<WatchList> retrieveWatchList(String name) {
      System.out.println("retrieveWatchList: - name: " + name);
      // String parseName = name.replaceAll("\"", " ");
      // System.out.println("parseName: " + parseName);
      // // String nname = "\"" + name + "\"";
      // System.out.println("nname: " + nname);
      
      if (!redisTemplate.hasKey(name)){
        System.out.println("retrieveWatchList - no key: " + name);
        return Optional.empty();
      }

      List<CryptoCurrency> contents = new LinkedList<>();
      ListOperations<String, String> listOps = redisTemplate.opsForList();
      long size = listOps.size(name);
      System.out.println("retrieveWatchList: - list of " + name + ", size: " + size);
      for (long i = 0; i < size; i++) {
        String str = listOps.index(name, i);
        System.out.println("retrieveWatchList: " + str);
           // Convert Payload to JsonObject
        // Convert the String to a Reader
        Reader strReader = new StringReader(str);
        // Create a JsonReader from reader
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject cryptoResult = jsonReader.readObject();
        System.out.println(cryptoResult);
        // contents.add(CryptoCurrency.create(str));
      }

      WatchList watchlist = new WatchList(name);
      watchlist.setWatchlist(contents);
      return Optional.of(watchlist);
	}

	public void save(WatchList watchlist) {
		String name = watchlist.getName();
		List<CryptoCurrency> contents = watchlist.getWatchlistContents();
		ListOperations<String, String> listOps = redisTemplate.opsForList();
		long l = listOps.size(name);
		if (l > 0)
      // Removes all items in list
			listOps.trim(name, 0, l);
      // Adds all items back in list
		  listOps.leftPushAll(name,
				contents.stream()
					.map(v -> v.toJson().toString()).toList()
		);
	}


    public Integer count() {
        Set<String> keys = redisTemplate.keys("users");
        return keys.size();
    }
    public List<String> keys() {
        Set<String> keys = redisTemplate.keys("users");
        List<String> result = new LinkedList<>(keys);
        return result.stream()
                .toList();
    }
}

package vttp2022.ssf.crypto_practice.models;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class WatchList {
  private String name;
  private List<CryptoCurrency> watchlistContents;

  public WatchList(String name) {

  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public List<CryptoCurrency> getWatchlistContents() {
    return watchlistContents;
  }

  public void setWatchlist(List<CryptoCurrency> watchlist) {
    this.watchlistContents = watchlist;
  }

  public void add(CryptoCurrency crypto) {
		String n = crypto.getFullName().toLowerCase();
		List<CryptoCurrency> sameItem = watchlistContents
      .stream()
			.filter(v -> crypto.getFullName().toLowerCase().equals(n))
			.limit(1)
			.toList();
		if (sameItem.size() > 0) {
			CryptoCurrency cc = sameItem.get(0);
			cc.setLots(cc.getLots() + crypto.getLots());
		} else
			watchlistContents.add(crypto);
	}

  public JsonObject toJson() {
		JsonArrayBuilder arr = Json.createArrayBuilder();
		watchlistContents.stream()
			.map(i -> i.toJson())
			.forEach(i -> arr.add(i));

		return Json.createObjectBuilder()
			.add("name", name)
			.add("watchlist", arr)
			.build();
	}

	public static WatchList create(String jsonString) {
		Reader reader = new StringReader(jsonString);
		JsonReader jr = Json.createReader(reader);
		JsonObject c = jr.readObject();
		WatchList watchlist = new WatchList(c.getString("name"));
		List<CryptoCurrency> contents = c.getJsonArray("contents").stream()
			.map(v -> (JsonObject)v)
			//.map(v -> CryptoCurrency.create(v))
			.map(CryptoCurrency::create)
			.toList();
		watchlist.setWatchlist(contents);
		return watchlist;
	}


  //   // Json to Model
  // public static WatchList create(JsonObject jo){
  //     WatchList watchlist = new WatchList();
  //     watchlist.setId(jo.getInt("id"));
  //     watchlist.setName(jo.getString("name"));
  //     System.out.println("Final JO object: " + jo);
  //     return watchlist;
  // }

  // // Model to Json
  // public JsonObject toJson(WatchList cart){
  //   return Json.createObjectBuilder()
  //   .add("id", cart.getId())
  //   .add("name", cart.getName())
  //   .build();
  // }

  public String toString(){
    return "WatchList - name: " + name + "name: " + name ;
  }

}

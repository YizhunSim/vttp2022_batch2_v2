package vttp2022.ssf.day17_boardgames.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Boardgame {
  private int id;
  private String name;
  private int year;
  private int ranking;
  private int users_rated;
  private String url;
  private String image;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getRanking() {
    return ranking;
  }

  public void setRanking(int ranking) {
    this.ranking = ranking;
  }

  public int getUsers_rated() {
    return users_rated;
  }

  public void setUsers_rated(int users_rated) {
    this.users_rated = users_rated;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Boardgame create(JsonObject jo){
      Boardgame bg = new Boardgame();
      bg.setId(jo.getInt("id"));
      bg.setName(jo.getString("name"));
      bg.setYear(jo.getInt("year"));
      bg.setRanking(jo.getInt("ranking"));
      bg.setUsers_rated(jo.getInt("users_rated"));
      bg.setUrl(jo.getString("url"));
      bg.setImage(jo.getString("image"));
      System.out.println("Final JO object: " + jo);
      return bg;
  }

  // Model to Json
  public JsonObject toJson(Boardgame bg){
    return Json.createObjectBuilder()
    .add("id", bg.getId())
    .add("name", bg.getName())
    .add("year", bg.getYear())
    .add("ranking", bg.getRanking())
    .add("users_rated", bg.getUsers_rated())
    .add("url", bg.getUrl())
    .add("image", bg.getImage())
    .build();
  }

  public String toString(){
    return "id: " + id + "name: " + name + "year: " + "ranking: " + ranking + "users_rated: " + users_rated + "url: " + url + "image: " + image;
  }
}

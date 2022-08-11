package vttp2022.ssf.crypto_practice.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.JsonObject;

public class NewsFeed {
  private int rank;
  private String imageUrl;
  private String title;
  private String url;
  private String source;
  private String body;
  private List<String> tags;
  private List<String> categories;

  public int getRank() {
    return rank;
  }
  public void setRank(int rank) {
    this.rank = rank;
  }
  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getSource() {
    return source;
  }
  public void setSource(String source) {
    this.source = source;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public List<String> getTags() {
    return tags;
  }
  public void setTags(List<String> tags) {
    this.tags = tags;
  }
  public List<String> getCategories() {
    return categories;
  }
  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public static NewsFeed create(int index, JsonObject jo){
      NewsFeed nf = new NewsFeed();
      nf.setRank(index + 1);
      nf.setImageUrl(jo.getString("imageurl"));
      nf.setTitle(jo.getString("title"));
      nf.setUrl(jo.getString("url"));
      nf.setSource(jo.getString("source"));
      nf.setBody(jo.getString("body"));

      List<String> tagList = new ArrayList<>();
      String[] tagArr = jo.getString("tags").split("\\|");
      for (String tag : tagArr){
        tagList.add(tag);
      }

      System.out.println("TAGGGGG LIST:  "+ tagList);
      nf.setTags(tagList);

      String [] categoriesArr = jo.getString("categories").split("\\|");
      List<String> categoriesList = new ArrayList<>();
      for (String cat: categoriesArr){
        categoriesList.add(cat);
      }
      System.out.println("CATEGORIESSS LIST:  "+ categoriesList);
      nf.setCategories(categoriesList);

      return nf;
  }
}

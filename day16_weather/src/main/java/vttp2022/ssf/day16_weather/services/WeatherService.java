package vttp2022.ssf.day16_weather.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.day16_weather.models.Weather;
import vttp2022.ssf.day16_weather.repositories.WeatherRepository;

@Service
public class WeatherService {

  private static final String URL = "https://api.openweathermap.org/data/2.5/weather";
  private static final String COUNTRYFLAGURL = "https://www.countryflagsapi.com/png";
 
  @Value("${API_KEY}")
  private String key;

  @Autowired
  private WeatherRepository weatherRepo;

  public List<Weather> getWeather(String city){
    System.out.printf(">>> city: %s\n", city);

    String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
    // CHECK IF WE HAVE THE WEATHER CACHE
    Optional<String> opt = weatherRepo.get(encodedCity);
    String payload;

    if (opt.isEmpty()){
      System.out.println("Getting weather from OpenWeatherMap");
      // create the url with query string
      // System.out.println("CITYYYY: " + city);
      String url = UriComponentsBuilder.fromUriString(URL).queryParam("q", encodedCity).queryParam("appid", key).toUriString();

      // System.out.println("URLLLLL: " + url);
      // create the GET Request, GET url
      RequestEntity<Void> request = RequestEntity.get(url).build();
      // System.out.println("URLLLLL++++: " + request);
      // Make the call to OpenWeatherMap
      RestTemplate template = new RestTemplate();
      ResponseEntity<String> response;

      try{
        response = template.exchange(request, String.class);
      } catch(Exception ex){
        System.err.printf("Error: %s\n", ex.getMessage());
        return Collections.emptyList();
      }

      if (response.getStatusCodeValue() != 200){
        System.err.println("Error status code is not 200");
        return Collections.emptyList();
      }

      payload = response.getBody();
      System.out.println("Payload: " + payload);

      weatherRepo.save(encodedCity, payload);
    } else{
      payload = opt.get();
       System.out.printf(">>> cache: %s\n", payload);
    }

    // Convert Payload to JsonObject
    // Convert the String to a Reader
    Reader strReader = new StringReader(payload);
    // Create a JsonReader from reader
    JsonReader jsonReader = Json.createReader(strReader);
    JsonObject weatherResult = jsonReader.readObject();
    JsonArray cities = weatherResult.getJsonArray("weather");
    List<Weather> list = new LinkedList<>();

    // System.out.println("CITIES SIZEEE: " + cities.size());

    for (int i = 0; i < cities.size(); i++){
      JsonObject jo = cities.getJsonObject(i);
      list.add(Weather.create(jo));
    }

    // System.out.println("WEATHERRRRR LIST: " + list);
    return list;
  }

  public String getCountryCode(String city){
      String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
      Optional<String> opt = weatherRepo.get(encodedCity);
      String countryCode = "";
      if (!opt.isEmpty()){
        String payload = opt.get();
        System.out.printf(">>> cache: %s\n", payload);

        // Convert Payload to JsonObject
        // Convert the String to a Reader
        Reader strReader = new StringReader(payload);
        // Create a JsonReader from reader
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject weatherResult = jsonReader.readObject();
        JsonObject weatherSys = weatherResult.getJsonObject("sys");
        countryCode = weatherSys.getString("country");
      }
      return countryCode;
  }
}

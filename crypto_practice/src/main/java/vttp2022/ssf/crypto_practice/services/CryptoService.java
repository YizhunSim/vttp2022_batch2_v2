package vttp2022.ssf.crypto_practice.services;

import java.io.Reader;
import java.io.StringReader;
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
import vttp2022.ssf.crypto_practice.models.CryptoCurrency;
import vttp2022.ssf.crypto_practice.models.WatchList;
import vttp2022.ssf.crypto_practice.repositories.CryptoWatchListRepositories;

@Service
public class CryptoService {

  @Autowired
  private CryptoWatchListRepositories cryptoWatchListRepo;

  @Value("${API_KEY}")
  private String key;


 // https://min-api.cryptocompare.com/data/top/mktcapfull?limit=10&tsym=USD&api_key=fbe2d81f5a444b0de213218649025433c161a560c28576fbe600f73ca6585e0c
 //                                                       limit     tsym      api_key
  private static final String URL = "https://min-api.cryptocompare.com/data/top/mktcapfull";

  // https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD
  //                                                fsym    tsyms
  private static final String URL_COIN = "https://min-api.cryptocompare.com/data/price";

  // https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC&tsyms=USD,EUR
  //                                                        fsyms     tsyms
  private static final String URL_MULTI = "https://min-api.cryptocompare.com/data/pricemultifull";

  public boolean getUser(String user) {
    // System.out.println("getUsers - name: " + name);
    // boolean result = cryptoWatchListRepo.isUserFound(user);
    boolean result = false;
    System.out.println("CryptoService: getUsers - result: " + result);
    if (!result)
      return false;
    else{
      // return Optional.of(WatchList.create(result));
      return true;
    }
  }

  public void createNewUser(String name){
    cryptoWatchListRepo.saveUser(name);
  }

  public Optional<WatchList> getWatchList(String name){
    return cryptoWatchListRepo.retrieveWatchList(name);
  }

  public Optional<CryptoCurrency> getCryptoCoin(String ticker, String tsym){
     String url = UriComponentsBuilder.fromUriString(URL_COIN).queryParam("fsym", ticker).queryParam("tsyms", tsym).queryParam("app_id", key).toUriString();

       // System.out.println("URLLLLL: " + url);
      // create the GET Request, GET url
      RequestEntity<Void> request = RequestEntity.get(url).build();
      // System.out.println("URLLLLL++++: " + request);
      // Make the call to OpenWeatherMap
      RestTemplate template = new RestTemplate();
      ResponseEntity<String> response;
      String payload;

      try{
        response = template.exchange(request, String.class);
      } catch(Exception ex){
        System.err.printf("Error: %s\n", ex.getMessage());
        return Optional.empty();
      }

      if (response.getStatusCodeValue() != 200){
        System.err.println("Error status code is not 200");
        return Optional.empty();
      }

      payload = response.getBody();
      System.out.println("Payload: " + payload);

       // Convert Payload to JsonObject
      // Convert the String to a Reader
      Reader strReader = new StringReader(payload);
      // Create a JsonReader from reader
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject cryptoResult = jsonReader.readObject();
      // System.out.println("Cryptoresultttttt: " + cryptoResult);

      return Optional.of(CryptoCurrency.create(cryptoResult, tsym));
  }

  public List<CryptoCurrency> getTopCyptoCurrencies(String limit, String tsym){
      String url = UriComponentsBuilder.fromUriString(URL).queryParam("limit", limit).queryParam("tsym", tsym).queryParam("app_id", key).toUriString();

       // System.out.println("URLLLLL: " + url);
      // create the GET Request, GET url
      RequestEntity<Void> request = RequestEntity.get(url).build();
      // System.out.println("URLLLLL++++: " + request);
      // Make the call to OpenWeatherMap
      RestTemplate template = new RestTemplate();
      ResponseEntity<String> response;
      String payload;

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

      // Convert Payload to JsonObject
      // Convert the String to a Reader
      Reader strReader = new StringReader(payload);
      // Create a JsonReader from reader
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject cryptoResult = jsonReader.readObject();
      JsonArray cryptoData = cryptoResult.getJsonArray("Data");

      List<CryptoCurrency> list = new LinkedList<>();

      // System.out.println("CITIES SIZEEE: " + cities.size());

      for (int i = 0; i < cryptoData.size(); i++){
        JsonObject jo = cryptoData.getJsonObject(i);
          JsonObject coinInfoJo = jo.getJsonObject("CoinInfo");

          JsonObject displayJo = jo.getJsonObject("DISPLAY");
          JsonObject displayUSDJo = displayJo.getJsonObject("USD");

        System.out.println("COININFOOOOOO: " + coinInfoJo);
        System.out.println("DISPLAYYYYYYY: " + displayJo);
        System.out.println("DISPLAYYYUSDJOOO: " + displayUSDJo);
        list.add(CryptoCurrency.create(i, coinInfoJo, displayUSDJo));
      }

      System.out.println("Cyptocurrency LIST: " + list);
      return list;
  }
}

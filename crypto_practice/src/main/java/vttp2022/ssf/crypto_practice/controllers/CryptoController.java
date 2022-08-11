package vttp2022.ssf.crypto_practice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.ssf.crypto_practice.models.CryptoCurrency;
import vttp2022.ssf.crypto_practice.models.NewsFeed;
import vttp2022.ssf.crypto_practice.models.WatchList;
import vttp2022.ssf.crypto_practice.services.CryptoService;
import vttp2022.ssf.crypto_practice.services.NewsFeedService;

@Controller
@RequestMapping(path={"/crypto"})
public class CryptoController {

  @Autowired
  private CryptoService cryptoService;

  @Autowired
  private NewsFeedService newsFeedService;

  @PostMapping
	public String postWatchList(@RequestBody MultiValueMap<String, String> form
				, Model model) {

		String name = form.getFirst("name");
    boolean isUserFound = cryptoService.getUser(name);

    if (isUserFound){
      // load user cart items
      System.out.println("User exists in Redis");
      // Optional<WatchList> watchList = cryptoService.getWatchList(name);
    } else{
      // save user as new user
      System.out.println("Create new user to redis: " + name);
      cryptoService.createNewUser(name);
    }

    // Call Redis search for name in database
		if ((null == name) || (name.trim().length() <= 0))
			name = "anonymous";

		model.addAttribute("name", name.toUpperCase());

    List<NewsFeed> newsFeedList = getLatestNewsFeed();

    model.addAttribute("newsFeedData", newsFeedList);

		return "crypto";
	}

  @GetMapping (path={"/watchlist"})
  public String retrieveWatchlist(Model model, @RequestParam String user){
    Optional<WatchList> watchlist = cryptoService.getWatchList(user);
    model.addAttribute("watchlistData", watchlist);
    return "crypto";
  }

  @PostMapping (path={"/top"})
  public String getTopCryptoCurrencies(Model model, @RequestBody MultiValueMap<String, String> form){
    String limit = form.getFirst("limit");
    String tsym = form.getFirst("tsym");
    List<CryptoCurrency> currencies = cryptoService.getTopCyptoCurrencies(limit, tsym);
    model.addAttribute("number", limit);
    model.addAttribute("currenciesData", currencies);
    return "crypto";
  }

  @GetMapping (path={"/coin"})
  public String getCryptoCoin(Model model, @RequestParam String ticker, @RequestParam String tsym){
    Optional<CryptoCurrency> coin = cryptoService.getCryptoCoin(ticker, tsym);
    String price = coin.get().getPrice();
    model.addAttribute("coinPrice", price);
    return "crypto";
  }

  private List<NewsFeed> getLatestNewsFeed(){
      List<NewsFeed> newsFeedList = newsFeedService.retrieveLatestNewsFeed();
      return newsFeedList;
  }
}

package vttp2022.ssf.crypto_practice.models;

import java.io.StringReader;
import java.math.BigDecimal;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// Name

// Price

// 1h %

// 24h %

// Market Cap

// Volume(24h)

// Circulating Supply



// Rank | 	Asset (FullName + Name)	| Price (DISPLAY.USD.PRICE)| 	Market Cap (DISPLAY.USD.MKTCAP) 	| 	Volume (DISPLAY.USD.VOLUMEDAYTO)	| Open Day (DISPLAY.USD.OPENDAY)| High Day (DISPLAY.USD.HIGHDAY)| Low Day (DISPLAY.USD.LOWDAY)

// 1		Bitcoin (BTC)			$ 23,901.0	$ 455.55 B 		$ 456.88 B 					$ 23,179.9 $ 23,910.1  $ 23,162.4
public class CryptoCurrency {
  private int rank;
  private String fullName;
  private String tickerCode;
  private String asset;
  private String price;
  private String marketCap;
  private String volume24h;
  private String openDay;
  private String highDay;
  private String lowDay;
  private int lots;

  public int getRank() {
    return rank;
  }
  public void setRank(int rank) {
    this.rank = rank;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getTickerCode() {
    return tickerCode;
  }
  public void setTickerCode(String tickerCode) {
    this.tickerCode = tickerCode;
  }

  public String getAsset() {
    return getFullName() + "(" + getTickerCode() + ")";
  }

  public void setAsset(String fullName, String tickerCode) {
    this.asset = fullName + "(" + tickerCode + ")";
  }

  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  public String getMarketCap() {
    return marketCap;
  }
  public void setMarketCap(String marketCap) {
    this.marketCap = marketCap;
  }
  public String getVolume() {
    return volume24h;
  }
  public void setVolume(String volume) {
    this.volume24h = volume;
  }
  public String getOpenDay() {
    return openDay;
  }
  public void setOpenDay(String openDay) {
    this.openDay = openDay;
  }
  public String getHighDay() {
    return highDay;
  }
  public void setHighDay(String highDay) {
    this.highDay = highDay;
  }
  public String getLowDay() {
    return lowDay;
  }
  public void setLowDay(String lowDay) {
    this.lowDay = lowDay;
  }

  public int getLots() {
    return lots;
  }
  public void setLots(int lots) {
    this.lots = lots;
  }

  public static CryptoCurrency create(String jsonStr) {
		StringReader reader = new StringReader(jsonStr);
		JsonReader r = Json.createReader(reader);
		return create(r.readObject());
	}
	public static CryptoCurrency create(JsonObject jo) {
		CryptoCurrency cc = new CryptoCurrency();
		cc.setFullName(jo.getString("name"));
		// cc.setLots(jo.getInt("lots"));
		return cc;
	}

  public static CryptoCurrency create(JsonObject jo, String tsym){
    CryptoCurrency c = new CryptoCurrency();
    c.setPrice(jo.get(tsym).toString());
    return c;
  }

  // Rank | 	Asset (FullName + Name)	| Price (DISPLAY.USD.PRICE)| 	Market Cap (DISPLAY.USD.MKTCAP) 	| 	Volume (DISPLAY.USD.VOLUMEDAYTO)	| Open Day (DISPLAY.USD.OPENDAY)| High Day (DISPLAY.USD.HIGHDAY)| Low Day (DISPLAY.USD.LOWDAY)
  public static CryptoCurrency create(int index, JsonObject coinInfoJo, JsonObject displayUsdJo){
      CryptoCurrency c = new CryptoCurrency();
      c.setRank(index + 1);
      c.setFullName(coinInfoJo.getString("FullName"));
      c.setTickerCode(coinInfoJo.getString("Name"));
      c.setPrice(displayUsdJo.getString("PRICE").toString());
      c.setMarketCap(displayUsdJo.getString("MKTCAP").toString());
      c.setVolume(displayUsdJo.getString("VOLUMEDAYTO").toString());
      c.setOpenDay(displayUsdJo.getString("OPENDAY").toString());
      c.setHighDay(displayUsdJo.getString("HIGHDAY").toString());
      c.setLowDay(displayUsdJo.getString("LOWDAY").toString());

      return c;
  }

  public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("name", asset)
			.add("price", price)
      .add("marketcap", marketCap)
      .add("volume24h", volume24h)
      .add("openday", openDay)
      .add("highday", highDay)
      .add("lowday", lowDay)
			.build();
	}

  public String toString(){
    return "Rank: " + rank + ", Full Name: " + fullName + ", Ticker Code: " + tickerCode + "Price: " + price + "MarketCap: " + marketCap + "Volume 24H: " + volume24h + "Open Day: " + openDay + "High Day: " + highDay + "Low Day: " + lowDay;
   }
}

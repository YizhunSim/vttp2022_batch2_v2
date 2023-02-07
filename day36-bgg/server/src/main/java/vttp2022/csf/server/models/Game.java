package vttp2022.csf.server.models;

import org.bson.Document;

public class Game {
    private int gameId;
    private String name;

    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    

    public static Game create(Document doc){
        Game game = new Game();
        game.setGameId(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        return game;
    }

}

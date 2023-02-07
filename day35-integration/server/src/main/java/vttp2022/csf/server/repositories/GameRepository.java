package vttp2022.csf.server.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.csf.server.models.Game;

import static vttp2022.csf.server.repositories.Queries.*;

@Repository
public class GameRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Game> findGamesByName(String name) {

        List<Game> games = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_GAME_BY_NAME, name);
        while (rs.next())
            games.add(Game.create(rs));

        return games;
    }
    
}
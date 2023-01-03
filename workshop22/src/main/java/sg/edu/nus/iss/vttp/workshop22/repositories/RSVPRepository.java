package sg.edu.nus.iss.vttp.workshop22.repositories;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp.workshop22.models.RSVP;
import sg.edu.nus.iss.vttp.workshop22.models.RSVPTotalCntMapper;

import static sg.edu.nus.iss.vttp.workshop22.repositories.Queries.*;

@Repository
public class RSVPRepository {
    @Autowired
    private JdbcTemplate template;

    public List<RSVP> getAllRSVP(String q){
        final List<RSVP> rsvp = new LinkedList<>();
        SqlRowSet result = null;
        if (q == null){
            result = template.queryForRowSet(SQL_SELECT_ALL_RSVP);
        } else{
            result = template.queryForRowSet(SQL_SEARCH_RSVP_BY_NAME, q);
        }

        while (result.next()){
            rsvp.add(RSVP.create(result));
        }
        return rsvp;
    }

    public RSVP searchRSVPByEmail(String email) {
        // prevent inheritance
        final List<RSVP> rsvps = new LinkedList<>();
        // perform the query
        final SqlRowSet rs = template.queryForRowSet(SQL_SEARCH_RSVP_BY_EMAiL, email);

        while (rs.next()) {
            rsvps.add(RSVP.create(rs));
        }
        return rsvps.get(0);
    }

    public RSVP insertRSVP(RSVP rsvp){
        KeyHolder keyholder = new GeneratedKeyHolder();

        try {
            template.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_RSVP,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, rsvp.getName());
                ps.setString(2, rsvp.getEmail());
                ps.setString(3, rsvp.getPhone());
                System.out.println("Confirmation date > " + rsvp.getConfirmationDate());
                ps.setTimestamp(4, new Timestamp(rsvp.getConfirmationDate().toDateTime().getMillis()));
                ps.setString(5, rsvp.getComments());
                return ps;
            }, keyholder);
            BigInteger primaryKeyVal = (BigInteger) keyholder.getKey();
            rsvp.setId(primaryKeyVal.intValue());

        } catch (DataIntegrityViolationException e) {
            RSVP existingRSVP = searchRSVPByEmail(rsvp.getEmail());
            existingRSVP.setComments(rsvp.getComments());
            existingRSVP.setName(rsvp.getName());
            existingRSVP.setPhone(rsvp.getPhone());
            existingRSVP.setConfirmationDate(rsvp.getConfirmationDate());
            if (updateRSVP(existingRSVP))
                rsvp.setId(existingRSVP.getId());
        }
        return p;
    }

    public boolean updateRSVP(RSVP p){
        return template.update(SQL_UPDATE_RSVP_BY_EMAIL,
        p.getName(),
        p.getEmail(),
        p.getPhone(), 
        new Timestamp(p.getConfirmationDate().toDateTime().getMillis()),
        p.getComments(),
        p.getEmail()
        ) > 0; 
    }

    public List<RSVP> searchRSVPByName(String name){
        final List<RSVP> rsvps = new LinkedList<>();
        final SqlRowSet result = template.queryForRowSet(SQL_SEARCH_RSVP_BY_NAME, name);
       while (result.next()){
        rsvps.add(RSVP.create(result));
       }
        return rsvps;
    }

    public Integer getTotalRSVP(){
        List<RSVP> rsvps = template.query(SQL_TOTAL_CNT_RSVP, new RSVPTotalCntMapper(), new Object[]{});
        return rsvps.get(0).getTotalCount();
    }

    
}

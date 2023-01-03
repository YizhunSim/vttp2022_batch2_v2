package sg.edu.nus.iss.vttp.workshop22.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class RSVPTotalCntMapper implements RowMapper<RSVP> {

    @Override
    @Nullable
    public RSVP mapRow(ResultSet rs, int rowNum) throws SQLException {
        RSVP r = new RSVP();
        r.setTotalCount(rs.getInt("total"));
        return r;
    }
    
}

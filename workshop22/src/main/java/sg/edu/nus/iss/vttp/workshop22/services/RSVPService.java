package sg.edu.nus.iss.vttp.workshop22.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp.workshop22.models.RSVP;
import sg.edu.nus.iss.vttp.workshop22.repositories.RSVPRepository;

@Service
public class RSVPService {
    @Autowired
    private RSVPRepository rsvpRepository;

    public List<RSVP> getAllRSVP(String q) throws Exception{
        return rsvpRepository.getAllRSVP(q);
    }

    public List<RSVP> searchRSVPByName(String name) throws Exception {
        return rsvpRepository.searchRSVPByName(name);
    }

    public RSVP insertRSVP(RSVP r){
        return rsvpRepository.insertRSVP(r);
    }

    public boolean updateRSVP(RSVP p){
        return rsvpRepository.updateRSVP(p);
    }

    public Integer getTotalRsvp(){
        return rsvpRepository.getTotalRSVP();
    }
}

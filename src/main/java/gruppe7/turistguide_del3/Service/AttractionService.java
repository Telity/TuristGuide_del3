package gruppe7.turistguide_del3.Service;

import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AttractionService {
    private final List<AttractionService> attractions = new ArrayList<>();

    public final AttractionRepository touristRepository;

    public AttractionService(AttractionRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<Attraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public List<Attraction> getAttractionbyName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public Attraction addAttraction(Attraction attraction) {
        return touristRepository.addAttraction(attraction);
    }

    public Attraction updateAttraction(Attraction attraction) {
        return touristRepository.updateAttraction(attraction);
    }

    /*public Tourist deleteAttraction(Tourist attraction) {
        return touristRepository.deleteAttraction(attraction);
    }*/

    public List<String> getTagsList() {
        return touristRepository.getTagsList();
    }

    public void deleteAttraction(Attraction attraction) {
        touristRepository.deleteAttraction(attraction);
    }

    public List<Attraction> AddAttractionsList(Attraction attraction) {
        touristRepository.addAttraction(attraction);
        return touristRepository.getAllAttractions();
    }
    public List<String> getTagsByName(String name){
        return touristRepository.getTagsByName(name);
    }
    public Object getTownList() {
        return touristRepository.getTownList();
    }
}
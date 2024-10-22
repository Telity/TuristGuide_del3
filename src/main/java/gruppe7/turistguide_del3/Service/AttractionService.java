package gruppe7.turistguide_del3.Service;

import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Model.City;
import gruppe7.turistguide_del3.Model.Tag;
import gruppe7.turistguide_del3.Repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttractionService {

    public final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public List<Attraction> getAllAttractions() {
        return attractionRepository.getAllAttractions();
    }

    public Attraction getAttractionByName(String name) {
        return attractionRepository.getAttractionByName(name);
    }

    public void addAttraction(Attraction attraction, List<Tag> tags) {
        attractionRepository.addAttraction(attraction, tags);
    }

    public Attraction updateAttraction(Attraction attraction, List<Tag> tags) {
        int updatedRows = attractionRepository.updateAttraction(attraction, tags);

        if(updatedRows > 0){
            return attraction;
        }else{
            return null;
        }
    }

    public int deleteAttractionByName(String name) {
       return attractionRepository.deleteAttraction(name);
    }

   /* public List<Attraction> AddAttractionsList(Attraction attraction) {
        attractionRepository.addAttraction(attraction);
        return attractionRepository.getAllAttractions();
    } */

    public List<Tag> getTagsList() {
        return attractionRepository.getTagsList();
    }

    public List<City> getTownList() {
        return attractionRepository.getTownList();
    }

    public List<String> getTagsByName(String name){
        return attractionRepository.getTagsByName(name);
    }
}
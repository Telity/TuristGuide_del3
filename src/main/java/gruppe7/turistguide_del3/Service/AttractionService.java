package gruppe7.turistguide_del3.Service;

import gruppe7.turistguide_del3.Model.Attraction;
import gruppe7.turistguide_del3.Model.City;
import gruppe7.turistguide_del3.Model.Tag;
import gruppe7.turistguide_del3.Repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttractionService {

    // Dependency injection of the repository
    public final AttractionRepository attractionRepository;

    // Constructor that injects the AttractionRepository
    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    // Retrieves all attractions from the repository
    public List<Attraction> getAllAttractions() {
        return attractionRepository.getAllAttractions();
    }

    // Retrieves a specific attraction by name
    public Attraction getAttractionByName(String name) {
        return attractionRepository.getAttractionByName(name);
    }

    // Adds a new attraction to the repository
    public void addAttraction(Attraction attraction) {
        attractionRepository.addAttraction(attraction);
    }

    // Updates an existing attraction and its tags
    public Attraction updateAttraction(Attraction attraction, List<Tag> tags) {
        int updatedRows = attractionRepository.updateAttraction(attraction, tags);

        if(updatedRows > 0){
            return attraction;
        }else{
            return null;
        }
    }

    // Deletes an attraction by name
    public int deleteAttractionByName(String name) {
        return attractionRepository.deleteAttraction(name);
    }

    // Retrieves the list of tags from the repo
    public List<Tag> getTagsList() {
        return attractionRepository.getTagsList();
    }

    // Retrieves the list of cities from the repo
    public List<City> getTownList() {
        return attractionRepository.getTownList();
    }

    // Gets a list of tags associated with a specific attraction by name
    public List<String> getTagsByName(String name){
        return attractionRepository.getTagsByName(name);
    }
}
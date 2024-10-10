package gruppe7.turistguide_del3.Repository;

import gruppe7.turistguide_del3.Model.Attraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AttractionRepository {
    private final List<Attraction> attractions = new ArrayList<>();
    private int id;
    private final List<String> towns = new ArrayList<>();
    private final List<String> tagsList = new ArrayList<>();
    private boolean initialized = false;

    public AttractionRepository() {
        populateAttractions();
        populateTagsList();
        populateTownList();
    }
    public List<String> getTownList () {
        return towns;
    }
    private void populateTownList () {
        towns.add("København");
        towns.add("Paris");
        towns.add("Odense");
        towns.add("Berlin");
        towns.add("Montevideo");
        towns.add("Luxembough");
        towns.add("Longyearbyen");
    }

    public List<String> getTagsList () {
        return tagsList;
    }
    private void populateTagsList () {
        tagsList.add("Tårn");
        tagsList.add("Historisk");
        tagsList.add("Natur");
        tagsList.add("Eventyr");
        tagsList.add("Museum");
        tagsList.add("Figur");
    }
    private void populateAttractions () {
        attractions.add(new Attraction("Rundetårn", "Højt rundt tårn i midten af København", "København", List.of("Tårn", "Historisk", "Museum")));
        attractions.add(new Attraction("Eiffel Tower", "Tower in paris","Paris", List.of("Tårn", "Historisk", "Natur")));
        attractions.add(new Attraction("Lille havfrue", "Kendt dansk figur baseret på H.C Andersens eventyr Den Lille Havfrue","København", List.of("Eventyr", "Figur")));
    }


    public List<Attraction> getAllAttractions () {
        return attractions;
    }


    public Attraction getAttractionByName(String name) {
        for (Attraction touristAttraction : attractions) {
            if (touristAttraction.getName().equals(name)) {
                return touristAttraction;
            }
        }
        return null;
    }

    public List<String> getTagsByName(String name) {
        List<String> tags = new ArrayList<>();
        for (Attraction touristAttraction : attractions) {
            if (touristAttraction.getName().equals(name)) {
                tags.addAll(touristAttraction.getTags());
            }
        }
        return tags;
    }


    public Attraction addAttraction(Attraction attraction) {
        attractions.add(attraction);
        return attraction;
    }


    /*public Tourist updateAttraction(Tourist attraction) {
        int index = attractions.indexOf(attraction);
        attractions.set(index, attraction);
        return attraction;
    }*/

    public Attraction updateAttraction(Attraction updatedAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            Attraction attraction = attractions.get(i);
            if (attraction.getName().equals(updatedAttraction.getName())) {
                attractions.set(i, updatedAttraction); // Replace old attraction with updated one
                return updatedAttraction;
            }
        }
        return null; // Return null if the attraction was not found
    }


    public void deleteAttraction(Attraction attraction) {
        attractions.remove(attraction);
    }

    public List<Attraction> addAttractionList(Attraction attraction) {
        attractions.add(attraction);
        return attractions;
    }

}

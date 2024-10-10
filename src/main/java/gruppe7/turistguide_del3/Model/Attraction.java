package gruppe7.turistguide_del3.Model;

import java.util.ArrayList;
import java.util.List;

public class Attraction {

    private String name;
    private String description;
    private String town;
    private List<String> tags = new ArrayList<>();

    public Attraction(String name, String description, String town, List<String> tags) {
        this.name = name;
        this.description = description;
        this.town = town;
        this.tags = tags;
    }

    public Attraction() {

    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

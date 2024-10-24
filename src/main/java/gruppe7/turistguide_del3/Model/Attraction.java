package gruppe7.turistguide_del3.Model;

import java.util.ArrayList;
import java.util.List;

public class Attraction {

    private int id;
    private String name;
    private String description;
    private String town;
    private int fee;
    private Integer cityID;
    private List<String> tags = new ArrayList<>();

    public Attraction() {
    }

    public int getId() {
        return id;
    }

    public int getFee() {
        return fee;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public Integer getCityID() {
        return cityID;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setFee(int fee) {
        this.fee = fee;
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

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

}
